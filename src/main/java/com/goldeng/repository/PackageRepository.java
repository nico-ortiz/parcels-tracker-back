package com.goldeng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goldeng.model.Package;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
    
}
