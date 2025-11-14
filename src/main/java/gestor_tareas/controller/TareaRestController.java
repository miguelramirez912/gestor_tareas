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

import gestor_tareas.dto.TareaDto;
import gestor_tareas.entity.Tarea;
import gestor_tareas.service.TareaService;

@RestController
@RequestMapping("/api")
public class TareaRestController {
	
	@Autowired
	private TareaService tareaService;
	
	//Consultar todas las Tareas
	@GetMapping("/tareas")
	@ResponseStatus(HttpStatus.OK)
	public List<Tarea> consultarTareas(){
		return tareaService.findAll();
	}
	
	//Consultar una tarea
	@GetMapping("/tareas/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> consultarTareaPorId(@PathVariable Long id){
		
		Tarea tarea = null;
		String response = "";
		
		try {
			
			tarea = tareaService.findById(null);
			
		}catch(DataAccessException e){
			response = "Error al realizar la consulta ".concat(e.getMessage().concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		if(tarea == null) {
			response = "La tarea con el ID ".concat(id.toString()).concat(" no existe en la base de datos");
		}
		
		return new ResponseEntity<Tarea>(tarea, HttpStatus.OK);
	}
	
	//Crear tarea
	@PostMapping("/tareas")
	public ResponseEntity<?> crearTarea(@RequestBody TareaDto tareaDto){
		Tarea nuevaTarea = null;
		Map<String,Object> response = new HashMap<>();
		try {
			nuevaTarea = this.tareaService.createTarea(tareaDto);
		}catch(DataAccessException e) {
			response.put("mensaje", "No se pudo crear la tarea");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La tarea fue creada con exito");
		response.put("tarea", nuevaTarea);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	//Eliminar tarea
	@DeleteMapping("/tareas/{id}")
	public ResponseEntity<?> eliminarTarea(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		try {
			Tarea tareaAEliminar = tareaService.findById(id);
			
			if(tareaAEliminar == null) {
				response.put("mensaje", "la tarea no se encuentra en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar en la base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().toString()));
		}
		
		response.put("mensaje", "La tarea fue eliminada con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	//Actualizar tarea
	@PutMapping("/tareas/{id}")
	public ResponseEntity<?> actualizarTarea(@PathVariable Long id, @RequestBody TareaDto tareaDto){
		Tarea tareaAActualizar = null;
		Tarea tareaActualizada = null;
		String response = "";
		
		try {
			tareaAActualizar = this.tareaService.findById(id);
			
			if(tareaAActualizar == null) {
				response = "La tarea no fue localizada en la base de datos";
				return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
			}
			
			tareaActualizada = this.tareaService.updateTarea(tareaDto, id);
			
		}catch(DataAccessException e) {
			response = "Error al realizar la consulta";
			response = response.concat(e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response = "Tarea actualizada con exito";
		return new ResponseEntity<Tarea>(tareaActualizada, HttpStatus.OK);
		
	}
}
