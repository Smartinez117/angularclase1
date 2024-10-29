package resfullEstudiante.restFullEstudiante.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import resfullEstudiante.restFullEstudiante.modelos.Estudiante;
import resfullEstudiante.restFullEstudiante.repositorios.EstudianteRepositorio;

@Service
public class EstudianteServicioImpl implements IEstudianteSevicio {
	
	@Autowired
	EstudianteRepositorio estudianteRepositorio;

	@Override
	public List<Estudiante> obtenertodo() {
		// TODO Auto-generated method stub
		return estudianteRepositorio.findAll();
	}

	@Override
	public Estudiante guardar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		return estudianteRepositorio.save(estudiante);
	}

	@Override
	public Estudiante obtenerPorId(long legajo) {
		// TODO Auto-generated method stub
		return estudianteRepositorio.findById(legajo).orElse(null);
	}

	@Override
	public void eliminar(long legajo) {
		estudianteRepositorio.deleteById(legajo); 
		
	}

}
