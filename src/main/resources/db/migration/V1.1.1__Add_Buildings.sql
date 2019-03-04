CREATE SEQUENCE buildings_id_seq;

create table buildings (
id bigint not null DEFAULT NEXTVAL('buildings_id_seq'),
 buildingName varchar(255),
 primary key (id)
);

ALTER SEQUENCE buildings_id_seq OWNED BY buildings.id;