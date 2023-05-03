package com.enjoytrip.attraction.repository;

import com.enjoytrip.model.Attraction;
import com.enjoytrip.model.SearchCondition;

import java.util.List;

public interface AttractionRepositoryCustom {
    List<Attraction> findBySearchCondtion(SearchCondition condition,int memberId);



}
