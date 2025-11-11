package gestor_tareas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gestor_tareas.entity.Responsable;

public interface IResponsableRepository extends JpaRepository <Responsable, Long> {

}
