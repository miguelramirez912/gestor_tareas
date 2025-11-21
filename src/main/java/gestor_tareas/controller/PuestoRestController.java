package gestor_tareas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gestor_tareas.entity.Puesto;
import gestor_tareas.service.PuestoService;

@RestController
@RequestMapping("/api")
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
/*	public ResponseEntity<?> consultarPuestoPorId(@PathVariable Long id){
		Puesto puestoAConsultar = null;
		String response = "";
		
		try {
			
			puestoAConsultar = this.puestoService.consultarPuestoPorId(id);
			
			if(puestoAConsultar == null) {
				response = "El puesto con el id ".concat(id.toString()).concat(" no se encuentra en la base de datos");
			}
			
		}catch(DataAccessException e) {
			
		}
	}*/
	//Crear puesto
	//Actualizar
	//Eliminar

}
