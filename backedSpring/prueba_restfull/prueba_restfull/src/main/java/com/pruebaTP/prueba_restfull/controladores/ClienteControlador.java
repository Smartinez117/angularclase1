package com.pruebaTP.prueba_restfull.controladores;

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

import com.pruebaTP.prueba_restfull.modelos.Cliente;
import com.pruebaTP.prueba_restfull.servicios.ClienteServicioImpl;

@RestController
@RequestMapping("/api/v1") 
public class ClienteControlador {
	
	@Autowired
	ClienteServicioImpl clienteservicio;
	
	@GetMapping("/clientes")
	public List<Cliente> obtenerCliente(){
		return clienteservicio.obtenertodo();
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<Cliente> guardarCliente(@RequestBody Cliente cliente){
		Cliente nuevo_cliente = clienteservicio.guardar(cliente);
		return new ResponseEntity<>(nuevo_cliente, HttpStatus.CREATED); 
	}
	
	@GetMapping("/cliente/{id}")
	public ResponseEntity<Cliente> obtenerClienteId(@PathVariable long id){
		Cliente clientePorId = clienteservicio.obtenerPorId(id);
		return ResponseEntity.ok(clientePorId);
	}
	
	@PutMapping("/cliente/{id}")
	public ResponseEntity<Cliente> actualizar(@PathVariable long id,@RequestBody Cliente cliente ){
		Cliente clientePorId = clienteservicio.obtenerPorId(id); 
		clientePorId.setNombre(cliente.getNombre());
		clientePorId.setApellido(cliente.getApellido());
		clientePorId.setEmail(cliente.getEmail());
		
		Cliente cliente_actualizado = clienteservicio.guardar(clientePorId);
		return new ResponseEntity<>(cliente_actualizado,HttpStatus.CREATED );
	}
	
	@DeleteMapping("/cliente/{id}")
	public ResponseEntity<HashMap<String, Boolean>> eliminarCliente(@PathVariable long id) {
		this.clienteservicio.eliminar(id);
		
		HashMap<String, Boolean> estadoClienteEliminado = new HashMap<>();
		estadoClienteEliminado.put("eliminado", true);
		return ResponseEntity.ok(estadoClienteEliminado);
	}

}
