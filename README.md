# Sigapp


App de android que hace de seguidor de carrera y tramites (entre otras cosas) con información del sistema de la universidad [SIGA](http://www.siga.frba.utn.edu.ar/) (que lamentablemente no tiene un API)

Se encuentra en las primeras etapas del desarrollo. Para usarlo hay que tener instalado el [Android Studio](http://developer.android.com/intl/es/sdk/index.html)

# Estructura del proyecto

Nota: Se excluyen algunos archivos/carpetas para mantenerlo sintético

```
Sigapp/app/src
|
|--test/java/com/example/usuario/siga : tests unitarios
|
+-main
  |--java/com/example/usuario/siga/
  |   |--fileloader/
  |   |   |
  |   |   +--FileLoader.java  : utilitario para leer archivos en android (se acceden por medio de Context)
  |   |
  |   |--service/
  |   |   |
  |   |   |--Service.java     : interfaz para servicios del SIGA (por ejemplo, login)
  |   |   +--crawler/         : crawler con el que se scrapea el SIGA, es usado por Servicios que scrapean aunque la idéa despues es usar un API JSON
  |   |
  |   |--sigapi/              : interfaz desde donde se usan los servicios del SIGA. inyectable a javascript
  |   |
  |   |--sigapifactory/       : factories de SigaApi's. Por ahora solo existe un SigApi que crawlea la web del SIGA
  |   |
  |   +--MainActivity.java    : punto de entrada de la aplicación
  |
  +--/assets/www/             : frontend web
```

#Screenshots

![alt tag](https://github.com/julianSelser/Sigapp/blob/showcase/showcase-imgs/146013516110873.png)
![alt tag](https://github.com/julianSelser/Sigapp/blob/showcase/showcase-imgs/146013536284484.png)


