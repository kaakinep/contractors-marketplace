package com.ak.contractors.marketplace.repository;

import com.ak.contractors.marketplace.entities.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Integer> {

    @Query(value = "select * from Bid where project_id = ?1 and bid_amount <= ?2",
            nativeQuery = true)
    List<Bid> findBidByProjectId(int projectId, int bidAmount);

    @Transactional
    @Modifying
    @Query(value = "update Bid set bid_status = 'WON', bid_won = true where project_id = ?1 AND contractor_name = ?2",
            nativeQuery = true)
    void updateFinalBidderForProject(int projectId, String contractorName, boolean b, String won);
}
