package com.ak.contractors.marketplace.service.impl;

import com.ak.contractors.marketplace.entities.Bid;
import com.ak.contractors.marketplace.entities.Project;
import com.ak.contractors.marketplace.repository.BidRepository;
import com.ak.contractors.marketplace.repository.ProjectRepository;
import com.ak.contractors.marketplace.service.BidService;
import com.ak.contractors.marketplace.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
public class BidServiceImpl implements BidService {

    @Autowired
    BidRepository bidRepository;

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public String createBid(Bid bid) {
        if(bid == null){
            return "Empty bid data";
        }

        if(projectService.getproject(bid.getProjectId()) == null){
            return "Project wtih ID : '" + bid.getProjectId() + "', that is being bid for doesn't exist";
        }

        Bid savedBid = bidRepository.save(bid);
        return "Bid with ID '" + String.valueOf(savedBid.getBidId()) + "' is created successfully";
    }

    @Override
    public Bid getLowestBidder(int projectId) {
        Project project = projectService.getproject(projectId);
        if(project == null){
            return null;
        }
        List<Bid> listOfBids = bidRepository.findBidByProjectId(projectId, project.getMaximumBudget());
        if(listOfBids.isEmpty()){
            return null;
        }
        List<Integer> amountList = listOfBids.stream().map(Bid::getBidAmount).collect(Collectors.toList());
        Collections.sort(amountList);

        return listOfBids.stream().filter(bid -> bid.getBidAmount() == amountList.get(0)).findFirst().get();
    }

    @Override
    @Async
    public Future<String> finalizePendingBids() {

        List<Project> projectList = projectRepository.getBidsToBeFinalized(new Date());

        for(Project project:projectList){
            Bid bid = getLowestBidder(project.getProjectId());
            if(bid == null){
                projectRepository.updateProjectWithFinalBidder(project.getProjectId(), "NOT_TO_BE_FINALIZED");
            } else {
                projectRepository.updateProjectWithFinalBidder(project.getProjectId(), bid.getContractorName());
                bidRepository.updateFinalBidderForProject(project.getProjectId(), bid.getContractorName(), true, "WON");
            }
        }

        return new AsyncResult<String>("Bids have been finalized");
    }

}
