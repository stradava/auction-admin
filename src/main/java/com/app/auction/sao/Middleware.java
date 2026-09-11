package com.app.auction.sao;

import org.springframework.stereotype.Service;

import com.app.auction.dto.SummaryDto;
import com.app.auction.service.ItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Middleware {

    private final ItemService itemService;

    public SummaryDto.Response getSummary(Long id) {
        SummaryDto.Response out = new SummaryDto.Response(itemService.getSummary(id));
        out.setSuccessResponse();
        return out;
    }

}
