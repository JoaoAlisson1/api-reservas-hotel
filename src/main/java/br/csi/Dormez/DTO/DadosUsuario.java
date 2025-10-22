package br.csi.Dormez.DTO;

import br.csi.Dormez.model.Usuario;

public record DadosUsuario(Long id, String Login, String permissao) {

    public DadosUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getLogin(), usuario.getPermissao());
    }
}
