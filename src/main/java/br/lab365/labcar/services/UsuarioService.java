package br.lab365.labcar.services;

import br.lab365.labcar.models.UsuarioModel;

public interface UsuarioService {

    UsuarioModel salvar(UsuarioModel usuario) throws Exception;

    Boolean existe(String email);

}
