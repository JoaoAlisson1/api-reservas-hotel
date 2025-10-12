package br.csi.Dormez.repository;

import br.csi.Dormez.model.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HospedeRepository extends JpaRepository<Hospede, Long> {
    public Hospede findHospedeByUuid(UUID uuid);
    public Hospede findHospedeByNome(String nome);
    public Hospede findHospedeByCpf(String cpf);
    public void deleteHospedeByUuid(UUID uuid);

    List<Hospede> findAllByUuidIn(List<UUID> uuids);
}
