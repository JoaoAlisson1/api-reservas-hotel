package br.csi.Dormez.repository;

import br.csi.Dormez.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    public Reserva findReservaByUuid(UUID uuid);
    public void deleteReservaByUuid(UUID uuid);
}
