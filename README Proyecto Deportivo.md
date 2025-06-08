# 🧾 PDR – Implementación del ControllerUsuario en Java

## 🎯 Objetivo

Desarrollar la clase `ControllerUsuario` y sus métodos asociados para la gestión de usuarios dentro del sistema. El controlador deberá permitir la creación, modificación y eliminación de usuarios utilizando `UsuarioDTO` como objeto de transferencia.

---

## 🧱 Estructura general a implementar

### 📁 Modelo

#### `Usuario.java`

```java
import java.util.List;

public class Usuario {
    private String nombre;
    private String correo;
    private String contrasena;
    private List<Deporte> deportesFavoritos;
    private List<Nivel> nivelesDeportes;
    private MedioNotificacion medioNotificacion;

    public Usuario(String nombre, String correo, String contrasena, List<Deporte> deportesFavoritos, List<Nivel> nivelesDeportes, MedioNotificacion medioNotificacion) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.deportesFavoritos = deportesFavoritos;
        this.nivelesDeportes = nivelesDeportes;
        this.medioNotificacion = medioNotificacion;
    }

    // Getters y Setters
}
```

#### `UsuarioDTO.java`

```java
import java.util.List;

public class UsuarioDTO {
    private String nombre;
    private String correo;
    private List<Deporte> deportesFavoritos;
    private List<Nivel> nivelesDeportes;
    private MedioNotificacion medioNotificacion;

    public UsuarioDTO(String nombre, String correo, List<Deporte> deportesFavoritos, List<Nivel> nivelesDeportes, MedioNotificacion medioNotificacion) {
        this.nombre = nombre;
        this.correo = correo;
        this.deportesFavoritos = deportesFavoritos;
        this.nivelesDeportes = nivelesDeportes;
        this.medioNotificacion = medioNotificacion;
    }

    // Getters y Setters
}
```

#### `MedioNotificacion.java`

```java
public enum MedioNotificacion {
    SMS,
    EMAIL
}
```

#### `Deporte.java`

```java
public enum Deporte {
    FUTBOL,
    BASQUET,
    VOLEY
}
```

#### `Nivel.java`

```java
public enum Nivel {
    PRINCIPIANTE,
    INTERMEDIO,
    AVANZADO
}
```

---

### 📁 Controller

#### `ControllerUsuario.java`

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ControllerUsuario {
    private List<Usuario> usuarios;

    public ControllerUsuario() {
        this.usuarios = new ArrayList<>();
    }

    public void crearUsuario(UsuarioDTO dto, String contrasena) {
        if (buscarUsuarioPorCorreo(dto.getCorreo()) != null) {
            System.out.println("Error: Ya existe un usuario con ese correo.");
            return;
        }

        Usuario nuevoUsuario = new Usuario(
            dto.getNombre(),
            dto.getCorreo(),
            contrasena,
            dto.getDeportesFavoritos(),
            dto.getNivelesDeportes(),
            dto.getMedioNotificacion()
        );

        usuarios.add(nuevoUsuario);
        System.out.println("Usuario creado exitosamente.");
    }

    public void modificarUsuario(UsuarioDTO dto) {
        Usuario usuario = buscarUsuarioPorCorreo(dto.getCorreo());
        if (usuario == null) {
            System.out.println("Error: Usuario no encontrado.");
            return;
        }

        usuario.setNombre(dto.getNombre());
        usuario.setDeportesFavoritos(dto.getDeportesFavoritos());
        usuario.setNivelesDeportes(dto.getNivelesDeportes());
        usuario.setMedioNotificacion(dto.getMedioNotificacion());

        System.out.println("Usuario modificado correctamente.");
    }

    public void eliminarUsuario(UsuarioDTO dto) {
        Usuario usuario = buscarUsuarioPorCorreo(dto.getCorreo());
        if (usuario == null) {
            System.out.println("Error: Usuario no encontrado.");
            return;
        }

        usuarios.remove(usuario);
        System.out.println("Usuario eliminado correctamente.");
    }

    public Usuario buscarUsuarioPorCorreo(String correo) {
        for (Usuario u : usuarios) {
            if (u.getCorreo().equalsIgnoreCase(correo)) {
                return u;
            }
        }
        return null;
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }
}
```

---

## ✅ Extras sugeridos

- Validaciones básicas de campos (no nulos, estructura de correo válida).
- Uso de `Optional<Usuario>` en lugar de `null`, si se desea mayor robustez.
- Test unitarios con JUnit (no incluidos en este PDR).

---

## 🧪 Ejemplo de uso

```java
public class Main {
    public static void main(String[] args) {
        ControllerUsuario controller = new ControllerUsuario();

        UsuarioDTO dto = new UsuarioDTO(
            "Juan Pérez",
            "juan@example.com",
            List.of(Deporte.FUTBOL),
            List.of(Nivel.INTERMEDIO),
            MedioNotificacion.EMAIL
        );

        controller.crearUsuario(dto, "miContrasena123");

        controller.modificarUsuario(new UsuarioDTO(
            "Juan P.",
            "juan@example.com",
            List.of(Deporte.FUTBOL, Deporte.BASQUET),
            List.of(Nivel.AVANZADO),
            MedioNotificacion.SMS
        ));

        controller.eliminarUsuario(dto);
    }
}
```

---

## 📩 Contacto

Para cualquier duda sobre la implementación, podés consultar con el autor del PDR.
