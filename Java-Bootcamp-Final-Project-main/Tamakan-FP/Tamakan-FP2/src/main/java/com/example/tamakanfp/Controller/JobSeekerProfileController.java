package com.example.tamakanfp.Controller;


import com.example.tamakanfp.ApiResponse.ApiResponse;
import com.example.tamakanfp.DTO.JobSeekerProfileDTO;
import com.example.tamakanfp.Service.JobSeekerProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/job-seeker-profile")
@RequiredArgsConstructor
public class JobSeekerProfileController {

    private final JobSeekerProfileService jobSeekerProfileService;


    @GetMapping("get-job-seekers-profile")
    private ResponseEntity getJobSeekersProfile() {
        return ResponseEntity.status(200).body(jobSeekerProfileService.getJobSeekersProfile());
    }
    @PostMapping("add-job-seeker-profile/{seekerId}")
    private ResponseEntity addJobSeekerProfile(@RequestBody @Valid JobSeekerProfileDTO jobSeekerProfileDTO, @PathVariable Integer seekerId) {
        jobSeekerProfileService.addJobSeekerProfile(jobSeekerProfileDTO,seekerId);
        return ResponseEntity.status(200).body(new ApiResponse("Profile added"));
    }
    @PutMapping("update-job-seeker-profile/{id}")
    private ResponseEntity updateJobSeekerProfile(@RequestBody @Valid JobSeekerProfileDTO jobSeekerProfileDTO, @PathVariable Integer id) {
        jobSeekerProfileService.updateJobSeekerProfile(jobSeekerProfileDTO,id);
        return ResponseEntity.status(200).body(new ApiResponse("Profile updated"));
    }
    @DeleteMapping("delete-job-seeker-profile/{id}")
    private ResponseEntity deleteJobSeekerProfile(@PathVariable Integer id) {
        jobSeekerProfileService.deleteJobSeekerProfile(id);
        return ResponseEntity.status(200).body(new ApiResponse("Profile delete"));
    }


}
