# RappiJob

## Arquitectura
![Clean Architecture](https://miro.medium.com/max/1838/1*B7LkQDyDqLN3rRSrNYkETA.jpeg)

El proyecto esta separado en diferentes módulos o capas, dentro de cada módulo se hace una separación por feature, se utiliza la librería Dagger para proveer las clases de un módulo a otro.

- Model

Módulo que contiene los modelos ocupados en la aplicación, se define un modelo como Entity para crear una tabla en la Base de Datos, igualmente tiene notaciones Serialized para parsear los datos obtenidos del servicio REST.

Contiene:
```
- Movie: Data class que define un objeto película.
- Series: Data class que define un objeto serie.
- Resource: Data class utilizado para definir cambios de estados al obtener datos dentro de la aplicación así como el dato que se quiere obtener.
```

- Remote

Módulo encargado de obtener datos consumiendo servicios REST, se utiliza la librería Retrofit para el consumo de los servicios.

Contiene:
```
- Service: Interfaz utilizada por Retrofit donde se definen los servicios que se ocuparán, como se deben consumir y el resultado que obtendremos.

- ServiceFactory: Clase que define la configuración de Retrofit.

- ApiResult: Data class para obtener la lista de películas/series despúes de consumir un servicio.

- MovieRemote/SeriesRemote: Clases que exponen los diferentes servicios para películas/series.

- RemoteModule: Clase auxiliar para Dagger que define como proveer las clases del módulo Remote. 
```

- Local

Módulo encargado de obtener datos de una Base de Datos creada localmente en el dispositivo, se utiliza la librería Room como gestor para la base de datos.

Contiene:
```
- RappiJobDatabase: Define como debe ser creada la Base de Datos, asi como las clases auxiliares para obtener los datos(Daos).

- BaseDao: Clase padre que define el query para insertar datos remplazandolos en caso que ya existan.

- MovieDao/SeriesDao: Clase que define los diferentes querys para obtener/guardar datos de películas/series.

- LocalModule: Clase auxiliar para Dagger que define como proveer las clases del módule Local.
```

- Repository

Módulo encargado de proveer datos, los datos puede proveenir de una Base de Datos o de un servicio REST.

Contiene:
```
- DataDelivery: Clase auxiliar que define el flujo para obtener datos principalmente de un servicio REST y guardarlo en la Base de Datos, en caso de ocurrir un error al consumir el servicio REST, obtiene la información de la Base de Datos.

- MovieRepository/SeriesRepository: Clase que define las diferentes funciones para proveer información de películas/series.

- RepositoryModule: Clase auxiliar para Dagger que define como proveer las clases del módulo Repository.
```

- Domain

Módulo donde se definen las reglas de negocio, provee la información obtenida de una clase del módulo Repository.

Contiene:
```
- MovieUseCases/SeriesUseCases: Clase que obtiene los datos del módulo Repository para películas/series y los provee al módulo App.
```

- App

Módulo encargado de presentar la información al usuario con los diferentes componentes de Android, se utiliza Navigation Component para presentar la información utilizando Fragments.

Contiene:
```
- MoviesViewModel/SeriesViewModel: Clase que provee los datos obtenidos de un UseCases ejecutando una Coroutine en un hilo diferente al principal, utiliza LiveData para proveer los datos.

- MoviesFragment/SeriesFragment: Clase enlazada a un layout para presentar los datos al usuario, se subscribe a una variable LiveData definida en un ViewModel respondiendo a sus cambios de estado.

- ActivityModule: Clase auxiliar de Dagger que ayuda a injectar clases en las clases Fragments.

- ViewModelFactory: Clase auxiliar que configura la clase ViewModelProvider.

- ViewModelModule: Clase auxiliar de Dagger que define como proveer las clases ViewModel.

- ApplicationComponent: Interface que define que clases debe injectar Dagger y como se deben inicializar en caso de que requieran alguna información adicional.

- RappiJobApplication: Clase que inicia la inyección de Dagger.
```

___

1. En qué consiste el principio de responsabilidad única? Cuál es su propósito?

Significa que si tienes un método solo debe haber una razón para realizar modificaciones a ese método.

El próposito es que, suponiendo que se tiene una validación que debe ser modificada constantemente, esta validación debería estar en otro método, asi no se afecta el propósito del método principal.


2. Qué características tiene, según su opinión, un “buen” código o código limpio?

Debería ser fácil de modificar, por decir si se tiene que hacer un arreglo en el código, es rápido encontrar donde hacer la modificación. También debe ser fácil agregar nuevas funciones al código.

Un buen código puede ser entendido por otro programador sin tener tanta noción sobre la aplicación.
