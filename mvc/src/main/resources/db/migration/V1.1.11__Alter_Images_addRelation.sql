ALTER table images ADD COLUMN food_id bigint;
ALTER table images ADD CONSTRAINT fk_images_food
FOREIGN KEY (food_id)
REFERENCES images (id);
ALTER SEQUENCE image_id_seq OWNED BY images.id;