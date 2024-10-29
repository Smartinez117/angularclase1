package resfullEstudiante.restFullEstudiante.servicios;

import java.util.List;

import resfullEstudiante.restFullEstudiante.modelos.Estudiante;

public interface IEstudianteSevicio {
	public List<Estudiante> obtenertodo();
	
	public Estudiante guardar(Estudiante estudiante);
	
	public Estudiante obtenerPorId(long legajo);
	
	public void eliminar(long legajo);
}
