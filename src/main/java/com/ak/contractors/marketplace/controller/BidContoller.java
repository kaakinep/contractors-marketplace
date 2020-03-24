package com.ak.contractors.marketplace.controller;

import com.ak.contractors.marketplace.entities.Bid;
import com.ak.contractors.marketplace.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bid")
public class BidContoller {

    @Autowired
    BidService bidService;

    @RequestMapping(value = "/createBid", method = RequestMethod.POST)
    public ResponseEntity<String> createBid(@RequestBody Bid bid){
        return new ResponseEntity<String>(bidService.createBid(bid), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getLowestBid/{projectId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getLowestBidForAProject(@PathVariable int projectId){
        Bid bid = bidService.getLowestBidder(projectId);
        if(bid == null){
            return new ResponseEntity<Object>("Invalid or Empty Project ID or No Bid found" , new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Object>(bid, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/finalizePendingBids", method = RequestMethod.GET)
    public ResponseEntity<String> finalizeBid(){
        bidService.finalizePendingBids();
        return new ResponseEntity<String>("Job to finalize the bids has been triggered successfully", new HttpHeaders(), HttpStatus.OK);
    }

}
