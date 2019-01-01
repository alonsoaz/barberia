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
public class NegocioInternoModel {

	private ConexionBD dbCon;
	private Connection conn;
	private ResultSet rslt;
	private PreparedStatement stmt;
	private CallableStatement clbl;
	
	private List<MensajesBeans> mensaje = new ArrayList<MensajesBeans>();
	private List<Aidis> aidi = new ArrayList<Aidis>();
	private List<MessagenID> mesgid = new ArrayList<MessagenID>();
	
	private List<MostrarVentaInterno> ShowVenta = new ArrayList<MostrarVentaInterno>();
	private List<MostrarDetalleVentaInterno> ShowDetVenta = new ArrayList<MostrarDetalleVentaInterno>();
	private List<ListarReservasPorClienteInterno> ShowResCliente = new ArrayList<ListarReservasPorClienteInterno>();
	private List<ListarReservasPorBarberoInterno> ShowResBarbero = new ArrayList<ListarReservasPorBarberoInterno>();
	
	private static NegocioInternoModel stdregd = null;
	
	public static NegocioInternoModel getInstance() {
		
		if(stdregd == null) {
			stdregd = new NegocioInternoModel();
			return stdregd;
		}
		else {
			return stdregd;
		}
	}
	
	public List<MostrarVentaInterno> getVenta() {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "call `sp.Mostrar_Venta_Interno`";
		
		try {
			conn = ConexionBD.setDBConnection();			
			clbl = conn.prepareCall(SQLQuery);
			rslt = clbl.executeQuery();			
			while(rslt.next()) {
				ShowVenta.add(new MostrarVentaInterno(
						rslt.getInt(1), 
						rslt.getString(2), 
						rslt.getString(3),
						rslt.getString(4),
						rslt.getString(5),
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
		
		return ShowVenta;
	}
	
	public List<MostrarDetalleVentaInterno> getDetVenta(int idVenta) {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "call `sp.Mostrar_Detalle_Venta_Interno`(?)";
		
		try {
			conn = ConexionBD.setDBConnection();			
			clbl = conn.prepareCall(SQLQuery);
			clbl.setInt(1, idVenta);	
			rslt = clbl.executeQuery();			
			while(rslt.next()) {
				ShowDetVenta.add(new MostrarDetalleVentaInterno(
						rslt.getInt(1),
						rslt.getInt(2),
						rslt.getString(3), 
						rslt.getString(4),
						rslt.getString(5),
						rslt.getString(6),
						rslt.getString(7), 
						rslt.getString(8),
						rslt.getString(9),
						rslt.getString(10),	
						rslt.getString(11),						
						rslt.getDouble(12),
						rslt.getInt(13),						
						rslt.getDouble(14),						
						rslt.getDouble(15)
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
		
		return ShowDetVenta;
	}
	
	public List<ListarReservasPorClienteInterno> getResCliente(int idCliente) {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "call `sp.Listar_Reservas_Por_Cliente_Interno`(?)";
		
		try {
			conn = ConexionBD.setDBConnection();			
			clbl = conn.prepareCall(SQLQuery);
			clbl.setInt(1, idCliente);			
			rslt = clbl.executeQuery();			
			int res = 0;
			if(rslt.last()) {
				res = rslt.getMetaData().getColumnCount();
				if (res == 1) {
					rslt.beforeFirst();
					while(rslt.next()) {ShowResCliente.add(new ListarReservasPorClienteInterno(rslt.getString(1)));}
				}else {
					rslt.beforeFirst();
					while(rslt.next()) {
						ShowResCliente.add(new ListarReservasPorClienteInterno(
								rslt.getString(1), 
								rslt.getString(2),
								rslt.getString(3),
								rslt.getString(4),
								rslt.getString(5), 
								rslt.getString(6),
								rslt.getString(7)
								));
					}
					
				}
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
		
		return ShowResCliente;
	}
	
	public List<ListarReservasPorBarberoInterno> getResBarbero(int idBarbero) {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "call `sp.Listar_Reservas_Por_Barbero_Interno`(?)";
		
		try {
			conn = ConexionBD.setDBConnection();			
			clbl = conn.prepareCall(SQLQuery);
			clbl.setInt(1, idBarbero);			
			rslt = clbl.executeQuery();
			int res = 0;
			if(rslt.last()) {
				res = rslt.getMetaData().getColumnCount();
				if (res == 1) {
					rslt.beforeFirst();
					while(rslt.next()) {ShowResBarbero.add(new ListarReservasPorBarberoInterno(rslt.getString(1)));}
				}else {
					rslt.beforeFirst();
					while(rslt.next()) {
						ShowResBarbero.add(new ListarReservasPorBarberoInterno(
								rslt.getString(1), 
								rslt.getString(2),
								rslt.getString(3),
								rslt.getString(4),
								rslt.getString(5), 
								rslt.getString(6),
								rslt.getString(7)
								));
					}
					
				}
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
		
		return ShowResBarbero;
	}
	
}
