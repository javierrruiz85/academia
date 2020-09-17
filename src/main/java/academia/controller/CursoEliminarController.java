package academia.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academia.modelo.dao.CursoDAO;
import academia.modelo.dao.impl.CursoDAOImpl;
import academia.modelo.pojo.Curso;
import academia.modelo.pojo.Usuario;


/**
 * Servlet implementation class CursoEliminarController
 */
@WebServlet("/curso_eliminar")
public class CursoEliminarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		HttpSession session = request.getSession();
		
		String mensajeOk = "Curso eliminado con exito";
		String mensajeError = "No se ha podido eliminar el curso";
		
		// recoger parametro
		String parametroId = request.getParameter("id");
		int idCurso = Integer.parseInt(parametroId);
		
		// llamar al modelo
		CursoDAOImpl dao = CursoDAOImpl.getInstance();
		
		try {
			
			dao.delete(idCurso);
			request.setAttribute("mensajeOk", mensajeOk);
			
		} catch (Exception e) {
			
			request.setAttribute("mensajeError", mensajeError);
			e.printStackTrace();
			
		} // try-catch
		
		
		Usuario profesor = (Usuario)session.getAttribute("usuarioSesion");
		int idProfesor = profesor.getId();
		
		
		// enviar datos a la vista
		ArrayList<Curso> cursos = dao.listarPorId(idProfesor);
		// enviamos el arraylist con los cursos del profesor para poder verlos en la jsp (profesor.jsp)
		request.setAttribute("cursos", cursos);
		
		
		
		
		
		// ir a la nueva vista o jsp
		request.getRequestDispatcher("privado/profesor.jsp").forward(request, response);
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
