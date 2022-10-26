INSERT INTO category (id, name) VALUES ('235afe6a-7e98-4689-acf0-ce16db3ad625', 'Fantasy');
INSERT INTO category (id, name) VALUES ('f83ea05f-0dba-4239-aab8-e0259dc87894', 'Sci-Fi');
INSERT INTO category (id, name) VALUES ('cccc4a2d-2297-4c21-b72c-256ccf5afbe5', 'Mystery');
INSERT INTO category (id, name) VALUES ('f16e091e-6733-4b46-aa84-9ca6ca41279a', 'Thriller');
INSERT INTO category (id, name) VALUES ('6db8c13e-bc29-4d30-9edf-b4a6abd39202', 'Romance');
INSERT INTO category (id, name) VALUES ('8f6c135e-fd52-4bda-b492-cf2b2cef9f21', 'Contemporary');

INSERT INTO author (id, name) VALUES ('593aab5d-96f6-459a-82ee-d5c83bc55309', 'Olive');
INSERT INTO author (id, name) VALUES ('91125d80-c392-4ac3-99e9-68f1a581e1f1', 'Rickie');
INSERT INTO author (id, name) VALUES ('fbd3bd23-b153-4b82-af18-3e23469d0102', 'Riley');

INSERT INTO item (id, title, type, description, category_id) VALUES ('439198ae-ded2-459f-b253-8b2cf7d6bb72', 'Harry Potter', 'book', 'Harry Potter and the Order of the Phoenix', '235afe6a-7e98-4689-acf0-ce16db3ad625');
INSERT INTO item (id, title, type, description, category_id) VALUES ('55ef894f-f4e3-48b2-8b0e-1a7aeeb771f4', 'The Book Thief', 'book', 'The Book Thief', 'cccc4a2d-2297-4c21-b72c-256ccf5afbe5');
INSERT INTO item (id, title, type, description, category_id) VALUES ('057c045f-2c40-4d03-a809-dc4d205e3b47', 'Animal Farm', 'book', 'Animal Farm', '8f6c135e-fd52-4bda-b492-cf2b2cef9f21');

INSERT INTO publisher (id, name) VALUES ('9fc769dc-f11b-49e5-9471-f371b33c1ce7', 'Busybird');
INSERT INTO publisher (id, name) VALUES ('46e5effc-8b94-41af-bf06-026825366ea1', 'WorkingType');

INSERT INTO book (id, isbn, version, item_id, publisher_id) VALUES ('7d1e0d3f-add4-4f21-83ef-e47f7f508fe6', '6738564139654', 1, '439198ae-ded2-459f-b253-8b2cf7d6bb72', '9fc769dc-f11b-49e5-9471-f371b33c1ce7');
INSERT INTO book (id, isbn, version, item_id, publisher_id) VALUES ('887d0e20-a909-4e06-8996-26458ad79d13', '7536413698547', 2, '439198ae-ded2-459f-b253-8b2cf7d6bb72', '46e5effc-8b94-41af-bf06-026825366ea1');

INSERT INTO book_copy (id, create_date, book_id) VALUES ('0a32e0cd-238f-49ec-9346-40673bc6a5ab', now() - INTERVAL '2 hour', '7d1e0d3f-add4-4f21-83ef-e47f7f508fe6');
INSERT INTO book_copy (id, create_date, book_id) VALUES ('b4db84f2-a11b-4326-a132-dea1991ee840', now() - INTERVAL '2 hour', '7d1e0d3f-add4-4f21-83ef-e47f7f508fe6');
INSERT INTO book_copy (id, create_date, book_id) VALUES ('e9c820a0-7f63-4ae4-98a9-66714ec8ad69', now() - INTERVAL '2 hour', '887d0e20-a909-4e06-8996-26458ad79d13');

INSERT INTO item_author (item_id, author_id) VALUES ('439198ae-ded2-459f-b253-8b2cf7d6bb72', '593aab5d-96f6-459a-82ee-d5c83bc55309');
INSERT INTO item_author (item_id, author_id) VALUES ('439198ae-ded2-459f-b253-8b2cf7d6bb72', '91125d80-c392-4ac3-99e9-68f1a581e1f1');
INSERT INTO item_author (item_id, author_id) VALUES ('55ef894f-f4e3-48b2-8b0e-1a7aeeb771f4', '593aab5d-96f6-459a-82ee-d5c83bc55309');
INSERT INTO item_author (item_id, author_id) VALUES ('057c045f-2c40-4d03-a809-dc4d205e3b47', 'fbd3bd23-b153-4b82-af18-3e23469d0102');