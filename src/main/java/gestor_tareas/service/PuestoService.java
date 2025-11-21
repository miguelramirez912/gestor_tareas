package gestor_tareas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gestor_tareas.dto.PuestoDto;
import gestor_tareas.entity.Puesto;
import gestor_tareas.repository.IPuestoRepository;

@Service
public class PuestoService {
	
	@Autowired
	private IPuestoRepository puestoRepository;
	
	//Consultar puestos
	@Transactional(readOnly = true)
	public List<Puesto> consultarPuestos(){
		return this.puestoRepository.findAll();
	}
	//Consultar puesto por id
	@Transactional(readOnly = true)
	public Puesto consultarPuestoPorId(Long id) {
		return this.puestoRepository.findById(id).orElse(null);
	}
	//Crear puesto
	public Puesto crearPuesto(PuestoDto puestoDto) {
		Puesto nuevoPuesto = new Puesto();
		nuevoPuesto.setNombrePuesto(puestoDto.getNombrePuesto());
		return this.puestoRepository.save(nuevoPuesto);
	}
	//Actualizar puesto
	public Puesto actualizarPuesto(PuestoDto puestoDto, Long id) {
		Puesto puestoAEditar = new Puesto();
		puestoAEditar.setId(id);
		puestoAEditar.setNombrePuesto(puestoDto.getNombrePuesto());
		return this.puestoRepository.save(puestoAEditar);
	}
	//Eliminar puesto
	public void eliminarPuesto(Long id) {
		this.puestoRepository.deleteById(id);
	}

}
