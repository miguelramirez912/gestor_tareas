package gestor_tareas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gestor_tareas.dto.ResponsableDto;
import gestor_tareas.entity.Responsable;
import gestor_tareas.repository.IResponsableRepository;


@Service
public class ResponsableService {
	
	@Autowired
	private IResponsableRepository responsableRespository;
	
	//Consultar responsables
	@Transactional(readOnly = true)
	private List<Responsable> consultarResponsables(){
		return (List<Responsable>)this.responsableRespository.findAll();
	}
	//Consultar responsable por id
	@Transactional(readOnly = true)
	private Responsable consultarResponsablePorId(Long id) {
		return (Responsable)this.responsableRespository.findById(id).orElseGet(null);
	}
	//Crear responsable
	@Transactional
	private Responsable crearResponsable(ResponsableDto responsableDto) {
		Responsable nuevoResponsable = new Responsable();
		nuevoResponsable.setNombre(responsableDto.getNombre());
		return this.responsableRespository.save(nuevoResponsable);
	}
	//Actualizar responsable
	//Eliminar responsable

}
