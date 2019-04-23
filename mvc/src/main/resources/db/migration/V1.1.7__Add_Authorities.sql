CREATE SEQUENCE authority_id_seq;

create table authorities (
id bigint not null DEFAULT NEXTVAL('authority_id_seq'),
authority varchar(255),
 primary key (id),
 user_id bigint NOT NULL,
 CONSTRAINT fk_authorities_users
 FOREIGN KEY (user_id)
 REFERENCES users (id)
 ON DELETE NO ACTION
 ON UPDATE NO ACTION
);

ALTER SEQUENCE authority_id_seq OWNED BY authorities.id;


