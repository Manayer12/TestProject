package com.example.tamakanfp.Controller;

import com.example.tamakanfp.Service.CertificatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/file")
public class CertificatesController {


    @Autowired
    private CertificatesService certificatesService ;

    @PostMapping("/addfile/{job_application}")
    public ResponseEntity<?> uploadImage(@PathVariable Integer job_application,@RequestParam("file")MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(certificatesService.uploadFile(job_application,file));
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
      return ResponseEntity.status(HttpStatus.OK)
              .contentType(MediaType.valueOf(MediaType.APPLICATION_PDF_VALUE))
                .body(certificatesService.downloadFile(fileName));

    }


}





