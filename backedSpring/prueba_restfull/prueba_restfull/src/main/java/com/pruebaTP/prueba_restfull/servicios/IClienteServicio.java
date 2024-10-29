package com.pruebaTP.prueba_restfull.servicios;

import java.util.List;

import com.pruebaTP.prueba_restfull.modelos.Cliente;

public interface IClienteServicio {
	
	public List<Cliente> obtenertodo();
	
	public Cliente guardar(Cliente cliente);
	
	public Cliente obtenerPorId(long id);
	
	public void eliminar(long id);
}
