package com.uottawa.seg2105_group20_project;

public class PurchaseRequest {
    public String id;
    public String cookId;
    public String clientId;
    public String mealId;
    public String pickupTime;
    public String status;
    public boolean notificationSent, rated, complained;

    public PurchaseRequest(){}
    public PurchaseRequest(String id, String cookId, String clientId, String mealId, String pickupTime){
        this.id= id;
        this.cookId= cookId;
        this.clientId= clientId;
        this.mealId= mealId;
        this.pickupTime= pickupTime;
        this.status="Pending";
        this.notificationSent = false;
        this.rated = false;
        this.complained = false;
    }
}