<h1>Challenge "LiterAlura"</h1>
<p align="left">
 <img src="https://img.shields.io/badge/Status-Finalizado-green">
 <img src="https://img.shields.io/badge/Release_date-August_2025-olive">
</p>

<h2> :memo: Descripción del Proyecto </h2>
<p>LiterAlura es el proyecto desarrollado para dar solución al challenge propuesto por Alura Latam y Oracle One en OracleNextEducation G8.</p>
<p>Se trata de un proyecto en dónde se ponen en práctica el uso y manejo de APIs públicas, la conexión con bases de datos, las consultas mediante derived queries y JPQL.</p>
<p>El programa hace uso del API publica Gutendex para obtener datos de distintos libros Clásicos. A través del API y la conexión de base de datos PostgreSQL se guardan los datos 
  tanto de los libros como de los autores en dos tablas diferentes, además de una tercera tabla que guarda la relación entre las dos anteriores. En base a los datos guardados, 
  se realizan 8 operaciones mediante búsqueda y filtrado de datos para practicar las derived queries y el JPQL.</p>

<h2> :point_right: Funcionalidades del proyecto</h2>
<p>El programa consta de 9 principales funciones, además de una 10ma para salir del programa.</p>

<div align="center">
  <img width="459" height="273" alt="imagen" src="https://github.com/user-attachments/assets/b8a90dfe-0864-469a-9e61-9a130ea93868"/>
</div>

<p>La primera opción hace uso del API de Gutendex para realizar una búsqueda de un libro a través de su título, para después, 
  en caso de no existir dentro de la base de datos, ser añadido a la base de datos. Es necesario escribir el nombre del libro que se desea buscar a través de la consola.</p>
<p>La segunda opción muestra todos los libros de los que se tienen registro en la base de datos. Cabe mencionar que inicialmente estará vacía a menos que se agregue un libro a través de la opción 1.</p>
<p>La tercera opción muestra a todos los autores de los que se tienen registros. Los autores son agregados de forma automática al agregar los libros mediante la primera opción.</p>
<p>La cuarta opción muestra una lista con todos los autores que se encontraban vivos en un determinado año, para de esa forma, conocer que autores vivían en el mismo periodo de tiempo. 
  Para esto es necesario ingresar de forma numérica de 4 dígitos un año.</p>
<p>La quinta opción filtra los libros existentes de la base de datos según el idioma. Para poder realizar el filtrado es necesario ingresar únicamente el código del idioma, 
  generalmente compuesto por dos únicos caracteres, ej. “es” para español o “en” para inglés.</p>
<p>La sexta opción se basa es mostrar las estadísticas de las descargas, mostrando el total de descargas de las que se tienen registro en la base de datos, el total de registros de libros que se tiene, 
  la media de descargas, el libro con la cantidad más altas de descargas, así como el libro con la menor cantidad de descargas.</p>
<p>La séptima opción muestra un top 10 libros más descargados, en caso de no existir más de 10 registros, solo se mostrarán los existentes ordenados en orden descendente.</p>
<p>La octava opción realiza una búsqueda en la base de datos en base al nombre de un autor. Cabe recalcar que es importante comenzar cada nombre con mayúscula y poner sus respectivos acentos. 
  En caso de querer buscar un autor por nombre y apellido, es necesario respetar la regla anterior además de seguir el orden de “apellido, nombre” para que pueda ser aceptado, caso contrario marcará como un autor inexistente en la base de datos.</p>
<p>La novena opción filtra los autores según una cantidad mínima de libros registrados en la base de datos que un autor ha escrito. Es necesario introducir un número entero que servirá como cantidad mínima para filtrar a los autores.</p>

<h2> :open_file_folder: Acceso al Proyecto</h2>
<p>Para poder hacer uso del programa es necesario contar con JDK (Java Development Kit) en su versión 24, el cual puede ser descargado desde el sitio oficial de <a href="https://www.oracle.com/mx/java/technologies/downloads/">Oracle.</a></p>

<p>A su vez, será necesario <a href="https://www.aluracursos.com/blog/como-configurar-variables-de-entorno-en-windows-linux-y-macos">crear algunas variables de entorno </a> para el correcto funcionamiento con los siguientes nombres y valores:</p>

| Nombre | Valor|
|------------|--------|
| DB_HOST | El host de la base de datos. En caso de estar corriendo de forma local basta con “localhost” (sin las comillas) |
| DB_LITERALURA | El nombre de la base de datos previamente creada en PostgreSQL. |
| DB_USER | Tú nombre de usuario en PostgreSQL |
| DB_PASSWORD | La contraseña de tu base de datos en PostgreSQL |

<h2> :paw_prints: Desarrolladora del proyecto</h2>
<p>
 <img src="https://avatars.githubusercontent.com/u/111654273?v=4" width=100px>
</p>

<p>
 <a href="https://github.com/NydiaOlmos">Nydia Olmos</a>
</p>

