package com.pruebaTP.prueba_restfull.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebaTP.prueba_restfull.modelos.Cliente;
import com.pruebaTP.prueba_restfull.repositorios.ClienteRepositorio;

@Service
public class ClienteServicioImpl implements IClienteServicio {
	
	@Autowired
	ClienteRepositorio clienterepositorio;

	@Override
	public List<Cliente> obtenertodo() {
		return clienterepositorio.findAll();
	}

	@Override
	public Cliente guardar(Cliente cliente) {
		// TODO Auto-generated method stub
		return clienterepositorio.save(cliente);
	}

	@Override
	public Cliente obtenerPorId(long id) {
		// TODO Auto-generated method stub
		return clienterepositorio.findById(id).orElse(null);
	}

	@Override
	public void eliminar(long id) {
		// TODO Auto-generated method stub
		clienterepositorio.deleteById(id);	
	}

}
