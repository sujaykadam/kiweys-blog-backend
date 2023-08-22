package com.kiweysblog.backend.services.impl;

import com.kiweysblog.backend.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException{
        String name = file.getOriginalFilename();
        String randId = UUID.randomUUID().toString();
        String fileName = randId.concat(name.substring(name.lastIndexOf(".")));

        String filePath = path + File.separator + fileName;

        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName;
    };
    @Override
    public InputStream getImage(String path, String pathName) throws FileNotFoundException{
        String fullPath = path + File.separator + pathName;
        InputStream stream = new FileInputStream(fullPath);
        return stream;

    };
}
