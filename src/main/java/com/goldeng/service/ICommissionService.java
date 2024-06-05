package com.goldeng.service;

import java.time.LocalDate;
import java.util.List;

import com.goldeng.dto.CommissionDTO;
import com.goldeng.dto.PackageDTO;

public interface ICommissionService {
    
    CommissionDTO createCommission(CommissionDTO commissionDTO);

    CommissionDTO getCommission(Long commissionId);

    CommissionDTO deleteCommission(Long commissionId);

    CommissionDTO updateCommission(Long commissionId, CommissionDTO commissionDTO);
  
    List<CommissionDTO> getCommissionsByDate(LocalDate date);
    
    List<PackageDTO> getPackagesByCommission(Long commissionId);
    
    /*
     * -> Agregar paquete
     * -> Eliminar paquete
     * -> Modificar paquete
     * -> 
     */
}
