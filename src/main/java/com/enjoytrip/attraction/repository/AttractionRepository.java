package com.enjoytrip.attraction.repository;

import com.enjoytrip.model.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionRepository extends JpaRepository<Attraction,Integer>,AttractionRepositoryCustom {
}
