package com.app.auction.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.app.auction.dto.SummaryDto;
import com.app.auction.repository.ItemRepository;
import com.app.auction.utility.GeneralHelper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).findAndRegisterModules();

    public SummaryDto.SummaryData getSummary(Long id) {
        Map<String, Object> summaryMap = itemRepository.findSummaryById(id).orElseThrow(() -> new IllegalStateException(GeneralHelper.ERR_BID_NOT_FOUND));
        return objectMapper.convertValue(summaryMap, SummaryDto.SummaryData.class);
    }

}
