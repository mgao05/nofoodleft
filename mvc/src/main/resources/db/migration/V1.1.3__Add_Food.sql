CREATE SEQUENCE food_id_seq;

create table food (
id bigint not null DEFAULT NEXTVAL('food_id_seq'),
 food_type varchar(255),
 building_id bigint NOT NULL,
 area_id bigint NOT NULL,
 primary key (id)
);

ALTER SEQUENCE food_id_seq OWNED BY food.id;

ALTER table food ADD CONSTRAINT fk_food_buildings
FOREIGN KEY (building_id)
REFERENCES food (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;
