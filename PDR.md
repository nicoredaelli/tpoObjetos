# PDR - Proyecto MatchMakerDeportivo

## Descripción General

**MatchMakerDeportivo** es un sistema para la organización y gestión de partidos deportivos, con emparejamiento automatizado de jugadores, notificaciones multicanal y seguimiento del estado de cada encuentro. Permite crear, administrar y participar en partidos de fútbol, básquet y vóley, considerando niveles de experiencia y ubicación de los jugadores.

### Principales características:

- Registro y modificación de usuarios con preferencias deportivas.
- Creación y seguimiento de partidos.
- Emparejamiento automatizado según nivel, ubicación o aleatoriamente.
- Notificaciones vía SMS o email.
- Gestión del ciclo de vida del partido (necesita jugadores, armado, confirmado, en juego, finalizado, cancelado).
- Comentarios post-partido.

---

## Controladores

### 1. ControllerUsuario

**Responsabilidad:**
Gestiona la creación, modificación y eliminación de usuarios, así como la asignación de deportes y niveles a cada usuario.

**Métodos principales:**

- `crearUsuario(...)`: Crea un nuevo usuario con nombre, correo, contraseña, deportes y niveles, medio de notificación y ubicación.
- `eliminarUsuario(correo)`: Elimina un usuario por correo.
- `modificarUsuario(correo, usuarioModificado)`: Modifica los datos de un usuario existente.
- `getUsuarios()`: Devuelve la lista de usuarios.
- `getUsuarioPorNombre(nombre)`: Busca un usuario por nombre.
- `agregarDeporteNivel(correo, deporte, nivel)`: Agrega un deporte y nivel a un usuario.

**Modelos asociados:**

- `Usuario`
- `Ubicacion`

**Enums asociados:**

- `Deporte`
- `Nivel`
- `MedioNotificacion`

---

### 2. ControllerPartido

**Responsabilidad:**
Gestiona la creación de partidos, búsqueda por nivel o ubicación, agregado de jugadores, y el ciclo de vida del partido (armar, confirmar, comenzar, finalizar).

**Métodos principales:**

- `crearPartido(...)`: Crea un nuevo partido con deporte, cantidad de jugadores, duración, ubicación, horario, estrategia de emparejamiento, usuario creador y nivel.
- `buscarPartidosPorNivel(nivel)`: Busca partidos por nivel.
- `buscarPartidosPorUbicacion(ubicacion, cantidad)`: Busca partidos cercanos a una ubicación.
- `agregarJugadorAPartido(id, jugador)`: Agrega un jugador a un partido.
- `armarPartido(id)`, `confirmarPartido(id, jugador)`, `comenzarPartido(id, jugador)`, `finalizarPartido(id, jugador)`: Gestionan el ciclo de vida del partido.

**Modelos asociados:**

- `Partido`
- `Usuario`
- `Ubicacion`
- `Notificador`

**Servicios asociados:**

- `EmparejamientoPorNivel`
- `EmparejamientoPorUbicacion`
- `EstrategiaEmparejamiento`
- `AdapterMail` / `AdapterNotificacionMail`
- Estados: `NecesitamosJugadores`, `PartidoArmado`, `Confirmacion`, `EnJuego`, `Finalizado`, `Cancelado`

**Enums asociados:**

- `Deporte`
- `Nivel`
- `MedioNotificacion`

---

## Modelos

- **Usuario**: nombre, correo, contraseña, deportes y niveles, medio de notificación, ubicación.
- **Partido**: deporte, cantidad de jugadores, duración, ubicación, horario, estado, emparejamiento, jugadores, nivel, observadores.
- **Ubicacion**: latitud, longitud.
- **Notificador**: maneja la estrategia de notificación y notifica a los usuarios.
- **Notificacion**: mensaje y usuario destinatario.
- **Comentario**: jugador y comentario.

---

## Servicios

- **EmparejamientoPorNivel**: Empareja jugadores según el nivel requerido para el partido.
- **EmparejamientoPorUbicacion**: Empareja jugadores según cercanía geográfica al partido.
- **EstrategiaEmparejamiento**: Interfaz para definir estrategias de emparejamiento.
- **AdapterMail / AdapterNotificacionMail**: Adaptadores para notificaciones por email.
- **EstrategiaNotificacion**: Interfaz para notificaciones (SMS, Email).
- **Estados del Partido**: Implementan el ciclo de vida del partido (`NecesitamosJugadores`, `PartidoArmado`, `Confirmacion`, `EnJuego`, `Finalizado`, `Cancelado`).

---

## Enums

- **Deporte**: `FUTBOL`, `BASQUET`, `VOLEY`
- **Nivel**: `PRINCIPIANTE`, `INTERMEDIO`, `AVANZADO`
- **MedioNotificacion**: `SMS`, `EMAIL`

---

## Resumen de Arquitectura

- **MVC**: Separación clara entre controladores, modelos y servicios.
- **Observer**: Notificación de eventos a los usuarios.
- **Strategy**: Emparejamiento configurable.
- **State**: Gestión de estados del partido.

---

_Este documento resume la estructura y responsabilidades del sistema, facilitando su comprensión y mantenimiento._
