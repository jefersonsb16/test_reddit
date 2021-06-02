# Test Reddit

### Descripción de la responsabilidad de cada capa ###

El proyecto se basa en Clean Architecture usando el patrón de presentación MVVM, y se trabajó con las siguientes capas:

* Presentation (app): En esta capa se integra todo lo que tiene que ver con la interfaz de usuario (Activities/Fragments) y sus respectivos view models.
* Use Cases: Se identifican las acciones más relevantes que el usuario puede ejecutar en el uso de la app y se definen en esta capa, ejemplo (Consultar las películas populares que ofrece la Api).
* Domain: Se agregan las entidades con las que se trabaja la lógica de negocio de la app.
* Data: En esta capa se hace uso del patrón repository y se establecen las distintas fuentes de datos a utilizar a partir de abstracciones.
* Framework: En esta capa se realiza el detalle de la implementación del uso de bibliotecas como retrofit y room.

### ¿En qué consiste el principio de responsabilidad única? ¿Cuál es su propósito?

Este primer principio de los principios SOLID tiene como proposito definir o marcar un estandar para definir las responsabilidades que se le asignan a cada clase o capa, esto quiere decir que una clase o capa solo debe tener un único fin para existir. Esto lo podemos ver cuando definimos nuestra capa de presentación, esta básicamente se enfocará en presentar la información al usuario. Este principio es importante ya que si una clase crece mucho en responsabilidades se vuelve díficil darle mantenimiento y en caso de errores se puede generar un efecto en cascada.

### Requerimientos técnicos para construir el proyecto ###

La aplicación se construyó y compiló usando las siguientes versiones:

* Android Studio 4.2
* Versión del Gradle 6.7.1
* Versión de Kotlin 1.4.31
* SDK mínimo: 23
* SDK objetivo: 30
* JDK 1.8
