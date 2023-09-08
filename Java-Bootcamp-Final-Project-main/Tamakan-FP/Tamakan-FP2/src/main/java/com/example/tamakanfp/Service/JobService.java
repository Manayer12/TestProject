package com.example.tamakanfp.Service;

import com.example.tamakanfp.ApiResponse.ApiException;
import com.example.tamakanfp.Model.Job;
import com.example.tamakanfp.Model.JobProvider;
import com.example.tamakanfp.Repository.JobProviderRepository;
import com.example.tamakanfp.Repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final JobProviderRepository jobProviderRepository;

    public List<Job> getJobs(){
        return jobRepository.findAll();
    }
    public void addJob(Job job,Integer providerId){
       JobProvider provider=jobProviderRepository.findJopProviderById(providerId);
       if(provider==null)
           throw new ApiException("Provider id not found");

        job.setJobProvider(provider);
        jobRepository.save(job);
    }

    public void updateJob(Job job,Integer id){
        Job oldJob=jobRepository.findJobById(id);
        if (oldJob==null)
            throw new ApiException("Job id not found");

        oldJob.setJobName(job.getJobName());
        oldJob.setJobDescription(job.getJobDescription());
        oldJob.setCity(job.getCity());
        oldJob.setAddress(oldJob.getAddress());
        oldJob.setStartDate(job.getStartDate());
        oldJob.setEndDate(job.getEndDate());
        oldJob.setStatus(job.getStatus());
        oldJob.setSector(job.getSector());
        oldJob.setSalary(job.getSalary());
        oldJob.setWorkingDays(job.getWorkingDays());
        oldJob.setWorkingHours(job.getWorkingHours());

        jobRepository.save(oldJob);
    }

    public void deleteJob(Integer id) {
        Job job = jobRepository.findJobById(id);
        if (job==null)
         throw new ApiException("Job id not found");

        jobRepository.delete(job);
    }

    public  List<Job>getJobBySalary(Double salary){

        return jobRepository.findJobBySalary(salary);

    }

    public Job getJobById(Integer job_id){

        return jobRepository.findJobById(job_id);

    }
    public List<Job> getJobByName(String jobName){

        return jobRepository.findJobByJobName(jobName);

    }


    public List<Job> getJobByCity(String city){
        return jobRepository.findJobByCity(city);

    }

    public List<Job> getJobByEndDate(LocalDate endDate ){
        return jobRepository.findJobByEndDate(endDate);

    }



    public Long countjobs(){
        return jobRepository.count();}
    public Long countjobsSalary(Double salary){
        return jobRepository.countJobBySalary(salary);}

    public Long countjobsName(String jobname){
        return jobRepository.countJobByJobName(jobname);}

    public Long countjobsCity(String city){
        return jobRepository.countJobByCity(city);}

    public Long countjobsEndDate(LocalDate enddate){
        return jobRepository.countJobByEndDate(enddate);}


}
