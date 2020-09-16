package academia.modelo.dao;

import java.util.ArrayList;

import academia.modelo.pojo.Curso;

public interface CursoDAO {
	
	ArrayList<Curso> listar();
	
	ArrayList<Curso> listarPorId(int id);

}