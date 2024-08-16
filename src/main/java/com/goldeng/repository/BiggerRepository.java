package com.goldeng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goldeng.model.packageSubClasses.Bigger;

@Repository
public interface BiggerRepository extends JpaRepository<Bigger, Long>{
    
}
