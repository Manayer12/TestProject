package com.example.tamakanfp.Controller;


import com.example.tamakanfp.ApiResponse.ApiResponse;
import com.example.tamakanfp.Model.Job;
import com.example.tamakanfp.Service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/job")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping("get-jobs")
    private ResponseEntity getJobs() {
        return ResponseEntity.status(200).body(jobService.getJobs());
    }

    @PostMapping("add-job/{providerId}")
    private ResponseEntity addJob(@RequestBody @Valid Job job,@PathVariable Integer providerId) {
        jobService.addJob(job,providerId);
        return ResponseEntity.status(200).body(new ApiResponse("Job added"));
    }

    @PutMapping("update-job/{id}")
    private ResponseEntity updateJob(@RequestBody @Valid Job job,@PathVariable Integer id) {
        jobService.updateJob(job,id);
        return ResponseEntity.status(200).body(new ApiResponse("Job updated"));

    }
    @DeleteMapping("delete-job/{id}")
    private ResponseEntity deleteJob(@PathVariable Integer id) {
        jobService.deleteJob(id);
        return ResponseEntity.status(200).body(new ApiResponse("Job deleted"));

    }

    @GetMapping("/get-by-salary/{salary}")
    private ResponseEntity getJobBySalary(@PathVariable Double salary){
        return ResponseEntity.status(200).body(jobService.getJobBySalary(salary));
    }

    @GetMapping("/get-by-id/{job_id}")
    private ResponseEntity getJobBySalary(@PathVariable Integer job_id){
        return ResponseEntity.status(200).body(jobService.getJobById(job_id));
    }

    @GetMapping("/get-by-name/{jobName}")
    private ResponseEntity getJobBySalary(@PathVariable String jobName){
        return ResponseEntity.status(200).body(jobService.getJobByName(jobName));
    }

    @GetMapping("/get-by-city/{city}")
    private ResponseEntity getJobByCity(@PathVariable String city){
        return ResponseEntity.status(200).body(jobService.getJobByCity(city));
    }

    @GetMapping("/get-by-endDate/{endDate}")
    private ResponseEntity getJobByCity(@PathVariable LocalDate endDate){
        return ResponseEntity.status(200).body(jobService.getJobByEndDate(endDate));
    }




    @GetMapping("/count-all-jobs")
    private ResponseEntity countAllJobs(){
        return ResponseEntity.status(200).body(jobService.countjobs());
    }
    @GetMapping("/count-job-bysalary/{salary}")
    private ResponseEntity countjobbySalary(Double salary){
        return ResponseEntity.status(200).body(jobService.countjobsSalary(salary));
    }
    @GetMapping("/count-job-name/{name}")
    private ResponseEntity countjobbyName(String name){
        return ResponseEntity.status(200).body(jobService.countjobsName(name));
    }
    @GetMapping("/count-job-city/{city}")
    private ResponseEntity countjobbycity(String city){
        return ResponseEntity.status(200).body(jobService.countjobsCity(city));
    }

    @GetMapping("/count-job-enddate/{enddate}")
    private ResponseEntity countjobbyEndDate(LocalDate enddate){
        return ResponseEntity.status(200).body(jobService.countjobsEndDate(enddate));
    }
}