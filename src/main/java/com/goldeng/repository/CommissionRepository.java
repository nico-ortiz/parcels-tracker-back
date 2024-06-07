package com.goldeng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goldeng.model.Commission;
import java.util.List;
import java.time.LocalDate;


@Repository
public interface CommissionRepository extends JpaRepository<Commission, Long> {
    
    List<Commission> findCommissionsByDate(LocalDate date);
}
