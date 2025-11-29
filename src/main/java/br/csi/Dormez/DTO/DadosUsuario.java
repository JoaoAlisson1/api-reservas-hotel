package br.csi.Dormez.DTO;

import br.csi.Dormez.model.Usuario;

public record DadosUsuario(Long id, String login, String permissao) {

    public DadosUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getLogin(), usuario.getPermissao());
    }
}
