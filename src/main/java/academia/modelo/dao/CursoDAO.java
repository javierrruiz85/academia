package academia.modelo.dao;

import java.util.ArrayList;

import academia.modelo.pojo.Curso;

public interface CursoDAO {
	
	ArrayList<Curso> listar();
	ArrayList<Curso> listarPorId(int id);
	
	// el metodo no devuelve nada
	void delete(int id) throws Exception;
	
	Curso crear(Curso pojo) throws Exception;

}