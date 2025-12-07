INSERT INTO responsables (nombre) VALUES ('Miguel Ramirez');
INSERT INTO responsables (nombre) VALUES ('Patricia Gonzalez');
INSERT INTO responsables (nombre) VALUES ('Adriana Juarez');

INSERT INTO proyectos (nombre_proyecto) VALUES ('Proyecto 1011 CFE');
INSERT INTO proyectos (nombre_proyecto) VALUES ('Proyecto 3267 SRE');
INSERT INTO proyectos (nombre_proyecto) VALUES ('Proyecto 8763 IPAB');

INSERT INTO puestos (nombre_puesto) VALUES ('Jefe de Departamento');
INSERT INTO puestos (nombre_puesto) VALUES ('Encargado de Recursos Humanos');
INSERT INTO puestos (nombre_puesto) VALUES ('Lider de Proyecto');

INSERT INTO tareas (descripcion, estado, prioridad, responsable_id, proyecto_id) VALUES ('tarea de prueba', 'NUEVO', 'ALTA', 1, 3);
INSERT INTO tareas (descripcion, estado, prioridad, responsable_id, proyecto_id) VALUES ('tarea de prueba', 'EN_PROGRESO', 'BAJA', 2, 2);
INSERT INTO tareas (descripcion, estado, prioridad, responsable_id, proyecto_id) VALUES ('tarea de prueba', 'CERRADO', 'BAJA', 3, 2);
INSERT INTO tareas (descripcion, estado, prioridad, responsable_id, proyecto_id) VALUES ('tarea de prueba', 'RECHAZADO', 'ALTA', 2, 1);
INSERT INTO tareas (descripcion, estado, prioridad, responsable_id, proyecto_id) VALUES ('tarea de prueba', 'EN_PROGRESO', 'ALTA', 1, 1);