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
public class UsuarioModel {

	private ConexionBD dbCon;
	private Connection conn;
	private ResultSet rslt;
	private PreparedStatement stmt;
	private CallableStatement clbl;
	
	private List<MensajesBeans> mensaje = new ArrayList<MensajesBeans>();
	private List<Aidis> aidi = new ArrayList<Aidis>();
	private List<MessagenID> mesgid = new ArrayList<MessagenID>();
	
	private List<ListarUsuario> usuarioRecords = new ArrayList<ListarUsuario>();
	private List<BuscarUsuario> usuarioSearch = new ArrayList<BuscarUsuario>();
	private List<RecuperaraUsuario> usuarioGet = new ArrayList<RecuperaraUsuario>();
	
	private static UsuarioModel stdregd = null;
	
	public static UsuarioModel getInstance() {
		
		if(stdregd == null) {
			stdregd = new UsuarioModel();
			return stdregd;
		}
		else {
			return stdregd;
		}
	}
	
	public List<ListarUsuario> getUsuarioRecords() {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "call `sp.Listar_Usuario`";
		
		try {
			conn = ConexionBD.setDBConnection();
			rslt = dbCon.getResultSet(SQLQuery, conn);
			while(rslt.next()) {
				usuarioRecords.add(new ListarUsuario(
						rslt.getString(1), 
						rslt.getString(2), 
						rslt.getString(3), 
						rslt.getString(4), 
						rslt.getString(5),
						rslt.getString(6), 
						rslt.getString(7),
						rslt.getString(8)));
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
		
		return usuarioRecords;
	}
	
	public List<BuscarUsuario> getUsuarioByWords(String words) {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "{call `sp.Buscar_Usuario`(?,?)}";
		
		try {
			conn = ConexionBD.setDBConnection();
			clbl = conn.prepareCall(SQLQuery);
			clbl.setString(1, words);
			clbl.registerOutParameter(2,Types.INTEGER);
			rslt = clbl.executeQuery();
			while(rslt.next()) {
				usuarioSearch.add(new BuscarUsuario(
						rslt.getString(1), 
						rslt.getString(2), 
						rslt.getString(3), 
						rslt.getString(4), 
						rslt.getString(5),
						rslt.getString(6), 
						rslt.getString(7),
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
		
		return usuarioSearch;
	}
	
	public List<RecuperaraUsuario> getUsuario(int id) {
		
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rslt;
		String SQLQuery = "{call `sp.Recuperar_Usuario`(?)}";
		
		try {
			conn = ConexionBD.setDBConnection();
			stmt = conn.prepareStatement(SQLQuery);
			stmt.setInt(1, id);
			rslt = stmt.executeQuery();
			while(rslt.next()) {
				usuarioGet.add(new RecuperaraUsuario(
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
		
		return usuarioGet;
	}
	
	public List<MensajesBeans> addUsuario(InsertarUsuario ins) {

		String SQLQuery = "{call `sp.Insertar_Usuario`(?,?,?,?,?,?,?,?,?,?)}";
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		try {
			conn = ConexionBD.setDBConnection();
			clbl = conn.prepareCall(SQLQuery);
			clbl.setInt(1, ins.getAidUsuario());
			clbl.setString(2, ins.getBnombre());
			clbl.setString(3, ins.getCapellido());
			clbl.setString(4, ins.getDcargo());
			clbl.setString(5, ins.getEaka());
			clbl.setString(6, ins.getFpass());
			clbl.setString(7, ins.getGemail());
			clbl.setString(8, ins.getHtelefono());
			clbl.setString(9, ins.getIdescripcion());
			clbl.registerOutParameter(10,Types.INTEGER);
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
	
	public List<Aidis> addUsuarioLogin(InsertarUsuario ins) {
		
		String SQLQuery = "{call `sp.Insertar_Usuario`(?,?,?,?,?,?,?,?,?,?)}";
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		try {
			conn = ConexionBD.setDBConnection();
			clbl = conn.prepareCall(SQLQuery);
			clbl.setInt(1, ins.getAidUsuario());
			clbl.setString(2, ins.getBnombre());
			clbl.setString(3, ins.getCapellido());
			clbl.setString(4, ins.getDcargo());
			clbl.setString(5, ins.getEaka());
			clbl.setString(6, ins.getFpass());
			clbl.setString(7, ins.getGemail());
			clbl.setString(8, ins.getHtelefono());
			clbl.setString(9, ins.getIdescripcion());
			clbl.registerOutParameter(10,Types.INTEGER);
			rslt = clbl.executeQuery();
            rslt.beforeFirst();
			
				aidi.add( new Aidis(clbl.getInt(10)));
			
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
	
	public List<MessagenID> LogInUser(LoginUsuario ins) {
		
		String SQLQuery = "{call `sp.Login_Usuario`(?,?,?)}";
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		try {
			conn = ConexionBD.setDBConnection();
			clbl = conn.prepareCall(SQLQuery);
			clbl.setString(1, ins.getAaka());
			clbl.setString(2, ins.getBpass());
			clbl.registerOutParameter(3,Types.INTEGER);
			
			rslt = clbl.executeQuery();
			
			int idUsuario = clbl.getInt(3);
			
			aidi.add(new Aidis(idUsuario));

            rslt.beforeFirst();
			while(rslt.next()) {
				mesgid.add(new MessagenID(rslt.getString(1),idUsuario));			
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
	
	public List<MessagenID> changePass(CambiarClave ins, int idUsuario) {

		String SQLQuery = "{call `sp.Cambiar_Clave_Usuario`(?,?,?)}";
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		try {
			conn = ConexionBD.setDBConnection();
			stmt = conn.prepareCall(SQLQuery);
			stmt.setString(2, ins.getApass());
			stmt.setString(3, ins.getbNewPass());
			stmt.setInt(1, idUsuario);
			rslt = stmt.executeQuery();
            rslt.beforeFirst();
			while(rslt.next()) {
				mesgid.add(new MessagenID(rslt.getString(1),idUsuario));			
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
	
	public List<MessagenID> updtUsr(RecuperaraUsuario ins, int idUser) {

		String SQLQuery = "{call `sp.Actualizar_Usuario`(?,?,?,?,?,?,?,?)}";
		try {
			dbCon = new ConexionBD();
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		try {
			conn = ConexionBD.setDBConnection();
			stmt = conn.prepareCall(SQLQuery);
			stmt.setInt(1, idUser);
			stmt.setString(2, ins.getAnombre());
			stmt.setString(3, ins.getCapellido());
			stmt.setString(4, ins.getDcargo());
			stmt.setString(5, ins.getEaka());
			stmt.setString(6, ins.getFcorreo());
			stmt.setString(7, ins.getGtelefono());
			stmt.setString(8, ins.getHdescripcion());
			rslt = stmt.executeQuery();
            rslt.beforeFirst();
			while(rslt.next()) {
				mesgid.add(new MessagenID(rslt.getString(1),idUser));			
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
	
}
