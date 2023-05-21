package com.enjoytrip.attraction.controller;

import com.enjoytrip.attraction.service.AttractionService;
import com.enjoytrip.model.Attraction;
import com.enjoytrip.model.SearchCondition;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
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
                                               @RequestParam(required = false,defaultValue = "") String word,
                                               @RequestParam(required = false,defaultValue = "false") boolean memberLike){
        SearchCondition condition=new SearchCondition(sidoCode,gugunCode,contentTypeCode,word,memberLike);
        List<Attraction> list=attractionService.getAttractionList(condition,1);
        return new ResponseEntity<List<Attraction>>(list, HttpStatus.OK);
    }
    @GetMapping("/attraction/{contentId}")
    public ResponseEntity<Attraction> getAttraction(@PathVariable String contentId){
        Attraction attraction=attractionService.getAttraction(Integer.parseInt(contentId));
        return new ResponseEntity<Attraction>(attraction, HttpStatus.OK);
    }
}
