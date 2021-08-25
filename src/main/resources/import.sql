INSERT INTO bd_admondelivery.cliente(apellido, create_at, email, nombre)VALUES('Nicolas', NOW(), 'jesus@gmail.com', 'Jesus');
INSERT INTO bd_admondelivery.cliente(apellido, create_at, email, nombre)VALUES('Tesla', NOW(), 'nicolas@gmail.com', 'Nicola');
INSERT INTO bd_admondelivery.cliente(apellido, create_at, email, nombre)VALUES('Cosme', NOW(), 'hayden3@gmail.com', 'Hayden');
INSERT INTO bd_admondelivery.cliente(apellido, create_at, email, nombre)VALUES('Vidal', NOW(), 'miriam@gmail.com', 'Elizabeth');
INSERT INTO bd_admondelivery.cliente(apellido, create_at, email, nombre)VALUES('Higuera', NOW(), 'karen@gmail.com', 'Karen');
INSERT INTO bd_admondelivery.cliente(apellido, create_at, email, nombre)VALUES('Polo', NOW(), 'isela@gmail.com', 'Isela');
/* Creamos algunos usuarios */
INSERT INTO bd_admondelivery.usuario (enabled, password, username, nombre, apellido, email) VALUES(1, '$2a$10$a7qLlUOW2nHLws22Q6QSRO7Z31ziiqfHkbkdW604fNIPo9bnoV7OO',  'jesus','Jesus','Nicolas','nicolas@gmail.com');
INSERT INTO bd_admondelivery.usuario (enabled, password, username, nombre, apellido, email) VALUES(1, '$2a$10$9M0q9svxy30T6X4Ar9V.3.tPr/drUJINoqf41dVEGaw172GVnJhU2','admin','Admin','Admin','admin@gmail.com');
/* Creamos dos roles */
INSERT INTO bd_admondelivery.`role`(nombre) VALUES('ROLE_USER');
INSERT INTO bd_admondelivery.`role`(nombre) VALUES('ROLE_ADMIN');
/* Creamos la realcion de usuarios y roles */
INSERT INTO bd_admondelivery.user_authorities (user_id, role_id) VALUES(1, 1);
INSERT INTO bd_admondelivery.user_authorities (user_id, role_id) VALUES(2, 1);
INSERT INTO bd_admondelivery.user_authorities (user_id, role_id) VALUES(2, 2);
/* Insertamos algunos productos */
INSERT INTO bd_admondelivery.producto(create_at, nombre, precio)VALUES(NOW(), 'Apple Watch', 11500);
INSERT INTO bd_admondelivery.producto(create_at, nombre, precio)VALUES(NOW(), 'Iphone 11', 10900);
INSERT INTO bd_admondelivery.producto(create_at, nombre, precio)VALUES(NOW(), 'Imac', 25000);
INSERT INTO bd_admondelivery.producto(create_at, nombre, precio)VALUES(NOW(), 'Sony Bravia', 38000);
INSERT INTO bd_admondelivery.producto(create_at, nombre, precio)VALUES(NOW(), 'PS5', 15800);
INSERT INTO bd_admondelivery.producto(create_at, nombre, precio)VALUES(NOW(), 'XBOX SERIES X', 25000);

INSERT INTO bd_admondelivery.factura(create_at, descripcion, observacion, cliente_id)VALUES(NOW(), 'Facturas de equipo de oficina', 'Equipo oficina', 1);
INSERT INTO bd_admondelivery.factura_item(cantidad, producto_id, factura_id)VALUES(2, 1, 1);
INSERT INTO bd_admondelivery.factura_item(cantidad, producto_id, factura_id)VALUES(1, 4, 1);




