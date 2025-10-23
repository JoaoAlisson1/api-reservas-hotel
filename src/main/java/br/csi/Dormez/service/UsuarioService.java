package br.csi.Dormez.service;

import br.csi.Dormez.DTO.DadosUsuario;
import br.csi.Dormez.model.Usuario;
import br.csi.Dormez.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(Usuario usuario) {

        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        this.repository.save(usuario);
    }

    public DadosUsuario findUsuario(Long id) {
        Usuario usuario = this.repository.getReferenceById(id);
        return new DadosUsuario(usuario);
    }

    public List<DadosUsuario> findAll() {
        return this.repository.findAll().stream().map(DadosUsuario::new).toList();
    }
}
