package gestor_tareas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gestor_tareas.entity.Proyecto;

public interface IProyectoRepository extends JpaRepository<Proyecto, Long> {

}
