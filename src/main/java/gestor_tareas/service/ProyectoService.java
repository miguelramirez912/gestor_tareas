package gestor_tareas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gestor_tareas.dto.ProyectoDto;
import gestor_tareas.entity.Proyecto;
import gestor_tareas.repository.IProyectoRepository;


@Service
public class ProyectoService {

	@Autowired
	private IProyectoRepository proyectoRepository;
	
	//Consultar proyectos
	@Transactional(readOnly = true)
	public List<Proyecto> consultarProyectos(){
		return this.proyectoRepository.findAll();
	}
	//Consultar proyecto por id
	@Transactional(readOnly = true)
	public Proyecto consultarProyectoPorId(Long id) {
		return (Proyecto)this.proyectoRepository.findById(id).orElse(null);
	}
	//Crear proyecto
	@Transactional
	public Proyecto crearProyecto(ProyectoDto proyectoDto) {
		Proyecto proyectoACrear = new Proyecto();
		proyectoACrear.setNombreProyecto(proyectoDto.getNombreProyecto());
		return this.proyectoRepository.save(proyectoACrear);
	}
	//Actualizar proyecto
	public Proyecto actualizarProyecto(Long id, ProyectoDto proyectoDto) {
		Proyecto proyectoAActualizar = new Proyecto();
		proyectoAActualizar.setId(id);
		proyectoAActualizar.setNombreProyecto(proyectoDto.getNombreProyecto());
		return this.proyectoRepository.save(proyectoAActualizar);
	}
	//Eliminar
	public void eliminarProyecto(Long id) {
		this.proyectoRepository.deleteById(id);
	}
	
}
