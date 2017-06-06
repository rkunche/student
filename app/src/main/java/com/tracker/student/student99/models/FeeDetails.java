package com.tracker.student.student99.models;


import io.realm.RealmObject;

public class FeeDetails extends RealmObject{
    private String feeDetails;
    private float amount;
    private float total;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    private String section;

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getFeeDetails() {
        return feeDetails;
    }

    public void setFeeDetails(String feeDetails) {
        this.feeDetails = feeDetails;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
