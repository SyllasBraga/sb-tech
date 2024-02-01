DROP DATABASE IF EXISTS sb_tech;
CREATE DATABASE sb_tech;
USE sb_tech;

CREATE TABLE repair(
id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
entry_date TIMESTAMP NOT NULL,
repair_time_stipulated TIMESTAMP NOT NULL,
out_date TIMESTAMP,
id_client VARCHAR(36) NOT NULL,
id_technician VARCHAR(36) NOT NULL,
id_payment_status VARCHAR(255) NOT NULL
);

CREATE TABLE repair_budget(
id_repair INT NOT NULL,
id_budget INT NOT NULL
);

CREATE TABLE budget(
id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
repair_value FLOAT(10.2) NOT NULL,
details VARCHAR(1000) NOT NULL,
id_hardware_type INT NOT NULL
);

CREATE TABLE client(
id VARCHAR(36) PRIMARY KEY NOT NULL,
document VARCHAR(11) NOT NULL UNIQUE,
name VARCHAR(255) NOT NULL,
phone VARCHAR(11) NOT NULL,
email VARCHAR(255) NOT NULL
);

CREATE TABLE payment_status(
id INT PRIMARY KEY AUTO_INCREMENT,
status VARCHAR(30) NOT NULL
);

CREATE TABLE technician(
id VARCHAR(36) PRIMARY KEY NOT NULL,
document VARCHAR(11) NOT NULL UNIQUE,
password_login VARCHAR(255) NOT NULL,
name VARCHAR(255) NOT NULL,
salary FLOAT(10.2) NOT NULL,
phone VARCHAR(11) NOT NULL,
email VARCHAR(255) NOT NULL,
birth_date TIMESTAMP NOT NULL,
admission_date TIMESTAMP NOT NULL,
fired_date TIMESTAMP,
id_account_status VARCHAR(255) NOT NULL 
);

CREATE TABLE hardware_type(
id INT PRIMARY KEY AUTO_INCREMENT,
details VARCHAR(255) NOT NULL
);

ALTER TABLE repair ADD CONSTRAINT fk_client_repair 
FOREIGN KEY (id_client) REFERENCES client(id);

ALTER TABLE repair ADD CONSTRAINT fk_technician_repair 
FOREIGN KEY (id_technician) REFERENCES technician(id);

ALTER TABLE repair_budget ADD CONSTRAINT fk_repair_budget 
FOREIGN KEY (id_repair) REFERENCES repair(id);

ALTER TABLE repair_budget ADD CONSTRAINT fk_budget_repair 
FOREIGN KEY (id_budget) REFERENCES budget(id);

ALTER TABLE budget ADD CONSTRAINT fk_hardware_type_budget 
FOREIGN KEY (id_hardware_type) REFERENCES hardware_type(id);

INSERT INTO payment_status(status) VALUES('paid'), ('pendent');
INSERT INTO hardware_type (details) VALUES ('CPU'),('RAM'),('GPU'),('HDD'),('SSD'),('Motherboard'),
('Power Supply'),('Monitor'),('Keyboard'),('Mouse');

INSERT INTO budget VALUES(null, 345.91, "Solda BGA", 3)

insert into repair_budget values(2,1)