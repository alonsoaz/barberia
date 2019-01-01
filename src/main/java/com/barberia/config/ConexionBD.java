package com.barberia.config;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.sql.PreparedStatement;

public class ConexionBD {

	public static Connection dbCon;
	public PreparedStatement dbPs;
	public CallableStatement dbCs;
	public ResultSet dbRst;
	
    public ConexionBD() throws SQLException {
        setDBConnection();
    }

	
	public static Connection setDBConnection() throws SQLException {
		
		String bd = "barberia?autoReconnect=true&useSSL=false&noAccessToProcedureBodies=true";
		String login = "root";
		String password = "";
		String url = "jdbc:mysql://localhost:3306/"+bd;
		
		System.out.println("Dentro de DBConnection");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			dbCon = DriverManager.getConnection(url, login, password);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		return dbCon;
		
	}
	
	public ResultSet getResultSet (String sqlQuery, Connection conn) throws SQLException {
		System.out.println("Dentro de getResultSet");
		System.out.println(sqlQuery);
		String sqlquery = sqlQuery;
		dbCon = setDBConnection();
		try {
			dbPs = dbCon.prepareStatement(sqlquery);
			dbRst = dbPs.executeQuery();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbRst;
	}
	
	public ResultSet getResultSet1 (String sqlQuery, Connection conn) throws SQLException {
		System.out.println("Dentro de getResultSet");
		System.out.println(sqlQuery);
		String sqlquery = sqlQuery;
		dbCon = setDBConnection();
		try {
			dbCs = dbCon.prepareCall(sqlquery);
			dbRst = dbCs.executeQuery();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbRst;
	}
	
    public static String[][] getRegistros (Connection conn, String value) throws SQLException {
		
		CallableStatement pstm;
		ResultSet dbRst1;
		String[][] mRegistros = null;
		try {
			pstm = conn.prepareCall("{CALL barberia.`sp.Buscar_Usuario`(?,?)}");
			pstm.setString(1, value);
			pstm.registerOutParameter(2,Types.INTEGER);
			dbRst1 = pstm.executeQuery();
            int fila = -1;
            if (dbRst1.last()) {
                mRegistros = new String[dbRst1.getRow()][dbRst1.getMetaData().getColumnCount()];
            }
            dbRst1.beforeFirst();
            int columnas = dbRst1.getMetaData().getColumnCount();
            while (dbRst1.next() && ++fila > -1) {
                for (int i = 0; i < columnas; i++) {
                	
                    mRegistros[fila][i] = dbRst1.getString(i + 1);
                    System.out.println(dbRst1.getString(i + 1));
                    
                }
            }
            dbRst1.close();
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mRegistros;
	}
	
    public boolean SQLchckingData(Connection conn, String row, String value) throws SQLException {
    	
    	PreparedStatement stmt;
    	ResultSet dbRst1;
    	
    	try {
			System.out.println("Antes de hacer preparedstatement"); 
    		stmt = conn.prepareStatement("SELECT * FROM usuario WHERE " + row + "= ?");
			stmt.setString(1, value);
			System.out.println(stmt);
			
			dbRst1 = stmt.executeQuery();
			
			if(dbRst1.next()) {
				return true;
			}else{
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return true;
    	
    }
    
	public static void main(String[] args) throws SQLException {
		ConexionBD c = new ConexionBD(); 
		try {
			getRegistros (c.setDBConnection(), "U");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
