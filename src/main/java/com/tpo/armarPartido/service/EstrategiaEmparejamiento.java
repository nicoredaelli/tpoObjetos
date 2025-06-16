package com.tpo.armarPartido.service;


import com.tpo.armarPartido.model.Partido;
import com.tpo.armarPartido.model.Usuario;

import java.util.List;

public interface EstrategiaEmparejamiento {


    List<Usuario> emparejar(Partido partido, List<Usuario> jugadores);


}