package gestor_tareas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gestor_tareas.dto.TareaDto;
import gestor_tareas.entity.Proyecto;
import gestor_tareas.entity.Responsable;
import gestor_tareas.entity.Tarea;
import gestor_tareas.entity.enums.Estado;
import gestor_tareas.entity.enums.Prioridad;
import gestor_tareas.repository.ITareaRepository;

@Service
public class TareaService {
	
	@Autowired
	private ITareaRepository tareaRepository;
	
	//Consulta de todas las tareas
	@Transactional(readOnly = true)
	public List<Tarea> findAll(){
		return(List<Tarea>)tareaRepository.findAll();
	}
	
	//Consulta de una tarea
	@Transactional(readOnly = true)
	public Tarea findById(Long id) {
		return (Tarea)tareaRepository.findById(id).orElseGet(null);
	}
	
	//Crear tarea
	@Transactional
	public Tarea createTarea(TareaDto tareaDto) {
		Tarea nuevaTareaEntity = new Tarea();
		nuevaTareaEntity.setDescripcion(tareaDto.getDescripcion());
		nuevaTareaEntity.setEstado(Enum.valueOf(Estado.class, tareaDto.getEstado()));
		nuevaTareaEntity.setPrioridad(Enum.valueOf(Prioridad.class, tareaDto.getPrioridad()));
		nuevaTareaEntity.setResponsable(new Responsable(tareaDto.getResponsable().getId()));
		nuevaTareaEntity.setProyecto(new Proyecto(tareaDto.getProyecto().getId()));
		return tareaRepository.save(nuevaTareaEntity);
	}
	
	//Eliminar tarea
	public void deleteTarea(Long id) {
		tareaRepository.deleteById(id);
	}
	
	//Actualizar tarea
	public Tarea updateTarea(TareaDto tareaDto, Long id) {
		Tarea tareaEntity = new Tarea();
		tareaEntity.setId(id);
		tareaEntity.setDescripcion(tareaDto.getDescripcion());
		tareaEntity.setEstado(Enum.valueOf(Estado.class, tareaDto.getEstado()));
		tareaEntity.setPrioridad(Enum.valueOf(Prioridad.class, tareaDto.getPrioridad()));
		tareaEntity.setResponsable(new Responsable(tareaDto.getResponsable().getId()));
		tareaEntity.setProyecto(new Proyecto(tareaDto.getProyecto().getId()));
		return tareaRepository.save(tareaEntity);
	}

}
