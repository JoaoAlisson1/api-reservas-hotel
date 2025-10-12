package br.csi.Dormez.service;

import br.csi.Dormez.model.Reserva;
import br.csi.Dormez.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ReservaService {

    private final ReservaRepository repository;

    public ReservaService(ReservaRepository repository) {this.repository = repository;}

    public void salvar(Reserva reserva) {
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

    public void atualizarUUID(Reserva reserva) {

        Reserva reserv = this.repository.findReservaByUuid(reserva.getUuid());
        reserv.setCheckIn(reserva.getCheckIn());
        reserv.setCheckOut(reserva.getCheckOut());
        reserv.setValorTotal(reserva.getValorTotal());
        reserv.setStatus(reserva.getStatus());
        reserv.setFuncionario(reserva.getFuncionario());
        reserv.setQuarto(reserva.getQuarto());
        reserv.setHospedes(reserva.getHospedes());
        this.repository.save(reserv);
    }
}
