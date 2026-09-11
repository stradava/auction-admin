package com.app.auction.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.app.auction.enums.AuctionStatus;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SummaryDto {

    private SummaryDto() {
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @JsonPropertyOrder(alphabetic = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response extends BaseResponse {

        private SummaryData data;

    }

    @Data
    public static class SummaryData {

        private Long id;
        private String name;
        private String description;
        @JsonAlias("starting_price")
        private BigDecimal startingPrice;
        @JsonAlias("current_highest_bid")
        private BigDecimal currentHighestBid;
        @JsonAlias("end_time")
        private Date endTime;
        @JsonAlias("created_at")
        private Date createdAt;
        @JsonAlias("updated_at")
        private Date updatedAt;
        private AuctionStatus status;
        @JsonAlias("first_bid")
        private Date firstBid;
        @JsonAlias("final_bid")
        private Date finalBid;
        @JsonAlias("bid_count")
        private Integer bidCount;
        @JsonAlias("min_bid")
        private BigDecimal minBid;
        @JsonAlias("max_bid")
        private BigDecimal maxBid;
        @JsonAlias("bid_increment_mode")
        private BigDecimal bidIncrementMode;

    }

}
