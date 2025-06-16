package com.tpo.armarPartido.repository;

import com.tpo.armarPartido.model.Partido;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PartidoRepository extends MongoRepository<Partido, String> {
    // Métodos personalizados si los necesitas
} 