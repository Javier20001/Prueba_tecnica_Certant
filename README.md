# ✅ Proyecto: Gestión de Eventos - Certant Challenge

Este sistema permite a los administradores de un teatro gestionar eventos como obras de teatro, recitales y conferencias, y a los asistentes reservar entradas de forma eficiente.

---

## 🛉 Tecnologías utilizadas

**Backend (Spring Boot):**
- Java 17
- Spring Boot
- JPA/Hibernate
- H2 (modo in-memory)
- Maven

**Frontend (React):**
- React + Vite
- TypeScript
- Axios
- React Router
- CSS (módulos o en línea)
- LocalStorage (para autenticación simple)

---

## 📁 Estructura del proyecto

```
/backend
  └── src/main/java/com/grandeventmanager/...
/frontend
  └── src/
```

---

## ⚙️ Cómo levantar los proyectos

### 🚀 Backend (Spring Boot)

**Requisitos:**
- Java 17+
- Maven

**Pasos:**
```bash
cd backend
mvn spring-boot:run
```
El backend corre por defecto en: [http://localhost:9000](http://localhost:9000)

---

### 💻 Frontend (React)

**Requisitos:**
- Node.js 16+
- npm o yarn

**Pasos:**
```bash
cd frontend
npm install
npm run dev
```
El frontend corre por defecto en: [http://localhost:5173](http://localhost:5173)

---

## 📝 Usuarios de prueba

Los datos de prueba se cargan automáticamente en la base de datos H2 al iniciar la app.

- **Admin:**  
  `lucia.fernandez@example.com` / `12345`

- **Usuarios:**  
  `martin.gomez@example.com` / `67890`  
  `camila.ruiz@example.com` / `98765`

Podés verlos en la consola de H2:  
[http://localhost:9000/h2-console](http://localhost:9000/h2-console)  
**JDBC URL:** `jdbc:h2:mem:gestor_eventos_db`

---

## 🛠️ Funcionalidades principales

- 🔐 Login con email + token simple
- 📅 Listado de eventos por tipo: obras, recitales y conferencias
- 🎟️ Reservas con selección de tipo de entrada
- ❌ Baja lógica (INACTIVE) de reservas y eventos
- ✏️ Edición de reservas y eventos
- ↺ Validaciones de capacidad y fechas

---

## ⚠️ Advertencias y consideraciones

- La base de datos es en memoria, por lo que se borra al reiniciar.
- No se implementa autenticación JWT real (es simplificada con LocalStorage).
- Los eventos cancelados o con capacidad 0 no permiten nuevas reservas.
- Las fechas de eventos no pueden editarse si existen reservas posteriores.

---

## 🔪 Tests

El backend tiene tests con JUnit 5 y Mockito (`ReservationServiceTest` y otros).

Se testean:
- Guardado
- Actualización
- Baja lógica

**Ejecutalos con:**
```bash
cd backend
mvn test
```

---

## 📦 Endpoints principales

- `GET /api/play` – Obtener obras
- `GET /api/recital` – Obtener recitales
- `GET /api/conference` – Obtener conferencias
- `POST /api/reservation` – Crear reserva
- `PUT /api/reservation/{id}` – Editar reserva
- `DELETE /api/reservation/{id}` – Baja lógica


## 🔍 Documentación Swagger
Podés acceder a la documentación completa de la API REST en Swagger UI:
[http://localhost:9000/swagger-ui/index.html#/](http://localhost:9000/swagger-ui/index.html#/)
