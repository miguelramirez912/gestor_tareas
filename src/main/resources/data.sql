ALTER TABLE responsables
DROP CONSTRAINT responsables_departamento_id_key;

ALTER TABLE responsables
DROP CONSTRAINT responsables_puesto_id_key;


INSERT INTO proyectos (nombre_proyecto) VALUES ('Proyecto 1011 CFE');
INSERT INTO proyectos (nombre_proyecto) VALUES ('Proyecto 3267 SRE');
INSERT INTO proyectos (nombre_proyecto) VALUES ('Proyecto 8763 IPAB');

INSERT INTO puestos (nombre_puesto) VALUES ('Jefe de Departamento');
INSERT INTO puestos (nombre_puesto) VALUES ('Auxiliar de Departamento');
INSERT INTO puestos (nombre_puesto) VALUES ('Lider de Proyecto');

INSERT INTO departamentos (nombre_departamento) VALUES ('Subgerencia de Proyectos');
INSERT INTO departamentos (nombre_departamento) VALUES ('Departamento de Recursos Humanos');
INSERT INTO departamentos (nombre_departamento) VALUES ('Subgerencia de Operación');

INSERT INTO responsables (nombre, puesto_id, departamento_id) VALUES ('Miguel Ramirez', 3, 1);
INSERT INTO responsables (nombre, puesto_id, departamento_id) VALUES ('Patricia Gonzalez', 1, 2);
INSERT INTO responsables (nombre, puesto_id, departamento_id) VALUES ('Adriana Juarez', 2, 3);

INSERT INTO tareas (descripcion, estado, prioridad, responsable_id, proyecto_id) VALUES ('tarea de prueba', 'NUEVO', 'ALTA', 1, 3);
INSERT INTO tareas (descripcion, estado, prioridad, responsable_id, proyecto_id) VALUES ('tarea de prueba', 'EN_PROGRESO', 'BAJA', 2, 2);
INSERT INTO tareas (descripcion, estado, prioridad, responsable_id, proyecto_id) VALUES ('tarea de prueba', 'CERRADO', 'BAJA', 3, 2);
INSERT INTO tareas (descripcion, estado, prioridad, responsable_id, proyecto_id) VALUES ('tarea de prueba', 'RECHAZADO', 'ALTA', 2, 1);
INSERT INTO tareas (descripcion, estado, prioridad, responsable_id, proyecto_id) VALUES ('tarea de prueba', 'EN_PROGRESO', 'ALTA', 1, 1);