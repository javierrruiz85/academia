package academia.modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import academia.modelo.ConnectionManager;
import academia.modelo.dao.CursoDAO;
import academia.modelo.pojo.Curso;
import academia.modelo.pojo.Usuario;

public class CursoDAOImpl implements CursoDAO {
	
	// Inicio singleton
	
			public static CursoDAOImpl INSTANCE = null;
			private final static Logger LOG = Logger.getLogger(CursoDAOImpl.class);
			
			private CursoDAOImpl() {
				super();
			}
			
			public static synchronized CursoDAOImpl getInstance() {
				
				if (INSTANCE == null) {
					INSTANCE = new CursoDAOImpl();
				}
				
				return INSTANCE;
				
			}
			
			// Fin Singleton
	
	
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
	
	private final String SQL_DELETE = " DELETE FROM cursos WHERE id = ?; ";
	private final String SQL_CREAR = "INSERT INTO cursos (nombre, identificador, horas, idProfesor) VALUES (?, ?, ?, ?);";
	
	
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
	
//////////////////////////////////////////////////////// SQL_DELETE /////////////////////////////////////////////////////	

	@Override
	public void delete(int id) throws Exception {
			
		// obtener el producto antes de eliminar
		
		try (
				Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_DELETE);
		) {
			
			pst.setInt(1, id);
			int affectedRows = pst.executeUpdate();
			
			if (affectedRows != 1) {
				throw new Exception("No se pudo eliminar el curso con ID " + id);
			} // if
			
		} //try
		
		// no existe un return por que es un void
		
	} // delete
	
	
//////////////////////////////////////////////////////// SQL_CREAR /////////////////////////////////////////////////////
	
	@Override
	public Curso crear(Curso pojo) throws Exception {
		
		try (
				Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_CREAR, PreparedStatement.RETURN_GENERATED_KEYS);
				
			) {
			
			pst.setString(1, pojo.getNombre());
			pst.setString(2, pojo.getIdentificador());
			pst.setInt(3, pojo.getHoras());
			pst.setInt(4, pojo.getProfesor().getId());
			
			
			LOG.debug(pst);
			
			int affectedRows = pst.executeUpdate();
			
			if (affectedRows == 1) {
				
				try ( ResultSet rsKeys = pst.getGeneratedKeys() ){
					
					if ( rsKeys.next() ) {
						pojo.setId(rsKeys.getInt(1));
					} // if
				}//try2
				
			} else {
				
				// en caso de que la Exception sea que ya esta repetida, en este caso, la pelicula, no muestra esta Exception, sino que va al controller y muestra las que tiene ahi
				throw new Exception ("No se ha podido guardar el curso " + pojo);
			} // if-else
						
		}//try 
		
		return pojo;
	}
	

	
	
	
	

	
	
	
	

}