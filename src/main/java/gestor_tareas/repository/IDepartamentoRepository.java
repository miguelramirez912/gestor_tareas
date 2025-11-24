package gestor_tareas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gestor_tareas.entity.Departamento;

public interface IDepartamentoRepository extends JpaRepository<Departamento, Long> {

}
