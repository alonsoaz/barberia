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
public class RegDetVentModel {
	
	private ConexionBD dbCon;
	private Connection conn;
	private ResultSet rslt;
	private PreparedStatement stmt;
	private CallableStatement clbl;
	
	private List<MensajesBeans> mensaje = new ArrayList<MensajesBeans>();
	private List<Aidis> aidi = new ArrayList<Aidis>();
	private List<MessagenID> mesgid = new ArrayList<MessagenID>();
	
	private List<MostrarVentaPorPagar> ShowVxPagar = new ArrayList<MostrarVentaPorPagar>();
	private List<MostrarDetalleVenta> ShowDVxPagar = new ArrayList<MostrarDetalleVenta>();
	private List<RecuperarDetalleVenta> getDetVenta = new ArrayList<RecuperarDetalleVenta>();
	private List<MostrarVentaPagada> ShowVpagada = new ArrayList<MostrarVentaPagada>();
	private List<MostrarDetalleVentaPagada> ShowDVpagada = new ArrayList<MostrarDetalleVentaPagada>();
	
	private static RegDetVentModel stdregd = null;
	
	public static RegDetVentModel getInstance() {
		
		if(stdregd == null) {
			stdregd = new RegDetVentModel();
			return stdregd;
		}
		else {
			return stdregd;
		}
	}
	
	public List<MessagenID> regDetVent(RegistrarDetalleVentaXpAgar ins, int idClient, int idProd) {
		
		String SQLQuery = "{call `sp.Registrar_Detalle_Venta_Por_Pagar`(?,?,?,?)}";
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		try {
			conn = ConexionBD.setDBConnection();
			clbl = conn.prepareCall(SQLQuery);
			clbl.setInt(1, idClient);
			clbl.setInt(2, idProd);
			clbl.setInt(3, ins.getCantidad());
			clbl.registerOutParameter(4,Types.INTEGER);		
			rslt = clbl.executeQuery();
            rslt.beforeFirst();
			while(rslt.next()) {
				mesgid.add( new MessagenID(rslt.getString(1),clbl.getInt(4)));
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
		
		return mesgid;
		
	}
	
	public List<MostrarVentaPorPagar> showVentaXpagar(int idClient) {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "{call `sp.Mostrar_Venta_Por_Pagar`(?,?)}";
		
		try {
			conn = ConexionBD.setDBConnection();
			clbl = conn.prepareCall(SQLQuery);
			clbl.setInt(1, idClient);
			clbl.registerOutParameter(2,Types.INTEGER);			
			rslt = clbl.executeQuery();
			while(rslt.next()) {
				ShowVxPagar.add(new MostrarVentaPorPagar(
						rslt.getString(1), 
						rslt.getString(2),
						rslt.getString(3),
						rslt.getString(4),
						rslt.getDouble(5), 
						rslt.getString(6),
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
		
		return ShowVxPagar;
	}
	
	public List<MensajesBeans> delVenta(int idCliente) {

		String SQLQuery = "{call `sp.Eliminar_Venta`(?)}";
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		try {
			conn = ConexionBD.setDBConnection();
			stmt = conn.prepareCall(SQLQuery);
			stmt.setInt(1, idCliente);
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
	
	public List<MensajesBeans> payVenta(int idCliente) {

		String SQLQuery = "{call `sp.Registrar_Venta_Pagada`(?)}";
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		try {
			conn = ConexionBD.setDBConnection();
			stmt = conn.prepareCall(SQLQuery);
			stmt.setInt(1, idCliente);
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
	
	public List<MostrarDetalleVenta> ShowDVxPagar(int idCliente) {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "{call `sp.Mostrar_Detalle_Venta`(?)}";
		
		try {
			conn = ConexionBD.setDBConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setInt(1, idCliente);		
			rslt = stmt.executeQuery();
			while(rslt.next()) {
				ShowDVxPagar.add(new MostrarDetalleVenta(
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
		
		return ShowDVxPagar;
	}
	
	public List<RecuperarDetalleVenta> getDV(int id) {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "{call `sp.Recuperar_Detalle_Venta`(?)}";
		
		try {
			conn = ConexionBD.setDBConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setInt(1, id);
			rslt = stmt.executeQuery();
			while(rslt.next()) {
				getDetVenta.add(new RecuperarDetalleVenta(
						rslt.getInt(1), 
						rslt.getDouble(2), 
						rslt.getInt(3), 
						rslt.getDouble(4), 
						rslt.getDouble(5)
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
		
		return getDetVenta;
	}

	public List<MensajesBeans> updtDetVent(int idDetVent, RegistrarDetalleVentaXpAgar ins) {

		String SQLQuery = "{call `sp.Actualizar_Detalle_Venta`(?,?)}";
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		try {
			conn = ConexionBD.setDBConnection();
			stmt = conn.prepareCall(SQLQuery);
			stmt.setInt(1, idDetVent);
			stmt.setInt(2, ins.getCantidad());
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
	
	public List<MensajesBeans> delDV(int idDetVent) {

		String SQLQuery = "{call `sp.Eliminar_Detalle_Venta`(?)}";
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		try {
			conn = ConexionBD.setDBConnection();
			stmt = conn.prepareCall(SQLQuery);
			stmt.setInt(1, idDetVent);
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
	
	public List<MensajesBeans> payDetVenta(int idCliente, int idDetVenta) {

		String SQLQuery = "{call `sp.Registrar_Detalle_Venta_Pagada`(?,?)}";
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		try {
			conn = ConexionBD.setDBConnection();
			stmt = conn.prepareCall(SQLQuery);
			stmt.setInt(1, idCliente);
			stmt.setInt(2, idDetVenta);
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
	
	public List<MostrarVentaPagada> showVentaPagada(int idClient) {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "{call `sp.Mostrar_Venta_Pagada`(?)}";
		
		try {
			conn = ConexionBD.setDBConnection();
			clbl = conn.prepareCall(SQLQuery);
			clbl.setInt(1, idClient);
			rslt = clbl.executeQuery();
			while(rslt.next()) {
				ShowVpagada.add(new MostrarVentaPagada(
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
		
		return ShowVpagada;
	}
	
	public List<MostrarDetalleVentaPagada> showDetVentaPagada(int idClient,int idVenta) {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "{call `sp.Mostrar_Detalle_Venta_Pagada`(?,?)}";
		
		try {
			conn = ConexionBD.setDBConnection();
			clbl = conn.prepareCall(SQLQuery);
			clbl.setInt(1, idClient);
			clbl.setInt(2, idVenta);
			rslt = clbl.executeQuery();
			while(rslt.next()) {
				ShowDVpagada.add(new MostrarDetalleVentaPagada(
						rslt.getString(1), 
						rslt.getString(2),
						rslt.getString(3),
						rslt.getString(4),
						rslt.getString(5), 
						rslt.getString(6),
						rslt.getString(7),
						rslt.getString(8),
						rslt.getString(9),						
						rslt.getDouble(10),
						rslt.getInt(11),
						rslt.getDouble(12),
						rslt.getDouble(13)
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
		
		return ShowDVpagada;
	}

}
