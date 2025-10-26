package br.csi.Dormez.service;

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
        UUID uuidformatado = UUID.fromString(uuid);
        return this.repository.findHospedeByUuid(uuidformatado);
    }

    public Hospede buscarPorNome(String nome) {
        return this.repository.findHospedeByNome(nome);
    }

    public Hospede buscarPorCpf(String cpf) {
        return this.repository.findHospedeByCpf(cpf);
    }

    @Transactional
    public void deletarPorUUID(String uuid) {
        this.repository.deleteHospedeByUuid(UUID.fromString(uuid));
    }

    // Atualiza um hospede e retorna a entidade atualizada
    public Hospede atualizarUUID(Hospede hospede) {

        Hospede hosp = this.repository.findHospedeByUuid(hospede.getUuid());
        if (hosp != null) {
            hosp.setNome(hospede.getNome());
            hosp.setEmail(hospede.getEmail());
            hosp.setTelefone(hospede.getTelefone());
            hosp.setCpf(hospede.getCpf());

            return this.repository.save(hosp); // Retorna a entidade atualizada
        }

        return null;
    }
}
