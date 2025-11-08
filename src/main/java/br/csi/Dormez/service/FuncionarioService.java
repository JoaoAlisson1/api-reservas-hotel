package br.csi.Dormez.service;

import br.csi.Dormez.infra.exceptions.RecursoNaoEncontradoException;
import br.csi.Dormez.model.Funcionario;
import br.csi.Dormez.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {this.repository = repository;}

    public Funcionario salvar(Funcionario funcionario) {
        return this.repository.save(funcionario);
    }

    public List<Funcionario> listar() {
        return this.repository.findAll();
    }

    public Funcionario buscarPorUUID(String uuid) {

        UUID uuidformatado;
        try {
            uuidformatado = UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new RecursoNaoEncontradoException("UUID inválido.");
        }

        Funcionario funcionario = repository.findFuncionarioByUuid(uuidformatado);
        if (funcionario == null) {
            throw new RecursoNaoEncontradoException("Funcionário não encontrado.");
        }
        return funcionario;
    }

    @Transactional
    public void deletarUUID(String uuid) {
        UUID uuidformatado;
        try {
            uuidformatado = UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new RecursoNaoEncontradoException("UUID inválido.");
        }

        Funcionario funcionario = repository.findFuncionarioByUuid(uuidformatado);
        if (funcionario == null) {
            throw new RecursoNaoEncontradoException("Funcionário não encontrado.");
        }

        repository.deleteFuncionarioByUuid(uuidformatado);
    }

    public Funcionario atualizarUUID(Funcionario funcionario) {

        Funcionario func = this.repository.findFuncionarioByUuid(funcionario.getUuid());
        if (func == null) {
            throw new RecursoNaoEncontradoException("Funcionário não encontrado.");
        }

        func.setNome(funcionario.getNome());
        func.setCargo(funcionario.getCargo());
        func.setEmail(funcionario.getEmail());
        func.setTelefone(funcionario.getTelefone());

        return repository.save(func);
    }
}
