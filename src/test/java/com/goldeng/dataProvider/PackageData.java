package com.goldeng.dataProvider;

import java.time.LocalDate;
import java.util.ArrayList;

import com.goldeng.dto.CommissionDTO;
import com.goldeng.dto.PackageDTO;
import com.goldeng.dto.PackageDTOWithoutCommission;
import com.goldeng.model.Commission;
import com.goldeng.model.Customer;
import com.goldeng.model.Receiver;
import com.goldeng.model.Package;
import com.goldeng.model.enums.PackageType;
import com.goldeng.model.enums.Status;

public class PackageData {
    
    public static PackageDTO packageDTOMock() {
        return new PackageDTO(
            1L,
            "Caja con repuestos de auto",
            PackageType.CAJA_MEDIANA,
            1L
        );
    }

    public static Package packageMock() {
        return new Package(
            1L,
            "Caja con repuestos de auto",
            1000f,
            PackageType.CAJA_MEDIANA,
            getCommissionMock()
        );
    }

    public static CommissionDTO getCommissionDTOMock() {
        return new CommissionDTO(
            1L,
            "Envio a correo",
            Status.EN_PROGRESO,
            LocalDate.of(2024, 12, 2),
            5000.0F,
            1L,
            1L,
            new ArrayList<PackageDTOWithoutCommission>()
        );
    }

    public static Commission getCommissionMock() {
        return new Commission(
            1L,
            "Envio a correo",
            Status.EN_PROGRESO,
            LocalDate.of(2024, 12, 2),
            5000.0F,
            new Customer(),
            new Receiver(),
            new ArrayList<>()
        );
    }

    public static PackageDTO newpackageDTOMock() {
        return new PackageDTO(
            1L,
            "Se modifico la caja a una caja mas grande con respuestos",
            PackageType.CAJA_GRANDE,
            1L
        );
    }

    public static Package newpackageMock() {
        return new Package(
            1L,
            "Se modifico la caja a una caja mas grande con respuestos",
            1000f,
            PackageType.CAJA_GRANDE,
            getCommissionMock()
        );
    }
}
