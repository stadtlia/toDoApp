package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

public class TaskController {
    
    public void save (Task task){
    
        String sql = "INSERT INTO tasks (idProject,"
                + "name, description, status,"
                + "notes, deadline, createdAt, updatedAt)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try{
        
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.getStatus());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date (task.getDeadline().getTime()));
            statement.setDate(7, new Date (task.getCreatedAt().getTime()));
            statement.setDate(8, new Date (task.getUpdatedAt().getTime()));
            statement.execute();
            
        } catch (SQLException ex){
            
            throw new RuntimeException("Erro ao salvar a tarefa" + ex.getMessage(), ex);
        
        } finally {
            
            ConnectionFactory.closeConnection(connection, statement);
        
        }
        
    }
    
    public void update (Task task){
    
        String sql = "UPDATE tasks SET idProject = ?,"
        + "name = ?, description = ?,"
        + "notes = ?, status = ?, deadline = ?,"
        + "createdAt = ?, updatedAt = ?"
        + " WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try{
           
            connection = ConnectionFactory.getConnection();
            
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.getStatus());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date (task.getDeadline().getTime()));
            statement.setDate(7, new Date (task.getCreatedAt().getTime()));
            statement.setDate(8, new Date (task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());
            statement.execute();
            
        } catch (SQLException ex) {
        
            throw new RuntimeException("Erro ao atualizar a tarefa" + ex.getMessage(), ex);
        
        } finally {
            
            ConnectionFactory.closeConnection(connection, statement);
        
        }
        
    }
    
    public void removeById(int taskId){
        
        String sql = "DELETE FROM tasks WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try{
        
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, taskId);
            statement.execute();
            
            
        } catch (SQLException ex) {
            
            throw new RuntimeException("Erro ao deletar a tarefa" + ex.getMessage(), ex);
        
        } finally {
        
            ConnectionFactory.closeConnection(connection, statement);
            
        }
        
    }
    
    public List<Task> getAll(int idProject){
        
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;        
        ResultSet resultSet = null;
        
        List<Task> tasks = new ArrayList<Task>();

        try{
        
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idProject);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
            
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setNotes(resultSet.getString("notes"));
                task.setStatus(resultSet.getBoolean("status"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setUpdatedAt(resultSet.getDate("updatedAt"));
                
                tasks.add(task);
                
            }
            
        } catch (SQLException ex) {
            
            throw new RuntimeException("Erro ao inserir a tarefa" + ex.getMessage(), ex);
        
        } finally {
        
            ConnectionFactory.closeConnection(connection, statement, resultSet);
            
        }        
        
        return tasks;
        
    }
    
    public List<Task> getByProjectId(int id) {
        String sql = "SELECT * FROM tasks where idProject = ?";

        List<Task> tasks = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);

            rset = stmt.executeQuery();

            while (rset.next()) {

                Task task = new Task();

                task.setId(rset.getInt("id"));
                task.setIdProject(rset.getInt("idProject"));
                task.setName(rset.getString("name"));
                task.setDescription(rset.getString("description"));
                task.setNotes(rset.getString("notes"));
                task.setStatus(rset.getBoolean("status"));
                task.setDeadline(rset.getDate("deadline"));
                task.setCreatedAt(rset.getDate("createdAt"));
                task.setUpdatedAt(rset.getDate("updatedAt"));

                //Adiciono o contato recuperado, a lista de contatos
                tasks.add(task);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar as tarefas", ex);
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão", ex);
            }
        }
        return tasks;
    }
}
