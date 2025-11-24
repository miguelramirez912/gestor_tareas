package gestor_tareas.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import gestor_tareas.entity.enums.Estado;
import gestor_tareas.entity.enums.Prioridad;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tareas")
public class Tarea {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descripcion;
	@Enumerated(EnumType.STRING)
	private Prioridad prioridad;
	@Enumerated(EnumType.STRING)
	private Estado estado;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "responsable_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Responsable responsable;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proyecto_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Proyecto proyecto;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Prioridad getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(Prioridad prioridad) {
		this.prioridad = prioridad;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Responsable getResponsable() {
		return responsable;
	}
	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}
	public Proyecto getProyecto() {
		return proyecto;
	}
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	
	

}
