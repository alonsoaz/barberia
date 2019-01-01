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
public class TipoProductoModel {
	
	private ConexionBD dbCon;
	private Connection conn;
	private ResultSet rslt;
	private PreparedStatement stmt;
	private CallableStatement clbl;
	
	private List<MensajesBeans> mensaje = new ArrayList<MensajesBeans>();
	private List<Aidis> aidi = new ArrayList<Aidis>();
	private List<MessagenID> mesgid = new ArrayList<MessagenID>();
	
	private List<ListarTipoProducto> TipoProductoR = new ArrayList<ListarTipoProducto>();
	private List<RecuperarTipoProducto> TipoProductoGet = new ArrayList<RecuperarTipoProducto>();

	private static TipoProductoModel stdregd = null;
	
	public static TipoProductoModel getInstance() {
		
		if(stdregd == null) {
			stdregd = new TipoProductoModel();
			return stdregd;
		}
		else {
			return stdregd;
		}
	}
	
	public List<ListarTipoProducto> getTipoProductoR() {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "call `sp.Listar_TipoProducto_Interno`";
		
		try {
			conn = ConexionBD.setDBConnection();
			rslt = dbCon.getResultSet(SQLQuery, conn);
			while(rslt.next()) {
				TipoProductoR.add(new ListarTipoProducto(
						rslt.getInt(1), 
						rslt.getString(3), 
						rslt.getString(4)
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
		
		return TipoProductoR;
	}
	
	public List<RecuperarTipoProducto> getTipoProducto(int id) {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "{call `sp.Recuperar_TipoProducto_Interno`(?)}";
		
		try {
			conn = ConexionBD.setDBConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setInt(1, id);
			rslt = stmt.executeQuery();
			while(rslt.next()) {
				TipoProductoGet.add(new RecuperarTipoProducto(
						rslt.getString(1), 
						rslt.getInt(2)
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
		
		return TipoProductoGet;
	}
		
		public List<MensajesBeans> addTipoProducto(String ins, int idUsuario) {

			String SQLQuery = "{call `sp.Insertar_Tipo_Producto`(?)}";
			try {
				dbCon = new ConexionBD();
			} catch (SQLException e) {
				e.printStackTrace();			
			}
			try {
				conn = ConexionBD.setDBConnection();
				clbl = conn.prepareCall(SQLQuery);
				clbl.setString(1, ins);
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
		
		public List<MensajesBeans> updtTipoProducto(int idTipoProducto, RecuperarTipoProducto ins) {

			String SQLQuery = "{call `sp.Actualizar_Tipo_Producto`(?,?,?)}";
			try {
				dbCon = new ConexionBD();
			} catch (SQLException e) {
				e.printStackTrace();			
			}
			try {
				conn = ConexionBD.setDBConnection();
				stmt = conn.prepareCall(SQLQuery);
				stmt.setInt(1, idTipoProducto);
				stmt.setString(2, ins.getbTipo());
				stmt.setInt(3, ins.getCidEstado());
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
		
		public List<MensajesBeans> delTipoProducto(int idTipoProducto) {

			String SQLQuery = "{call `sp.Eliminar_Tipo_Producto`(?)}";
			try {
				dbCon = new ConexionBD();
			} catch (SQLException e) {
				e.printStackTrace();			
			}
			try {
				conn = ConexionBD.setDBConnection();
				stmt = conn.prepareCall(SQLQuery);
				stmt.setInt(1, idTipoProducto);
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
