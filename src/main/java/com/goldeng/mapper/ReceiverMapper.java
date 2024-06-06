package com.goldeng.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.goldeng.dto.ReceiverDTO;
import com.goldeng.dto.ReceiverDTOWithCommissions;
import com.goldeng.model.Receiver;

@Mapper(
    componentModel = "spring",
    uses = {CommissionMapper.class})
public interface ReceiverMapper {
    
    ReceiverMapper INSTANCE = Mappers.getMapper(ReceiverMapper.class);

    @Mapping(source = "receiverId", target = "personId")
    @Mapping(target = "receivedCommissions", ignore = true)
    Receiver receiverDTOToReceiver(ReceiverDTO receiverDTO);

    @Mapping(source = "personId", target = "receiverId")
    ReceiverDTO receiverToReceiverDTO(Receiver receiver);

    @Mapping(target = "receiverId", source = "personId")
    ReceiverDTOWithCommissions receiverToReceiverDTOWC(Receiver receiver);
}
