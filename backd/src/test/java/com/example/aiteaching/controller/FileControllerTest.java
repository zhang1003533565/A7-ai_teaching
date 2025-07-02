package com.example.aiteaching.controller;

import com.example.aiteaching.common.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class FileControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private FileController fileController;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(fileController).build();
        // 设置上传路径为临时目录
        ReflectionTestUtils.setField(fileController, "uploadPath", tempDir.toString());
        ReflectionTestUtils.setField(fileController, "urlPrefix", "/uploads/");
    }

    @Test
    void uploadFile_Success() throws Exception {
        // 准备测试数据
        MockMultipartFile file = new MockMultipartFile(
            "file",
            "test.txt",
            MediaType.TEXT_PLAIN_VALUE,
            "Hello, World!".getBytes()
        );

        // 执行测试并验证结果
        mockMvc.perform(multipart("/api/file/upload")
                .file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.url").exists())
                .andExpect(jsonPath("$.data.filename").exists());
    }

    @Test
    void uploadFile_EmptyFile() throws Exception {
        // 准备空文件
        MockMultipartFile file = new MockMultipartFile(
            "file",
            "empty.txt",
            MediaType.TEXT_PLAIN_VALUE,
            new byte[0]
        );

        // 执行测试并验证结果
        mockMvc.perform(multipart("/api/file/upload")
                .file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(500))
                .andExpect(jsonPath("$.message").value("上传的文件不能为空"));
    }

    @Test
    void downloadFile_Success() throws Exception {
        // 先上传一个文件
        MockMultipartFile file = new MockMultipartFile(
            "file",
            "test.txt",
            MediaType.TEXT_PLAIN_VALUE,
            "Hello, World!".getBytes()
        );

        String response = mockMvc.perform(multipart("/api/file/upload")
                .file(file))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // 从响应中获取文件名
        String filename = response.split("filename\":\"")[1].split("\"")[0];

        // 下载文件
        mockMvc.perform(get("/api/file/download/" + filename))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "attachment; filename=\"" + filename + "\""))
                .andExpect(content().bytes("Hello, World!".getBytes()));
    }

    @Test
    void downloadFile_NotFound() throws Exception {
        mockMvc.perform(get("/api/file/download/nonexistent.txt"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteFile_Success() throws Exception {
        // 先上传一个文件
        MockMultipartFile file = new MockMultipartFile(
            "file",
            "test.txt",
            MediaType.TEXT_PLAIN_VALUE,
            "Hello, World!".getBytes()
        );

        String response = mockMvc.perform(multipart("/api/file/upload")
                .file(file))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // 从响应中获取文件名
        String filename = response.split("filename\":\"")[1].split("\"")[0];

        // 删除文件
        mockMvc.perform(delete("/api/file/" + filename))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").value(true));
    }

    @Test
    void deleteFile_NotFound() throws Exception {
        mockMvc.perform(delete("/api/file/nonexistent.txt"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(500))
                .andExpect(jsonPath("$.message").value("文件不存在"));
    }

    @Test
    void listFiles_Success() throws Exception {
        // 先上传两个文件
        MockMultipartFile file1 = new MockMultipartFile(
            "file",
            "test1.txt",
            MediaType.TEXT_PLAIN_VALUE,
            "Hello, World 1!".getBytes()
        );

        MockMultipartFile file2 = new MockMultipartFile(
            "file",
            "test2.txt",
            MediaType.TEXT_PLAIN_VALUE,
            "Hello, World 2!".getBytes()
        );

        mockMvc.perform(multipart("/api/file/upload").file(file1));
        mockMvc.perform(multipart("/api/file/upload").file(file2));

        // 获取文件列表
        mockMvc.perform(get("/api/file/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(2));
    }

    @Test
    void listFiles_EmptyDirectory() throws Exception {
        // 测试空目录
        mockMvc.perform(get("/api/file/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(0));
    }
} 