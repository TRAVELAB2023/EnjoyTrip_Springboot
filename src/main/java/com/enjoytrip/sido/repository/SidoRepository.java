package com.enjoytrip.sido.repository;

import com.enjoytrip.model.Sido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SidoRepository extends JpaRepository<Sido,Integer>, SidoRepositoryCustom{

}
