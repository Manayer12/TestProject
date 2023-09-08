package com.example.tamakanfp.Service;


import com.example.tamakanfp.ApiResponse.ApiException;
import com.example.tamakanfp.DTO.JobSeekerProfileDTO;
import com.example.tamakanfp.Model.JobSeeker;
import com.example.tamakanfp.Model.JobSeekerProfile;
import com.example.tamakanfp.Repository.JobSeekerProfileRepository;
import com.example.tamakanfp.Repository.JobSeekerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobSeekerProfileService {

    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final JobSeekerRepository jobSeekerRepository;

    public List<JobSeekerProfile> getJobSeekersProfile(){
        return jobSeekerProfileRepository.findAll();
    }
    public void addJobSeekerProfile(JobSeekerProfileDTO jobSeekerProfileDTO, Integer seekerId){
        JobSeeker seeker=jobSeekerRepository.findJopSeekerById(seekerId);

        if (seeker == null) {
            throw new ApiException("Seeker id is null");
        }

        JobSeekerProfile profile=new JobSeekerProfile(null, jobSeekerProfileDTO.getOut0fGPA(), jobSeekerProfileDTO.getCourses(),jobSeekerProfileDTO.getSkills(), jobSeekerProfileDTO.getGPA(),jobSeekerProfileDTO.getMajor(), jobSeekerProfileDTO.getUniversity(),jobSeekerProfileDTO.getAcademicQualification(), jobSeekerProfileDTO.getExpectedGraduationYear(), jobSeekerProfileDTO.getResume(), seeker);
        jobSeekerProfileRepository.save(profile);
    }

    public void updateJobSeekerProfile(JobSeekerProfileDTO profileDTO, Integer id) {

        JobSeekerProfile profile=jobSeekerProfileRepository.findJobSeekerProfileById(id);
        if (profile == null) {
            throw new ApiException("Profile id is null");
        }

        profile.setOut0fGPA(profileDTO.getOut0fGPA());
        profile.setCourses(profileDTO.getCourses());
        profile.setSkills(profileDTO.getSkills());
        profile.setGPA(profileDTO.getGPA());
        profile.setMajor(profileDTO.getMajor());
        profile.setUniversity(profileDTO.getUniversity());
        profile.setAcademicQualification(profileDTO.getAcademicQualification());
        profile.setExpectedGraduationYear(profileDTO.getExpectedGraduationYear());
        profile.setResume(profile.getResume());

        jobSeekerProfileRepository.save(profile);
    }

    public void deleteJobSeekerProfile(Integer id) {

        JobSeekerProfile profile = jobSeekerProfileRepository.findJobSeekerProfileById(id);
        if (profile == null) {
            throw new ApiException("Profile id is null");
        }
        JobSeeker seeker=jobSeekerRepository.findJopSeekerById(profile.getJobSeeker().getId());
        seeker.setJobSeekerProfile(null);
        jobSeekerProfileRepository.delete(profile);

    }

}
