package br.csi.Dormez.service;

import br.csi.Dormez.infra.exceptions.RecursoNaoEncontradoException;
import br.csi.Dormez.model.Hospede;
import br.csi.Dormez.repository.HospedeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class HospedeService {

    private final HospedeRepository repository;

    public HospedeService(HospedeRepository Repository) {this.repository = Repository;}

    // Salva um hospede e retorna a entidade salva
    public Hospede salvar(Hospede hospede) {
       return this.repository.save(hospede);
    }

    public List<Hospede> listar() {
        return this.repository.findAll();
    }

    public Hospede buscarPorUUID(String uuid) {
        UUID uuidFormatado;
        try {
            uuidFormatado = UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new RecursoNaoEncontradoException("UUID inválido.");
        }

        Hospede hospede = repository.findHospedeByUuid(uuidFormatado);
        if (hospede == null) {
            throw new RecursoNaoEncontradoException("Hóspede não encontrado.");
        }
        return hospede;
    }

    public Hospede buscarPorNome(String nome) {
        Hospede hospede = repository.findHospedeByNome(nome);
        if (hospede == null) {
            throw new RecursoNaoEncontradoException("Hóspede não encontrado.");
        }
        return hospede;
    }

    public Hospede buscarPorCpf(String cpf) {
        Hospede hospede = repository.findHospedeByCpf(cpf);
        if (hospede == null) {
            throw new RecursoNaoEncontradoException("Hóspede não encontrado.");
        }
        return hospede;
    }

    @Transactional
    public void deletarPorUUID(String uuid) {
        UUID uuidFormatado;
        try {
            uuidFormatado = UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new RecursoNaoEncontradoException("UUID inválido.");
        }

        Hospede hospede = repository.findHospedeByUuid(uuidFormatado);
        if (hospede == null) {
            throw new RecursoNaoEncontradoException("Hóspede não encontrado.");
        }

        repository.deleteHospedeByUuid(uuidFormatado);
    }

    // Atualiza um hospede e retorna a entidade atualizada
    public Hospede atualizarUUID(Hospede hospede) {

        Hospede hosp = repository.findHospedeByUuid(hospede.getUuid());
        if (hosp == null) {
            throw new RecursoNaoEncontradoException("Hóspede não encontrado.");
        }

        hosp.setNome(hospede.getNome());
        hosp.setEmail(hospede.getEmail());
        hosp.setTelefone(hospede.getTelefone());
        hosp.setCpf(hospede.getCpf());

        return repository.save(hosp);
    }
}
