package com.enjoytrip.attraction.service;

import com.enjoytrip.attraction.repository.AttractionRepository;
import com.enjoytrip.member_like.repository.MemberLikeRepository;
import com.enjoytrip.model.Attraction;
import com.enjoytrip.model.MemberLike;
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
    public List<Attraction> getAttractionList(SearchCondition condition,int memberId) {
        return attractionRepository.findBySearchCondtion(condition,memberId);
    }

    @Override
    public Attraction getAttraction(int contentId) {
        Attraction attraction= attractionRepository.findByContentId(contentId);

        return attraction;
    }


}
