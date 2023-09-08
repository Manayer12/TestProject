package com.example.tamakanfp.Controller;


import com.example.tamakanfp.ApiResponse.ApiResponse;
import com.example.tamakanfp.Model.JobApplication;
import com.example.tamakanfp.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/job-application")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;


    @GetMapping("get-job-applications")
    private ResponseEntity getJobApplications() {
        return ResponseEntity.status(200).body(jobApplicationService.getJobApplications());
    }

    @PostMapping("add-job-application/{jobId}/{seekerId}")
    private ResponseEntity addJobApplication(@RequestBody @Valid JobApplication jobApplication,@PathVariable Integer jobId,@PathVariable Integer seekerId) {
        jobApplicationService.addJobApplication(jobApplication,jobId,seekerId);
        return ResponseEntity.status(200).body(new ApiResponse("Job Application added"));
    }

    @PutMapping("update-job-application/{id}")
    private ResponseEntity updateJobApplication(@RequestBody @Valid JobApplication jobApplication,@PathVariable Integer id) {
        jobApplicationService.updateJobApplication(jobApplication,id);
        return ResponseEntity.status(200).body(new ApiResponse("Job Application updated"));

    }
    @DeleteMapping("delete-job-application/{id}")
    private ResponseEntity deleteJobApplication(@PathVariable Integer id) {
        jobApplicationService.deleteJobApplication(id);
        return ResponseEntity.status(200).body(new ApiResponse("Job Application deleted"));

    }
}
