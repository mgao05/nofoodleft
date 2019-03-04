ALTER TABLE building DROP COLUMN buildingname;
ALTER TABLE building ADD COLUMN building_name integer NOT NULL;
ALTER TABLE building DROP COLUMN area;
ALTER TABLE building ADD COLUMN area_id integer NOT NULL;