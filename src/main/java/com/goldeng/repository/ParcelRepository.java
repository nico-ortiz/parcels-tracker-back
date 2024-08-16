package com.goldeng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goldeng.model.packageSubClasses.Parcel;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Long> {
    
}
