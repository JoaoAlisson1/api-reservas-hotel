package br.csi.Dormez.DTO.mapper;

import br.csi.Dormez.DTO.*;
import br.csi.Dormez.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class ReservaMapper {

    // --- ReservaRequestDTO -> Reserva ---
    public static Reserva toEntity(ReservaRequestDTO dto, Funcionario funcionario, Quarto quarto, List<Hospede> hospedes) {
        Reserva reserva = new Reserva();
        reserva.setCheckIn(dto.getCheckIn());
        reserva.setCheckOut(dto.getCheckOut());
        reserva.setStatus(dto.getStatus());
        reserva.setFuncionario(funcionario);
        reserva.setQuarto(quarto);
        if (hospedes != null) {
            reserva.setHospedes(hospedes);
        }
        return reserva;
    }

    // --- Reserva -> ReservaResponseDTO ---
    public static ReservaResponseDTO toDTO(Reserva reserva) {
        ReservaResponseDTO dto = new ReservaResponseDTO();
        dto.setUuid(reserva.getUuid());
        dto.setCheckIn(reserva.getCheckIn());
        dto.setCheckOut(reserva.getCheckOut());
        dto.setValorTotal(reserva.getValorTotal());
        dto.setStatus(reserva.getStatus());

        dto.setFuncionario(toFuncionarioDTO(reserva.getFuncionario()));
        dto.setQuarto(toQuartoDTO(reserva.getQuarto()));
        dto.setHospedes(reserva.getHospedes().stream()
                .map(ReservaMapper::toHospedeDTO)
                .collect(Collectors.toList()));

        return dto;
    }

    // --- Mappers auxiliares ---
    private static FuncionarioResponseDTO toFuncionarioDTO(Funcionario funcionario) {
        return new FuncionarioResponseDTO(
                funcionario.getUuid(),
                funcionario.getNome(),
                funcionario.getEmail(),
                funcionario.getTelefone(),
                funcionario.getCargo()
        );
    }

    private static HospedeResponseDTO toHospedeDTO(Hospede hospede) {
        return new HospedeResponseDTO(
                hospede.getUuid(),
                hospede.getNome(),
                hospede.getEmail(),
                hospede.getTelefone()
        );
    }

    private static QuartoResponseDTO toQuartoDTO(Quarto quarto) {
        return new QuartoResponseDTO(
                quarto.getId(),
                quarto.getNumero(),
                quarto.getTipo(),
                quarto.getStatus(),
                quarto.getDiaria()
        );
    }
}
