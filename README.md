# **MedicoElectronico** - Aplicación de Gestión de Registros Médicos

# **Autores** - Pablo Barbosa, Augusto Perrone, Assil Asri y Daniel Sousa

## Descripción del Proyecto

**MedicoElectronico** es una aplicación móvil diseñada para ofrecer a los usuarios una forma segura y eficiente de gestionar su historial médico personal. La aplicación permite a los usuarios agregar, actualizar, visualizar y compartir sus registros médicos con facilidad. Además, está integrada con **Firebase Authentication** para una autenticación segura, y permite la interoperabilidad con sistemas de salud mediante el uso del estándar **FHIR** (Fast Healthcare Interoperability Resources). Los usuarios pueden generar reportes en formato PDF y compartirlos con profesionales médicos, todo dentro de un entorno seguro y privado.

### Características Principales

- **Autenticación segura**: Integración con **Firebase Authentication** para permitir a los usuarios iniciar sesión de manera segura con su correo electrónico y contraseña.
- **Gestión de registros médicos**: Los usuarios pueden ingresar y actualizar su historial médico, incluyendo detalles como diagnóstico, tratamiento, nombre del médico y fecha de la consulta.
- **Visualización de registros**: Los usuarios pueden acceder a una lista completa de sus registros médicos a través de un `RecyclerView`.
- **Generación y compartición de PDFs**: La aplicación permite generar un archivo PDF con los detalles del registro médico y compartirlo con otros profesionales de la salud.
- **Integración con servidores FHIR**: Envío de los datos médicos al servidor FHIR para interoperabilidad con sistemas de salud externos.
- **Almacenamiento local seguro**: Utiliza **Room** como base de datos local para almacenar los registros médicos de manera eficiente y segura.

## Requisitos del Sistema

Antes de comenzar con el uso de la aplicación, asegúrese de que su entorno de desarrollo cumpla con los siguientes requisitos:

- **Android Studio**: Para compilar y ejecutar el proyecto en dispositivos Android.
- **Java 8 o superior**: Necesario para ejecutar la aplicación, dado que algunas funcionalidades como las corutinas y la API de FHIR requieren de Java 8.
- **Firebase**: La aplicación utiliza **Firebase Authentication** para gestionar la autenticación de los usuarios. Necesitarás configurar un proyecto en Firebase.

## Configuración del Proyecto

### 1. **Clonar el Proyecto**

Para comenzar, clona el proyecto desde el repositorio utilizando Git:

```bash
git clone https://github.com/pablobarbosaojeda/MedicoElectronico.git
