package com.enjoytrip.notice.service;

import com.enjoytrip.exception.custom_exception.RoleException;
import com.enjoytrip.exception.custom_exception.WrongPageException;
import com.enjoytrip.exception.message.RoleExceptionMessage;
import com.enjoytrip.exception.message.WrongPageExceptionMessage;
import com.enjoytrip.members.mapper.MemberIdMapping;
import com.enjoytrip.members.repository.MemberRepository;
import com.enjoytrip.model.Member;
import com.enjoytrip.model.Notice;
import com.enjoytrip.notice.dto.NoticeDetailDto;
import com.enjoytrip.notice.dto.NoticeListDto;
import com.enjoytrip.notice.dto.NoticeRegisterDto;
import com.enjoytrip.notice.repository.NoticeRepository;
import com.enjoytrip.notice.util.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService{

    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;

    public NoticeServiceImpl(NoticeRepository noticeRepository, MemberRepository memberRepository) {
        this.noticeRepository = noticeRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public int register(NoticeRegisterDto registerDto) throws Exception {
        Member member = memberRepository.findById(registerDto.getMemberId()).get();
        if(!member.getRole().equals("admin")){
            throw new RoleException(RoleExceptionMessage.NO_AUTH);
        }

        Notice notice = Notice.builder()
                .memberId(registerDto.getMemberId())
                .title(registerDto.getTitle())
                .content(registerDto.getContent())
                .build();
        return noticeRepository.save(notice).getNoticeId();
    }

    @Override
    public List<NoticeListDto> noticeList(SearchCondition searchCondition, Pageable pageable) throws Exception {

        Page<Notice> noticePage= getNoticesBySearchConditionAndPageable(searchCondition, pageable);
        List<NoticeListDto> noticeListDtos = new ArrayList<>();
        for (Notice notice : noticePage) {
            noticeListDtos.add(new NoticeListDto(notice));
        }

        return noticeListDtos;
    }

    //Enum으로 변경 가능
    private Page<Notice> getNoticesBySearchConditionAndPageable(SearchCondition searchCondition, Pageable pageable) {
        if (searchCondition.getSearchType() == 0) {
            return noticeRepository.findAllByOrderByRegisterTimeDesc(pageable);
        }else if(searchCondition.getSearchType() == 1){
            return noticeRepository.findByTitleContainsOrderByRegisterTimeDesc(searchCondition.getSearchString(), pageable);
        }else if(searchCondition.getSearchType() == 2){
            return noticeRepository.findByContentContainsOrderByRegisterTimeDesc(searchCondition.getSearchString(), pageable);
        }else if(searchCondition.getSearchType() == 3){
            return noticeRepository.findByTitleContainsOrContentContainsOrderByRegisterTimeDesc(searchCondition.getSearchString(), searchCondition.getSearchString(), pageable);
        }else if(searchCondition.getSearchType() == 4){
            List<Integer> memberIdList = getMemberIdListByNicknameLike(searchCondition.getSearchString());
            return noticeRepository.findByMemberIdInOrderByRegisterTimeDesc(memberIdList, pageable);
        }
        return null;
    }

    private List<Integer> getMemberIdListByNicknameLike(String nickname){
        List<MemberIdMapping> memberIdMappings = memberRepository.findAllIdByNicknameContains(nickname);
        List<Integer> result = new ArrayList<>();
        for (MemberIdMapping memberIdMapping : memberIdMappings) {
            result.add(memberIdMapping.getMemberId());
        }
        return result;
    }


    @Override
    public NoticeDetailDto detail(int noticeId) throws Exception {
        Notice notice = noticeRepository.findByNoticeId(noticeId);
        if (notice == null) {
            throw new WrongPageException(WrongPageExceptionMessage.WRONG_PAGE);
        }
        return new NoticeDetailDto(notice);
    }

    @Override
    public void delete(int noticeId) throws Exception {
        noticeRepository.deleteByNoticeId(noticeId);
    }
}
