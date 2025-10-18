package com.saberpro.repository;

import com.saberpro.model.Estudiante;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface EstudianteRepository extends MongoRepository<Estudiante, String> {
    Optional<Estudiante> findByDocumento(String documento);
}
