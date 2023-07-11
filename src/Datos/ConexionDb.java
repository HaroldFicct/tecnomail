package Datos;

import java.sql.*;

public class ConexionDb {
	private static Connection connection;
    private static Statement statement;

    private static final String HOST = "jdbc:postgresql://mail.tecnoweb.org.bo:5432/";
    private static final String DATABASE = "db_grupo18sc";
    private static final String USER = "grupo18sc";
    private static final String PASS = "grup018grup018";

	public ConexionDb() {
		connection = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
	}
	
	public boolean connect() throws SQLException {
		connection = DriverManager.getConnection(HOST+DATABASE, USER, PASS);
		System.out.println("Connected");
		return true;
    }

    public boolean close() throws SQLException {
    	if (connection != null) {
    		System.out.println("Disconnected");
    		if (statement != null) {
    			statement.close();
    		}
    		connection.close();
    	}
    	return true;
    }

    public ResultSet select(String query) throws SQLException {
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        System.out.println("Select send");
        return resultSet;
    }

    public int insert(String query) throws SQLException {
        statement = connection.createStatement();
        if(query.charAt(query.length() - 1) == ';') {
        	query = query.substring(0, query.length() - 1);
        }
        ResultSet res = statement.executeQuery(query + " RETURNING id;");
        System.out.println("Insert send");
        if(res.next()){
        	return res.getInt("id");
        }
        return 0;
    }

    public int update(String query) throws SQLException {
        statement = connection.createStatement();
        statement.executeUpdate(query);
        System.out.println("Update send");
        return 0;
    }

    public int delete(String query) throws SQLException {
        statement = connection.createStatement();
        statement.executeUpdate(query);
        System.out.println("Delete send");
        return 0;
    }

}
