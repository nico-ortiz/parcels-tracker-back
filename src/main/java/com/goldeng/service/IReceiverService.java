package com.goldeng.service;

import com.goldeng.dto.ReceiverDTO;
import com.goldeng.dto.ReceiverDTOWithCommissions;

public interface IReceiverService {

    ReceiverDTO createReceiver(ReceiverDTO receiverDTO);

    ReceiverDTO getReceiver(Long receiverId);

    ReceiverDTO deleteReceiver(Long receiverId);

    ReceiverDTO updateReceiver(Long receiverId, ReceiverDTO receiverDTO);

    ReceiverDTOWithCommissions getReceiverWithReceivedCommissions(Long receiverId);
}