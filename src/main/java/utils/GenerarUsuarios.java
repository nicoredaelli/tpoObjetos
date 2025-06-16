package utils;

import java.util.HashMap;
import java.util.Map;
import com.tpo.armarPartido.controller.ControllerUsuario;
import com.tpo.armarPartido.enums.Deporte;
import com.tpo.armarPartido.enums.MedioNotificacion;
import com.tpo.armarPartido.enums.Nivel;
import com.tpo.armarPartido.model.Ubicacion;

public class GenerarUsuarios {
	
	public static void generar(ControllerUsuario userController) {
	    // Usuarios con sus niveles por deporte --> CAMBIO DE LISTAS POR DICCIONARIO
	    Map<Deporte, Nivel> nivelesJuan = new HashMap<>();
	    nivelesJuan.put(Deporte.FUTBOL, Nivel.INTERMEDIO);
	    nivelesJuan.put(Deporte.BASQUET, Nivel.PRINCIPIANTE);

	    Map<Deporte, Nivel> nivelesAna = new HashMap<>();
	    nivelesAna.put(Deporte.VOLEY, Nivel.INTERMEDIO);
	    nivelesAna.put(Deporte.FUTBOL, Nivel.INTERMEDIO);

	    Map<Deporte, Nivel> nivelesLuis = new HashMap<>();
	    nivelesLuis.put(Deporte.FUTBOL, Nivel.AVANZADO);
	    nivelesLuis.put(Deporte.BASQUET, Nivel.AVANZADO);

	    userController.crearUsuario("Juan Perez", "juan@example.com", "1234", nivelesJuan,
	            MedioNotificacion.EMAIL, new Ubicacion(1, 1));

	    userController.crearUsuario("Ana Gomez", "ana@example.com", "abcd", nivelesAna,
	            MedioNotificacion.SMS, new Ubicacion(2, 3));

	    userController.crearUsuario("Luis Martinez", "luis@example.com", "xyz789", nivelesLuis,
	            MedioNotificacion.SMS, new Ubicacion(5, 8));
	}


}
