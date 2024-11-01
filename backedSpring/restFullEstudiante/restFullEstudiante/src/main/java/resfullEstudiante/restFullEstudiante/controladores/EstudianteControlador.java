package resfullEstudiante.restFullEstudiante.controladores;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import resfullEstudiante.restFullEstudiante.modelos.Estudiante;
import resfullEstudiante.restFullEstudiante.servicios.EstudianteServicioImpl;



@RestController
@RequestMapping("/api/v1") 
public class EstudianteControlador {

	@Autowired
	EstudianteServicioImpl estudianteServicio;
	@GetMapping("/")
	public String home() {
		return "Hola Mundo";
	}
	@GetMapping("/estudiantes")
	public List<Estudiante> obtenerEstudiantes(){
		return estudianteServicio.obtenertodo();
	}

	@PostMapping("/guardar")
	public ResponseEntity<Estudiante> guardarEstudiante(@RequestBody Estudiante estudiante){
		Estudiante nuevo_estudiante = estudianteServicio.guardar(estudiante);
		return new ResponseEntity<>(nuevo_estudiante, HttpStatus.CREATED); 
	}

	@GetMapping("/estudiantes/{legajo}")
	public ResponseEntity<Estudiante> obtenerEstudiantePorLegajo(@PathVariable long legajo){
		Estudiante estudiantePorLegajo = estudianteServicio.obtenerPorId(legajo);
		return ResponseEntity.ok(estudiantePorLegajo);
	}

	@PutMapping("/estudiantes/{legajo}")
	public ResponseEntity<Estudiante> actualizar(@PathVariable long legajo, @RequestBody Estudiante estudiante ){
		Estudiante estudiantePorLegajo = estudianteServicio.obtenerPorId(legajo); 
		if (estudiantePorLegajo == null) {
			return ResponseEntity.notFound().build();
		}
		
		estudiantePorLegajo.setNombre(estudiante.getNombre());
		estudiantePorLegajo.setDNI(estudiante.getDNI());
		estudiantePorLegajo.setFecha_nacimiento(estudiante.getFecha_nacimiento());
		estudiantePorLegajo.setFecha_ingreso(estudiante.getFecha_ingreso());
		estudiantePorLegajo.setDireccion(estudiante.getDireccion());
		
		Estudiante estudiante_actualizado = estudianteServicio.guardar(estudiantePorLegajo);
		return new ResponseEntity<>(estudiante_actualizado, HttpStatus.OK);
	}

	@DeleteMapping("/estudiantes/{legajo}")
	public ResponseEntity<HashMap<String, Boolean>> eliminarEstudiante(@PathVariable long legajo) {
		this.estudianteServicio.eliminar(legajo);
		
		HashMap<String, Boolean> estadoEstudiateEliminado = new HashMap<>();
		estadoEstudiateEliminado.put("eliminado", true);
		return ResponseEntity.ok(estadoEstudiateEliminado);
	}
}    