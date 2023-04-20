package com.formulario_completo_con_consultas.form.servicios;

import java.util.List;

import com.formulario_completo_con_consultas.form.modelo.Empleado;

public interface EmpleadoService {
	
	public Empleado add(Empleado e);
	public List<Empleado> findAll();
	public Empleado findById(long id);
	public Empleado edit(Empleado e);

}
