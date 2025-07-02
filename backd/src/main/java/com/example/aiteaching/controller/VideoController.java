package com.example.aiteaching.controller;

import com.example.aiteaching.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Value("${file.video-dir}")
    private String videoDir;

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

    /**
     * 上传视频
     * @param file 视频文件
     * @return 视频信息
     */
    @PostMapping("/upload")
    public Result<Map<String, String>> uploadVideo(
            @RequestParam("file") MultipartFile file,
            HttpServletRequest request) {
        try {
            // 创建上传目录
            Path uploadPath = Paths.get(videoDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 生成文件名
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String filename = UUID.randomUUID().toString() + extension;

            // 保存文件
            Path filePath = uploadPath.resolve(filename);
            Files.copy(file.getInputStream(), filePath);

            // 生成完整URL
            String streamUrl = getFullUrl(request, "/api/videos/stream/" + filename);
            String downloadUrl = getFullUrl(request, "/api/videos/download/" + filename);

            // 返回视频信息
            Map<String, String> videoInfo = new HashMap<>();
            videoInfo.put("filename", filename);
            videoInfo.put("originalFilename", originalFilename);
            videoInfo.put("url", streamUrl);
            videoInfo.put("downloadUrl", downloadUrl);
            videoInfo.put("size", String.valueOf(file.getSize()));
            videoInfo.put("type", file.getContentType());

            return Result.success(videoInfo);
        } catch (IOException e) {
            return Result.fail("视频上传失败：" + e.getMessage());
        }
    }

    /**
     * 流式播放视频
     * @param filename 文件名
     * @param range 范围请求头
     * @return 视频流
     */
    @GetMapping("/stream/{filename}")
    public ResponseEntity<Resource> streamVideo(@PathVariable String filename, @RequestHeader(value = "Range", required = false) String range) {
        try {
            Path filePath = Paths.get(videoDir).resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                long contentLength = resource.contentLength();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType("video/mp4"));

                // 处理范围请求
                if (range != null) {
                    String[] ranges = range.replace("bytes=", "").split("-");
                    long start = Long.parseLong(ranges[0]);
                    long end = ranges.length > 1 ? Long.parseLong(ranges[1]) : contentLength - 1;
                    
                    headers.add("Content-Range", String.format("bytes %d-%d/%d", start, end, contentLength));
                    headers.add("Accept-Ranges", "bytes");
                    headers.setContentLength(end - start + 1);
                    
                    return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                            .headers(headers)
                            .body(resource);
                } else {
                    headers.setContentLength(contentLength);
                    return ResponseEntity.ok()
                            .headers(headers)
                            .body(resource);
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * 下载视频
     * @param filename 文件名
     * @return 视频文件
     */
    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadVideo(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(videoDir).resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType("application/octet-stream"))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * 删除视频
     * @param filename 文件名
     * @return 操作结果
     */
    @DeleteMapping("/{filename}")
    public Result<Boolean> deleteVideo(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(videoDir).resolve(filename);
            boolean deleted = Files.deleteIfExists(filePath);
            return deleted ? Result.success(true) : Result.fail("视频不存在");
        } catch (IOException e) {
            return Result.fail("视频删除失败：" + e.getMessage());
        }
    }

    /**
     * 获取视频列表
     * @return 视频列表
     */
    @GetMapping("/list")
    public Result<List<Map<String, String>>> listVideos(HttpServletRequest request) {
        try {
            Path uploadPath = Paths.get(videoDir);
            if (!Files.exists(uploadPath)) {
                return Result.success(Collections.emptyList());
            }

            List<Map<String, String>> videos = Files.list(uploadPath)
                    .filter(Files::isRegularFile)
                    .map(path -> {
                        Map<String, String> videoInfo = new HashMap<>();
                        String filename = path.getFileName().toString();
                        try {
                            videoInfo.put("filename", filename);
                            videoInfo.put("url", getFullUrl(request, "/api/videos/stream/" + filename));
                            videoInfo.put("downloadUrl", getFullUrl(request, "/api/videos/download/" + filename));
                            videoInfo.put("size", String.valueOf(Files.size(path)));
                            videoInfo.put("type", Files.probeContentType(path));
                            videoInfo.put("lastModified", String.valueOf(Files.getLastModifiedTime(path).toMillis()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return videoInfo;
                    })
                    .collect(Collectors.toList());

            return Result.success(videos);
        } catch (IOException e) {
            return Result.fail("获取视频列表失败：" + e.getMessage());
        }
    }
} 