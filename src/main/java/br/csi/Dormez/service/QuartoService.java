package br.csi.Dormez.service;

import br.csi.Dormez.model.Quarto;
import br.csi.Dormez.repository.QuartoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuartoService {

    private final QuartoRepository repository;

    public QuartoService(QuartoRepository repository) {
        this.repository = repository;
    }

    public Quarto salvar(Quarto quarto) {
        return repository.save(quarto);
    }

    public List<Quarto> listar() {
        return repository.findAll();
    }

    public Quarto buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Quarto buscarPorNumero(int numero) {
        return repository.findByNumero(numero);
    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    // Atualiza um quarto pelo ID e retorna o quarto atualizado
    @Transactional
    public Quarto atualizar(Quarto quartoAtualizado, Long id) {
        // Busca o quarto existente
        Quarto existente = repository.findById(id).orElse(null);

        if (existente != null) {
            // Atualiza os campos necessários
            existente.setNumero(quartoAtualizado.getNumero());
            existente.setTipo(quartoAtualizado.getTipo());
            existente.setStatus(quartoAtualizado.getStatus());
            existente.setDiaria(quartoAtualizado.getDiaria());

            // Salva e retorna o quarto atualizado
            return repository.save(existente);
        }

        // Retorna null se não encontrou o quarto
        return null;
    }
}
