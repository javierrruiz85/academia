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
import academia.modelo.dao.UsuarioDAO;
import academia.modelo.dao.impl.CursoDAOImpl;
import academia.modelo.dao.impl.UsuarioDAOImpl;
import academia.modelo.pojo.Curso;
import academia.modelo.pojo.Usuario;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

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
		
		// LOGIN
		String nombre = request.getParameter("nombre");
		String password = request.getParameter("password");
		
		String mensajeError = "error en el usuario o contraseña";
		
		HttpSession session = request.getSession();
		
		// Buscar usuario y password en la BBDD
		UsuarioDAO dao = UsuarioDAOImpl.getInstance();
		Usuario usuario = dao.buscar(nombre, password);
		
		CursoDAO daoCursos = CursoDAOImpl.getInstance();
		
		
		if (usuario != null) {
			
			if (usuario.getRol() == 2) {
				
				// seteamos en la variable idProfesor el id del profesor que se loguea, lo obtenemos de usuario.getId()
				int idProfesor = usuario.getId();
				// creamos la variable cursos de tipo arraylist, y le seteamos los cursos del profesor logueado arriba
				ArrayList<Curso> cursos = daoCursos.listarPorId(idProfesor);
				// enviamos el arraylist con los cursos del profesor para poder verlos en la jsp (profesor.jsp)
				request.setAttribute("cursos", cursos);
				
				session.setAttribute("usuarioSesion", usuario);
				
				// redirigimos a la jsp
				request.getRequestDispatcher("privado/profesor.jsp").forward(request, response);
				
			} else if (usuario.getRol() == 1) {
				
				request.getRequestDispatcher("privado/alumno.jsp").forward(request, response);
				
			} else {
				
				// TODO mostrar mensaje "error en el usuario o contraseña"
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
			}
			
		} else {
			
			request.setAttribute("mensajeError", mensajeError);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}
		
		
		
		
	}

}
