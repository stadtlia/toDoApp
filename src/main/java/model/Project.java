package model;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class Project {
    
    //Attributes
    private int id;
    private String name;
    private String description;
    private List<Task> tasks;
    private Date createdAt;
    private Date updatedAt;
    private static final Logger LOG = Logger.getLogger(Project.class.getName());

    //Constructor
    public Project(int id, String name, String description, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tasks = tasks;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    public Project(){
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
    
    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    //ToString
    @Override
    public String toString() {
        return this.name;
    }
   
}
