package br.csi.Dormez.service;

import br.csi.Dormez.model.Hospede;
import br.csi.Dormez.model.Reserva;
import br.csi.Dormez.repository.HospedeRepository;
import br.csi.Dormez.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
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

        BigDecimal valorTotal = calcularValorTotal(reserva.getCheckIn(), reserva.getCheckOut(), reserva.getQuarto().getDiaria());
        reserva.setValorTotal(valorTotal);
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

        // Recalcula valor total
        BigDecimal valorTotal = calcularValorTotal(reserva.getCheckIn(), reserva.getCheckOut(), reserva.getQuarto().getDiaria());
        reserv.setValorTotal(valorTotal); //oberservar

        this.repository.save(reserv);
    }

    private BigDecimal calcularValorTotal(LocalDateTime checkIn, LocalDateTime checkOut, BigDecimal diaria) {

        if (checkOut.isBefore(checkIn)) {
            throw new IllegalArgumentException("A data de check-out não pode ser anterior à data de check-in.");
        }

        long dias = java.time.Duration.between(checkIn, checkOut).toDays();

        if(dias == 0) { // Garante no mínimo 1 diária
            dias = 1;
        }

        return diaria.multiply(BigDecimal.valueOf(dias)).setScale(2, RoundingMode.HALF_UP);
    }
}
