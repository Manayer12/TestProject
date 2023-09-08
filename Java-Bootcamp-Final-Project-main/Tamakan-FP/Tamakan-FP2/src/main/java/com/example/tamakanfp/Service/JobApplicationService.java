package com.example.tamakanfp.Service;

import com.example.tamakanfp.ApiResponse.ApiException;
import com.example.tamakanfp.Model.Job;
import com.example.tamakanfp.Model.JobApplication;
import com.example.tamakanfp.Model.JobProvider;
import com.example.tamakanfp.Model.JobSeeker;
import com.example.tamakanfp.Repository.JobApplicationRepository;
import com.example.tamakanfp.Repository.JobProviderRepository;
import com.example.tamakanfp.Repository.JobRepository;
import com.example.tamakanfp.Repository.JobSeekerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final JobRepository jobRepository;
    private final JobSeekerRepository jobSeekerRepository;

    public List<JobApplication> getJobApplications(){
        return jobApplicationRepository.findAll();
    }

    public void  addJobApplication(JobApplication jobApplication, Integer jobId,Integer seekerId){

        Job job=jobRepository.findJobById(jobId);
        JobSeeker seeker=jobSeekerRepository.findJopSeekerById(seekerId);


        if (job==null ||seeker==null)
            throw new ApiException("Job id or seeker id not found");

        jobApplication.setJobSeeker(seeker);
        jobApplication.setJob(job);
        jobApplicationRepository.save(jobApplication);
    }

    public void  updateJobApplication(JobApplication jobApplication, Integer id){

        JobApplication oldJobApplication=jobApplicationRepository.findJobApplicationById(id);
        if (oldJobApplication==null)
            throw new ApiException("Job application id not found");

        oldJobApplication.setStatus(jobApplication.getStatus());
        jobApplicationRepository.save(oldJobApplication);
    }

    public void  deleteJobApplication(Integer id){

        JobApplication jobApplication=jobApplicationRepository.findJobApplicationById(id);
        if (jobApplication==null)
          throw new ApiException("Job application id not found");

        jobApplicationRepository.delete(jobApplication);
    }


}
