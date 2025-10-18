package com.saberpro.repository;

import com.saberpro.model.Resultado;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ResultadoRepository extends MongoRepository<Resultado, String> {
    List<Resultado> findByEstudianteId(String estudianteId);
}
