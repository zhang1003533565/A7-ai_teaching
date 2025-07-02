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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class VideoControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private VideoController videoController;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(videoController).build();
        // 设置视频上传路径为临时目录
        ReflectionTestUtils.setField(videoController, "videoDir", tempDir.toString());
    }

    @Test
    void uploadVideo_Success() throws Exception {
        // 准备测试数据
        MockMultipartFile file = new MockMultipartFile(
            "file",
            "test.mp4",
            "video/mp4",
            "test video content".getBytes()
        );

        // 执行测试并验证结果
        mockMvc.perform(multipart("/api/videos/upload")
                .file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.filename").exists())
                .andExpect(jsonPath("$.data.url").exists())
                .andExpect(jsonPath("$.data.downloadUrl").exists());
    }

    @Test
    void uploadVideo_EmptyFile() throws Exception {
        // 准备空文件
        MockMultipartFile file = new MockMultipartFile(
            "file",
            "empty.mp4",
            "video/mp4",
            new byte[0]
        );

        // 执行测试并验证结果
        mockMvc.perform(multipart("/api/videos/upload")
                .file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(500));
    }

    @Test
    void streamVideo_Success() throws Exception {
        // 先上传一个视频文件
        MockMultipartFile file = new MockMultipartFile(
            "file",
            "test.mp4",
            "video/mp4",
            "test video content".getBytes()
        );

        String response = mockMvc.perform(multipart("/api/videos/upload")
                .file(file))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // 从响应中获取文件名
        String filename = response.split("filename\":\"")[1].split("\"")[0];

        // 测试视频流
        mockMvc.perform(get("/api/videos/stream/" + filename))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "video/mp4"))
                .andExpect(content().bytes("test video content".getBytes()));
    }

    @Test
    void streamVideo_WithRange() throws Exception {
        // 先上传一个视频文件
        MockMultipartFile file = new MockMultipartFile(
            "file",
            "test.mp4",
            "video/mp4",
            "test video content".getBytes()
        );

        String response = mockMvc.perform(multipart("/api/videos/upload")
                .file(file))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String filename = response.split("filename\":\"")[1].split("\"")[0];

        // 测试带范围的视频流请求
        mockMvc.perform(get("/api/videos/stream/" + filename)
                .header("Range", "bytes=0-5"))
                .andExpect(status().isPartialContent())
                .andExpect(header().string("Content-Type", "video/mp4"))
                .andExpect(header().string("Content-Range", "bytes 0-5/18"))
                .andExpect(header().string("Content-Length", "6"));
    }

    @Test
    void streamVideo_NotFound() throws Exception {
        mockMvc.perform(get("/api/videos/stream/nonexistent.mp4"))
                .andExpect(status().isNotFound());
    }

    @Test
    void downloadVideo_Success() throws Exception {
        // 先上传一个视频文件
        MockMultipartFile file = new MockMultipartFile(
            "file",
            "test.mp4",
            "video/mp4",
            "test video content".getBytes()
        );

        String response = mockMvc.perform(multipart("/api/videos/upload")
                .file(file))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String filename = response.split("filename\":\"")[1].split("\"")[0];

        // 测试下载
        mockMvc.perform(get("/api/videos/download/" + filename))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/octet-stream"))
                .andExpect(header().string("Content-Disposition", "attachment; filename=\"" + filename + "\""))
                .andExpect(content().bytes("test video content".getBytes()));
    }

    @Test
    void downloadVideo_NotFound() throws Exception {
        mockMvc.perform(get("/api/videos/download/nonexistent.mp4"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteVideo_Success() throws Exception {
        // 先上传一个视频文件
        MockMultipartFile file = new MockMultipartFile(
            "file",
            "test.mp4",
            "video/mp4",
            "test video content".getBytes()
        );

        String response = mockMvc.perform(multipart("/api/videos/upload")
                .file(file))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String filename = response.split("filename\":\"")[1].split("\"")[0];

        // 测试删除
        mockMvc.perform(delete("/api/videos/" + filename))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").value(true));
    }

    @Test
    void deleteVideo_NotFound() throws Exception {
        mockMvc.perform(delete("/api/videos/nonexistent.mp4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(500))
                .andExpect(jsonPath("$.message").value("视频不存在"));
    }

    @Test
    void listVideos_Success() throws Exception {
        // 先上传两个视频文件
        MockMultipartFile file1 = new MockMultipartFile(
            "file",
            "test1.mp4",
            "video/mp4",
            "test video content 1".getBytes()
        );

        MockMultipartFile file2 = new MockMultipartFile(
            "file",
            "test2.mp4",
            "video/mp4",
            "test video content 2".getBytes()
        );

        mockMvc.perform(multipart("/api/videos/upload").file(file1));
        mockMvc.perform(multipart("/api/videos/upload").file(file2));

        // 测试获取列表
        mockMvc.perform(get("/api/videos/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(2));
    }

    @Test
    void listVideos_EmptyDirectory() throws Exception {
        mockMvc.perform(get("/api/videos/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(0));
    }
} 