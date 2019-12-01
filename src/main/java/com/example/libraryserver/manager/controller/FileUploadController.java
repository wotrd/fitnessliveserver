package com.example.libraryserver.manager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@RequestMapping("upload")
@RestController
public class FileUploadController {

    public static final String FILE_PATH = "/Users/wangkaijin/IdeaProjects/project/fitnessliveserver/target/classes/static/images/";

    public static final String DOWNLOAD_PATH = "http://192.168.1.105/images/";

    @RequestMapping("uploadImg")
    public String uploadImg(MultipartFile file, HttpServletRequest request) throws IOException {
        String fileName = UUID.randomUUID().toString()+".jpg";
        FileOutputStream outputStream = new FileOutputStream(FILE_PATH+fileName);
        byte[] bytes = file.getBytes();
        outputStream.write(bytes);
        return DOWNLOAD_PATH+fileName;
    }

}
