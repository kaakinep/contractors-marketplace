package com.ak.contractors.marketplace.service.impl;

import com.ak.contractors.marketplace.repository.ProjectRepository;
import com.ak.contractors.marketplace.entities.Project;
import com.ak.contractors.marketplace.service.ProjectService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class ProjectServiceImpl implements ProjectService {

    private final static Logger LOGGER = Logger.getLogger(ProjectServiceImpl.class.getName());

    /**
     *
     * Actual business class that executes the Project related functionalites
     *
     */

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public List<Project> getAllProjects() {
        List<Project> employeeList = new ArrayList<>();
        projectRepository.findAll().forEach(project -> {
                    employeeList.add(project);
                });

        if(employeeList.size() > 0) {
            LOGGER.info("Getting all the projects at " + new Date().toString());
            return employeeList;
        } else {
            return new ArrayList<Project>();
        }
    }

    @Override
    public String createProject(Project project) {
        if(project == null){
            return "Empty project data";
        }
        LOGGER.info("Request Payload to createProject : " + new Gson().toJson(project));
        Project savedProject = projectRepository.save(project);
        return "Project " + savedProject.getProjectId() + " created successfully";
    }

    @Override
    public Project getproject(int projectId) {
        Optional<Project> result = projectRepository.findById(projectId);
        if(!result.isPresent()){
            return null;
        }
        LOGGER.info("Getting project details for Project ID: " + projectId);
        return result.get();
    }


}
