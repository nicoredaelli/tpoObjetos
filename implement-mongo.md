# Checklist: Implementar Persistencia con MongoDB en Spring Boot

## 1. Verificar dependencias en pom.xml

- [x] Asegúrate de tener la dependencia:
  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-mongodb</artifactId>
  </dependency>
  ```

## 2. Instalar y correr MongoDB

- [ ] Instala MongoDB localmente o usa un servicio en la nube (MongoDB Atlas).
- [ ] Inicia el servicio de MongoDB (por defecto en `localhost:27017`).

## 3. Configurar application.properties o application.yml

- [ ] Agrega la URI de conexión en `src/main/resources/application.properties`:
  ```properties
  spring.data.mongodb.uri=mongodb://localhost:27017/armarpartido
  ```
  Cambia el nombre de la base de datos si lo deseas.

## 4. Anotar los modelos como documentos

- [ ] Agrega la anotación `@Document(collection = "nombre")` a las clases que quieras persistir (por ejemplo, `Usuario`, `Partido`, `Comentario`).
- [ ] Agrega un campo `@Id` (tipo `String` o `ObjectId`) para el identificador único.

## 5. Crear interfaces Repository

- [ ] Crea una interfaz que extienda `MongoRepository` para cada entidad:

  ```java
  import org.springframework.data.mongodb.repository.MongoRepository;
  import com.tpo.armarPartido.model.Usuario;

  public interface UsuarioRepository extends MongoRepository<Usuario, String> {
      // Puedes agregar métodos de consulta personalizados aquí
  }
  ```

## 6. Usar los repositorios en los servicios/controladores

- [ ] Inyecta los repositorios usando `@Autowired` o constructor.
- [ ] Usa los métodos de los repositorios para guardar, buscar, actualizar y eliminar entidades.

## 7. Probar la persistencia

- [ ] Ejecuta la aplicación y prueba crear, buscar y eliminar entidades.
- [ ] Verifica que los datos se guardan en MongoDB (puedes usar MongoDB Compass o la CLI de mongo).

## 8. (Opcional) Crear scripts de ejemplo o endpoints REST

- [ ] Agrega endpoints para exponer la funcionalidad CRUD usando los DTOs y los repositorios.

---

**¡Sigue estos pasos y tendrás persistencia con MongoDB funcionando en tu proyecto!**
