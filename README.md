
### _Proyecto Prueba Nach_


## Pre-Requisitos


* Java 8 (Instalado y Configurado)

* IDE para compilar proyectos Java (Instalado y Configurado en este caso use IntelliJ IDEA)

* lombok instalado en el IDE

* Maven para repositorio de dependencias (Instalado y Configurado)

## Instalación

_Copiar y ejecutar el siguiente comando en una terminal git_

```
git clone https://github.com/jhonatanMD/ws-prueba.git
```

_Dirigirse al IDE y importar el archivo_

_En la ruta del proyeceto ejecutar el siguiente comando para bajar las dependencias y luego dar un Build_

```
mvn clean install
```

## Ejecución

* Dentro del IDE buscar el main que se encuentra en la clase WsPruebaApplication.java darle click derecho y hacer correr programa (Run) 

* Despues de Eso ir a postman y consumir los servicios : http://localhost:8080/ws/....

## Documentación

* Para la documentación ir a la ruta : http://localhost:8080/swagger-ui.html
* Se podra ver los endpoint de la api y su funcionalidad

## Caracteristicas del proyecto. 
* Los empleados son unicos , no se podran registrar empleados con el mismo nombre y apellido.
* Los empleados a registrar deben ser mayores de edad.
* No se podra registrar empleados si el trabajo o genero no existe.
* Al registrar las horas tendran un limite de 20 Horas diarias.

* Al realizar el calculo del pago entre fechas , se realizara la consulta por todas las horas que se trabajo y se multiplicara
con el salario del trabajo.
```
(horasTotates * salario = pagoTotal)
```