package com.enjoytrip.attraction.controller;

import com.enjoytrip.attraction.service.AttractionService;
import com.enjoytrip.model.Attraction;
import com.enjoytrip.model.SearchCondition;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AttractionController {
    private final AttractionService attractionService;

    public AttractionController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }
    @GetMapping("/attraction")
    public ResponseEntity<?> getAttractionList(@RequestParam(required = false,defaultValue = "0") int sidoCode,
                                               @RequestParam(required = false,defaultValue = "0") int gugunCode,
                                               @RequestParam(required = false,defaultValue = "0") int contentTypeCode,
                                               @RequestParam(required = false,defaultValue = "0") String word){
        SearchCondition conditon=new SearchCondition(sidoCode,gugunCode,contentTypeCode,word);
        List<Attraction> list=attractionService.getAttractionList(conditon);
        return new ResponseEntity<List<Attraction>>(list, HttpStatus.OK);
    }
}
