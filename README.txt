Documentacion Proyecto 1

CRUD Empresa para gestion de Empleados

- El proyecto esta dividido en dos carpetas que guardan la logica del proyecto (la clase empledo) y la persistencia, donde se encuentran los controladores JPA y sus respectivos metodos
La clase empleados tiene los valores a guardar, con el id como clave privada
El controladorPersistencia simplemente usa los metodos de el EmpleadoJPAController por lo que no hay mucho interes en ella
Dentro de EmpleadoJPAController se crean los metodos utilizados en todo el proyecto:
Las clases edit, create y destroy funciona de forma similar, conectendo con el EntityManager para comenzar la transaccion, cerrandola al acabar para proteger que no se quede abierto
    la llamada a la base de datos. Ademas estas tienen un throwExcption que controla si el id a cambiar existe, aunque tambien esto se controlara en el main para que el programa no muestre
    los fallos internos al usuario.
Las otras clases son principalmente de busqueda, tanto de un solo empleado como para mostar la lista completa o la lista de un solo cargo.

Funcionamiento basico del Main

La aplicacion muestra al usuario un menu donde podra seleccionar la accion que desea realizar, manteniendo el proceso en bucle hasta que el usuario desee salir del programa.

- Añadir empleado
    Esta funcion primero comprueba el ultimo id añadido para que el empleado creado tenga el siguiente id, que se añade de forma automatica, protegiendo el programa de que se creen empleados con mismo id
    Posteriormente se llama al metodo de creacion de empleado, que mantiene el codigo principal algo mas limpio.
    Este metodo simplemente pregunta al usuario todas las variables a añadir, protegiendo de algun valor nulo o no valido mediante la excepcion ParseException.
    Tambien se utiliza el SimpleDateFormat para guardar la fecha aportada por el usuario en forma de String, asi poder ser parseada en ultimo momento a .sql.date para la base de datos
    Ademas se muestra los datos del usuario añadido por comodidad del usuario

- Borrar empleado
    Metodo sencillo que muestra primero la lista de empleados para que el usuario sepa el id que desea eliminar
    Tambien comprueba el id aportado antes de borrar para proteger el programa si no existe el mismo, comunicandoselo al usuario

- Buscar empleado
    De nuevo se comprueba que el usuario ha indicado un id correcto antes de mostar todos los datos

- Mostrar Lista
    Añadido por si se desea comprobar la lista completa

- Editar empleado
    Despues de comprobar si el usuario existe se llama un metodo que es un bucle que permite modificar todos los valores de un id seleccionado, permitiendo al usuario elegir que valores modificar y cuando desea detener este proceso
    Tambien se muestra los cambios realizados

- Buscar por cargo
    Funciona de la misma manera que la lista completa, pero creando una funcino que seleccione los empleados del cargo seleccionado dentro de esa lista
    Esto se consigue mediante el metodo CriteriaBuilder que nos permite crear la consulta especifica que necesitamos, en este caso el buscar por cargo dentro de la Lista completa de empleados

Pruebas

Al inicio del main se puede ver funciones rapidas para cargar la base de datos con empleados para realizar las posibles pruebas
