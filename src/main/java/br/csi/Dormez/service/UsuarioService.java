package br.csi.Dormez.service;

import br.csi.Dormez.DTO.DadosUsuario;
import br.csi.Dormez.infra.exceptions.RecursoNaoEncontradoException;
import br.csi.Dormez.model.Usuario;
import br.csi.Dormez.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Usuario usuario = repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));
        return new DadosUsuario(usuario);
    }

    public List<DadosUsuario> listar() {
        return this.repository.findAll().stream().map(DadosUsuario::new).toList();
    }

    @Transactional
    public void deletar(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));
        repository.delete(usuario);
    }

    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado."));

        usuario.setLogin(usuarioAtualizado.getLogin());
        usuario.setPermissao(usuarioAtualizado.getPermissao());

        if (usuarioAtualizado.getSenha() != null && !usuarioAtualizado.getSenha().isBlank()) {
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioAtualizado.getSenha()));
        }

        return repository.save(usuario);
    }

}
