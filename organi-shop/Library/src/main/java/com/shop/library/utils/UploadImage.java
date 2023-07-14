package com.shop.library.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class UploadImage {
    private final String uploadDir = System.getProperty("user.dir")
            + "\\Library\\src\\main\\resources\\static\\productImages";
    private final String uploadCate = System.getProperty("user.dir")
            + "\\Library\\src\\main\\resources\\static\\cateImages";
    private final String uploadCustomer = System.getProperty("user.dir")
            + "\\Library\\src\\main\\resources\\static\\customerImages";
    public String upload(MultipartFile image) throws IOException {
        String fileName;
        fileName = image.getOriginalFilename();
        System.out.println(uploadDir);
        Path fileImagePath = Paths.get(uploadDir,fileName);
        Files.write(fileImagePath, image.getBytes());
        return fileName;
    }
    public String uploadCate(MultipartFile image) throws IOException {
        String fileName;
        fileName = image.getOriginalFilename();
        System.out.println(uploadCate);
        Path fileImagePath = Paths.get(uploadCate,fileName);
        Files.write(fileImagePath, image.getBytes());
        return fileName;
    }
    public String uploadCustomer(MultipartFile image) throws IOException {
        String fileName;
        fileName = image.getOriginalFilename();
        System.out.println(uploadCustomer);
        Path fileImagePath = Paths.get(uploadCustomer,fileName);
        Files.write(fileImagePath, image.getBytes());
        return fileName;
    }
}
