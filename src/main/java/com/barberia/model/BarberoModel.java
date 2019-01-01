package com.barberia.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.barberia.config.ConexionBD;
import com.barberia.entity.*;

@Repository
public class BarberoModel {
	
	private ConexionBD dbCon;
	private Connection conn;
	private ResultSet rslt;
	private PreparedStatement stmt;
	private CallableStatement clbl;
	
	private List<MensajesBeans> mensaje = new ArrayList<MensajesBeans>();
	private List<Aidis> aidi = new ArrayList<Aidis>();
	private List<MessagenID> mesgid = new ArrayList<MessagenID>();
	
	private List<ListarBarberoInterno> BarberoRecords = new ArrayList<ListarBarberoInterno>();
	private List<ListarBarbero> BarberoR = new ArrayList<ListarBarbero>();
	private List<BuscarBarberoInterno> BarberoSearch = new ArrayList<BuscarBarberoInterno>();
	private List<BuscarBarbero> BarberoS = new ArrayList<BuscarBarbero>();
	private List<RecuperarBarberoInterno> BarberoGet = new ArrayList<RecuperarBarberoInterno>();
	
	private static BarberoModel stdregd = null;
	
	public static BarberoModel getInstance() {
		
		if(stdregd == null) {
			stdregd = new BarberoModel();
			return stdregd;
		}
		else {
			return stdregd;
		}
	}

	public List<ListarBarberoInterno> getBarberoRecords() {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "call `sp.Listar_Barbero_Interno`";
		
		try {
			conn = ConexionBD.setDBConnection();
			rslt = dbCon.getResultSet(SQLQuery, conn);
			while(rslt.next()) {
				BarberoRecords.add(new ListarBarberoInterno(
						rslt.getString(1), 
						rslt.getString(2), 
						rslt.getString(3), 
						rslt.getString(4), 
						rslt.getString(5),
						rslt.getString(6), 
						rslt.getString(7),
						rslt.getString(8),
						rslt.getString(9),
						rslt.getString(10)
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			if(conn != null ) {
				
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
		return BarberoRecords;
	}
	
	public List<ListarBarbero> getBarberoR() {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "call `sp.Listar_Barbero`";
		
		try {
			conn = ConexionBD.setDBConnection();
			rslt = dbCon.getResultSet(SQLQuery, conn);
			while(rslt.next()) {
				BarberoR.add(new ListarBarbero(
						rslt.getString(1), 
						rslt.getString(2), 
						rslt.getString(3), 
						rslt.getString(4), 
						rslt.getString(5),
						rslt.getString(6), 
						rslt.getString(7)
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			if(conn != null ) {
				
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
		return BarberoR;
	}
	
	public List<BuscarBarberoInterno> getBarberoByWords(String words) {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "{call `sp.Buscar_Barbero_Interno`(?,?)}";
		
		try {
			conn = ConexionBD.setDBConnection();
			clbl = conn.prepareCall(SQLQuery);
			clbl.setString(1, words);
			clbl.registerOutParameter(2,Types.INTEGER);
			rslt = clbl.executeQuery();
			while(rslt.next()) {
				BarberoSearch.add(new BuscarBarberoInterno(
						rslt.getString(1), 
						rslt.getString(2), 
						rslt.getString(3), 
						rslt.getString(4), 
						rslt.getString(5),
						rslt.getString(6), 
						rslt.getString(7),
						rslt.getString(8),
						rslt.getString(9),
						rslt.getString(10),
						clbl.getInt(2)
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			if(conn != null ) {
				
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
		return BarberoSearch;
	}
	
	public List<BuscarBarbero> getBarbByWords(String words) {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "{call `sp.Buscar_Barbero`(?,?)}";
		
		try {
			conn = ConexionBD.setDBConnection();
			clbl = conn.prepareCall(SQLQuery);
			clbl.setString(1, words);
			clbl.registerOutParameter(2,Types.INTEGER);
			rslt = clbl.executeQuery();
			while(rslt.next()) {
				BarberoS.add(new BuscarBarbero(
						rslt.getString(1), 
						rslt.getString(2), 
						rslt.getString(3), 
						rslt.getString(4), 
						rslt.getString(5),
						rslt.getString(6), 
						rslt.getString(7),
						clbl.getInt(2)
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			if(conn != null ) {
				
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
		return BarberoS;
	}
	
	public List<RecuperarBarberoInterno> getBarbero(int id) {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "{call `sp.Recuperar_Barbero_Interno`(?)}";
		
		try {
			conn = ConexionBD.setDBConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setInt(1, id);
			rslt = stmt.executeQuery();
			while(rslt.next()) {
				BarberoGet.add(new RecuperarBarberoInterno(
						rslt.getInt(1),
						rslt.getString(2), 
						rslt.getString(3), 
						rslt.getString(4), 
						rslt.getString(5),
						rslt.getString(6),
						rslt.getString(7)
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			if(conn != null ) {
				
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
		return BarberoGet;
	}

	public List<MensajesBeans> addBarbero(InsertarBarbero ins, int idUsuario) {

		String SQLQuery = "{call `sp.Insertar_Barbero`(?,?,?,?,?,?,?)}";
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		try {
			conn = ConexionBD.setDBConnection();
			clbl = conn.prepareCall(SQLQuery);
			clbl.setString(1, ins.getAnombre());
			clbl.setString(2, ins.getBapellido());
			clbl.setString(3, ins.getCtelefono());
			clbl.setString(4, ins.getDdni());
			clbl.setString(5, ins.getEcorreo());
			clbl.setString(6, ins.getFdireccion());
			clbl.setInt(7, idUsuario);
			rslt = clbl.executeQuery();
            rslt.beforeFirst();
			while(rslt.next()) {
				mensaje.add( new MensajesBeans(rslt.getString(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			if(conn != null ) {
				
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
		return mensaje;
		
	}
	
	public List<MensajesBeans> updtBarbero(int idBarbero, RecuperarBarberoInterno ins, int idUser) {

		String SQLQuery = "{call `sp.Actualizar_Barbero`(?,?,?,?,?,?,?,?,?)}";
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		try {
			conn = ConexionBD.setDBConnection();
			stmt = conn.prepareCall(SQLQuery);
			stmt.setInt(1, idBarbero);
			stmt.setInt(2, ins.getAidEstado());
			stmt.setString(3, ins.getbNombre());
			stmt.setString(4, ins.getcApellido());
			stmt.setString(5, ins.getdTelefono());
			stmt.setString(6, ins.geteDni());
			stmt.setString(7, ins.getfEmail());
			stmt.setString(8, ins.getgDireccion());
			stmt.setInt(9, idUser);
			rslt = stmt.executeQuery();
            rslt.beforeFirst();
			while(rslt.next()) {
				mensaje.add(new MensajesBeans(rslt.getString(1)));			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			if(conn != null ) {
				
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
		return mensaje;
		
	}
	
	public List<MensajesBeans> delBarbero(int idBarbero) {

		String SQLQuery = "{call `sp.Eliminar_Barbero`(?)}";
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		try {
			conn = ConexionBD.setDBConnection();
			stmt = conn.prepareCall(SQLQuery);
			stmt.setInt(1, idBarbero);
			rslt = stmt.executeQuery();
            rslt.beforeFirst();
			while(rslt.next()) {
				mensaje.add(new MensajesBeans(rslt.getString(1)));			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			if(conn != null ) {
				
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
		return mensaje;
		
	}

}
