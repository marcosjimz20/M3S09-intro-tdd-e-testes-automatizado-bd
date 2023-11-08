package br.lab365.labcar.repositories;

import br.lab365.labcar.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    boolean existsByEmailIgnoreCase(String email);

}
