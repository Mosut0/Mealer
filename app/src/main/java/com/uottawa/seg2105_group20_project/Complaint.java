package com.uottawa.seg2105_group20_project;

public class Complaint {
    private String complainee, complainant, complaintDescription;

    public Complaint(){};
    public Complaint(String complainee, String complainant,String complaintDescription){
        this.complainee = complainee;
        this.complainant = complainant;
        this.complaintDescription = complaintDescription;
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

    public String getComplaintDescription() {
        return complaintDescription;
    }

    public void setComplaintDescription(String complaintDescription) {
        this.complaintDescription = complaintDescription;
    }
}
