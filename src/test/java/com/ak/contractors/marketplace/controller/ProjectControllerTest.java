package com.ak.contractors.marketplace.controller;

import com.ak.contractors.marketplace.starter.ContractorsMarketplaceApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ContractorsMarketplaceApplication.class)
public class ProjectControllerTest {

    @Autowired
    ProjectController projectController;

    @Test
    public void testGetAllProjects(){
        Assert.assertNotNull(projectController.getAllProjects());
    }

}
