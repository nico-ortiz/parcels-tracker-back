package com.goldeng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goldeng.model.packageSubClasses.Envelope;

@Repository
public interface EnvelopeRepository extends JpaRepository<Envelope, Long> {
    
}
