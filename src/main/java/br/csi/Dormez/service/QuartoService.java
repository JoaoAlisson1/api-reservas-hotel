package br.csi.Dormez.service;

import br.csi.Dormez.infra.exceptions.RecursoNaoEncontradoException;
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
        return repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Quarto não encontrado."));
    }

    public Quarto buscarPorNumero(int numero) {
        Quarto quarto = repository.findByNumero(numero);
        if (quarto == null) {
            throw new RecursoNaoEncontradoException("Quarto não encontrado.");
        }
        return quarto;
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Quarto não encontrado.");
        }
        repository.deleteById(id);
    }

    // Atualiza um quarto pelo ID e retorna o quarto atualizado
    @Transactional
    public Quarto atualizar(Quarto quartoAtualizado, Long id) {
        // Busca o quarto existente
        Quarto existente = repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Quarto não encontrado."));

        existente.setNumero(quartoAtualizado.getNumero());
        existente.setTipo(quartoAtualizado.getTipo());
        existente.setStatus(quartoAtualizado.getStatus());
        existente.setDiaria(quartoAtualizado.getDiaria());

        return repository.save(existente);

    }
}
