DROP TABLE books;

CREATE TABLE books (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  titol VARCHAR(255),
  autor VARCHAR(255),
  editorial VARCHAR(255),
  datapublicacio DATE,
  tematica VARCHAR(255),
  isbn VARCHAR(13) UNIQUE NOT NULL
);