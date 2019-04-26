CREATE SEQUENCE image_id_seq;

create table images (
id bigint not null DEFAULT NEXTVAL('image_id_seq'),
primary key (id)
);


