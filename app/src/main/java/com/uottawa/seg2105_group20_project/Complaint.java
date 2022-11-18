package com.uottawa.seg2105_group20_project;

import java.util.ArrayList;

public class Complaint {
    private String complainee, complaineeID, complainant, description, dbId;
    private boolean reviewed;

    public Complaint(){};
    public Complaint(String complainee, String complaineeID, String complainant,String complaintDescription){
        this.complainee = complainee;
        this.complaineeID = complaineeID;
        this.complainant = complainant;
        this.description = complaintDescription;
        this.reviewed = false;
    }

    public String getComplainee() {
        return complainee;
    }

    public void setComplainee(String complainee) {
        this.complainee = complainee;
    }

    public String getComplainant() {
        return complainant;
    }

    public void setComplainant(String complainant) {
        this.complainant = complainant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDbId() {
        return dbId;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

    public String getComplaineeID() {
        return complaineeID;
    }

    public void setComplaineeID(String complaineeID) {
        this.complaineeID = complaineeID;
    }
}
