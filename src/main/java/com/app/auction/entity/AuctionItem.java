package com.app.auction.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.app.auction.enums.AuctionStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "tb_item")
@JsonNaming(PropertyNamingStrategies.LowerCaseStrategy.class)
public class AuctionItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private BigDecimal startingPrice;
    @Column(nullable = false)
    private BigDecimal currentHighestBid;
    @Column(nullable = false)
    private LocalDateTime endTime;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AuctionStatus status = AuctionStatus.ACTIVE;

}

