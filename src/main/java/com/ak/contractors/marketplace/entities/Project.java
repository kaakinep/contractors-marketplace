package com.ak.contractors.marketplace.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="project")
public class Project {

    @Id
    @GeneratedValue
    private int projectId;

    @Column(name="requirements")
    private String requirements;

    @Column(name="description")
    private String description;

    @Column(name="maximum_budget")
    private int maximumBudget;

    @Column(name="last_allowed_bid_date")
    private Date lastAllowedBidDate;

    @Column(name = "contractor_name")
    private String contractorName = "NOT_FINALIZED";

    Project(){}

    public Project(String requirements, String description, int maximumBudget, Date lastAllowedBidDate) {
        this.requirements = requirements;
        this.description = description;
        this.maximumBudget = maximumBudget;
        this.lastAllowedBidDate = lastAllowedBidDate;
    }

    public String getContractorName() {
        return contractorName;
    }

    public void setContractorName(String contractorName) {
        this.contractorName = contractorName;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaximumBudget() {
        return maximumBudget;
    }

    public void setMaximumBudget(int maximumBudget) {
        this.maximumBudget = maximumBudget;
    }

    public Date getLastAllowedBidDate() {
        return lastAllowedBidDate;
    }

    public void setLastAllowedBidDate(Date lastAllowedBidDate) {
        this.lastAllowedBidDate = lastAllowedBidDate;
    }

}
