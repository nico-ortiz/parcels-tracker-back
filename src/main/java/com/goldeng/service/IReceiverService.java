package com.goldeng.service;

import com.goldeng.dto.ReceiverDTO;

public interface IReceiverService {

    ReceiverDTO createReceiver(ReceiverDTO receiverDTO);

    ReceiverDTO getReceiver(Long receiverId);

    ReceiverDTO deleteReceiver(Long receiverId);

    ReceiverDTO updateReceiver(Long receiverId, ReceiverDTO receiverDTO);
    
}