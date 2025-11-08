package br.csi.Dormez.service;

import br.csi.Dormez.infra.RecursoNaoEncontradoException;
import br.csi.Dormez.model.*;
import br.csi.Dormez.repository.FuncionarioRepository;
import br.csi.Dormez.repository.HospedeRepository;
import br.csi.Dormez.repository.QuartoRepository;
import br.csi.Dormez.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

@Service
public class ReservaService {

    private static final EnumSet<StatusQuarto> STATUS_INDISPONIVEIS =
            EnumSet.of(StatusQuarto.Ocupado, StatusQuarto.Reservado, StatusQuarto.Manutenção,
                    StatusQuarto.Limpeza,StatusQuarto.Indisponível);

    private final ReservaRepository repository;
    private final HospedeRepository hospedeRepository;
    private final QuartoRepository quartoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public ReservaService(ReservaRepository repository, HospedeRepository hospedeRepository, QuartoRepository quartoRepository,
                          FuncionarioRepository funcionarioRepository) {
        this.repository = repository;
        this.hospedeRepository = hospedeRepository;
        this.quartoRepository = quartoRepository;
        this.funcionarioRepository = funcionarioRepository;

    }

    @Transactional
    public Reserva salvar(Reserva reserva, List<UUID> hospedeUUIDs  ) {

        validarDisponibilidadeQuarto(reserva.getQuarto());

        // Adiciona hóspedes
        List<Hospede> hospedes = hospedeRepository.findAllByUuidIn(hospedeUUIDs);
        if (hospedes.isEmpty()) {
            throw new RecursoNaoEncontradoException("Nenhum hóspede válido encontrado.");
        }

        reserva.setHospedes(hospedes);

        // Calcula valor total
        BigDecimal valorTotal = calcularValorTotal(reserva.getCheckIn(), reserva.getCheckOut(), reserva.getQuarto().getDiaria());
        reserva.setValorTotal(valorTotal);

        return this.repository.save(reserva);
    }

    public List<Reserva> listar() {
        return this.repository.findAll();
    }

    public Reserva buscarPorUUID(String uuid) {
        UUID uuidformatado;
        try {
            uuidformatado = UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new RecursoNaoEncontradoException("UUID inválido.");
        }

        Reserva reserva = this.repository.findReservaByUuid(uuidformatado);
        if (reserva == null) {
            throw new RecursoNaoEncontradoException("Reserva não encontrada.");
        }
        return reserva;
    }

    @Transactional
    public void deletarUUID(String uuid) {
        UUID uuidFormatado;
        try {
            uuidFormatado = UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new RecursoNaoEncontradoException("UUID inválido.");
        }

        Reserva reserva = this.repository.findReservaByUuid(uuidFormatado);
        if (reserva == null) {
            throw new RecursoNaoEncontradoException("Reserva não encontrada.");
        }

        this.repository.deleteReservaByUuid(uuidFormatado);
    }

    @Transactional
    public Reserva atualizarUUID(Reserva reserva, List<UUID> hospedeUUIDs ) {

        Reserva reserv = this.repository.findReservaByUuid(reserva.getUuid());
        if (reserv == null) {
            throw new RecursoNaoEncontradoException("Reserva não encontrada.");
        }

        validarDisponibilidadeQuarto(reserva.getQuarto());

        reserv.setCheckIn(reserva.getCheckIn());
        reserv.setCheckOut(reserva.getCheckOut());
        reserv.setStatus(reserva.getStatus());
        reserv.setFuncionario(reserva.getFuncionario());
        reserv.setQuarto(reserva.getQuarto());

        List<Hospede> hospedes = hospedeRepository.findAllByUuidIn(hospedeUUIDs);
        if (hospedes.isEmpty()) {
            throw new RecursoNaoEncontradoException("Nenhum hóspede válido encontrado.");
        }

        reserv.setHospedes(hospedes);

        // Recalcula valor total
        BigDecimal valorTotal = calcularValorTotal(reserva.getCheckIn(), reserva.getCheckOut(), reserva.getQuarto().getDiaria());
        reserv.setValorTotal(valorTotal);

        return this.repository.save(reserv);
    }

    public Quarto buscarQuarto(Long id) {
        return quartoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Quarto não encontrado."));
    }

    public Funcionario buscarFuncionario(UUID uuid) {
        Funcionario funcionario = funcionarioRepository.findFuncionarioByUuid(uuid);
        if (funcionario == null) {
            throw new RecursoNaoEncontradoException("Funcionário não encontrado.");
        }
        return funcionario;

    }

    private void validarDisponibilidadeQuarto(Quarto quarto) {

        if (quarto == null) {
            throw new RecursoNaoEncontradoException("Quarto não informado.");
        }

        if (STATUS_INDISPONIVEIS.contains(quarto.getStatus())) {
            throw new RecursoNaoEncontradoException(
                    "Quarto " + quarto.getNumero() + " não disponível para reserva. Status atual: " + quarto.getStatus());
        }
    }

    private BigDecimal calcularValorTotal(LocalDateTime checkIn, LocalDateTime checkOut, BigDecimal diaria) {

        if (checkOut.isBefore(checkIn)) {
            throw new IllegalArgumentException("A data de check-out não pode ser anterior à data de check-in.");
        }

        long dias = java.time.temporal.ChronoUnit.DAYS.between(checkIn.toLocalDate(), checkOut.toLocalDate());

        if (dias <= 0) { // Garante no mínimo 1 diária
            dias = 1;
        }


        return diaria.multiply(BigDecimal.valueOf(dias)).setScale(2, RoundingMode.HALF_UP);
    }
}
