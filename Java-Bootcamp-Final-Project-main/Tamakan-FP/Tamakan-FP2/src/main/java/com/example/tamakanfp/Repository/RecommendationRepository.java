package com.example.tamakanfp.Repository;

import com.example.tamakanfp.Model.JobApplication;
import com.example.tamakanfp.Model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation,Integer> {



    Recommendation findRecommendationById(Integer id);
    Recommendation  findRecommendationByJobApplication(JobApplication jobApplication );




}
