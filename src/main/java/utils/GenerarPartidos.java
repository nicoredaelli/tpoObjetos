package utils;

import java.util.Calendar;
import java.util.Date;

import com.tpo.armarPartido.controller.ControllerPartido;
import com.tpo.armarPartido.controller.ControllerUsuario;
import com.tpo.armarPartido.enums.Deporte;
import com.tpo.armarPartido.enums.Nivel;
import com.tpo.armarPartido.model.Ubicacion;
import com.tpo.armarPartido.model.Usuario;
import com.tpo.armarPartido.service.EmparejamientoPorNivel;
import com.tpo.armarPartido.service.EmparejamientoPorUbicacion;

public class GenerarPartidos {
	
	public static void generar(ControllerUsuario userController, ControllerPartido partidoController) {
		
		Calendar calendar = Calendar.getInstance();
        // Previo a armar un partido necesito un usuario que lo cree y un horario del tipo Date. 
		// Partido 1 
		Usuario Luis = userController.getUsuarioPorNombre("Luis Martinez");
        
        calendar.set(Calendar.HOUR_OF_DAY, 22); // 22 horas
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date horarioPartidoLuis = (Date) calendar.getTime();
        partidoController.crearPartido(Deporte.FUTBOL, 2, 10, new Ubicacion(1, 1), horarioPartidoLuis, new EmparejamientoPorUbicacion(), Luis, Nivel.AVANZADO );
        //Partido 2
        Usuario Ana = userController.getUsuarioPorNombre("Ana Gomez");
        calendar.set(Calendar.HOUR_OF_DAY, 20); // 22 horas
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date horarioPartidoAna = (Date) calendar.getTime();
        partidoController.crearPartido(Deporte.VOLEY, 3, 10, new Ubicacion(1, 1), horarioPartidoAna, new EmparejamientoPorNivel(), Ana, Nivel.INTERMEDIO );
		
	}

}
