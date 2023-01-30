package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import model.Task;
import util.ConnectionFactory;


public class ProjectController {
    public void save (Project project){
    
        String sql = "INSERT INTO projects (name, description, "
                + "createdAt, updatedAt)"
                + " VALUES (?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try{
        
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date (project.getCreatedAt().getTime()));
            statement.setDate(4, new Date (project.getUpdatedAt().getTime()));
            statement.execute();
            
        } catch (SQLException ex){
            
            throw new RuntimeException("Erro ao salvar o projeto " + ex.getMessage(), ex);
        
        } finally {
            
            ConnectionFactory.closeConnection(connection, statement);
        
        }
        
    }
    
    public void update (Project project){
    
        String sql = "UPDATE projects SET name = ?, description = ?,"
        + "createdAt = ?, updatedAt = ? WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try{
           
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date (project.getCreatedAt().getTime()));
            statement.setDate(4, new Date (project.getUpdatedAt().getTime()));
            statement.setInt(5, project.getId());
            statement.execute();
            
        } catch (SQLException ex) {
        
            throw new RuntimeException("Erro ao atualizar o projeto" + ex.getMessage(), ex);
        
        } finally {
            
            ConnectionFactory.closeConnection(connection, statement);
        
        }
        
    }
    
    public void removeById(int idProject){
        
        String sql = "DELETE FROM projects WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try{
        
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idProject);
            statement.execute();
            
            
        } catch (SQLException ex) {
            
            throw new RuntimeException("Erro ao deletar o projeto" + ex.getMessage(), ex);
        
        } finally {
        
            ConnectionFactory.closeConnection(connection, statement);
            
        }
        
    }
    
        
    public List<Project> getAll(){
        
        String sql = "SELECT * FROM projects";
        
        List<Project> projects = new ArrayList<>();
        
        Connection connection = null;
        PreparedStatement statement = null;        
        ResultSet resultSet = null;

        try{
        
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
            
                Project project = new Project();
                
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreatedAt(resultSet.getDate("createdAt"));
                project.setUpdatedAt(resultSet.getDate("updatedAt"));
                
                projects.add(project);
                
            }
            
        } catch (SQLException ex) {
            
            throw new RuntimeException("Erro ao inserir o projeto", ex);
        
        } finally {
        
            //ConnectionFactory.closeConnection(connection, statement, resultSet);
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão", ex);
            }
        }        
        
        return projects;
        
    }
}
