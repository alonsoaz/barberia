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
public class ServicioModel {
	
	private ConexionBD dbCon;
	private Connection conn;
	private ResultSet rslt;
	private PreparedStatement stmt;
	private CallableStatement clbl;
	
	private List<MensajesBeans> mensaje = new ArrayList<MensajesBeans>();
	private List<Aidis> aidi = new ArrayList<Aidis>();
	private List<MessagenID> mesgid = new ArrayList<MessagenID>();
	
	private List<ListarServicioInterno> ServicioRecords = new ArrayList<ListarServicioInterno>();
	private List<ListarServicio> ServicioR = new ArrayList<ListarServicio>();
	private List<BuscarServicioInterno> ServicioSearch = new ArrayList<BuscarServicioInterno>();
	private List<BuscarServicio> ServicioS = new ArrayList<BuscarServicio>();
	private List<RecuperarServicioInterno> ServicioGet = new ArrayList<RecuperarServicioInterno>();
	
	private static ServicioModel stdregd = null;
	
	public static ServicioModel getInstance() {
		
		if(stdregd == null) {
			stdregd = new ServicioModel();
			return stdregd;
		}
		else {
			return stdregd;
		}
	}
	
	public List<ListarServicioInterno> getServicioRecords() {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "call `sp.Listar_Servicio_Interno`";
		
		try {
			conn = ConexionBD.setDBConnection();
			rslt = dbCon.getResultSet(SQLQuery, conn);
			while(rslt.next()) {
				ServicioRecords.add(new ListarServicioInterno(
						rslt.getInt(1), 
						rslt.getString(2), 
						rslt.getString(3),
						rslt.getString(4),
						rslt.getInt(5),
						rslt.getDouble(6), 
						rslt.getString(7),
						rslt.getString(8),
						rslt.getString(9)
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
		
		return ServicioRecords;
	}
	
	public List<ListarServicio> getServicioR() {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "call `sp.Listar_Servicio`";
		
		try {
			conn = ConexionBD.setDBConnection();
			rslt = dbCon.getResultSet(SQLQuery, conn);
			while(rslt.next()) {
				ServicioR.add(new ListarServicio(
						rslt.getInt(1), 
						rslt.getString(2), 
						rslt.getString(3), 
						rslt.getString(4), 
						rslt.getInt(5),
						rslt.getDouble(6), 
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
		
		return ServicioR;
	}
	
	public List<BuscarServicioInterno> getServicioByWords(String words) {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "{call `sp.Buscar_Servicio_Interno`(?,?)}";
		
		try {
			conn = ConexionBD.setDBConnection();
			clbl = conn.prepareCall(SQLQuery);
			clbl.setString(1, words);
			clbl.registerOutParameter(2,Types.INTEGER);
			rslt = clbl.executeQuery();
			while(rslt.next()) {
				ServicioSearch.add(new BuscarServicioInterno(
						rslt.getInt(1), 
						rslt.getString(2), 
						rslt.getString(3), 
						rslt.getString(4), 
						rslt.getInt(5),
						rslt.getDouble(6), 
						rslt.getString(7),
						rslt.getString(8),
						rslt.getString(9),
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
		
		return ServicioSearch;
	}
	
	public List<BuscarServicio> getServByWords(String words) {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "{call `sp.Buscar_Servicio`(?,?)}";
		
		try {
			conn = ConexionBD.setDBConnection();
			clbl = conn.prepareCall(SQLQuery);
			clbl.setString(1, words);
			clbl.registerOutParameter(2,Types.INTEGER);
			rslt = clbl.executeQuery();
			while(rslt.next()) {
				ServicioS.add(new BuscarServicio(
						rslt.getInt(1), 
						rslt.getString(2), 
						rslt.getString(3),
						rslt.getString(4),
						rslt.getInt(5),
						rslt.getDouble(6), 
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
		
		return ServicioS;
	}

	public List<RecuperarServicioInterno> getServicio(int id) {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "{call `sp.Recuperar_Servicio_Interno`(?)}";
		
		try {
			conn = ConexionBD.setDBConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setInt(1, id);
			rslt = stmt.executeQuery();
			while(rslt.next()) {
				ServicioGet.add(new RecuperarServicioInterno(
						rslt.getString(1), 
						rslt.getInt(2), 
						rslt.getInt(3), 
						rslt.getDouble(4),
						rslt.getString(5)
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
		
		return ServicioGet;
	}

	public List<MensajesBeans> addServicio(InsertarServicio ins, int idUsuario) {

		String SQLQuery = "{call `sp.Insertar_Servicio`(?,?,?,?,?)}";
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		try {
			conn = ConexionBD.setDBConnection();
			clbl = conn.prepareCall(SQLQuery);
			clbl.setString(1, ins.getaNombre());
			clbl.setInt(2, ins.getbDuracion());
			clbl.setDouble(3, ins.getcPrecio());
			clbl.setString(4, ins.getEdescripcion());
			clbl.setInt(5, idUsuario);
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
	
	public List<MensajesBeans> updtServicio(int idServicio, RecuperarServicioInterno ins, int idUser) {

		String SQLQuery = "{call `sp.Actualizar_Servicio`(?,?,?,?,?,?,?)}";
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		try {
			conn = ConexionBD.setDBConnection();
			stmt = conn.prepareCall(SQLQuery);
			stmt.setInt(1, idServicio);
			stmt.setString(2, ins.getBnombser());
			stmt.setInt(3, ins.getCidEstado());
			stmt.setInt(4, ins.getDminutos());
			stmt.setDouble(5, ins.getEprecio());
			stmt.setString(6, ins.getFdescripcion());
			stmt.setInt(7, idUser);
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
	
	public List<MensajesBeans> delServicio(int idServicio) {

		String SQLQuery = "{call `sp.Eliminar_Servicio`(?)}";
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		try {
			conn = ConexionBD.setDBConnection();
			stmt = conn.prepareCall(SQLQuery);
			stmt.setInt(1, idServicio);
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
