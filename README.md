# Library Client – Microservicio 2

## Descripción
Microservicio consumidor encargado de consultar los usuarios activos
del Microservicio 1 (Library Access), aplicando mecanismos de resiliencia
ante latencia o fallos del servicio principal.

⚠️ **Nota importante**  
Este microservicio fue desarrollado inicialmente en **Java 8** y posteriormente
**migrado a Java 21 (LTS)** como mejora adicional.

---

## Responsabilidades
- Consumir el endpoint de consulta del MS1
- Implementar timeout y circuit breaker
- Gestionar fallback ante fallos

---

## Resiliencia
- Comunicación vía Feign Client
- Timeout máximo: **5 segundos**
- Circuit Breaker con fallback
- Manejo diferenciado de errores:
  - MS1 lento
  - MS1 no disponible

---

## Dependencia Externa
Este microservicio depende del siguiente servicio en ejecución:

- **Library Access (MS1)**  
  URL por defecto: `http://localhost:8081`

---

## Tecnologías Utilizadas
- Java 21 (LTS)
- Spring Boot 3.2.x
- Spring Cloud 2023.x
- OpenFeign
- Resilience4j
- Maven
- Git

---

## Requisitos
- JDK 21
- Maven 3.8+
- MS1 en ejecución

---

## Ejecución del Proyecto

`bash
mvn clean spring-boot:run

## Ejecución del Proyecto

bash
mvn clean spring-boot:run

Puerto: 8082

--- 

### Endpoints

| Método | Endpoint                | Descripción                  |
| ------ | ----------------------- | ---------------------------- |
| GET    | `/client/library/users` | Consulta de usuarios vía MS1 |


---

### Header opcional
MyFlag=true

---

#### Manejo de Errores (Formato Genérico)


Formato json

#### MS1 tarda más de 5 segundos (TIMEOUT)

{
  "code": 400,
  "type": "Error",
  "timestamp": 1763045191,
  "details": "El MS 1 tardo mas de lo esperado"
}


#### MS1 fuera de línea (apagado)

{
  "code": 400,
  "type": "Error",
  "timestamp": 1763045191,
  "details": "El MS 1 no se encuentra disponible"
}



### Error 500 (Timeout interno)

Cuando el Microservicio 1 tarda más de 5 segundos en responder, el Microservicio 2 aborta la llamada y genera internamente un error 500 con el siguiente formato genérico:

```json
{
  "code": 500,
  "type": "Error",
  "timestamp": 1763045191,
  "details": "El MS 1 tardo mas de lo esperado"
}



---


## Casos Prueba.

#### MS1 con latencia

- Header MyFlag=true

- MS1 tarda 8 segundos

- MS2 corta a los 5 segundos

Mensaje:

"El MS 1 tardo mas de lo esperado"

#### MS1 fuera de línea

"El MS 1 no se encuentra disponible"


---

### Versionado

-Rama java-8-stable: versión estable en Java 8

-Rama master: versión final en Java 21


---

#### Autor:

Proyecto desarrollado como ejercicio técnico de Backend Java

Edgar Eduardo Gutiérrez Mejia
