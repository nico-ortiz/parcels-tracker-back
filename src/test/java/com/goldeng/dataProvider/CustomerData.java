package com.goldeng.dataProvider;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.goldeng.dto.CommissionDTOWithoutCustomer;
import com.goldeng.dto.CustomerDTO;
import com.goldeng.dto.CustomerDTOWithCommissions;
import com.goldeng.dto.PackageDTOWithoutCommission;
import com.goldeng.model.Customer;
import com.goldeng.model.enums.PackageType;
import com.goldeng.model.enums.Status;

public class CustomerData {
    
    public static CustomerDTO customerDTOMock() {
        return new CustomerDTO(1L, "Ramon", "Sinatra", "Buenos Aires 21", "3584123123", "12123123", "ramonSin@gmail.com", "12121231231", "Sin4tr4.");    
    }

    public static Customer customerMock() {
        return Customer.builder()
            .personId(1L)
            .firstName("Ramon")
            .lastName("Sinatra")
            .address("Buenos Aires 21")
            .phoneNumber("3584123123")
            .dni("12123123")
            .email("ramonSin@gmail.com")
            .cuit("12121231231")
            .password("Sin4tr4.")
            .commissions(new ArrayList<>())
            .build();
    }

    public static Customer customerUpdatedMock() {
        return Customer.builder()
            .personId(1L)
            .firstName("Ramon")
            .lastName("Sinatra")
            .address("Buenos Aires 21")
            .phoneNumber("3584333123")
            .dni("12123123")
            .email("rsinatra@gmail.com")
            .cuit("12121231231")
            .password("Sin4tr4.")
            .build();
    }

    public static CustomerDTO customerWithCommissionsDTOMock() {
        return new CustomerDTO(1L, "Ramon", "Sinatra", "Buenos Aires 21", "3584123123", "12123123", "ramonSin@gmail.com", "12121231231", "Sin4tr4.");    
    }

    public static Customer newCustomerMock() {
        return Customer.builder()
        .personId(22L)
        .firstName("Ronaldo")
        .lastName("Nazario")
        .address("Constitucion 44")
        .phoneNumber("3213321123")
        .email("rnazario@gmail.com")
        .dni("32321321")
        .cuit("00323213210")
        .password("Br4zil!")
        .build();
    }

    public static CustomerDTO newCustomerDTOMock() {
        return new CustomerDTO(22L, "Ronaldo", "Nazario" , "Constitucion 44", "3213321123", "32321321", "rnazario@gmail.com", "00323213210", "Br4zil!");
    }

    public static List<CustomerDTO> customerDTOListMock() {
        return List.of(
            new CustomerDTO(1L, "Lionel", "Messi", "Chaco 21", "3332333123", "12123321", "lmessi@gmail.com", "10401031021", "L10nel"),
            new CustomerDTO(2L, "Kevin", "De Bruyne", "Suipacha 901", "3332333122", "121233122", "kdebruyne@gmail.com", "10401033339", "K3v!n"),
            new CustomerDTO(3L, "Paulo", "Dybala", "Cordoba 1918", "3366733123", "121233676", "pdybala@gmail.com", "10401031021", "Joy4"),
            new CustomerDTO(4L, "Emiliano", "Martinez", "Jujuy 674", "12312367867", "12123778", "dibu@gmail.com", "10401031888", "D1bu_"),
            new CustomerDTO(5L, "Ronaldo", "Nazario", "25 de Mayo 54", "667322912", "9877300", "rnazario@gmail.com", "104010444", "_R0naldo")
        );
    }

    public static List<Customer> customerListMock() {
        return List.of(
            Customer.builder().personId(1L).firstName("Lionel").lastName("Messi").address("Chaco 21").phoneNumber("3332333123").dni("12123321").email("lmessi@gmail.com").cuit("10401031021").password("L10nel").build(),
            Customer.builder().personId(2L).firstName("Kevin").lastName("De Bruyne").address("Suipacha 901").phoneNumber("3332333122").dni("121233122").email("kdebruyne@gmail.com").cuit("10401031021").password("").build(),
            Customer.builder().personId(3L).firstName("Paulo").lastName("Dybala").address("Cordoba 1918").phoneNumber("3366733123").dni("121233676").email("pdybala@gmail.com").cuit("10401033339").password("Joy4").build(),
            Customer.builder().personId(4L).firstName("Emiliano").lastName("Martinez").address("Jujuy 674").phoneNumber("12312367867").dni("12123778").email("dibu@gmail.com").cuit("10401031888").password("D1bu_").build(),
            Customer.builder().personId(5L).firstName("Ronaldo").lastName("Nazario").address("25 de Mayo 54").phoneNumber("667322912").dni("9877300").email("rnazario@gmail.com").cuit("104010444").password("_R0naldo").build()
        );
    }

    public static CustomerDTOWithCommissions customerDTOWithCommissionsMock() {
        List<PackageDTOWithoutCommission> packages = List.of(
            new PackageDTOWithoutCommission(1L, "Sobre con dinero", PackageType.SOBRE),
            new PackageDTOWithoutCommission(4L, "Caja con herramientas", PackageType.CAJA_MEDIANA)
        );

        CommissionDTOWithoutCustomer commissionMock = new CommissionDTOWithoutCustomer(134L, "Envio de paquetes", Status.EN_PREPARACION, LocalDate.of(2024, 06, 15), 0.0f, 1L, packages);

        return new CustomerDTOWithCommissions("Ramon", "Sinatra", "Buenos Aires 21", "3584123123", 10L, "12123123", "ramonSin@gmail.com", "12121231231", "Sin4tr4.", List.of(commissionMock));
    }
}
