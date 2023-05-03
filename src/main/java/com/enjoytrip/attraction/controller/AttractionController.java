package com.enjoytrip.attraction.controller;


import com.enjoytrip.attraction.service.AttractionService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttractionController {
    private final AttractionService attractionService;

    public AttractionController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }
}
