package br.csi.Dormez.service;

import br.csi.Dormez.model.Quarto;
import br.csi.Dormez.repository.QuartoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuartoService {

    private final QuartoRepository repository;

    public QuartoService(QuartoRepository repository) {this.repository = repository;}

    public void salvar(Quarto quarto) {
        repository.save(quarto);
    }

    public List<Quarto> listar() {
        return repository.findAll();
    }

    public Quarto buscarPorId(Long id) {
        return repository.findById(id).get();
    }

    public Quarto buscarPorNumero(int numero) {
        return repository.findByNumero(numero);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }


}
