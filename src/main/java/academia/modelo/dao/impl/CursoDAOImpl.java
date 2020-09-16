package academia.modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import academia.modelo.ConnectionManager;
import academia.modelo.dao.CursoDAO;
import academia.modelo.pojo.Curso;
import academia.modelo.pojo.Usuario;

public class CursoDAOImpl implements CursoDAO {
	
	
	private final static String SQL_LISTAR = "SELECT \n" + 
			"	c.id as 'curso_id', " + 
			"	c.identificador, " + 
			"	c.nombre as 'curso_nombre', " + 
			"	c.horas, " + 
			"	f.id as 'profesor_id', " + 
			"	f.nombre as 'profesor_nombre', " + 
			"	f.apellidos as 'profesor_apellidos', " + 
			"	rol " +												
			" FROM cursos c, usuarios f " + 
			" WHERE " + 
			"	c.idProfesor = f.id;";
	
	private final static String SQL_LISTAR_BY_ID_USUARIO = "SELECT \n" + 
			"	c.id as 'curso_id', " + 
			"	c.identificador, " + 
			"	c.nombre as 'curso_nombre', " + 
			"	c.horas, " + 
			"	f.id as 'profesor_id', " + 
			"	f.nombre as 'profesor_nombre', " + 
			"	f.apellidos as 'profesor_apellidos', " + 
			"	rol " +												
			" FROM cursos c, usuarios f " + 
			" WHERE " + 
			"	c.idProfesor = f.id AND idProfesor = ?;";
	
	
//////////////////////////////////////////////////////// SQL_LISTAR /////////////////////////////////////////////////////
	@Override
	public ArrayList<Curso> listar() {
		
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		
		try( Connection con = ConnectionManager.getConnection() ;
			 PreparedStatement pst = con.prepareStatement(SQL_LISTAR);
			 ResultSet rs = pst.executeQuery()	
			){
			
			while ( rs.next() ) {				
				
				Curso c = new Curso();
				c.setId( rs.getInt("curso_id"));
				c.setNombre( rs.getString("curso_nombre"));
				c.setIdentificador(rs.getString("identificador"));
				c.setHoras(rs.getInt("horas"));
				
				Usuario p = new Usuario();
				p.setId(rs.getInt("profesor_id"));
				p.setNombre(rs.getString("profesor_nombre"));
				p.setApellidos(rs.getString("profesor_apellidos"));
				p.setRol(rs.getInt("rol"));
				
				c.setProfesor(p);
				
				cursos.add(c);
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return cursos;
	}

//////////////////////////////////////////////////////// SQL_LISTAR_BY_ID_USUARIO /////////////////////////////////////////////////////	

	@Override
	public ArrayList<Curso> listarPorId(int id) {

		ArrayList<Curso> cursos = new ArrayList<Curso>();
		
		try( Connection con = ConnectionManager.getConnection() ;
			 PreparedStatement pst = con.prepareStatement(SQL_LISTAR_BY_ID_USUARIO);	
			){
				
			pst.setInt(1, id);
			System.out.println(pst);
			
			try ( ResultSet rs = pst.executeQuery() ) {
				
				while ( rs.next() ) {
					// 
					
					Curso c = new Curso();
					c.setId( rs.getInt("curso_id"));
					c.setNombre( rs.getString("curso_nombre"));
					c.setIdentificador(rs.getString("identificador"));
					c.setHoras(rs.getInt("horas"));
					
					Usuario p = new Usuario();
					p.setId(rs.getInt("profesor_id"));
					p.setNombre(rs.getString("profesor_nombre"));
					p.setApellidos(rs.getString("profesor_apellidos"));
					p.setRol(rs.getInt("rol"));
					
					c.setProfesor(p);
					
					cursos.add(c);
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}// try

		return cursos;
	}
	/*
	public Usuario buscar(String nombre, String password) {
		
		Usuario usuario = null;
		try (	Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_BUSCAR);
				) {
			
			pst.setString(1, nombre);
			pst.setString(2, password);
			
			try ( ResultSet rs = pst.executeQuery() ) {
				if ( rs.next() ) {
					// usuario
					usuario = new Usuario();
					usuario.setId(rs.getInt("id"));
					usuario.setNombre(rs.getString("nombre"));
					usuario.setApellidos(rs.getString("apellidos"));
					usuario.setRol(rs.getInt("rol"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return usuario;
				
	}
	*/

	
	
	
	

	
	
	
	

}