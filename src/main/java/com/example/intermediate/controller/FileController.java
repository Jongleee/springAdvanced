package com.example.intermediate.controller;

import com.example.intermediate.FileTypeErrorException;
import com.example.intermediate.controller.response.ResponseDto;
import com.example.intermediate.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class FileController {

    private final FileService fileService;

    @PostMapping("/api/upload")
    public ResponseDto<?> upload(@RequestParam("images") MultipartFile multipartFile) throws IOException {
        String imgUrl= fileService.upload(multipartFile, "static");
        return ResponseDto.success(imgUrl);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseDto<?> handlingException(){
        return ResponseDto.fail("CONVERT_FAIL","fail convert multipart to file");
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseDto<?> handlingNotFoundException(){
        return ResponseDto.fail("EMPTY","multipart file is empty");
    }
    @ExceptionHandler(FileTypeErrorException.class)
    public ResponseDto<?> handlingFileTypeErrorException(){
        return ResponseDto.fail("FILE_TYPE_ERROR","file type is not image");
    }
}