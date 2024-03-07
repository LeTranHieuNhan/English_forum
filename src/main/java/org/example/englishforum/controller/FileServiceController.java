package org.example.englishforum.controller;

import lombok.RequiredArgsConstructor;
import org.example.englishforum.service.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
public class FileServiceController {

    private final FileService fileService;
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("image") MultipartFile multipartFile
                             ) throws IOException {
        String imageURL = fileService.uploadFile(multipartFile);
        return imageURL;
    }
}
