package com.example.tamakanfp.Repository;

import com.example.tamakanfp.Model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication,Integer> {


    JobApplication findJobApplicationById(Integer id);

    List<JobApplication> findJobApplicationByJobSeekerId(Integer id);

}
