package com.enjoytrip.attraction.service;

import com.enjoytrip.attraction.repository.AttractionRepository;
import org.springframework.stereotype.Service;


@Service
public class AttractionServiceImpl implements AttractionService{
    private final AttractionRepository attractionRepository;

    public AttractionServiceImpl(AttractionRepository attractionRepository) {
        this.attractionRepository = attractionRepository;
    }
}
