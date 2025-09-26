package br.csi.Dormez.service;

import br.csi.Dormez.model.Funcionario;
import br.csi.Dormez.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Funcionario buscarPorId(Long id) {
        return this.repository.findById(id).get();
    }

    public void excluir(Long id) {
        this.repository.deleteById(id);
    }

    public void atualizar(Funcionario funcionario) {

        Funcionario func = this.repository.getReferenceById(funcionario.getId());
        func.setNome(funcionario.getNome());
        func.setEmail(funcionario.getEmail());
        func.setTelefone(funcionario.getTelefone());
        this.repository.save(func);
    }
}
