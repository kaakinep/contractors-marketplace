package com.ak.contractors.marketplace.service.impl;

import com.ak.contractors.marketplace.service.ProjectService;
import com.ak.contractors.marketplace.starter.ContractorsMarketplaceApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ContractorsMarketplaceApplication.class)
public class ProjectServiceImplTest {

    @Autowired
    ProjectService projectService;

    @Test
    public void testGetProject(){
        Assert.assertTrue(projectService.getproject(1).getProjectId() == 1);
    }
}
