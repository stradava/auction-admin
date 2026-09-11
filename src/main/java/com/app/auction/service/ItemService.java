package com.app.auction.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.app.auction.dto.AuctionItemDto;
import com.app.auction.dto.SummaryDto;
import com.app.auction.entity.AuctionItem;
import com.app.auction.enums.AuctionStatus;
import com.app.auction.repository.ItemRepository;
import com.app.auction.utility.GeneralHelper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).findAndRegisterModules();

    @Transactional 
    public AuctionItemDto.ItemData addItem(AuctionItemDto.AddRequest in) {
        AuctionItem newItem = new AuctionItem();
        newItem.setCurrentHighestBid(new BigDecimal(0));
        newItem.setDescription(in.getDescription());
        newItem.setEndTime(in.getEndTime());
        newItem.setName(in.getName());
        newItem.setStartingPrice(in.getStartingPrice());
        newItem.setStatus(AuctionStatus.getById(in.getStatus()));
        newItem = itemRepository.save(newItem);
        return new AuctionItemDto.ItemData(newItem);
    }

    public SummaryDto.SummaryData getSummary(Long id) {
        Map<String, Object> summaryMap = itemRepository.findSummaryById(id).orElseThrow(() -> new IllegalStateException(GeneralHelper.ERR_BID_NOT_FOUND));
        return objectMapper.convertValue(summaryMap, SummaryDto.SummaryData.class);
    }

}
