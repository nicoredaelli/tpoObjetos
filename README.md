# MatchMakerDeportivo

Sistema para la organización y gestión de partidos deportivos, con emparejamiento automatizado de jugadores, notificaciones multicanal y seguimiento del estado de cada encuentro.

## 🧩 Descripción

Este proyecto permite crear, administrar y participar en partidos deportivos (fútbol, básquet, vóley), considerando niveles de experiencia y ubicación de los jugadores. El sistema incluye:

- Registro y modificación de usuarios con preferencias deportivas.
- Creación y seguimiento de partidos.
- Emparejamiento automatizado según nivel, ubicación o aleatoriamente.
- Notificaciones vía SMS o email.
- Sistema de observadores para notificar eventos.
- Gestión del ciclo de vida del partido (necesita jugadores, armado, confirmado, en juego, finalizado, cancelado).
- Comentarios post-partido.

## 🏗️ Arquitectura

### Entidades Principales

- **Usuario**: nombre, correo, contraseña, deportes favoritos, niveles y medio de notificación preferido.
- **Partido**: contiene información sobre el deporte, cantidad de jugadores, duración, ubicación, horario, estado actual, emparejamiento y jugadores.
- **Comentario**: permite a los jugadores dejar feedback al finalizar el partido.

### Patrón de Diseño

- **Observer**: los usuarios son notificados de cambios relevantes.
- **Strategy**: el emparejamiento de jugadores se realiza mediante distintas estrategias: por nivel, por ubicación o aleatoriamente.
- **State**: los partidos transitan entre distintos estados (ej. `NecesitamosJugadores`, `PartidoArmado`, `EnJuego`, `Finalizado`, `Cancelado`).

## 🔧 Componentes

- **Controllers**:

  - `ControllerUsuario`: crea, modifica y elimina usuarios.
  - `ControllerPartido`: gestiona la creación de partidos y el agregado o remoción de jugadores.

- **Notificación**:

  - `Notificador`: maneja una lista de observadores y delega las notificaciones.
  - `EstrategiaNotificacion`: interfaz que permite cambiar entre SMS y Email.

- **Emparejamiento**:
  - `EmparejamientoPorNivel`
  - `EmparejamientoPorUbicacion`
  - `EmparejamientoAleatorio`

## 🗂️ Clases y Enums

### Enums

- `MedioNotificacion`: `SMS`, `EMAIL`
- `Deporte`: `FUTBOL`, `BASQUET`, `VOLEY`
- `Nivel`: `PRINCIPIANTE`, `INTERMEDIO`, `AVANZADO`

### DTOs

- `UsuarioDTO`, `PartidoDTO`, `ComentarioDTO`

## ✅ Requisitos Técnicos

- Lenguaje: Java (sugerido por la notación)
- Arquitectura orientada a objetos
- Patrón MVC

## 🚀 Cómo empezar

1. Clonar el repositorio.
2. Configurar el entorno de desarrollo Java.
3. Ejecutar la clase `Main` ubicada en el paquete `Model`.
4. Utilizar los controllers para gestionar usuarios y partidos.
