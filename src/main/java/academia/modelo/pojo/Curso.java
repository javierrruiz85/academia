package academia.modelo.pojo;

public class Curso {
	
	private int id;
	private String curso;
	private String identificador;
	private int horas;
	private Profesor profesor;
	// TODO
    // private ArrayList<Alumno> alumno
	
	public Curso() {
		super();
		this.id = 0;
		this.curso = "";
		this.identificador = "";
		this.horas = 0;
		this.profesor = new Profesor();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return curso;
	}

	public void setNombre(String nombre) {
		this.curso = nombre;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", nombre=" + curso + ", identificador=" + identificador + ", horas=" + horas
				+ ", profesor=" + profesor + "]";
	}
	
	
}