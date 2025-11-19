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

import gestor_tareas.dto.ProyectoDto;
import gestor_tareas.entity.Proyecto;
import gestor_tareas.service.ProyectoService;

@RestController
@RequestMapping("/api")
public class ProyectoRestController {
	
	@Autowired
	private ProyectoService proyectoService;
	
	//Consultar proyectos
	@GetMapping("/proyectos")
	@ResponseStatus(HttpStatus.OK)
	public List<Proyecto> consultarProyectos(){
		return this.proyectoService.consultarProyectos();
	}
	//Consultar proyecto por id
	@GetMapping("/proyectos/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> consultarProyectoPorId(@PathVariable Long id) {
		
		Proyecto proyectoConsultado = null;
		String response = "";
		
		try {
			proyectoConsultado = this.proyectoService.consultarProyectoPorId(id);
			
			if(proyectoConsultado == null) {
				response = "El proyecto con el id ".concat(id.toString()).concat(" no existe en la base de datos");
				return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch(DataAccessException e) {
			response = "Error al realizar la consulta";
			response = response.concat(e.getMessage().concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Proyecto>(proyectoConsultado, HttpStatus.OK);
	}
	//Crear proyecto
	@PostMapping("/proyectos")
	public ResponseEntity<?> crearProyecto(@RequestBody ProyectoDto proyectoDto){
		
		Proyecto nuevoProyecto = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			nuevoProyecto = this.proyectoService.crearProyecto(proyectoDto);
		}catch(DataAccessException e) {
			response.put("mensaje", "No se puedo crear al proyecto");
			response.put("Error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El proyecto fue creado con exito");
		response.put("Proyecto", nuevoProyecto);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	//Actualizar proyecto
	@PutMapping("/proyectos/{id}")
	public ResponseEntity<?> actualizarProyecto(@PathVariable Long id, @RequestBody ProyectoDto proyectoDto){
		Proyecto proyectoAActualizar = null;
		Proyecto proyectoActualizado = null;
		String response = "";
		
		try {
			
			proyectoAActualizar = this.proyectoService.actualizarProyecto(id, proyectoDto);
			
			if(proyectoAActualizar == null) {
				response = "El proyecto con el id ".concat(id.toString()).concat(" no se encuentra en la base de datos");
				return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
			}
			
			proyectoActualizado = this.proyectoService.actualizarProyecto(id, proyectoDto);
			
		}catch(DataAccessException e) {
			response = "Error al realizar la consulta";
			response = response.concat(e.getMessage()).concat(e.getMostSpecificCause().toString());
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
				
		return new ResponseEntity<Proyecto>(proyectoActualizado, HttpStatus.OK);
		
	}
	//Eliminar proyecto
	@DeleteMapping("/proyecto/{id}")
	public ResponseEntity<?> eliminarProyecto(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			Proyecto proyectoAEliminar = this.proyectoService.consultarProyectoPorId(id);
			
			if(proyectoAEliminar == null) {
				response.put("mensaje", "El proyecto no existe en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			this.proyectoService.eliminarProyecto(id);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al consultar la base de datos");
			response.put("Error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El proyecto fue eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
