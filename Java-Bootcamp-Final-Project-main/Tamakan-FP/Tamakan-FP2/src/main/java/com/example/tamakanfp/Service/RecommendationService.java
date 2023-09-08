package com.example.tamakanfp.Service;


import com.example.tamakanfp.ApiResponse.ApiException;
import com.example.tamakanfp.DTO.RecommendationDTO;
import com.example.tamakanfp.Model.*;
import com.example.tamakanfp.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final JobProviderRepository jobProviderRepository;
    private final JobApplicationRepository jobApplicationRepository;
    private final JobSeekerRepository jobSeekerRepository;
    private final JobSeekerProfileRepository jobSeekerProfileRepository;


    public List<Recommendation> getAllRecommend(Integer jobSeeker_id){
        JobSeeker jobSeeker=jobSeekerRepository.findJopSeekerById(jobSeeker_id);
        List<JobApplication> jobApplication=jobApplicationRepository.findJobApplicationByJobSeekerId(jobSeeker_id);

        if (jobSeeker==null && jobApplication== null){
            throw  new ApiException("id is null");
        }
        List<Recommendation> rec1=null;
        for (JobApplication j:jobApplication) {
            rec1.add(recommendationRepository.findRecommendationByJobApplication(j));

        }
//        List<Recommendation> jobApplicationsrec=recommendationRepositoory.findRecommendationByJobApplication(jobApplication);
        return rec1;
    }


    public void addRecommendation(Integer jobP_id,Integer jobApp_id, RecommendationDTO recommendationDTO){
//        User user=authRepository.findUserById(user_id);
//        if (user==null){
//            throw new ApiException("id is null");
//        }
        JobApplication jobsApp=jobApplicationRepository.findJobApplicationById(jobApp_id);
        if (jobsApp == null ) {
            throw new ApiException("id is null");
        }

        JobSeekerProfile jobSeekerpro=jobSeekerProfileRepository.findJobSeekerProfileById(jobsApp.getJobSeeker().getId());
        JobProvider jobP=jobProviderRepository.findJopProviderById(jobP_id);
        if (jobP==null && jobSeekerpro==null){
            throw new ApiException("Job Provider or job seeker  id is null");
        }


        LocalDate currentDate = LocalDate.now();
        if (!Objects.equals(jobsApp.getStatus(), "accepted") && !jobsApp.getJob().getEndDate().isBefore(currentDate)){
            throw new ApiException("job application status is not accepted or the job period is not done yet! ");
        }

        Recommendation recommendation=new Recommendation(null, recommendationDTO.getReccomendations(), recommendationDTO.getCompany(), null) {
        };
//        jobSeeker.getJobSeekerProfile().setRecommendation((Set<Recommendation>) recommendation);
        recommendation.setCompany(jobP.getName());
        recommendation.setJobApplication(jobsApp);
        recommendationRepository.save(recommendation);

    }

    public void deleteRecommendation(Integer id){

//        User user=authRepository.findUserById(user_id);
//        if (user==null){
//            throw new ApiException("id is null");


        Recommendation recommendation=recommendationRepository.findRecommendationById(id);
        if(recommendation==null) {

            throw new ApiException("id is null");
        }
        recommendationRepository.deleteById(id);}}





