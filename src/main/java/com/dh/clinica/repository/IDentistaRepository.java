package com.dh.clinica.repository;

import com.dh.clinica.model.Dentista;
import com.dh.clinica.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDentistaRepository extends JpaRepository<Dentista, Integer> {

    Dentista findDentistaByNomeContainingIgnoreCase(String nome);

    Dentista findDentistaById(Integer id);

    Dentista findDentistaByMatriculaContainingIgnoreCase(String matricula);

}
