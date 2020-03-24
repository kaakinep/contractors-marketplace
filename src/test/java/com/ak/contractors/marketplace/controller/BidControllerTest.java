package com.ak.contractors.marketplace.controller;

import com.ak.contractors.marketplace.entities.Bid;
import com.ak.contractors.marketplace.starter.ContractorsMarketplaceApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ContractorsMarketplaceApplication.class)
public class BidControllerTest {

    @Autowired
    BidContoller bidContoller;

    @Test
    public void testCreateBid() {

        Bid bid = new Bid(1, "Contractor 1", 12345, new Date());

        ResponseEntity<String> responseEntity = bidContoller.createBid(bid);

        Assert.assertEquals(responseEntity.getStatusCode().value(), 200);
        Assert.assertNotNull(responseEntity.getBody());
    }

}
