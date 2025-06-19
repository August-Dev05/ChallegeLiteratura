package com.AluraLatam.ChallegeDeLiteratura.repository;

import com.AluraLatam.ChallegeDeLiteratura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
