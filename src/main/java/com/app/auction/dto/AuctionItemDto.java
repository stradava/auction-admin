package com.app.auction.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.app.auction.entity.AuctionItem;
import com.app.auction.enums.AuctionStatus;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

public class AuctionItemDto {
    
    private AuctionItemDto() {}

    @Data
    @JsonPropertyOrder(alphabetic = true)
    public static class AddRequest {

        @NotBlank
        private String name;
        private String description;
        @PositiveOrZero 
        private BigDecimal startingPrice;
        @NotNull
        private LocalDateTime endTime;
        @NotNull
        @Min(value = 0)
        @Max(value = 3)
        private Integer status;

    }
    
    @Data
    @EqualsAndHashCode(callSuper = false)
    @JsonPropertyOrder(alphabetic = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddResponse extends BaseResponse {

        private ItemData data;

    }

    @Data
    @NoArgsConstructor 
    public static class ItemData {
    
        private Long id;
        private String name;
        private String description;
        private BigDecimal startingPrice;
        private Date endTime;
        private AuctionStatus status;
        
        public ItemData(AuctionItem item) {
            this.id = item.getId();
            this.name = item.getName();
            this.description = item.getDescription();
            this.startingPrice = item.getStartingPrice();
            this.endTime = Date.from(item.getEndTime().atZone(ZoneId.systemDefault()).toInstant());
            this.status = item.getStatus();
        }
    }

}
