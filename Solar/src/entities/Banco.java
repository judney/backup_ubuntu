package entities;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


public class Banco {
    public Connection getConnection(){
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost/teste","root","1234");
        }
        catch(SQLException e){
            System.out.println("Problemas na conexao com o banco de dados."+e);
        }
    } 
    return DriverManager.getConnection("jdbc:mysql://localhost/teste","root","1234");
}