package com.goldeng.service;

import com.goldeng.dto.BiggerDTO;

public interface IBiggerService {
    
    BiggerDTO createBigger(BiggerDTO biggerDTO);

    BiggerDTO getBigger(Long biggerId);

    BiggerDTO deleteBigger(Long biggerId);

    BiggerDTO updateBiggerById(Long biggerId, String description, double height, double weight, double width);
}
