package com.enjoytrip.attraction.service;

import com.enjoytrip.attraction.repository.AttractionRepository;
import com.enjoytrip.model.Attraction;
import com.enjoytrip.model.SearchCondition;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AttractionServiceImpl implements AttractionService{
    private final AttractionRepository attractionRepository;

    public AttractionServiceImpl(AttractionRepository attractionRepository) {
        this.attractionRepository = attractionRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Attraction> getAttractionList(SearchCondition condition) {
        return attractionRepository.findBySearchCondtion(condition);
    }
}
