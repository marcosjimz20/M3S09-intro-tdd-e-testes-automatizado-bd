package br.lab365.labcar.repositories;

import br.lab365.labcar.models.CarroModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<CarroModel, Long> {
}
