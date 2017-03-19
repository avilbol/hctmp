# Frontend de Hallocasa

### Instalación

 - Instale NodeJS, el proyecto de Frontend de Hallocasa requiere de Node.js v4+ para funcionar, lo puede descargar desde el siguiente [enlace](https://nodejs.org/es/download/)
 - Compruebe que la instalación se ha realizado correctamente ejecutando en consola el comando
    ```sh
    $ node -v
    ```
 - Instale globalmente el gestor de paquetes Bower y el gestor de tareas Gulp
    ```sh
    $ npm install -g gulp bower
    ```
 - Estando dentro de la carpeta del proyecto instale tanto las dependencias de Bower y de Node
    ```sh
    $ cd hallocasa-portal/HalloCasa/hallocasa-view
    $ npm install
    $ bower install
    ```
### Lista de tareas disponibles
Para ejecutar las siguientes tareas debe haber realizado todos los pasos del proceso de instalación y debe estar situado en la carpeta del proyecto 

| Tarea | Descripción |
| ------ | ------ |
| serve | Usado en fase de desarrollo, el comando `gulp serve` inicia un servidor local que soporta eventos de recargas del navegador al modificar archivos locales|
| inject | El comando `gulp inject` inyecta las dependencias declaradas en Bower dentro del archivo index.html, solo usado cuando el proceso no se realiza automáticamente|
| build | El comando `gulp build` genera la versión de producción del proyecto, el resultado del proceso queda almacenado en la carpeta /dist |
| serve:dist | El comando `gulp serve:dist` inicia un servidor local a partir de la versión de producción del proyecto |

Podrá encontrar mayor información de estos comandos y de sus procesos [aquí](https://github.com/Swiip/generator-gulp-angular/blob/master/docs/user-guide.md)
