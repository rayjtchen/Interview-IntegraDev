CREATE TABLE "category" (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE "author" (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE "item_author" (
    item_id VARCHAR(36),
    author_id VARCHAR(36),
    PRIMARY KEY (author_id, item_id)
);

CREATE TABLE "item" (
    id VARCHAR(36) PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    type VARCHAR(36) NOT NULL,
    description VARCHAR(50) NOT NULL,
    category_id VARCHAR(36) NOT NULL,
    CONSTRAINT type_check CHECK (type IN('book', 'paper'))
);

CREATE TABLE "book" (
   id VARCHAR(36) PRIMARY KEY,
   isbn VARCHAR(13) NOT NULL,
   version INTEGER DEFAULT 1 NOT NULL,
   item_id VARCHAR(36) NOT NULL,
   publisher_id VARCHAR(36) NOT NULL
);

CREATE TABLE "publisher" (
  id VARCHAR(36) PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE "book_copy" (
  id VARCHAR(36) PRIMARY KEY,
  create_date TIMESTAMP NOT NULL,
  book_id VARCHAR(36) NOT NULL
);

ALTER TABLE "item_author" ADD CONSTRAINT item_author_author_fk FOREIGN KEY (author_id) REFERENCES "author" (id);
ALTER TABLE "item_author" ADD CONSTRAINT item_author_item_fk FOREIGN KEY (item_id) REFERENCES "item" (id);
ALTER TABLE "item" ADD CONSTRAINT item_category_fk FOREIGN KEY (category_id) REFERENCES "category" (id);
ALTER TABLE "book" ADD CONSTRAINT book_item_fk FOREIGN KEY (item_id) REFERENCES "item" (id);
ALTER TABLE "book" ADD CONSTRAINT book_publisher_fk FOREIGN KEY (publisher_id) REFERENCES "publisher" (id);
ALTER TABLE "book_copy" ADD CONSTRAINT book_copy_book_fk FOREIGN KEY (book_id) REFERENCES "book" (id);


