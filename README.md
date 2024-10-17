# Trabajo de Interfaces - La Triada

## Description
Proyecto en Java hecho usando las librerias Swing...

## Setup Instructions
1. Clona el repositorio en local.
2. Importa el proyecto en un Workspace de Eclipse: `File > Import > Existing Projects into Workspace`.
3. Run `App.java` file located in the `src/com/` folder.

## Instrucciones temporales de Git
git clone https://github.com/Grodondo/La-Triada-App.git  ->  Clona el repositorio github en local

git pull origin branch -> 
    git pull -> primero descarga los cambios del servidor remoto y luego los fusiona con tu rama local actual.
    origin = https://github.com/Grodondo/La-Triada-App.git
    branch es el nombre de la rama específica de la cual deseas traer esos cambios.

git push origin branch -> Exactamente igual, pero al reves.


## Dependencias
- JDK 11 or higher.

## Estructura -> Para leerla, necesitarás abrirlo en local.
/LaTriada
│
├── /src                    # Código fuente de la aplicación
│   ├── /com                # Paquete raíz para mantener tu código bien organizado
│   │   ├── /models          # Clases relacionadas con los modelos de datos
│   │   │   └── Car.java     # Clase que representa el objeto Coche
│   │   │
│   │   ├── /views           # Clases relacionadas con la interfaz gráfica (Swing)
│   │   │   ├── MainView.java # Ventana principal de la aplicación
│   │   │   └── CarView.java  # Vista para añadir o listar coches
│   │   │
│   │   ├── /controllers     # Lógica de control y manejo de eventos
│   │   │   ├── CarController.java  # Lógica para gestionar los coches
│   │   │   └── MainController.java # Lógica para la ventana principal
│   │   │
│   │   ├── /database        # Clases relacionadas con la base de datos
│   │   │   ├── DatabaseConnection.java # Clase para manejar la conexión a la BD
│   │   │   └── CarDAO.java           # Clase para realizar operaciones con la BD
│   │   │
│   │   └── App.java         # Clase principal para iniciar la aplicación
│   ├── /resources           # Recursos como imágenes, archivos de configuración, etc.
│   │   ├── /images          # Imágenes para la aplicación
│   │   └── config.properties # Configuración de la base de datos
│   │
│   └── tests   # Paquete para las pruebas
│           └── CarTest.java      # Pruebas unitarias para la clase Car
│
├── /lib                     # Librerías externas que usará el proyecto
│   └── sqlite-jdbc.jar       # Ejemplo de driver para SQLite, si es el caso
│
│
├── .gitignore                # Ignorar archivos no relevantes (como /bin, /.settings)
├── build.gradle              # Si usas Gradle como herramienta de compilación
├── pom.xml                   # Si usas Maven
└── README.md                 # Información sobre el proyecto
