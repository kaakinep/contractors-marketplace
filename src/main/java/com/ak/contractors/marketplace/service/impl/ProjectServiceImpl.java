package com.ak.contractors.marketplace.service.impl;

import com.ak.contractors.marketplace.repository.ProjectRepository;
import com.ak.contractors.marketplace.entities.Project;
import com.ak.contractors.marketplace.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public List<Project> getAllProjects() {
        List<Project> employeeList = new ArrayList<>();
        projectRepository.findAll().forEach(project -> {
                    employeeList.add(project);
                });

        if(employeeList.size() > 0) {
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
        Project savedProject = projectRepository.save(project);
        return "Project " + savedProject.getProjectId() + " created successfully";
    }

    @Override
    public Project getproject(int projectId) {
        Optional<Project> result = projectRepository.findById(projectId);
        if(!result.isPresent()){
            return null;
        }
        return result.get();
    }


}
