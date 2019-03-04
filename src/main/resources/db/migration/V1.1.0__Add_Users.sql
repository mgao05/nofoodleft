CREATE SEQUENCE user_id_seq;

create table users (
id bigint not null DEFAULT NEXTVAL('user_id_seq'),
 first_name varchar(255) not NULL,
 last_name varchar(255) not NULL,
 email varchar(255) not NULL UNIQUE,
 password varchar(255) not NULL,
 username varchar (255) not NULL UNIQUE,
 primary key (id)
);

ALTER SEQUENCE user_id_seq OWNED BY users.id;

