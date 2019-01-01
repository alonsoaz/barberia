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
public class ProductoModel {
	
	private ConexionBD dbCon;
	private Connection conn;
	private ResultSet rslt;
	private PreparedStatement stmt;
	private CallableStatement clbl;
	
	private List<MensajesBeans> mensaje = new ArrayList<MensajesBeans>();
	private List<Aidis> aidi = new ArrayList<Aidis>();
	private List<MessagenID> mesgid = new ArrayList<MessagenID>();
	
	private List<ListarProductoInterno> ProductoRecords = new ArrayList<ListarProductoInterno>();
	private List<ListarProducto> ProductoR = new ArrayList<ListarProducto>();
	private List<BuscaProductoInterno> ProductoSearch = new ArrayList<BuscaProductoInterno>();
	private List<BuscarProducto> ProductoS = new ArrayList<BuscarProducto>();
	private List<RecuperarProductoInterno> ProductoGet = new ArrayList<RecuperarProductoInterno>();
	
	private static ProductoModel stdregd = null;
	
	public static ProductoModel getInstance() {
		
		if(stdregd == null) {
			stdregd = new ProductoModel();
			return stdregd;
		}
		else {
			return stdregd;
		}
	}
	
	public List<ListarProductoInterno> getProductoRecords() {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "call `sp.Listar_Producto_Interno`";
		
		try {
			conn = ConexionBD.setDBConnection();			
			clbl = conn.prepareCall(SQLQuery);
			rslt = clbl.executeQuery();			
			while(rslt.next()) {
				ProductoRecords.add(new ListarProductoInterno(
						rslt.getInt(1), 
						rslt.getString(2), 
						rslt.getString(3),
						rslt.getString(4),
						rslt.getString(5),
						rslt.getString(6), 
						rslt.getInt(7),
						rslt.getDouble(8),
						rslt.getString(9),
						rslt.getString(10),
						rslt.getString(11)
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
		
		return ProductoRecords;
	}
	
	public List<ListarProducto> getProductoR() {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "call `sp.Listar_Producto`";
		
		try {
			conn = ConexionBD.setDBConnection();
			rslt = dbCon.getResultSet(SQLQuery, conn);
			while(rslt.next()) {
				ProductoR.add(new ListarProducto(
						rslt.getInt(1), 
						rslt.getString(2), 
						rslt.getString(3), 
						rslt.getString(4), 
						rslt.getString(5), 
						rslt.getInt(6), 
						rslt.getDouble(7),
						rslt.getString(8)
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
		
		return ProductoR;
	}
	
	public List<BuscaProductoInterno> getProductoByWords(String words) {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "{call `sp.Buscar_Producto_Interno`(?,?)}";
		
		try {
			conn = ConexionBD.setDBConnection();
			clbl = conn.prepareCall(SQLQuery);
			clbl.setString(1, words);
			clbl.registerOutParameter(2,Types.INTEGER);
			rslt = clbl.executeQuery();
			while(rslt.next()) {
				ProductoSearch.add(new BuscaProductoInterno(
						rslt.getInt(1), 
						rslt.getString(2), 
						rslt.getString(3),
						rslt.getString(4),
						rslt.getString(5),
						rslt.getString(6), 
						rslt.getInt(7),
						rslt.getDouble(8),
						rslt.getString(9),
						rslt.getString(10),
						rslt.getString(11),
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
		
		return ProductoSearch;
	}
	
	public List<BuscarProducto> getProdByWords(String words) {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "{call `sp.Buscar_Producto`(?,?)}";
		
		try {
			conn = ConexionBD.setDBConnection();
			clbl = conn.prepareCall(SQLQuery);
			clbl.setString(1, words);
			clbl.registerOutParameter(2,Types.INTEGER);
			rslt = clbl.executeQuery();
			while(rslt.next()) {
				ProductoS.add(new BuscarProducto(
						rslt.getInt(1), 
						rslt.getString(2), 
						rslt.getString(3), 
						rslt.getString(4), 
						rslt.getString(5), 
						rslt.getInt(6), 
						rslt.getDouble(7),
						rslt.getString(8),
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
		
		return ProductoS;
	}

	public List<RecuperarProductoInterno> getProducto(int id) {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "{call `sp.Recuperar_Producto_Interno`(?)}";
		
		try {
			conn = ConexionBD.setDBConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setInt(1, id);		
			rslt = stmt.executeQuery();
			while(rslt.next()) {
				ProductoGet.add(new RecuperarProductoInterno(
						rslt.getInt(1), 
						rslt.getInt(2),						
						rslt.getString(3), 
						rslt.getInt(4), 
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
		
		return ProductoGet;
	}

	public List<RecuperarProductoInterno> getProd(int id) {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "{call `sp.Recuperar_Producto_Interno`(?)}";
		
		try {
			conn = ConexionBD.setDBConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setInt(1, id);
			rslt = stmt.executeQuery();
			while(rslt.next()) {
				ProductoGet.add(new RecuperarProductoInterno(
						rslt.getInt(1), 
						rslt.getInt(2),						
						rslt.getString(3), 
						rslt.getInt(4), 
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
		
		return ProductoGet;
	}
	
	public List<MensajesBeans> addProducto(InsertarProducto ins, int idUsuario, int idMarca, int idTipo) {

		String SQLQuery = "{call `sp.Insertar_Producto`(?,?,?,?,?,?,?)}";
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		try {
			conn = ConexionBD.setDBConnection();
			clbl = conn.prepareCall(SQLQuery);
			clbl.setString(1, ins.getAnombre());
			clbl.setInt(2, idMarca);
			clbl.setInt(3, idTipo);
			clbl.setInt(4, ins.getdStock());
			clbl.setDouble(5, ins.getePrecio());
			clbl.setString(6, ins.getFdescripcion());
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
	
	public List<MensajesBeans> updtProducto(int idProducto, RecuperarProductoInterno ins, int idUser) {

		String SQLQuery = "{call `sp.Actualizar_Producto`(?,?,?,?,?,?,?,?,?)}";
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		try {
			conn = ConexionBD.setDBConnection();
			stmt = conn.prepareCall(SQLQuery);
			stmt.setInt(1, idProducto);
			stmt.setInt(2, ins.getaIdMarca());
			stmt.setInt(3, ins.getBidTipo());
			stmt.setString(4, ins.getcNombre());
			stmt.setInt(5, ins.getDidEstado());
			stmt.setInt(6, ins.getfStock());
			stmt.setDouble(7, ins.getgPrecio());
			stmt.setString(8, ins.getHdescripcion());
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
	
	public List<MensajesBeans> delProducto(int idProducto) {

		String SQLQuery = "{call `sp.Eliminar_Producto`(?)}";
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		try {
			conn = ConexionBD.setDBConnection();
			stmt = conn.prepareCall(SQLQuery);
			stmt.setInt(1, idProducto);
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
