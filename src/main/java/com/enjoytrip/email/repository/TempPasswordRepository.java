package com.enjoytrip.email.repository;

import com.enjoytrip.model.TempPassword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TempPasswordRepository extends JpaRepository<TempPassword, Integer> {
    Optional<TempPassword> findByTempKey(String tempKey);
}
