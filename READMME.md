# Sistema Biblioteca

Proyecto de práctica para consolidar arquitectura de capas en Java puro, sin frameworks.

Modela un sistema de préstamos de libros con separación explícita en Domain, Repository y Service.

---

## Stack

- Java 21
- Maven

---

## Arquitectura

```
src/main/java/org/example/
├── domain/          → Entidades del negocio (Libro, Prestamo, EstadoPrestamo)
├── repository/      → Persistencia en memoria (LibroRepository, PrestamoRepository)
├── service/         → Lógica de negocio (LibroService, PrestamoService)
├── exception/       → Excepciones custom del dominio
└── Main.java        → Punto de entrada — simula el rol de un controller
```

### Responsabilidades por capa

| Capa | Responsabilidad |
|------|----------------|
| Domain | Modela las entidades. Se autogobierna — valida su propio estado. |
| Repository | Persiste y recupera objetos. Solo operaciones CRUD y consultas. |
| Service | Orquesta la lógica de negocio. Coordina entre repositories y domain. |

### Dirección de dependencias

```
Main → Service → Repository → Domain
                     ↑
                 Exception
```

Ninguna capa conoce a la que está por encima de ella.

---

## Flujo principal

```java
// Registrar un libro
libroService.registrarLibro(1, "Autor", "Titulo", "Descripcion", 10);

// Registrar un préstamo
prestamoService.registrarPrestamo(1, 1); // idPrestamo, idLibro

// Consultar préstamo
prestamoService.consultarPrestamo(1);

// Devolver libro
prestamoService.devolverLibro(1);
```

---

## Decisiones de diseño

- **Autogobierno en Domain:** `Libro` protege su propio estado. `incrementarPrestados()` y `decrementarPrestados()` lanzan `IllegalStateException` si se violan los invariantes, sin depender de capas externas.

- **Inyección de dependencias manual:** Los services reciben sus repositories por constructor. En Spring Boot esto es equivalente a la inyección automática con `@Autowired` o constructor injection.

- **Service como única puerta de entrada:** El Main solo conoce los services, nunca los repositories directamente.

- **Excepciones fluyen hacia arriba:** Los services lanzan, el Main captura. Cada capa tiene una responsabilidad clara respecto al manejo de errores.

---

## Cómo ejecutar

1. Clonar el repositorio
```bash
git clone https://github.com/manuelVerdugoK/java-sistema-biblioteca.git
```

2. Abrir con IntelliJ IDEA o cualquier IDE compatible con Maven

3. Ejecutar `Main.java`

---

## Contexto

Este proyecto es parte de un plan de desarrollo hacia QA Automation Engineer y posteriormente backend developer en Java.
El objetivo de este ejercicio fue entender en carne propia por qué existe cada capa antes de introducir Spring Boot.