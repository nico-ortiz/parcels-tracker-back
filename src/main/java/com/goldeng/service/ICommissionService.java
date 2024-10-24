package com.goldeng.service;

import java.time.LocalDate;
import java.util.List;

import com.goldeng.dto.CommissionDTO;
import com.goldeng.dto.CommissionDTORequest;
import com.goldeng.dto.PackageDTOWithoutCommission;

public interface ICommissionService {
    
    CommissionDTO createCommission(CommissionDTORequest commissionDTO);

    CommissionDTO getCommission(Long commissionId);

    CommissionDTO deleteCommission(Long commissionId);

    CommissionDTO updateCommission(Long commissionId, CommissionDTO commissionDTO);
  
    List<CommissionDTO> getCommissionsByDate(LocalDate date);
    
    List<PackageDTOWithoutCommission> getPackagesByCommission(Long commissionId);
    
    /*
     * -> Agregar paquete
     * -> Eliminar paquete
     * -> Modificar paquete
     * -> 
     */
}
