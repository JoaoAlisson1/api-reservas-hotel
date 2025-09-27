package br.csi.Dormez.service;

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

    public void salvar(Funcionario funcionario) {
        this.repository.save(funcionario);
    }

    public List<Funcionario> listar() {
        return this.repository.findAll();
    }

    public Funcionario buscarPorUUID(String uuid) {
        UUID uuidformatado = UUID.fromString(uuid);
        return this.repository.findFuncionarioByUuid(uuidformatado);
    }

    @Transactional
    public void deletarUUID(String uuid) {
        this.repository.deleteFuncionarioByUuid(UUID.fromString(uuid));
    }

    public void atualizarUUID(Funcionario funcionario) {

        Funcionario func = this.repository.findFuncionarioByUuid(funcionario.getUuid());
        func.setNome(funcionario.getNome());
        func.setEmail(funcionario.getEmail());
        func.setTelefone(funcionario.getTelefone());
        func.setCargo(funcionario.getCargo());
        this.repository.save(func);
    }
}
