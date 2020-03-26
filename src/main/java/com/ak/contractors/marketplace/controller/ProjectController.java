package com.ak.contractors.marketplace.controller;

import com.ak.contractors.marketplace.entities.Project;
import com.ak.contractors.marketplace.service.ProjectService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    /**
     *
     * Controller class for all Project Related functionality
     *
     */

    Logger LOG = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "/createProject", method = RequestMethod.POST)
    public ResponseEntity<String> createProject(@RequestBody Project project){

        if(project == null){
            return new ResponseEntity<String>("Empty project data" , new HttpHeaders(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<String>(projectService.createProject(project) , new HttpHeaders(), HttpStatus.OK);
    }

    /**
     *
     * Added a REQUESTER_TYPE header so that we can block Contractors from seeing Maximum Budget and Contractor Name if not finalized
     *
     * @param projectId
     * @param requesterType
     * @return
     */
    @RequestMapping(value = "/getProject/{projectId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getProject(@PathVariable int projectId, @RequestHeader("REQUESTER_TYPE") String requesterType){

        if(StringUtils.isEmpty(projectId)){
            return new ResponseEntity<Object>("Invalid or Empty Project ID" , new HttpHeaders(), HttpStatus.NOT_FOUND);
        }

        try{
            Project project = projectService.getproject(projectId);
            if(!requesterType.equalsIgnoreCase("Client") && !requesterType.equalsIgnoreCase("Contractor") ){
                return new ResponseEntity<Object>("Invalid Requester" , new HttpHeaders(), HttpStatus.UNAUTHORIZED);
            } else if(requesterType.equalsIgnoreCase("Contractor")){
                project.setMaximumBudget(0);
                project.setContractorName("NOT_FINALIZED");
            }
            return projectService.getproject(projectId) == null ?
                    new ResponseEntity<Object>("No Project found" , new HttpHeaders(), HttpStatus.NOT_FOUND) :
                    new ResponseEntity<Object>(project, new HttpHeaders(), HttpStatus.OK);
        } catch(NumberFormatException nfe){
            LOG.error("Error while parsing the project with ID : " + projectId + ", error occurred is " + nfe.getMessage());
            return new ResponseEntity<Object>("Invalid Project ID : " + projectId , new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/getAllProjects", method = RequestMethod.GET)
    public ResponseEntity<List<Project>> getAllProjects(){
        return new ResponseEntity<List<Project>>(projectService.getAllProjects(), new HttpHeaders(), HttpStatus.OK);
    }

}
