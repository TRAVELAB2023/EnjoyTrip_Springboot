package com.enjoytrip.sido.service;

import com.enjoytrip.model.Gugun;
import com.enjoytrip.sido.dto.GugunDto;
import com.enjoytrip.sido.dto.SidoDto;

import java.util.List;

public interface SidoService {
    List<SidoDto> getSidoList();

    List<GugunDto> getGugunList(int sidoCode);
}
