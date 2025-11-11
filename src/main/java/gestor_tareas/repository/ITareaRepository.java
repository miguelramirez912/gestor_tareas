package gestor_tareas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gestor_tareas.entity.Tarea;

public interface ITareaRepository extends JpaRepository <Tarea, Long> {

}
