package gestor_tareas.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gestor_tareas.dto.PuestoDto;
import gestor_tareas.entity.Puesto;
import gestor_tareas.service.PuestoService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class PuestoRestController {
	
	@Autowired
	private PuestoService puestoService;

	//Consultar puestos
	@GetMapping("/puestos")
	@ResponseStatus(HttpStatus.OK)
	public List<Puesto> consultarPuestos(){
		return this.puestoService.consultarPuestos();
	}
	//Consultar puesto por id
	@GetMapping("/puestos/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> consultarPuestoPorId(@PathVariable Long id){
		Puesto puestoAConsultar = null;
		String response = "";
		
		try {
			
			puestoAConsultar = this.puestoService.consultarPuestoPorId(id);
			
			if(puestoAConsultar == null) {
				response = "El puesto con el id ".concat(id.toString()).concat(" no se encuentra en la base de datos");
				return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch(DataAccessException e) {
			response = "Error al realizar la consulta";
			response = response.concat(e.getMessage().concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Puesto>(puestoAConsultar, HttpStatus.OK);
		
	}
	//Crear puesto
	@PostMapping("/puestos")
	public ResponseEntity<?> crearPuesto(@RequestBody PuestoDto puestoDto){
		Puesto nuevoPuesto = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			nuevoPuesto = this.puestoService.crearPuesto(puestoDto);
		}catch(DataAccessException e) {
			response.put("mensaje", "No se pudo crear el puesto");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El puesto fue creado con exito");
		response.put("puesto", nuevoPuesto);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}
	//Actualizar
	@PutMapping("/puestos/{id}")
	public ResponseEntity<?> actualizarPuesto(@PathVariable Long id, @RequestBody PuestoDto puestoDto){
		Puesto puestoAEditar = null;
		Puesto puestoEditado = null;
		String response = "";
		
		try {
			
			puestoAEditar = this.puestoService.consultarPuestoPorId(id);
			
			if(puestoAEditar == null) {
				response = "Error al realizar la actualización. Puesto no encontrado";
				return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
			}
			
			puestoEditado = this.puestoService.actualizarPuesto(puestoDto, id);
			
		}catch(DataAccessException e) {
			response = "Error al acceder a la base de datos";
			response = response.concat(e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Puesto>(puestoEditado, HttpStatus.OK);
	}
	//Eliminar
	@DeleteMapping("puestos/{id}")
	public ResponseEntity<?> eliminarPuesto(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		
		try {
			Puesto puestoAEliminar = this.puestoService.consultarPuestoPorId(id);
			if(puestoAEliminar == null) {
				response.put("mensaje", "Error al eliminar. El puesto no fue encontrado en la base de datos");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			this.puestoService.eliminarPuesto(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al acceder a la base de datos");
			response.put("Error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El puesto fue eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
