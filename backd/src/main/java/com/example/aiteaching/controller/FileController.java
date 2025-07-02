package com.example.aiteaching.controller;

import com.example.aiteaching.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @Value("${file.upload.path}")
    private String uploadPath;

    @Value("${file.upload.url-prefix}")
    private String urlPrefix;

    /**
     * 获取完整的URL
     */
    private String getFullUrl(HttpServletRequest request, String path) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();
        
        return String.format("%s://%s:%d%s%s", 
            scheme, serverName, serverPort, contextPath, path);
    }

    @PostMapping("/upload")
    public Result<Object> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // 检查文件是否为空
            if (file.isEmpty()) {
                return Result.error("上传的文件不能为空");
            }

            // 获取文件原始名称
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

            // 生成新的文件名
            String newFilename = UUID.randomUUID().toString() + extension;

            // 确保上传目录存在
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // 保存文件
            Path path = Paths.get(uploadPath, newFilename);
            Files.write(path, file.getBytes());

            // 返回文件访问URL
            String fileUrl = urlPrefix + newFilename;
            return Result.success(new FileUploadResponse(fileUrl, newFilename));

        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("文件上传失败：" + e.getMessage());
        }
    }

    /**
     * 下载文件
     * @param filename 文件名
     * @return 文件资源
     */
    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(uploadPath).resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                String contentType = Files.probeContentType(filePath);
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{filename}")
    public Result<Boolean> deleteFile(@PathVariable String filename) {
        try {
            Path path = Paths.get(uploadPath, filename);
            if (Files.exists(path)) {
                Files.delete(path);
                return Result.success(true);
            }
            return Result.error("文件不存在");
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("文件删除失败：" + e.getMessage());
        }
    }

    /**
     * 获取文件列表
     * @return 文件列表
     */
    @GetMapping("/list")
    public Result<List<Map<String, String>>> listFiles(HttpServletRequest request) {
        try {
            Path uploadPath = Paths.get(this.uploadPath);
            if (!Files.exists(uploadPath)) {
                return Result.success(Collections.emptyList());
            }

            List<Map<String, String>> files = Files.list(uploadPath)
                    .filter(Files::isRegularFile)
                    .map(path -> {
                        Map<String, String> fileInfo = new HashMap<>();
                        String filename = path.getFileName().toString();
                        try {
                            fileInfo.put("filename", filename);
                            fileInfo.put("url", getFullUrl(request, "/api/file/download/" + filename));
                            fileInfo.put("size", String.valueOf(Files.size(path)));
                            fileInfo.put("type", Files.probeContentType(path));
                            fileInfo.put("lastModified", String.valueOf(Files.getLastModifiedTime(path).toMillis()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return fileInfo;
                    })
                    .collect(Collectors.toList());

            return Result.success(files);
        } catch (IOException e) {
            return Result.fail("获取文件列表失败：" + e.getMessage());
        }
    }

    private static class FileUploadResponse {
        private String url;
        private String filename;

        public FileUploadResponse(String url, String filename) {
            this.url = url;
            this.filename = filename;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }
    }
} 