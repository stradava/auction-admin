package com.app.auction.sao;

import org.springframework.stereotype.Service;

import com.app.auction.dto.AuctionItemDto;
import com.app.auction.dto.SummaryDto;
import com.app.auction.service.ItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Middleware {

    private final ItemService itemService;

    public AuctionItemDto.AddResponse addItem(AuctionItemDto.AddRequest in) {
        AuctionItemDto.ItemData newItem = itemService.addItem(in);
        AuctionItemDto.AddResponse out = new AuctionItemDto.AddResponse();
        out.setData(newItem);
        out.setSuccessResponse();
        return out;
    }

    public SummaryDto.Response getSummary(Long id) {
        SummaryDto.Response out = new SummaryDto.Response(itemService.getSummary(id));
        out.setSuccessResponse();
        return out;
    }

}
