# Proyecto NominaEmpleados sin Framework(Servlet) y con PostgreSQL

Este es un proyecto de ejemplo utilizando **Servlets** y **PostgreSQL** como base de datos. A continuación, se proporciona información sobre cómo configurar el proyecto, importar la base de datos en PostgreSQL y ejecutar la aplicación tanto en una máquina local como utilizando **Docker Compose**.

## Requisitos

Antes de comenzar, asegúrate de tener los siguientes requisitos instalados en tu sistema:

- **Java 8**
- **Maven 3.9.4** para la gestión de dependencias
- **Tomcat 9** (Si deseas ejecutarlo fuera de un contenedor)
- **Docker** y **Docker Compose** (si deseas ejecutar el proyecto en contenedores, ademas la composición te brinda la base de datos y pgadmin4)
- **PostgreSQL** o un contenedor Docker para PostgreSQL
- **Nginx** Para despliegue de HTML y JS

## Configuración del Proyecto

### Clona el repositorio de Git:

```
git clone https://github.com/martinmgb/nominaempleadosback.git
```
### Importar la Base de Datos desde el archivo SQL ubicado en el repositorio

Puedes importarlo de la siguiente manera:

Acceder a tu servidor PostgreSQL:

Si tienes PostgreSQL instalado en tu máquina, puedes usar el siguiente comando para acceder al cliente interactivo de PostgreSQL:
```bash
psql -U postgres -h localhost
```

Si tienes un contenedor Docker con PostgreSQL, accede al contenedor:

docker exec -it nombre_del_contenedor psql -U postgres

Lo puedes hacer tambien desde pgadmin (en tu maquina o contenedor)

### Crear la base de datos (si aún no existe):

En el cliente de PostgreSQL, crea una base de datos para tu proyecto:
```bash
CREATE DATABASE empresas_bd;
```
Lo puedes hacer tambien desde pgadmin (en tu maquina o contenedor)

### Importar el archivo SQL:

Una vez que tengas la base de datos creada, puedes importar el archivo empleados_bd.sql ubicado en /rutaproyecto/script/empresas_bd.sql con el siguiente comando:

```
psql -U postgres -d empresas_bd -f /ruta/a/tu/empleados_bd.sql
```
Si usas Docker, el comando sería similar pero desde dentro del contenedor:

docker exec -i nombre_del_contenedor psql -U postgres -d empresas_bd -f /ruta/a/tu/empresas_bd.sql

Esto importará todas las tablas, datos, índices y relaciones definidas en el archivo .sql a tu base de datos PostgreSQL.

Lo puedes hacer tambien desde pgadmin (en tu maquina o contenedor)

## Ejecutar el Proyecto

### Ejecutar en una máquina local
Compilar el proyecto con Maven, esto dejara archivo war en el directorio target el cual podras desplegar en tu servidor Tomcat. IMPORTANTE: Debes en tu servidor tomcat configurar las siguientes variables de entorno de configuracion de conexion a la base de datos y esten disponibles para el aplicativo que vas a desplegar.

```bash
DB_HOST=localhost DB_PORT=5432 DB_DATABASE=empleados_bd DB_USER=root DB_PASSWORD=root
```

Una vez despleado estara disponible la api para ser consumida en el puerto predeterminado (8080).

Acceder a la aplicación:

Una vez que la aplicación esté en ejecución, podrás acceder a ella mediante un navegador o herramientas como Postman a los siguientes endpoints:

http://localhost:8080/nominaEmpleadosBack/api/empleados (Para operaciones atomicas sobre empleados)
http://localhost:8080/nominaEmpleadosBack/api/nominas/calcular (Para procesar archivo CSV con empleados)


### Ejecutar con docker compose
Compilar y ejecutar el proyecto con Maven:

```bash
mvn clean compile package
```

Finalmente ejecutar el docker compose:

```bash
docker-compose up --build
```
	
Esto levantará tres contenedores: postgres, pgadmin y api-nominaempleados

Todos estos comandos deben ejecutarse en la raiz del fuente donde estan ubicados los archivos de docker.
Para ejecutar en un contenedor Docker, asegúrate de tener Docker y Docker Compose instalados y configurados correctamente en tu máquina.
Si no tienes docker-compose instalado, puedes hacerlo siguiendo la guía oficial de Docker Compose.

Acceder a la aplicación:

Una vez que la aplicación esté en ejecución, podrás acceder a ella mediante un navegador o herramientas como Postman:

http://localhost:8080/nominaEmpleadosBack/api/empleados (Para operaciones atomicas sobre empleados)
http://localhost:8080/nominaEmpleadosBack/api/nominas/calcular (Para procesar archivo CSV con empleados)

### Depliegue de HTML y JS
Copiar carpetas ubicadas en /rutaproyecto/front/ en tu servidor de nginx nginx/html, inicia tu servidor si no lo tienes iniciado y podrás consumir los recursos a través de las siguientes urls:

http://localhost:${tu-puerto-nginx}/nominaEmpleados/ (Para operaciones atomicas de Empleados)
http://localhost:${tu-puerto-nginx}/cargarNominaCsv/ (Para procesamiento de archivo CSV con empleados)


## Tecnologías Usadas

Servlets
PostgreSQL (Base de datos relacional)
Docker y Docker Compose (Contenerización y orquestación)
Maven (Gestión de dependencias)
Nginx

# Estructura del Proyecto
```textplain
fuentes/back/nominaEmpleadosBack/
├── src/
│ ├── main/
│ │ ├── java/
│ │ ├── resources/
│ │ │ ├── config.properties
├── target/
│ └── mi-aplicacion.war
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
fuentes/front/
├── nominaEmpleados/
├── cargarNominaCsv/
script/
├── empleados_bd.sql
```

src/main/java: Contiene el código fuente de la aplicación.
src/main/resources: Contiene los archivos de configuración, como config.properties.
target: Contendrá el archivo WAR generado al compilar el proyecto con Maven.
Dockerfile: Archivo para construir la imagen Docker de la aplicación.
docker-compose.yml: Archivo de configuración para Docker Compose, que dispone de 3 contenedores configurados para postgresql, pgadmin y tomcat con la aplicacion desplegada y disponible en el puerto 8080.


## Notas adicionales
Si estás ejecutando el proyecto en tu máquina local y tienes problemas de conexión con PostgreSQL, asegúrate de que el servicio de PostgreSQL esté en ejecución y accesible en el puerto configurado (5432 por defecto).

# nominaEmpleados
