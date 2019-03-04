CREATE SEQUENCE area_id_seq;

create table area (
id bigint not null DEFAULT NEXTVAL('area_id_seq'),
 area_zipcode integer NOT NULL,
 area_name varchar(255),
 primary key (id)
);

ALTER SEQUENCE area_id_seq OWNED BY area.id;