package com.ak.contractors.marketplace.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="bid")
public class Bid {

    @Id
    @GeneratedValue
    private int bidId;

    @Column(name="project_id")
    private int projectId;

    @Column(name="contractor_name")
    private String contractorName;

    @Column(name="bid_amount")
    private int bidAmount;

    @Column(name="bid_date")
    private Date bidDate;

    @Column(name="bid_won")
    private boolean bidWon = false;

    @Column(name="bid_status")
    private String bidStatus = "NOT_FINALIZED";

    Bid(){}

    public Bid(int projectId, String contractorName, int bidAmount, Date bidDate) {
        this.projectId = projectId;
        this.contractorName = contractorName;
        this.bidAmount = bidAmount;
        this.bidDate = bidDate;
    }

    public int getBidId() {
        return bidId;
    }

    public void setBidId(int bidId) {
        this.bidId = bidId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getContractorName() {
        return contractorName;
    }

    public void setContractorName(String contractorName) {
        this.contractorName = contractorName;
    }

    public int getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(int bidAmount) {
        this.bidAmount = bidAmount;
    }

    public Date getBidDate() {
        return bidDate;
    }

    public void setBidDate(Date bidDate) {
        this.bidDate = bidDate;
    }

    public boolean isBidWon() {
        return bidWon;
    }

    public void setBidWon(boolean bidWon) {
        this.bidWon = bidWon;
    }

    public String getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(String bidStatus) {
        this.bidStatus = bidStatus;
    }
}
