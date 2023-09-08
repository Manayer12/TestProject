package com.example.tamakanfp.Service;

import com.example.tamakanfp.ApiResponse.ApiException;
import com.example.tamakanfp.Model.Certificates;
import com.example.tamakanfp.Model.FileUtil;
import com.example.tamakanfp.Model.JobApplication;
import com.example.tamakanfp.Model.JobSeeker;
import com.example.tamakanfp.Repository.CertificatesRepository;
import com.example.tamakanfp.Repository.JobApplicationRepository;
import com.example.tamakanfp.Repository.JobSeekerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CertificatesService {
    @Autowired
    private CertificatesRepository certificatesRepository;
    private final JobApplicationRepository jobApplicationRepository;
    private final JobSeekerRepository jobSeekerRepository;

    //add authentication to job provider to add certificates and recommendation

    public String uploadFile(Integer jobapplication_id,MultipartFile file) throws IOException {
        JobApplication jobsApp=jobApplicationRepository.findJobApplicationById(jobapplication_id);
        JobSeeker jobSeeker=jobSeekerRepository.findJopSeekerById(jobsApp.getJobSeeker().getId());


        if (jobsApp == null && jobSeeker==null ) {
            throw new ApiException("id is null");
        }


        LocalDate currentDate = LocalDate.now();
        if (!Objects.equals(jobsApp.getStatus(), "accepted") && !jobsApp.getJob().getEndDate().isBefore(currentDate)){
            throw new ApiException("job application status is not accepted or the job period is not done yet! ");
        }

        Certificates imageData = certificatesRepository.save(Certificates.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .fileData(FileUtil.compressFile(file.getBytes())).build());


        if (imageData == null) {
            return null;
        }

        imageData.setJobApplication(jobsApp);
        imageData.getJobApplication().setJobSeeker(jobSeeker);
        certificatesRepository.save(imageData);

        return "file uploaded successfully : " + file.getOriginalFilename();



    }

    public byte[] downloadFile(String fileName){
//        JobApplication jobApplication=jobApplicationRepository.findJobApplicationById(Jid);
//        JobSeeker jobSeeker=jobSeekerRepository.findJopSeekerById(jobApplication.getId());
//        if (jobApplication==null && jobSeeker==null){
//            throw new ApiException("is is null");
//        }


        Optional<Certificates> dbImageData = certificatesRepository.findByName(fileName);

//        jobApplication.setCertificates();

        byte[] files= FileUtil.decompressFile(dbImageData.get().getFileData());
        return files;
    }



}




