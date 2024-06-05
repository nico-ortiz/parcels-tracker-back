package com.goldeng.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.goldeng.dto.CommissionDTO;
import com.goldeng.dto.CommissionDTOWithoutCustomer;
import com.goldeng.model.Commission;

@Mapper(
    componentModel = "spring",
    uses = {CustomerMapper.class, ReceiverMapper.class, PackageMapper.class})
public interface CommissionMapper {
    
    CommissionMapper INSTANCE = Mappers.getMapper(CommissionMapper.class);

    @Mapping(target = "packages", ignore = true)
    @Mapping(target = "customer.personId", source = "customerId")
    @Mapping(target = "receiver.personId", source = "receiverId")
    Commission commissionDTOToCommission(CommissionDTO commissionDTO);

    @Mapping(target = "customerId", source = "customer.personId")
    @Mapping(target = "receiverId", source = "receiver.personId")
    CommissionDTO commissionToCommissionDTO(Commission commission);

    @Mapping(target = "receiverId", source = "receiver.personId")
    CommissionDTOWithoutCustomer commissionToCommissionDTOWithoutCustomer(Commission commission);
}
