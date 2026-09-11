package com.app.auction.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder(alphabetic = true)
public class BaseResponse {

    private String responseCode;
    private String responseDesc;

    public void setSuccessResponse() {
        responseCode = "00";
        responseDesc = "Success";
    }

}
