package gestor_tareas.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gestor_tareas.dto.DepartamentoDto;
import gestor_tareas.entity.Departamento;
import gestor_tareas.service.DepartamentoService;

@RestController
@RequestMapping("/api")
public class DepartamentoRestController {
	@Autowired
	private DepartamentoService departamentoService;
	
	//Consultar departamentos
	@GetMapping("/departamentos")
	@ResponseStatus(HttpStatus.OK)
	public List<Departamento> consultarDepartamentos(){
		return this.departamentoService.consultarDepartementos();
	}
	//Consultar departamento por id
	@GetMapping("/departamentos/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> consultarDepartamentoPorId(@PathVariable Long id){
		Departamento departamentoAConsultar = null;
		String response = "";
		
		try {
			departamentoAConsultar = this.departamentoService.consultarDepartamentoPorId(id);
			
			if(departamentoAConsultar == null) {
				response = "El departamento no existe en la base de datos";
				return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
			}		
			
		}catch(DataAccessException e) {
			response = "Error al consultar la base de datos";
			response = response.concat(e.getMessage()).concat(e.getMostSpecificCause().getLocalizedMessage());
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Departamento>(departamentoAConsultar, HttpStatus.OK);
		
	}
	//Crear departamento
	@PostMapping("/departamentos")
	public ResponseEntity<?> crearDepartamento(@RequestBody DepartamentoDto departamentoDto){
		Departamento nuevoDepartamento = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			nuevoDepartamento = this.departamentoService.crearDepartamento(departamentoDto);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al acceder a la base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El departamento fue creado con exito");
		response.put("departamento", nuevoDepartamento);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	//Actualizar departamento
	@PutMapping("/departametos/{id}")
	public ResponseEntity<?> actualizarDepartamento(@PathVariable Long id, @RequestBody DepartamentoDto departamentoDto){
		Departamento departamentoAActualizar = null;
		Departamento departamentoActualizado = null;
		String response = "";
		
		try {
			departamentoAActualizar = this.departamentoService.consultarDepartamentoPorId(id);
			if(departamentoAActualizar == null) {
				response = "El departamento no existe en la base de datos";
				return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
			}
			departamentoActualizado = this.departamentoService.actualizarDepartamento(id, departamentoDto);
		}catch(DataAccessException e) {
			response = "Error al acceder a la base de datos";
			response = response.concat(e.getMessage()).concat(e.getMostSpecificCause().getLocalizedMessage());
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Departamento>(departamentoActualizado, HttpStatus.OK);
	}
	//Eliminar
	@DeleteMapping("/departamentos/{id}")
	public ResponseEntity<?> eliminarDepartamento(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			Departamento departamentoAEliminar = this.departamentoService.consultarDepartamentoPorId(id);
			if(departamentoAEliminar == null) {
				response.put("mensaje", "El departamento no existe en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			this.departamentoService.eliminarDepartamento(id);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al acceder a la base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El departamento fue eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
