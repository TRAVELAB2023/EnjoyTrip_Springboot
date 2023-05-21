package com.enjoytrip.attraction.service;

import com.enjoytrip.model.Attraction;
import com.enjoytrip.model.SearchCondition;

import java.util.List;

public interface AttractionService {

    List<Attraction> getAttractionList(SearchCondition condition,int memberId);

    Attraction getAttraction(String contentId);

}
