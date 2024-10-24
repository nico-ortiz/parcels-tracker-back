package com.goldeng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goldeng.model.Receiver;

@Repository
public interface ReceiverRepository extends JpaRepository<Receiver, Long> {    
}