package br.lab365.labcar.repositories;

import br.lab365.labcar.fixtures.UsuarioFixture;
import br.lab365.labcar.models.UsuarioModel;
import br.lab365.labcar.repositories.UsuarioRepository;
import net.datafaker.Faker;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Locale;
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {

    private Faker faker;
    private Integer quantidade;
    private List<UsuarioModel> usuarios;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void beforeEach() {
        faker = new Faker(new Locale("pt-BR"));
        quantidade = faker.number().numberBetween(1, 100);
        usuarios = UsuarioFixture.criarUsuariosValidos(quantidade);

        usuarioRepository.saveAllAndFlush(usuarios);
    }

    @Test
    public void findByIdTest() {
        UsuarioModel usuario = usuarios.get(0);
        UsuarioModel usuarioEncontrado = usuarioRepository.findById(usuario.getId()).orElse(null);

        Assertions.assertEquals(usuario.getId(), usuarioEncontrado.getId());
        Assertions.assertEquals(usuario.getNome(), usuarioEncontrado.getNome());
        Assertions.assertEquals(usuario.getEmail(), usuarioEncontrado.getEmail());
        Assertions.assertEquals(usuario.getSenha(), usuarioEncontrado.getSenha());
    }

    @Test
    public void findByAllTest() {
        List<UsuarioModel> listUsuarios = usuarioRepository.findAll();
        Assertions.assertFalse(listUsuarios.isEmpty());
        Assertions.assertTrue(listUsuarios.size() == quantidade);
    }

    @Test
    public void saveTest() {
        UsuarioModel usuarioNovo = UsuarioFixture.criarUsuarioValido();
        usuarioRepository.saveAndFlush(usuarioNovo);
        List<UsuarioModel> listUsuarios = usuarioRepository.findAll();

        Assertions.assertNotNull(usuarioNovo.getId());
        Assertions.assertTrue(listUsuarios.size() == quantidade + 1);
    }

    @Nested
    class ColumnNameTest {

        @Test
        public void notEmptyExceptionTest() {
            UsuarioModel usuario = UsuarioFixture.criarUsuarioValido();
            usuario.setNome("");

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                usuarioRepository.saveAndFlush(usuario);
            });
        }

        @Test
        public void notNullExceptionTest() {
            UsuarioModel usuario = UsuarioFixture.criarUsuarioValido();
            usuario.setNome(null);

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                usuarioRepository.saveAndFlush(usuario);
            });
        }

        @Test
        public void exceededLengthExceptionTest() {
            UsuarioModel usuario = UsuarioFixture.criarUsuarioValido();
            usuario.setNome(faker.lorem().characters(300));

            Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
                usuarioRepository.saveAndFlush(usuario);
            });
        }
    }


    @Nested
    class ColumnEmailTest {

        @Test
        public void notEmptyExceptionTest() {
            UsuarioModel usuario = UsuarioFixture.criarUsuarioValido();
            usuario.setEmail("");

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                usuarioRepository.saveAndFlush(usuario);
            });
        }

        @Test
        public void notNullExceptionTest() {
            UsuarioModel usuario = UsuarioFixture.criarUsuarioValido();
            usuario.setEmail(null);

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                usuarioRepository.saveAndFlush(usuario);
            });
        }

        @Test
        public void notEmailExceptionTest() {
            UsuarioModel usuario = UsuarioFixture.criarUsuarioValido();
            usuario.setEmail(faker.lorem().characters(300));

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                usuarioRepository.saveAndFlush(usuario);
            });
        }

        @Test
        public void exceededLengthExceptionTest() {
            UsuarioModel usuario = UsuarioFixture.criarUsuarioValido();
            usuario.setEmail(
                    faker.lorem().characters(300) +
                            faker.internet().emailAddress()
            );

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                usuarioRepository.saveAndFlush(usuario);
            });
        }
    }

    @Nested
    class ColumnSenhaTest {

        @Test
        public void notEmptyExceptionTest() {
            UsuarioModel usuario = UsuarioFixture.criarUsuarioValido();
            usuario.setSenha("");

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                usuarioRepository.saveAndFlush(usuario);
            });
        }

        @Test
        public void notNullExceptionTest() {
            UsuarioModel usuario = UsuarioFixture.criarUsuarioValido();
            usuario.setSenha(null);

            Assertions.assertThrows(ConstraintViolationException.class, () -> {
                usuarioRepository.saveAndFlush(usuario);
            });
        }

        @Test
        public void exceededLengthExceptionTest() {
            UsuarioModel usuario = UsuarioFixture.criarUsuarioValido();
            usuario.setSenha(faker.lorem().characters(20));

            Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
                usuarioRepository.saveAndFlush(usuario);
            });
        }
    }

}

