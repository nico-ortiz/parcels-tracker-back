package com.goldeng.service;

import com.goldeng.dto.CommissionDTO;

public interface ICommissionService {
    
    CommissionDTO createCommission(CommissionDTO commissionDTO);

    CommissionDTO getCommission(Long commissionId);

    CommissionDTO deleteCommission(Long commissionId);

    CommissionDTO updateCommission(Long commissionId, CommissionDTO commissionDTO);
    /*
     * -> Comisiones de una fecha
     * -> Agregar paquete
     * -> Eliminar paquete
     * -> Modificar paquete
     * -> Ver paquetes de una comision
     * -> 
     */
    
}
