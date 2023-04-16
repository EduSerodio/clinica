package com.dh.clinica.repository;
import com.dh.clinica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Integer> {

    Paciente findPacienteBynomeContainingIgnoreCase(String nome);

    Paciente findPacienteByRgContainingIgnoreCase(String rg);

}
