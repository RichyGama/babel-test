# Prueba tecnica, crud de empleados con springboot 
API REST para gestión de empleados con operaciones CRUD, desarrollada con Spring Boot 3.x y Java 17.

#  Características principales
- Operaciones CRUD completas
- Validación de datos y manejo de errores
- Documentación Swagger/OpenAPI
- Pruebas unitarias (JUnit 5 + Mockito)
- Compatible con H2 (dev) y PostgreSQL/MySQL

# Instalación rápida
1. Clonar repositorio:
   ```bash
   git clone https://github.com/RichyGama/babel-test.git
   cd babel-test
   
# Endpoints principales
GET /api/babel/empleados - Listar todos
GET /api/babel/empleados/{id}  - Listar por ID
POST /api/babel/empleados - Crear empleado
PUT /api/babel/empleados/{id} - Actualizar
DELETE /api/babel/empleados/{id} - Eliminar

# Documentación
Accede a Swagger UI en:
http://localhost:8080/swagger-ui.html

# Ejemplo de petición (POST)
json
{
  "primerNombre": "Ricardo",
  "apellidoPaterno": "Gama",
  "edad": 35,
  "sexo": "M",
  "fechaNacimiento": "30-10-1988",
  "puesto": "Java Developer"
}

# Ejecución
bash
mvn spring-boot:run

Nota: Asegúrate de tener Java 17 y Maven instalados previamente.

