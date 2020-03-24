package com.ak.contractors.marketplace.service;

import com.ak.contractors.marketplace.entities.Bid;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.Future;

public interface BidService {

    String createBid(Bid bid);

    Bid getLowestBidder(int projectId);

    @Async
    Future<String> finalizePendingBids();
}
