package br.csi.Dormez.service;

import br.csi.Dormez.model.Hospede;
import br.csi.Dormez.model.Reserva;
import br.csi.Dormez.repository.HospedeRepository;
import br.csi.Dormez.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ReservaService {

    private final ReservaRepository repository;
    private final HospedeRepository hospedeRepository;

    public ReservaService(ReservaRepository repository, HospedeRepository hospedeRepository) {
        this.repository = repository;
        this.hospedeRepository = hospedeRepository;
    }

    public void salvar(Reserva reserva, List<UUID> hospedeUUIDs  ) {
        List<Hospede> hospedes = hospedeRepository.findAllByUuidIn(hospedeUUIDs);
        reserva.setHospedes(hospedes);
        this.repository.save(reserva);
    }

    public List<Reserva> listar() {
        return this.repository.findAll();
    }

    public Reserva buscarPorUUID(String uuid) {
        UUID uuidformatado = UUID.fromString(uuid);
        return this.repository.findReservaByUuid(uuidformatado);
    }

    @Transactional
    public void deletarUUID(String uuid) {
        this.repository.deleteReservaByUuid(UUID.fromString(uuid));
    }


    public void atualizarUUID(Reserva reserva, List<UUID> hospedeUUIDs ) {

        Reserva reserv = this.repository.findReservaByUuid(reserva.getUuid());
        reserv.setCheckIn(reserva.getCheckIn());
        reserv.setCheckOut(reserva.getCheckOut());
        reserv.setValorTotal(reserva.getValorTotal());
        reserv.setStatus(reserva.getStatus());
        reserv.setFuncionario(reserva.getFuncionario());
        reserv.setQuarto(reserva.getQuarto());

        List<Hospede> hospedes = hospedeRepository.findAllByUuidIn(hospedeUUIDs);
        reserv.setHospedes(hospedes);
        this.repository.save(reserv);
    }
}
