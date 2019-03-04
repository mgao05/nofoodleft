CREATE SEQUENCE food_id_seq;

create table food (
id bigint not null DEFAULT NEXTVAL('food_id_seq'),
 food_type varchar(255),
 building_id integer NOT NULL,
 area_id integer NOT NULL,
 food_time integer NOT NULL,
 primary key (id)
);

ALTER SEQUENCE food_id_seq OWNED BY food.id;