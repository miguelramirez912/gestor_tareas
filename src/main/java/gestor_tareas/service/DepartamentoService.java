package gestor_tareas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gestor_tareas.dto.DepartamentoDto;
import gestor_tareas.entity.Departamento;
import gestor_tareas.repository.IDepartamentoRepository;


@Service
public class DepartamentoService {
	@Autowired
	private IDepartamentoRepository departamentoRepository;
	
	//Consultar departamentos
	@Transactional(readOnly = true)
	public List<Departamento> consultarDepartementos(){
		return this.departamentoRepository.findAll();
	}
	//Consultar departamento por id
	@Transactional(readOnly = true)
	public Departamento consultarDepartamentoPorId(Long id){
		return this.departamentoRepository.findById(id).orElse(null);
	}
	//Crear Departamento
	public Departamento crearDepartamento(DepartamentoDto departamentoDto){
		Departamento nuevoDepartamento = new Departamento();
		nuevoDepartamento.setNombreDepartamento(departamentoDto.getNombreDepartamento());
		return this.departamentoRepository.save(nuevoDepartamento);
	}
	//Actualizar Departamento
	public Departamento actualizarDepartamento(Long id, DepartamentoDto departamentoDto) {
		Departamento departamentoAActualizar = new Departamento();
		departamentoAActualizar.setId(id);
		departamentoAActualizar.setNombreDepartamento(departamentoDto.getNombreDepartamento());
		return this.departamentoRepository.save(departamentoAActualizar);
	}
	//Eliminar Departamento
	public void eliminarDepartamento(Long id) {
		this.departamentoRepository.deleteById(id);
	}
	
}
