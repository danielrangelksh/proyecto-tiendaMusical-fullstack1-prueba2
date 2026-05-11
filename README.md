# proyecto-tiendaMusical-fullstack1-prueba2
## Descripción.

Creamos una plataforma digital enfocado en un e-commerce de instrumentos musicales, en donde contamos con un login para nuestros usuarios, el cual entregará un token para poder acceder a los distintos endpoints de nuestra aplicación. Es importante mencionar que todos los microservicios tienen un endpoint “/publico”, de esta manera demostrar que los token funcionen correctamente en cada microservicio. 
Contamos con una base de datos de nuestros clientes, en donde se guardará la información básica de cada cliente que quiera acceder a nuestra tienda, y también con un catálogo con distintos instrumentos musicales.
Si bien nuestra tienda en un principio fue pensada para tener dos microservicio de “PedidoVenta” y “PedidoArriendo”, en esta versión se utilizó solamente la clase “pedidos” para demostrar cómo es que funciona la conexión entre Cliente y Producto al microservicio, obteniendo de estos el nombre y apellido del cliente, el número de serie,  nombre de producto, su precio, etc. 
También contamos con devolución y servicio técnico, la cual estarán obteniendo los datos directamente desde el microservicio pedidos, de esta manera nos aseguramos que este tipo de requerimientos solo vengan con los datos de algún pedido ya realizado, obteniendo coherencia en el modelo de negocio que queremos lograr.
Para nuestra tienda es sumamente importante que la comunicación con nuestros clientes sea clara y fluida, es por esto que creamos un microservicio de reclamos, en donde se podrá ingresar el id de un cliente ya registrado en nuestro sistema para obtener los datos como su nombre y apellido para ser rellenado en la base de datos.
Si bien en esta primera versión sólo tenemos “pedidos”, la idea innovadora de nuestro proyecto es el lado de “arriendo”, como músico, creo que una tienda que pueda arrendar sus instrumentos musicales sería un gran acierto al mercado, ya que si un músico tuviera la urgencia de necesitar de un instrumento ya que el suyo tuvo una falla, o simplemente no quiere someter su instrumento a un viaje largo por el  mal trato al momento de llevarlo en la bodega del avión o del bus, puedan arrendar su instrumento a través de esta plataforma, ofreciendo una alternativa para salir de un apuro profesional.

## ¿Cómo usar el proyecto?

Para empezar se debe abrir la aplicación **Postman** para poder visualizar, agregar, actualizar y eliminar los datos de los servicios y también para poder acceder al token que se necesita para poder acceder a la aplicación. No obstante, también ocupamos **Laragon** con su base de datos ya que en este lugar ingresaremos las tablas con los datos de los clientes, pedidos, productos, etc. También es necesario conocer los microservicios con los que cuenta nuestro proyecto. Tales son:  

***Se encuentran ordenados según sus puertos***
1. **auth-Service** -8080  
2. **cliente** -8081  
3. **reclamos** -8082  
4. **producto** -8083
5. **servicioTecnico** -8084
6. **pedidos** -8085
7. **devolucion** -8086

### IMPORTANTE  
Antes de iniciar todos los microservicios es necesario tener abierto postman y laragon junto con la base de datos.  
También es importante saber que las credenciales para que la base de datos funcione son:  
```
username: root  
password: system  
```
Accederemos al apartado de **Laragon.MySQL**, daremos click en **consulta**, ingresaremos y ejecutaremos el siguiente script para crear nuestra base de datos:  
```
CREATE DATABASE db_tiendamusical;
```
También al momento de abrir cada microservicio es necesario instalar el JDK (la versión 21) y hacer RELOAD del MAVEN para que el mismo pueda funcionar y tome todas las dependencias.   
Cabe destacar también que si el usuario no cuenta con ninguna credencial solamente podrá ocupar la entrada publica o (/publico) en el cual nos confirmará si el microservicio se encuentra activo.  

## Auth-Service:  
**Si no cuentas con credenciales**  
```
http://localhost:8080/auth/publico
```
**Si cuentas con credenciales**
```
http://localhost:8080/auth/login
```
En el postman, colocaremos el link para acceder con nuestras credenciales al token. Para ello colocaremos el método POST, luego en body y por último escogeremos el modo raw ya que ahí podremos acceder al formato JSON donde podremos ingresar todos los datos.  

***IMPORTANTE:*** **Desde este punto en adelante la mayoría de ingreso de datos se realizarán con el mismo método Postman>Ingreso de localhost + puerto + método>tipo de acción o método que queremos realizar (GET, POST, PUT, DELETE)>Body>Raw>JSON>SEND luego de haber ingresado los datos o luego de seleccionar lo que queremos buscar**  

En el JSON colocaremos los siguientes datos para acceder al token:  
```
{
  "username": "admin",
  "password": "1234"
}
```
Al ingresar y clickar en "SEND" se nos hará entrega del token, estaríamos viendo un formato parecido a este:
```
{
  "mensaje": "Usuario autorizado",
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbCI6IkFETUlOIiwiaWF0IjoxNzc4MzkwMTIyLCJleHAiOjE3NzgzOTM3MjJ9.bHLO51HLLs-p0GzqNvvEGZT4oMYodJqWJmDdG9gaWx8",
  "username": "admin",
  "rol": "ADMIN"
}
```
Ese texto largo es el token que copiaremos, luego iremos al apartado de "Authorization" en postman. Estará como predeterminado la opción de "No auth", seleccionaremos la opción "Bearer token" y pegaremos el token que se nos entregó.  
Luego de esto estaríamos autorizados para realizar cualquier acción en todos los microservicios.  

***IMPORTANTE:*** **NO DESACTIVAR EL MICROSERVICIO AUTH-SERVICE MIENTRAS ESTÉS OCUPANDO EL PROYECTO, DE LO CONTRARIO EL SISTEMA TE PEDIRÁ OTRO TOKEN.**

## Cliente.  
Con la siguiente entrada podremos visualizar con un GET el listado total de todos los clientes ingresados y también podremos ingresar un nuevo cliente con un POST en la base de datos:
```
http://localhost:8081/api/clientes
```
Para ingresar un cliente debemos ocupar el siguiente formato:
```
{
  "run": "99.999.999-9",
  "nombre": "Roberto",
  "apellido: "Carlos Suarez",
  "correo": "CarlosSuarezRoberto@Gmail.com",
  "fechaNacimiento": "1999-10-10"
}
```
Cabe destacar que ninguno de estos datos pueden estar vacíos, a excepción de fechaNacimiento. Luego de ingresar los datos le damos a SEND y ya quedaría ingresado el cliente.  

***IMPORTANTE:*** **El ID de los clientes se genera automático por cada cliente, al momento de ingresarlo te muestra el ID asignado al mismo.**

Si queres buscar a un cliente por su ID, ya sea para ver sus datos (GET), actualizarlos (PUT) o borrar el cliente (DELETE) debemos ocupar la siguiente entrada:
```
http://localhost:8081/api/clientes/{id}
```
***EN {id} VA EL NUMERO ID DEL CLIENTE EN CUESTIÓN***  
***PARA ACTUALIZAR EL CLIENTE SE DEBE OCUPAR EN EL PUT EL MISMO FORMATO QUE SE USA AL MOMENTO DE INGRESARLO***  
***DOS O MÁS CLIENTES NO PUEDEN TENER EL MISMO NÚMERO DE RUN, NI AL INGRESARLO, NI AL ACTUALIZARLO***  

***IMPORTANTE:*** **El microservicio cliente tampoco puede ser desactivado al momento de ocupar los microservicios que dependen de él.**

## Producto.
Con la siguiente entrada podremos visualizar con un GET el listado total de todos los productos ingresados y también podremos ingresar un nuevo producto con un POST en la base de datos: 
```
http://localhost:8083/api/productos
```
Para ingresar un producto debemos ocupar el siguiente formato:
```
{
  "numeroSerie": "SN-STRAT-001",
  "nombreProducto": "Fender Stratocaster",
  "tipoInstrumento": "Cuerdas",
  "descripcion": "Guitarra eléctrica clásica color Sunburst",
  "precioInstrumento": 1200000,
  "precioArriendo": 45000,
  "ventaArriendo": "Venta",
  "estado": "Disponible",
  "fechaRegistro": "2026-01-10"
}
```
Cabe destacar que la descripción, si es venta o arriendo, el nombre del producto y su estado (disponible, vendido, arrendado, agotado) y el tipo de instrumento son obligatorios.

***IMPORTANTE:*** **El ID de los productos se genera automático por cada producto, al momento de ingresarlo te muestra el ID asignado al mismo.**

Si queres buscar a un producto por su ID, ya sea para ver sus datos (GET), actualizarlos (PUT) o borrarlo del sistema (DELETE) debemos ocupar la siguiente entrada:
```
http://localhost:8083/api/productos/{id}
```
***EN {id} VA EL NÚMERO ID DEL PRODUCTO EN CUESTIÓN***
***PARA ACTUALIZAR EL PRODUCTO DEBE OCUPAR EN EL PUT EL MISMO FORMATO QUE SE USA AL MOMENTO DE INGRESARLO***
***DOS O MÁS PRODUCTOS NO PUEDEN TENER EL MISMO NUMERO DE SERIE, NI AL INGRESARLO NI AL ACTUALIZARLO***

***IMPORTANTE:*** **El microservicio producto tampoco puede ser desactivado al momento de ocupar los microservicios que dependen de él.**

## Pedidos.
Con la siguiente entrada podremos visualizar con un GET el listado total de todos los pedidos ingresados y también podremos ingresar un nuevo pedido con un POST en la base de datos:
```
http://localhost:8085/api/pedidos
```
Acá ya empezamos a experimentar como los microservicios se conectan entre sí, ya que la clase pedidos depende de la clase clientes y de la clase productos.  
Para ingresar un pedido debemos ocupar el siguiente formato:
```
{
  "productoId": "2",
  "clienteId": "3",
  "fechaPedido": "2026-10-10",
  "precioEnvio": "10000",
  "direccion": "Av la florida 81"
}
```
Cabe destacar que el id del producto y del cliente, el precio de envío, la fecha del pedido y la dirección son campos obligatorios.

***IMPORTANTE:*** **El ID del pedido se genera automático por cada pedido, al momento de ingresarlo te muestra el ID asignado al mismo.**

Acá ya se puede ver como se conectan los microservicios y como operan entre sí, ya que si buscamos el pedido que ingresamos con su ID ya sea para borrarlo (DELETE), visualziarlo (GET) o actualizarlo (PUT):
```
http://localhost:8085/api/pedidos/{id}
```
Podremos ver el formato que toma y los datos que trae de la base de datos:
```
{
  "id": 1,
  "productoId": 2,
  "clienteId": 3,
  "nombreCliente": "Lucas Silva",
  "numeroSerie": "SN-YAM-P45",
  "nombreProducto": "Yamaha P-45",
  "tipoInstrumento": "Teclado",
  "descripcion": "Piano digital de 88 teclas pesadas",
  "precioInstrumento": 450000,
  "precioArriendo": 25000,
  "ventaArriendo": "Arriendo",
  "estado": "Disponible",
  "fechaRegistro": "2026-02-15",
  "direccion": "Av la florida 81",
  "precioEnvio": 10000,
  "fechaPedido": "2026-10-10"
}
```
***EN {id} VA EL NÚMERO ID DEL PEDIDO EN CUESTIÓN***

***IMPORTANTE:*** **El microservicio pedidos tampoco puede ser desactivado al momento de ocupar los microservicios que dependen de él.**

## Servicio Técnico.
Con la siguiente entrada podremos visualizar con un GET el listado total de todos los tickets de servicio técnico ingresados y también podremos ingresar un nuevo ticket con un POST en la base de datos: 
```
http://localhost:8084/api/servicioTecnico
```
Este microservicio depende de la clase pedido, es decir, este tomará los datos del pedido realizado anteriormente.  
Para ingresar un nuevo ticket de servicio ténico se tiene que utilizar el siguente formato POST:
```
{
  "pedidoId": "1" ,
  "falla": "Se le dañó el cable de poder del teclado.",
  "descripcion": "Mientras se estuvo utillizando el teclado el cliente recibió un leve corte de luz que, al estar enchufado, el cable de poder del teclado comenzó a fallar.",
  "fechaIngreso": "2026-11-11",
  "estado": "recibido"
}
```
Cabe destacar que toda la información que hay que ingresar es importante, a excepción de la fecha de ingreso, y solo hay dos estados que son "RECIBIDO" y "ENTREGADO".

***IMPORTANTE:*** **El ID del ticket se genera automático por cada ticket, al momento de ingresarlo te muestra el ID asignado al mismo.**

Acá podemos ver como al traer la información de la clase pedidos, esta también trae consigo la información de las clases clientes y productos. Si queres buscar un ticket de sercivio técnico por su ID, ya sea para ver sus datos (GET) o borrarlo del sistema (DELETE) debemos ocupar la siguiente entrada:

```
http://localhost:8084/api/servicioTecnico/{id}
```

Podemos ver el formato que trae de la base de datos:
```
{
  "id": 2,
  "numeroSerie": "SN-YAM-P45",
  "pedidoId": 1,
  "nombreCliente": "Lucas Silva",
  "nombreProducto": "Yamaha P-45",
  "fechaIngreso": "2026-11-10",
  "falla": "Se le dañó el cable de poder del teclado.",
  "descripcion": "Mientras se estuvo utillizando el teclado el cliente recibió un leve corte de luz que, al estar enchufado, el cable de poder del teclado comenzó a fallar.",
  "estado": "recibido"
}
```
***EN {id} VA EL NÚMERO ID DEL TICKET DE SERVICIO TÉCNICO EN CUESTIÓN***

## Devolución.
Con la siguiente entrada podremos visualizar con un GET el listado total de todos los tickets de devolución ingresados y también podremos ingresar un nuevo ticket con un POST en la base de datos: 
```
http://localhost:8086/api/devoluciones
```
Este microservicio depende de la clase pedido, es decir, este tomará los datos del pedido realizado anteriormente.  
Para ingresar un nuevo ticket de devolución se tiene que utilizar el siguente formato POST:
```
{
  "pedidoId": "1",
  "motivo": "El teclado no funcionó más después de un corte leve de luz",
  "requerimiento": "CAMBIO"
}
```
Acá todo lo que se pide es de campo obligatorio. Al igual que el servicio técnico, este al ir conectado con la clase pedidos, trae información de las clases clientes y productos. 

***IMPORTANTE:*** **El ID del ticket se genera automático por cada ticket, al momento de ingresarlo te muestra el ID asignado al mismo.**

Si queres buscar un ticket de devolución por su ID, ya sea para ver sus datos (GET) o borrarlo del sistema (DELETE) debemos ocupar la siguiente entrada:
```
http://localhost:8086/api/devoluciones/{id}
```
Así se vería la información completa al buscar:
```
{
  "id": 1,
  "pedidoId": 1,
  "requerimiento": "CAMBIO",
  "motivo": "El teclado no funcionó más después de un corte leve de luz",
  "nombreCliente": "Lucas Silva",
  "numeroSerie": "SN-YAM-P45",
  "nombreProducto": "Yamaha P-45",
  "precioInstrumento": 450000
}
```
***EN {id} VA EL NÚMERO ID DEL TICKET DE DEVOLUCIÓN EN CUESTIÓN***

## Reclamos.
Con la siguiente entrada podremos visualizar con un GET el listado total de todos reclamos y también podremos ingresar un nuevo reclamo con un POST en la base de datos: 
```
http://localhost:8082/api/reclamos
```
Este microservicio depende de la clase cliente, es decir, este tomará los datos del cliente ingresado anteriormente.  
Para ingresar un nuevo reclamo se tiene que utilizar el siguente formato POST:
```
{
  "clienteId": "2",
  "fechaRegistro": "2026-10-10",
  "asunto": "Programa muy lento",
  "descripcion": "El programa tardó mucho tiempo en realizar ingresar mi pedido."
}
```
Los campos de id del cliente, asunto y descripción son obligatorios. 

***IMPORTANTE:*** **El ID del reclamo se genera automático por cada reclamo, al momento de ingresarlo te muestra el ID asignado al mismo.**

Si queres buscar un reclamo por su ID, ya sea para ver sus datos (GET) o borrarlo del sistema (DELETE) debemos ocupar la siguiente entrada:
```
http://localhost:8082/api/reclamos/{id}
```
Así se vería la información completa al buscar:
```
{
  "id": 2,
  "clienteId": 2,
  "nombreCliente": "María González",
  "fechaRegistro": "2026-10-10",
  "asunto": "Programa muy lento",
  "descripcion": "El programa tardó mucho tiempo en realizar ingresar mi pedido."
}
```
***EN {id} VA EL NÚMERO ID DEL RECLAMO EN CUESTIÓN***
