package com.app.auction.enums;

import java.util.Arrays;

import com.app.auction.utility.GeneralHelper;

public enum AuctionStatus {

    ACTIVE(0), 
    INACTIVE(1), 
    COMPLETED(2), 
    CANCELLED(3)
    ;

    private final int statusId;

    private AuctionStatus(int statusId) {
        this.statusId = statusId;
    }

    public static AuctionStatus getById(int key) {
        return Arrays.asList(AuctionStatus.values()).stream().filter(i -> i.statusId == key).findFirst().orElseThrow(() -> new IllegalStateException(GeneralHelper.ERR_INVALID_STATUS));
    }

}
