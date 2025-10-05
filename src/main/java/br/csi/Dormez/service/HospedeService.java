package br.csi.Dormez.service;

import br.csi.Dormez.model.Hospede;
import br.csi.Dormez.repository.HospedeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HospedeService {

    private final HospedeRepository Repository;

    public HospedeService(HospedeRepository Repository) {this.Repository = Repository;}

    public void salvar(Hospede hospede) {
        this.Repository.save(hospede);
    }

    public List<Hospede> listar() {
        return this.Repository.findAll();
    }

    public Hospede buscarPorUUID(String uuid) {
        UUID uuidformatado = UUID.fromString(uuid);
        return this.Repository.findHospedeByUuid(uuidformatado);
    }

    public Hospede buscarPorNome(String nome) {
        return this.Repository.findHospedeByNome(nome);
    }

    public Hospede buscarPorCpf(String cpf) {
        return this.Repository.findHospedeByCpf(cpf);
    }

    public void deletarPorUUID(String uuid) {
        this.Repository.deleteHospedeByUuid(UUID.fromString(uuid));
    }

    public void atualizarUUID(Hospede hospede) {

        Hospede hosp = this.Repository.findHospedeByUuid(hospede.getUuid());
        hosp.setNome(hospede.getNome());
        hosp.setEmail(hospede.getEmail());
        hosp.setTelefone(hospede.getTelefone());
        hosp.setCpf(hospede.getCpf());
        this.Repository.save(hosp);
    }
}
