package com.example.tamakanfp.Repository;

import com.example.tamakanfp.Model.JobSeekerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSeekerProfileRepository extends JpaRepository<JobSeekerProfile,Integer> {

    JobSeekerProfile findJobSeekerProfileById(Integer id);
}
