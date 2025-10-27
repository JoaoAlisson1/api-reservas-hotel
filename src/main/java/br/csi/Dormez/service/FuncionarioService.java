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

    public Funcionario salvar(Funcionario funcionario) {
        return this.repository.save(funcionario);
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

    public Funcionario atualizarUUID(Funcionario funcionario) {

        Funcionario func = this.repository.findFuncionarioByUuid(funcionario.getUuid());
        if (func != null) {
            func.setNome(funcionario.getNome());
            func.setEmail(funcionario.getEmail());
            func.setTelefone(funcionario.getTelefone());
            func.setCargo(funcionario.getCargo());

            return this.repository.save(func); // Retorna a entidade atualizada
        }

        return null;
    }
}
