# Activitat A02 UF4: Cambiem l'accés a dades de l'aplicació MVC library

## Creació de l'usuari a la base de dades

```sql
CREATE USER 'pau'@'localhost' IDENTIFIED BY 'system';
GRANT ALL PRIVILEGES ON llibreria.* TO 'pau'@'localhost';
FLUSH PRIVILEGES;
```
## Definició de la taula

```sql
CREATE TABLE books (
  id_llibre BIGINT PRIMARY KEY AUTO_INCREMENT,
  titol VARCHAR(255),
  autor VARCHAR(255),
  editorial VARCHAR(255),
  data_publicacio DATE,
  tematica VARCHAR(255),
  isbn VARCHAR(13) UNIQUE NOT NULL
);
```


#   M 6 - U F 2 - A C T 0 2  
 #   M 6 - U F 2 - A C T 0 2  
 