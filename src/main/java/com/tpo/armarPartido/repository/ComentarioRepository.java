package com.tpo.armarPartido.repository;

import com.tpo.armarPartido.model.Comentario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComentarioRepository extends MongoRepository<Comentario, String> {
    // Métodos personalizados si los necesitas
} 