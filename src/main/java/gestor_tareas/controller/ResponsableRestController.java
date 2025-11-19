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

import gestor_tareas.dto.ResponsableDto;
import gestor_tareas.entity.Responsable;
import gestor_tareas.service.ResponsableService;

@RestController
@RequestMapping("/api")
public class ResponsableRestController {
	
	@Autowired
	private ResponsableService responsableService;
	
	//Consultar todos responsables
	@GetMapping("/responsables")
	@ResponseStatus(HttpStatus.OK)
	public List<Responsable> consultarResponsables(){
		return this.responsableService.consultarResponsables();
	}
	//Consultar responsable por Id
	@GetMapping("/responsables/{id}")
	public ResponseEntity<?> consultarResponsablePorId(@PathVariable Long id){
		
		Responsable responsableEncontrado = null;
		String response = "";
		
		try{
			
			responsableEncontrado = this.responsableService.consultarResponsablePorId(id);
			
			if(responsableEncontrado == null) {
				response = "El responsable con el ID ".concat(id.toString()).concat(" no fue encontrado en la base de datos");
				return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch(DataAccessException e) {
			response = "Error al realizar la consulta";
			response = response.concat(e.getMessage().concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<String>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
					
		return new ResponseEntity<Responsable>(responsableEncontrado, HttpStatus.OK);
	}
	//Crear responsable
	@PostMapping("/responsables")
	public ResponseEntity<?> crearResponsable(@RequestBody ResponsableDto responsableDto){
		
		Responsable nuevoResponsable = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			nuevoResponsable = this.responsableService.crearResponsable(responsableDto);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "No se pudo crear el responsable");
			response.put("Error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El responsable fue creado con exito");
		response.put("responsable", nuevoResponsable);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	//Actualizar responsable
	@PutMapping("/responsables/{id}")
	public ResponseEntity<?> actualizarResponsable(@RequestBody ResponsableDto responsabledto, @PathVariable Long id){
		Responsable responsableAEditar = null;
		Responsable responsableEditado = null;
		String response = "";
		
		try {
			
			responsableAEditar = this.responsableService.consultarResponsablePorId(id);
			
			if(responsableAEditar == null) {
				response = "Error al realizar la actualizar. El responsable no fue encontrado";
				return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
			}
			
			responsableEditado = this.responsableService.editarResponsable(responsabledto, id);
		}catch(DataAccessException e) {
			
			response = "Error al realizar la consulta";
			response = response.concat(e.getMessage().concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
		return new ResponseEntity<Responsable>(responsableEditado, HttpStatus.OK);
				
	}
	//Eliminar responsable
	@DeleteMapping("/responsables/{id}")
	public ResponseEntity<?> eliminarResponsable(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		try {
			Responsable responsableAElimiar = this.responsableService.consultarResponsablePorId(id);
			
			if(responsableAElimiar == null) {
				response.put("mensaje", "Error al eliminar. El responsable no existe en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			this.responsableService.eliminarResponsable(id);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error el eliminar en base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El resposable fue eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
