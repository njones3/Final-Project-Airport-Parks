DROP TABLE IF EXISTS airport_amenity;
DROP TABLE IF EXISTS amenity;
DROP TABLE IF EXISTS airport_parks;
DROP TABLE IF EXISTS pilot;

CREATE TABLE pilot (
	pilot_id int NOT NULL AUTO_INCREMENT,
	pilot_name varchar(50) NOT NULL,
	pilot_phone varchar(20) NOT NULL,
	pilot_email varchar(128),
	PRIMARY KEY (pilot_id)
);

CREATE TABLE airport_parks (
	airport_id int NOT NULL AUTO_INCREMENT,
	pilot_id int NOT NULL,
	airport_name varchar(256) NOT NULL,
	airport_address varchar(256) NOT NULL,
	airport_city varchar(50) NOT NULL,
	airport_state varchar(50) NOT NULL,
	airport_zip varchar(10) NOT NULL,
    PRIMARY KEY (airport_id),
	FOREIGN KEY (pilot_id) REFERENCES pilot (pilot_id)
);

CREATE TABLE amenity (
	amenity_id int NOT NULL AUTO_INCREMENT,
	amenity_type varchar(256),
	PRIMARY KEY (amenity_id)
);

CREATE TABLE airport_amenity (
	airport_id int NOT NULL,
	amenity_id int NOT NULL,
	FOREIGN KEY (airport_id) REFERENCES airport_parks (airport_id) ON DELETE CASCADE,
	FOREIGN KEY (amenity_id) REFERENCES amenity (amenity_id) ON DELETE CASCADE
);