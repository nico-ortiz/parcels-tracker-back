package com.goldeng.dataProvider;

import java.time.LocalDate;
import java.util.List;

import com.goldeng.dto.CommissionDTO;
import com.goldeng.dto.CommissionDTORequest;
import com.goldeng.dto.PackageDTOWithoutCommission;
import com.goldeng.model.Commission;
import com.goldeng.model.Package;
import com.goldeng.model.enums.PackageType;
import com.goldeng.model.enums.Status;

public class CommissionData {
    
    public static CommissionDTORequest commissionDTORequestMock() {
        return new CommissionDTORequest(
            "Comision con 3 sobres",
            1L,
            1L
        );
    }

    public static CommissionDTO commissionDTOMock() {
        List<PackageDTOWithoutCommission> packages = List.of(
            new PackageDTOWithoutCommission(6L, "Sobre con dinero", PackageType.SOBRE),
            new PackageDTOWithoutCommission(7L, "Sobre con documentacion", PackageType.SOBRE),
            new PackageDTOWithoutCommission(8L, "Recibos", PackageType.SOBRE)
        );

        return new CommissionDTO(
            23L,
            "Comision con 3 sobres",
            Status.EN_PREPARACION,
            LocalDate.of(2024, 8, 23),
            6000f,
            1L,
            1L,
            packages
        );
    }

    public static Commission commissionMock() {
        List<Package> packages = List.of(
            new Package(6L, "Sobre con dinero", PackageType.SOBRE, new Commission()),
            new Package(7L, "Sobre con documentacion", PackageType.SOBRE, new Commission()),
            new Package(8L, "Recibos", PackageType.SOBRE, new Commission())
        );

        return new Commission(
            23L,
            "Comision con 3 sobres",
            Status.EN_PREPARACION,
            LocalDate.of(2024, 8, 23),
            6000f,
            CustomerData.customerMock(),
            ReceiverData.receiverMock(),
            packages
        );
    }

    public static Commission commissionUpdatedMock() {
        List<Package> packages = List.of(
            new Package(6L, "Sobre con dinero", PackageType.SOBRE, new Commission()),
            new Package(7L, "Sobre con documentacion", PackageType.SOBRE, new Commission())
        );

        return new Commission(
            23L,
            "Comision con 2 sobres",
            Status.EN_PREPARACION,
            LocalDate.of(2024, 8, 23),
            4500f,
            CustomerData.customerMock(),
            ReceiverData.receiverMock(),
            packages
        );
    }

    public static CommissionDTO commissionUpdateDTOMock() {
        List<PackageDTOWithoutCommission> packages = List.of(
            new PackageDTOWithoutCommission(6L, "Sobre con dinero", PackageType.SOBRE),
            new PackageDTOWithoutCommission(7L, "Sobre con documentacion", PackageType.SOBRE)
        );

        return new CommissionDTO(
            23L,
            "Comision con 2 sobres",
            Status.EN_PREPARACION,
            LocalDate.of(2024, 8, 23),
            4500f,
            1L,
            1L,
            packages
        );
    }

    public static List<CommissionDTO> commissionDTOListMock() {
        return List.of(
            commissionDTOMock()
        );
    }

    public static List<Commission> commissionListMock() {
        return List.of(
            commissionMock()
        );
    }
} 
