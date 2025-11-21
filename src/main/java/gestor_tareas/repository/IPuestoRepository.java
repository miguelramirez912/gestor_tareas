package gestor_tareas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gestor_tareas.entity.Puesto;

public interface IPuestoRepository extends JpaRepository<Puesto, Long> {

}
