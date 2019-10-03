/**
 *
 */
package com.imooc.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.imooc.dto.FileInfo;

/**
 * @author ai
 *
 */
@RestController
@RequestMapping("/file")
public class FileController {

    // 文件路径
    private String folder = "/Users/eistert/Desktop/spring_security_JoJo/imooc-security/imooc-security-demo/src/main/java/com/imooc/web/controller";

    /**
     * 上传文件
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping
    public FileInfo upload(MultipartFile file) throws Exception {

        System.out.println(file.getName());
        // 文件名
        System.out.println(file.getOriginalFilename());
        // 文件名
        System.out.println(file.getSize());
// 文件路径 新的文件名
        File localFile = new File(folder, new Date().getTime() + ".txt");
// 传进来的文件写入本地文件
        file.transferTo(localFile);

        return new FileInfo(localFile.getAbsolutePath());
    }

    /**
     * 下载文件
     * @param id
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // JDK7的写法 流自动关闭
        try (InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
             OutputStream outputStream = response.getOutputStream();) {

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=test.txt");

            // 文件内容写入到响应中
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }

    }

}
