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
public class ClienteModel {
	
	private ConexionBD dbCon;
	private Connection conn;
	private ResultSet rslt;
	private PreparedStatement stmt;
	private CallableStatement clbl;
	
	private List<MensajesBeans> mensaje = new ArrayList<MensajesBeans>();
	private List<Aidis> aidi = new ArrayList<Aidis>();
	private List<MessagenID> mesgid = new ArrayList<MessagenID>();
	
	private List<ListarClienteInterno> clienteRecords = new ArrayList<ListarClienteInterno>();
	private List<BuscarClienteInterno> clienteSearch = new ArrayList<BuscarClienteInterno>();
	private List<RecuperarClienteInterno> clienteGet = new ArrayList<RecuperarClienteInterno>();
	
	private static ClienteModel stdregd = null;
	
	public static ClienteModel getInstance() {
		
		if(stdregd == null) {
			stdregd = new ClienteModel();
			return stdregd;
		}
		else {
			return stdregd;
		}
	}

	public List<ListarClienteInterno> getClienteRecords() {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "call `sp.Listar_Cliente_Interno`";
		
		try {
			conn = ConexionBD.setDBConnection();
			rslt = dbCon.getResultSet(SQLQuery, conn);
			while(rslt.next()) {
				clienteRecords.add(new ListarClienteInterno(
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
		
		return clienteRecords;
	}
	
	public List<BuscarClienteInterno> getClienteByWords(String words) {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "{call `sp.Buscar_Cliente_Interno`(?,?)}";
		
		try {
			conn = ConexionBD.setDBConnection();
			clbl = conn.prepareCall(SQLQuery);
			clbl.setString(1, words);
			clbl.registerOutParameter(2,Types.INTEGER);
			rslt = clbl.executeQuery();
			while(rslt.next()) {
				clienteSearch.add(new BuscarClienteInterno(
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
		
		return clienteSearch;
	}
	
	public List<RecuperarClienteInterno> getCliente(int id) {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "{call `sp.Recuperar_Cliente_Interno`(?)}";
		
		try {
			conn = ConexionBD.setDBConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setInt(1, id);
			rslt = stmt.executeQuery();
			while(rslt.next()) {
				clienteGet.add(new RecuperarClienteInterno(
						rslt.getInt(7),
						rslt.getString(1), 
						rslt.getString(2), 
						rslt.getString(3), 
						rslt.getString(4),
						rslt.getString(5),
						rslt.getString(6)
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
		
		return clienteGet;
	}

	public List<MensajesBeans> addCliente(InsertarCliente ins, int idUsuario) {

		String SQLQuery = "{call `sp.Insertar_Cliente`(?,?,?,?,?,?,?,?)}";
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		try {
			conn = ConexionBD.setDBConnection();
			clbl = conn.prepareCall(SQLQuery);
			clbl.setString(1, ins.getaNombre());
			clbl.setString(2, ins.getbApellido());
			clbl.setString(3, ins.getcTelefono());
			clbl.setString(4, ins.getdDni());
			clbl.setString(5, ins.geteEmail());
			clbl.setString(6, ins.getfDirecion());
			clbl.setInt(7, idUsuario);
			clbl.registerOutParameter(8,Types.INTEGER);
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
	
	public List<Aidis> addClienteLogin(InsertarCliente ins, int idUsuario) {
		
		String SQLQuery = "{call `sp.Insertar_Cliente`(?,?,?,?,?,?,?,?)}";
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		try {
			conn = ConexionBD.setDBConnection();
			clbl = conn.prepareCall(SQLQuery);
			clbl.setString(1, ins.getaNombre());
			clbl.setString(2, ins.getbApellido());
			clbl.setString(3, ins.getcTelefono());
			clbl.setString(4, ins.getdDni());
			clbl.setString(5, ins.geteEmail());
			clbl.setString(6, ins.getfDirecion());
			clbl.setInt(7, idUsuario);
			clbl.registerOutParameter(8,Types.INTEGER);
			rslt = clbl.executeQuery();
            rslt.beforeFirst();
			
				aidi.add( new Aidis(clbl.getInt(8)));
			
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
		
		return aidi;
		
	}
	
	public List<MessagenID> LogInClient(LoginCliente ins) {
		
		String SQLQuery = "{call `sp.Login_Cliente`(?,?,?)}";
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		try {
			conn = ConexionBD.setDBConnection();
			clbl = conn.prepareCall(SQLQuery);
			clbl.setString(1, ins.getaDni());
			clbl.setString(2, ins.getbTelf());
			clbl.registerOutParameter(3,Types.INTEGER);
			
			rslt = clbl.executeQuery();
			
			int idCliente = clbl.getInt(3);
			
			aidi.add(new Aidis(idCliente));

            rslt.beforeFirst();
			while(rslt.next()) {
				mesgid.add(new MessagenID(rslt.getString(1),idCliente));			
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
	
	public List<MensajesBeans> updtClient(int idCliente, RecuperarClienteInterno ins, int idUser) {

		String SQLQuery = "{call `sp.Actualizar_Cliente`(?,?,?,?,?,?,?,?,?)}";
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		try {
			conn = ConexionBD.setDBConnection();
			stmt = conn.prepareCall(SQLQuery);
			stmt.setInt(1, idCliente);
			stmt.setInt(2, ins.getIdEstado());
			stmt.setString(3, ins.getBnombre());
			stmt.setString(4, ins.getCapellido());
			stmt.setString(5, ins.getDtelefono());
			stmt.setString(6, ins.geteDni());
			stmt.setString(7, ins.getFcorreo());
			stmt.setString(8, ins.getGdireccion());
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
	
	public List<MensajesBeans> delClient(int idCliente) {

		String SQLQuery = "{call `sp.Eliminar_Cliente`(?)}";
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

}
