# Usamos una imagen base de Tomcat con JDK 8
FROM tomcat:9-jdk8

# Copiamos el archivo WAR de la aplicación en el directorio de despliegue de Tomcat
COPY target/nominaEmpleadosBack.war /usr/local/tomcat/webapps/nominaEmpleadosBack.war

# Exponemos el puerto 8080 para acceder a la aplicación
EXPOSE 8080

# Inicia Tomcat
CMD ["catalina.sh", "run"]