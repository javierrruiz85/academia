package academia.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;

import academia.modelo.dao.impl.CursoDAOImpl;
import academia.modelo.pojo.Curso;
import academia.modelo.pojo.Usuario;


/**
 * Servlet implementation class CursoCrearController
 */
@WebServlet("/curso_crear")
public class CursoCrearController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final CursoDAOImpl daoCurso = CursoDAOImpl.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CursoCrearController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Declaramos variables
		Curso curso = new Curso();
		int idProfesor = 0;
		String crearOk = "Curso creado con exito";
		String crearError = "No se ha podido crear el curso";
		
		HttpSession session = request.getSession();
		
		// llamar al modelo
		CursoDAOImpl dao = CursoDAOImpl.getInstance();
		
		// recoger parametros del formulario
		String nombre = request.getParameter("nombre");
		String identificador = request.getParameter("identificador");
		String parametroHoras = request.getParameter("horas");

		
		try {
			
			// parsear los parametros
			int horas = Integer.parseInt(parametroHoras);
			
			// recoger el usuario de la sesion y obtener su id
			Usuario profesor = (Usuario)session.getAttribute("usuarioSesion");
			idProfesor = profesor.getId();
			
			// crear el objeto con los parametros recogidos anteriormente
			curso.setNombre(nombre);
			curso.setIdentificador(identificador);
			curso.setHoras(horas);
			
			// recuperar el usuario de la sesion y setearlo a la pelicula
			curso.setProfesor(profesor);
			
			dao.crear(curso);
			
	
				
		} catch (Exception e) {
			
			request.setAttribute("mensajeError", crearError);
			e.printStackTrace();
			
		} finally {
			
			request.setAttribute("mensajeOk", crearOk);
			// enviar datos a la vista
			ArrayList<Curso> cursos = dao.listarPorId(idProfesor);
			// enviamos el arraylist con los cursos del profesor para poder verlos en la jsp (profesor.jsp)
			request.setAttribute("cursos", cursos);
			// ir a la nueva vista o jsp
			request.getRequestDispatcher("privado/profesor.jsp").forward(request, response);
			
		} // try-catch-finally
		
		
	}

}
