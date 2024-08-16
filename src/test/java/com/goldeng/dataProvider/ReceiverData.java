package com.goldeng.dataProvider;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.goldeng.dto.CommissionDTOWithoutReceiver;
import com.goldeng.dto.PackageDTOWithoutCommission;
import com.goldeng.dto.ReceiverDTO;
import com.goldeng.dto.ReceiverDTOWithCommissions;
import com.goldeng.model.Receiver;
import com.goldeng.model.enums.PackageType;
import com.goldeng.model.enums.Status;

public class ReceiverData {
    
    public static ReceiverDTO receiverDTOMock() {
        return new ReceiverDTO(
            1L,
            "Sergio",
            "Ramos",
            "Italia 34",
            "45612033",
            LocalDate.of(2024, 12, 22),
            LocalTime.of(16, 30),
            LocalTime.of(20, 30)
        );
    }

    public static ReceiverDTO receiverDTOUpdatedMock() {
        return new ReceiverDTO(
            1L,
            "Sergio",
            "Ramos",
            "Francia 104",
            "45612033",
            LocalDate.of(2025, 12, 22),
            LocalTime.of(16, 30),
            LocalTime.of(20, 30)
        );
    }

    public static Receiver receiverMock() {
        return Receiver.builder()
            .personId(1L)
            .firstName("Sergio")
            .lastName("Ramos")
            .address("Italia 34")
            .phoneNumber("45612033")
            .date(LocalDate.of(2024, 12, 22))
            .openingHour(LocalTime.of(16, 30))
            .closingHour(LocalTime.of(20, 30))
            .receivedCommissions(new ArrayList<>())
            .build();
    }

    public static ReceiverDTOWithCommissions receiverDTOWithCommissionsMock() {
        List<PackageDTOWithoutCommission> packages = List.of(
            new PackageDTOWithoutCommission(1L, "Sobre con dinero", 3000, PackageType.SOBRE),
            new PackageDTOWithoutCommission(4L, "Caja con herramientas", 50000, PackageType.CAJA_MEDIANA)
        );

        CommissionDTOWithoutReceiver commissionMock = new CommissionDTOWithoutReceiver(134L, "Envio de paquetes", Status.EN_PREPARACION, LocalDate.of(2024, 06, 15), 0.0f, 1L, packages);

        return new ReceiverDTOWithCommissions(
            22L,
            "Marco",
            "Reus",
            "Francia 15",
            "34211123430",
            LocalDate.of(2024, 07, 12),
            LocalTime.of(8, 30),
            LocalTime.of(16, 00),
            List.of(commissionMock)
        );
    }    
}
