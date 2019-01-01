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
public class MarcaProductoModel {
	
	private ConexionBD dbCon;
	private Connection conn;
	private ResultSet rslt;
	private PreparedStatement stmt;
	private CallableStatement clbl;
	
	private List<MensajesBeans> mensaje = new ArrayList<MensajesBeans>();
	private List<Aidis> aidi = new ArrayList<Aidis>();
	private List<MessagenID> mesgid = new ArrayList<MessagenID>();
	
	private List<ListarMarca> MarcaProductoR = new ArrayList<ListarMarca>();
	private List<RecuperarMarcaInterno> MarcaProductoGet = new ArrayList<RecuperarMarcaInterno>();

	private static MarcaProductoModel stdregd = null;
	
	public static MarcaProductoModel getInstance() {
		
		if(stdregd == null) {
			stdregd = new MarcaProductoModel();
			return stdregd;
		}
		else {
			return stdregd;
		}
	}
	
	public List<ListarMarca> getMarcaProductoR() {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "call `sp.Listar_Marca_Interno`";
		
		try {
			conn = ConexionBD.setDBConnection();
			rslt = dbCon.getResultSet(SQLQuery, conn);
			while(rslt.next()) {
				MarcaProductoR.add(new ListarMarca(
						rslt.getInt(1), 
						rslt.getString(2), 
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
		
		return MarcaProductoR;
	}
	
	public List<RecuperarMarcaInterno> getMarcaProducto(int id) {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "{call `sp.Recuperar_Marca_Interno`(?)}";
		
		try {
			conn = ConexionBD.setDBConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setInt(1, id);
			rslt = stmt.executeQuery();
			while(rslt.next()) {
				MarcaProductoGet.add(new RecuperarMarcaInterno(
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
		
		return MarcaProductoGet;
	}
		
		public List<MensajesBeans> addMarcaProducto(String ins, int idUsuario) {

			String SQLQuery = "{call `sp.Insertar_Marca_Producto`(?)}";
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
		
		public List<MensajesBeans> updtMarcaProducto(int idMarcaProducto, RecuperarMarcaInterno ins) {

			String SQLQuery = "{call `sp.Actualizar_Marca_Producto`(?,?,?)}";
			try {
				dbCon = new ConexionBD();
			} catch (SQLException e) {
				e.printStackTrace();			
			}
			try {
				conn = ConexionBD.setDBConnection();
				stmt = conn.prepareCall(SQLQuery);
				stmt.setInt(1, idMarcaProducto);
				stmt.setString(2, ins.getCmarca());
				stmt.setInt(3, ins.getDidEstado());
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
		
		public List<MensajesBeans> delMarcaProducto(int idMarcaProducto) {

			String SQLQuery = "{call `sp.Eliminar_MarcaProducto`(?)}";
			try {
				dbCon = new ConexionBD();
			} catch (SQLException e) {
				e.printStackTrace();			
			}
			try {
				conn = ConexionBD.setDBConnection();
				stmt = conn.prepareCall(SQLQuery);
				stmt.setInt(1, idMarcaProducto);
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
