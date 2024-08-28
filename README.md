# TechGirlNTTDATA-AutomatizacionWeb

# Proyecto de Automatización de Pruebas Web

Este proyecto contiene una serie de pruebas automatizadas para verificar el correcto funcionamiento de una tienda en línea. Las pruebas están desarrolladas utilizando Java, Selenium, Cucumber y JUnit.

## Estructura del Proyecto

- **src/main/java**: Contiene el código fuente principal, incluidas las clases necesarias para la ejecución de las pruebas.
- **src/test/java**: Contiene las clases de prueba y las definiciones de pasos de Cucumber.
- **src/test/resources/features**: Contiene los archivos `.feature` que definen los escenarios de prueba en lenguaje Gherkin.
- **pom.xml**: Archivo de configuración de Maven para la gestión de dependencias.

## Requisitos Previos

Para ejecutar este proyecto, necesitas tener instalados los siguientes elementos:

- Java Development Kit (JDK) 11 o superior.
- Maven 3.6.0 o superior.
- Un navegador compatible (Chrome, Firefox, etc.).
- [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/downloads).

## Ejecución de las Pruebas

1. Clona el repositorio en tu máquina local.
2. Navega a la carpeta raíz del proyecto.
3. Ejecuta el siguiente comando para instalar las dependencias:

   ```bash
   mvn clean install

4. Configura las variables de entorno para los reportes de cucumber: CUCUMBER_PUBLISH_ENABLED=true
