package com.enjoytrip.sido.repository;

import com.enjoytrip.model.Gugun;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SidoRepositoryCustom {
    List<Gugun> getGugunList(int sidoCode);
}
