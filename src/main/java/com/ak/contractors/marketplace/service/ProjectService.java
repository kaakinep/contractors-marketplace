package com.ak.contractors.marketplace.service;

import com.ak.contractors.marketplace.entities.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getAllProjects();

    String createProject(Project project);

    Project getproject(int projectId);
}
