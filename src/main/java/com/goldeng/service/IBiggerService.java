package com.goldeng.service;

import com.goldeng.dto.BiggerDTO;

public interface IBiggerService {
    
    BiggerDTO createBigger(BiggerDTO biggerDTO);

    BiggerDTO getBigger(Long biggerId);
}
