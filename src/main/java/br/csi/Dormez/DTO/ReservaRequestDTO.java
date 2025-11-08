package br.csi.Dormez.DTO;

import br.csi.Dormez.model.enums.StatusReserva;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaRequestDTO {

    @NotNull
    @Schema(description = "Data e hora de check-in da reserva", example = "2025-12-20T14:00:00")
    private LocalDateTime checkIn;

    @NotNull(message = "A data de check-out é obrigatória")
    @Schema(description = "Data e hora de check-out da reserva", example = "2025-12-22T12:00:00")
    private LocalDateTime checkOut;

    @NotNull(message = "O status da reserva é obrigatório")
    @Schema(description = "Status atual da reserva", example = "Reservada")
    private StatusReserva status;

    @NotNull(message = "O ID do funcionário é obrigatório")
    @Schema(description = "ID do funcionário responsável pelo cadastro da reserva", example = "3")
    private UUID funcionarioUuid;

    @NotNull(message = "O ID do quarto é obrigatório")
    @Schema(description = "ID do quarto reservado", example = "101")
    private Long quartoId;

    @Schema(description = "Lista de UUIDs dos hóspedes associados à reserva")
    private List<@NotNull(message = "UUID do hóspede não pode ser nulo") UUID> hospedeUUIDs;
}
