package com.belong.phone.customer.model;

public class CustomerPhoneResponse {
    private String message;
    private String details;

    public CustomerPhoneResponse(String message, String details) {
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
