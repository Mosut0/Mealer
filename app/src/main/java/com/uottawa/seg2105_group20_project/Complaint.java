package com.uottawa.seg2105_group20_project;

public class Complaint {
    private String complainee, complainant, description, dbId;

    public Complaint(){};
    public Complaint(String complainee, String complainant,String complaintDescription){
        this.complainee = complainee;
        this.complainant = complainant;
        this.description = complaintDescription;
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
}
