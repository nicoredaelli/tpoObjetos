package utils;

import java.util.Comparator;

import com.tpo.armarPartido.model.Partido;
import com.tpo.armarPartido.model.Ubicacion;

public class PartidoPorDistanciaComparator implements Comparator<Partido> {
    private final Ubicacion ubicacionCentral;

    public PartidoPorDistanciaComparator(Ubicacion ubicacionCentral) {
        if (ubicacionCentral == null) {
            throw new IllegalArgumentException("La ubicación central no puede ser null");
        }
        this.ubicacionCentral = ubicacionCentral;
    }

    @Override
    public int compare(Partido p1, Partido p2) {
        Ubicacion u1 = p1.getUbicacion();
        Ubicacion u2 = p2.getUbicacion();

        if (u1 == null && u2 == null) {
            return 0;
        } else if (u1 == null) {
            return 1;
        } else if (u2 == null) {
            return -1;
        }
        double d1 = ubicacionCentral.distanciaCuadradoA(u1);
        double d2 = ubicacionCentral.distanciaCuadradoA(u2);
        return Double.compare(d1, d2);
    }
}
