# ChallegeLiteratura

# 📚 Challenge de Literatura - Spring Boot + PostgreSQL

Una aplicación de consola construida con **Java**, **Spring Boot** y **PostgreSQL**, que consume datos de la API pública [Gutendex](https://gutendex.com/) para consultar, guardar y listar libros de dominio público.

---

## 🚀 Funcionalidades

Menú interactivo en consola con las siguientes opciones:

1 - Buscar libro por título
2 - Listar libros guardados
3 - Listar autores registrados
4 - Listar autores vivos en un año
5 - Listar libros por idioma
0 - Salir


---

## 🛠 Tecnologías

- Java 17+ (probado también en Java 22)
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

---

## 🧑‍💻 Estructura del proyecto

```bash
src/
├── main/
│   ├── java/
│   │   └── com/AluraLatam/ChallegeDeLiteratura/
│   │       ├── ChallegeDeLiteraturaApplication.java   # Clase principal
│   │       ├── model/                                 # Entidades: Libro, Autor
│   │       ├── repository/                            # Repositorios JPA
│   │       ├── service/                               # Servicio para consumir Gutendex
│   │       └── principal/                             # Clase con menú interactivo
│   └── resources/
│       └── application.properties


✅ Comportamiento especial
Libros duplicados no se guardan nuevamente.

Datos con campos nulos o vacíos son descartados.

La salida en consola está formateada para mejor legibilidad.
--------------------------------------------
Título   : Don Quixote
Autor    : Miguel de Cervantes
Idioma   : es
Descargas: 25000
ID       : 1342
