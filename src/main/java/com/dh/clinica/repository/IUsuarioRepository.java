package com.dh.clinica.repository;
import com.dh.clinica.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

        List<Usuario> findUsuarioBynomeContainingIgnoreCase(String nome);

        Usuario findUsuarioByEmailContainingIgnoreCase(String email);

}
