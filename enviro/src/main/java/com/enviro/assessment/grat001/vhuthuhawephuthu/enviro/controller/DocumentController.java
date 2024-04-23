package com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.controller;

import com.enviro.assessment.grat001.vhuthuhawephuthu.enviro.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("api/v1/documents")
@AllArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping(path = "upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    private ResponseEntity<String> upload(@RequestParam("file") MultipartFile file){
        return documentService.readDocument(file);
    }

}
