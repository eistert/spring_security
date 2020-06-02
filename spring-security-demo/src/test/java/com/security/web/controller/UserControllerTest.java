package com.security.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;
    // 伪造mvc环境
    private MockMvc mockMvc;

    // 每一个测试方法执行之前执行
    @Before
    public void setup() {
        // 构建mvc 测试环境
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * 上传成功
     *
     * @throws Exception
     */
    @Test
    public void whenUploadSuccess() throws Exception {
        String result = mockMvc.perform(fileUpload("/file")
                // file: 请求参数名字  test.txt: 文件名   contentType ： multipart/form-data  hello upload： 文件内容要求比特数组
                .file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "hello upload".getBytes("UTF-8"))))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    /**
     * 查询用户信息
     *
     * @throws Exception
     */
    @Test
    public void whenQuerySuccess() throws Exception {
        String result = mockMvc.perform(
                // 模拟发出get请求
                get("/user")
//                        .param("username", "jojo").param("age", "18").param("ageTo", "60").param("xxx", "yyy")
                        // .param("size", "15")
                        // .param("page", "3")
                        // .param("sort", "age,desc")
                        // utf-8编码格式
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                // 期望的状态
                .andExpect(status().isOk())
                // 期望的集合长度是3
                .andExpect(jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }


    @Test
    public void whenGetInfoSuccess() throws Exception {
        String result = mockMvc.perform(get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("tom"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenGetInfoFail() throws Exception {
        // 模拟发出
        mockMvc.perform(get("/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void whenCreateSuccess() throws Exception {

        Date date = new Date();
        System.out.println(date.getTime());
        String content = "{\"username\":\"tom\",\"password\":null,\"birthday\":" + date.getTime() + "}";
        String reuslt = mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(reuslt);
    }
//
//    /**
//     * 创建失败测试用例
//     *
//     * @throws Exception
//     */
//    @Test
//    public void whenCreateFail() throws Exception {
//
//        Date date = new Date();
//        System.out.println(date.getTime());
//        String content = "{\"username\":\"tom\",\"password\":null,\"birthday\":" + date.getTime() + "}";
//        String reuslt = mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(content))
////				.andExpect(status().isOk())
////				.andExpect(jsonPath("$.id").value("1"))
//                .andReturn().getResponse().getContentAsString();
//
//        System.out.println(reuslt);
//    }

    /**
     * 修改测试用例
     *
     * @throws Exception
     */
    @Test
    public void whenUpdateSuccess() throws Exception {

        Date date = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        System.out.println(date.getTime());
        String content = "{\"id\":\"1\", \"username\":\"tom\",\"password\":null,\"birthday\":" + date.getTime() + "}";
        String reuslt = mockMvc.perform(put("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(reuslt);
    }
//
//    @Test
//    public void whenDeleteSuccess() throws Exception {
//        mockMvc.perform(delete("/user/1")
//                .contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk());
//    }


}
