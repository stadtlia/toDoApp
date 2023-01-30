package com.mycompany.todoapp;

import controller.ProjectController;
import java.sql.Connection;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

public class ToDoApp {

    public static void main(String[] args){
        
        //Connection c = ConnectionFactory.getConnection();
        
        
        
        // Teste de Save de Projeto
        
        //ProjectController projectController = new ProjectController();
        
        //Project project = new Project();
        //project.setName("Projeto teste");
        //project.setDescription("Teste");
        //projectController.save(project);
       
        // Teste de update de Projeto
        
        //ProjectController projectController = new ProjectController();

        //Project project = new Project();
        //project.setId(1);
        //project.setName("Teste update");
        //project.setDescription("Teste");
      
        //projectController.update(project);
        
        //List<Project> projects = projectController.getAll();
        //System.out.println("Total de projetos = " + projects.size());
        
        // Teste de Remove de Projeto
        
        //projectController.removeById(1);
        
        //projects = projectController.getAll();
        //System.out.println("Total de projetos = " + projects.size());
        //ConnectionFactory.closeConnection(c);
    }
}
