package com.enjoytrip.sido.service;

import com.enjoytrip.model.Gugun;
import com.enjoytrip.model.Sido;
import com.enjoytrip.sido.dto.GugunDto;
import com.enjoytrip.sido.dto.SidoDto;
import com.enjoytrip.sido.repository.SidoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SidoServiceImpl implements SidoService{
    private final SidoRepository sidoRepository;
    public SidoServiceImpl(SidoRepository sidoRepository) {
        this.sidoRepository = sidoRepository;
    }

    @Override
    public List<SidoDto> getSidoList() {

        List<Sido> list= sidoRepository.findAll();
        List<SidoDto> resultList=new ArrayList<>();
        for(Sido sido : list){
            resultList.add(new SidoDto(sido));
        }
        return resultList;
    }

    @Override
    public List<GugunDto> getGugunList(int sidoCode) {
        List<Gugun> list=sidoRepository.getGugunList(sidoCode);
        List<GugunDto> resultList=new ArrayList<>();
        for(Gugun gugun : list){
            resultList.add(new GugunDto(gugun));
        }
        return resultList;
    }
}
