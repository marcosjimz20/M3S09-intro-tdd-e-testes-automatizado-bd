package br.lab365.labcar.repositories;


import br.lab365.labcar.fixtures.CarroFixture;
import br.lab365.labcar.models.CarroModel;
import jakarta.validation.ConstraintViolationException;
import net.datafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Locale;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CarroRepositoryTest {

    private Faker faker;
    private Integer quantidade;
    private List<CarroModel> carros;

    @Autowired
    private CarroRepository carroRepository;

    @BeforeEach
    public void beforeEach() {
        faker = new Faker(new Locale("pt-BR"));
        quantidade = faker.number().numberBetween(1, 100);
        carros = CarroFixture.criarCarrosValidos(quantidade);

        carroRepository.saveAllAndFlush(carros);
    }

    @Test
    public void findByIdTest() {
        CarroModel carro = carros.get(0);
        CarroModel carroEncontrado = carroRepository.findById(carro.getId()).orElse(null);

        Assertions.assertEquals(carro.getId(), carroEncontrado.getId());
        Assertions.assertEquals(carro.getMarca(), carroEncontrado.getMarca());
        Assertions.assertEquals(carro.getModelo(), carroEncontrado.getModelo());
        Assertions.assertEquals(carro.getAno(), carroEncontrado.getAno());
        Assertions.assertEquals(carro.getPreco(), carroEncontrado.getPreco());
        Assertions.assertEquals(carro.getFoto(), carroEncontrado.getFoto());
    }

    @Test
    public void findByAllTest() {
        List<CarroModel> listCarros = carroRepository.findAll();
        Assertions.assertFalse(listCarros.isEmpty());
        Assertions.assertTrue(listCarros.size() == quantidade);
    }

    @Test
    public void saveTest() {
        CarroModel carroNovo = CarroFixture.criarCarroValido();
        carroRepository.saveAndFlush(carroNovo);
        List<CarroModel> listCarros = carroRepository.findAll();

        Assertions.assertNotNull(carroNovo.getId());
        Assertions.assertTrue(listCarros.size() == quantidade + 1);
    }

    @Nested
    class ColumnMarcaTest {

        @Test
        public void notEmptyExceptionTest() {
            CarroModel carro = CarroFixture.criarCarroValido();
            carro.setMarca("");

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                carroRepository.saveAndFlush(carro);
            });
        }

        @Test
        public void notNullExceptionTest() {
            CarroModel carro = CarroFixture.criarCarroValido();
            carro.setMarca(null);

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                carroRepository.saveAndFlush(carro);
            });
        }

        @Test
        public void exceededLengthExceptionTest() {
            CarroModel carro = CarroFixture.criarCarroValido();
            carro.setMarca(faker.lorem().characters(200));

            Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
                carroRepository.saveAndFlush(carro);
            });
        }
    }

    @Nested
    class ColumnModeloTest {

        @Test
        public void notEmptyExceptionTest() {
            CarroModel carro = CarroFixture.criarCarroValido();
            carro.setModelo("");

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                carroRepository.saveAndFlush(carro);
            });
        }

        @Test
        public void notNullExceptionTest() {
            CarroModel carro = CarroFixture.criarCarroValido();
            carro.setModelo(null);

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                carroRepository.saveAndFlush(carro);
            });
        }

        @Test
        public void exceededLengthExceptionTest() {
            CarroModel carro = CarroFixture.criarCarroValido();
            carro.setModelo(faker.lorem().characters(200));

            Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
                carroRepository.saveAndFlush(carro);
            });
        }
    }

    @Nested
    class ColumnAnoTest {

        @Test
        public void notNullExceptionTest() {
            CarroModel carro = CarroFixture.criarCarroValido();
            carro.setAno(null);

            Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
                carroRepository.saveAndFlush(carro);
            });
        }
    }

    @Nested
    class ColumnPrecoTest {

        @Test
        public void notNullExceptionTest() {
            CarroModel carro = CarroFixture.criarCarroValido();
            carro.setPreco(null);

            Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
                carroRepository.saveAndFlush(carro);
            });
        }
    }

    @Nested
    class ColumnFotoTest {

        @Test
        public void notEmptyExceptionTest() {
            CarroModel carro = CarroFixture.criarCarroValido();
            carro.setFoto("");

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                carroRepository.saveAndFlush(carro);
            });
        }

        @Test
        public void notNullExceptionTest() {
            CarroModel carro = CarroFixture.criarCarroValido();
            carro.setFoto(null);

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                carroRepository.saveAndFlush(carro);
            });
        }
    }


}
