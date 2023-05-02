package com.enjoytrip.sido.controller;

import com.enjoytrip.model.Gugun;
import com.enjoytrip.sido.dto.GugunDto;
import com.enjoytrip.sido.dto.SidoDto;
import com.enjoytrip.sido.repository.SidoRepositoryImpl;
import com.enjoytrip.sido.service.SidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SidoController {

    private final SidoService sidoService;


    public SidoController(SidoService sidoService) {
        this.sidoService = sidoService;
    }

    @GetMapping("/sido")
    public ResponseEntity<List<SidoDto>> getSido(){
        List<SidoDto> list=sidoService.getSidoList();
        return new ResponseEntity<List<SidoDto>>(list, HttpStatus.OK);
    }

    @GetMapping("/test/{sidoCode}")
    public ResponseEntity<?> getGugun(@PathVariable int sidoCode){
        List<GugunDto> list=sidoService.getGugunList(sidoCode);
        return new ResponseEntity<List<GugunDto>>(list,HttpStatus.OK);
    }
}
