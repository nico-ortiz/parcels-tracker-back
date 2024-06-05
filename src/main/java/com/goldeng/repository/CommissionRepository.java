package com.goldeng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goldeng.model.Commission;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Long> {
    
}
