package br.lab365.labcar.fixtures;

import br.lab365.labcar.models.UsuarioModel;
import net.datafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UsuarioFixture {

    private static final Faker faker = new Faker(new Locale("pt-BR"));
    public static List<UsuarioModel> criarUsuariosValidos(Integer qtd) {
        List<UsuarioModel> usuarios = new ArrayList<>();
        for (int i = 0; i < qtd; i++) {
            usuarios.add(criarUsuarioValido());
        }
        return usuarios;
    }

    public static UsuarioModel criarUsuarioValido() {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setNome(faker.name().name());
        usuario.setEmail(faker.internet().emailAddress());
        usuario.setSenha(faker.lorem().characters(8));

        return usuario;
    }

}

