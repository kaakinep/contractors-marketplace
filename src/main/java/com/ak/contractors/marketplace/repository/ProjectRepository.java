package com.ak.contractors.marketplace.repository;

import com.ak.contractors.marketplace.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    @Query(value = "select * from Project where contractor_name = 'NOT_FINALIZED' and last_allowed_bid_date <= ?1",
            nativeQuery = true)
    List<Project> getBidsToBeFinalized(Date currentDate);

    @Transactional
    @Modifying
    @Query(value = "update Project set contractor_name = ?2 where project_id = ?1",
            nativeQuery = true)
    void updateProjectWithFinalBidder(int projectId, String contractorName);

}
