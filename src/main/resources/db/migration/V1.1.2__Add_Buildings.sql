CREATE SEQUENCE building_id_seq;

create table buildings (
id bigint not null DEFAULT NEXTVAL('building_id_seq'),
 building_name varchar(255),
 area_id bigint not NULL,

 primary key (id)
);

ALTER SEQUENCE building_id_seq OWNED BY buildings.id;

ALTER table buildings ADD CONSTRAINT fk_buildings_areas
FOREIGN KEY (area_id)
REFERENCES areas (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;