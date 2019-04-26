ALTER SEQUENCE image_id_seq OWNED BY image.id;
ALTER table images ADD CONSTRAINT fk_images_food
FOREIGN KEY (food_id)
REFERENCES images (id)