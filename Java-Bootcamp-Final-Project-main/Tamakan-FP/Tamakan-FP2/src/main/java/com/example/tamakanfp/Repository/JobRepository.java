package com.example.tamakanfp.Repository;

import com.example.tamakanfp.Model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Integer> {

    Job findJobById(Integer id);

    List<Job> findJobBySalary(Double salary);
    List<Job> findJobByJobName(String jobName);

    List<Job> findJobByCity(String city);

    List<Job> findJobByEndDate(LocalDate endDate);

    long countJobByEndDate(LocalDate enddate);
    long countJobByCity(String city);
    long countJobByJobName(String name);
    long countJobBySalary(Double salary);
    long count();}
