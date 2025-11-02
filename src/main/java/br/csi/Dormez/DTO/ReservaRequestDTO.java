package br.csi.Dormez.DTO;

import br.csi.Dormez.model.StatusReserva;
import jakarta.validation.constraints.NotBlank;
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
    private LocalDateTime checkIn;

    @NotNull
    private LocalDateTime checkOut;

    @NotNull
    private StatusReserva status;

    @NotNull
    private Long funcionarioId;

    @NotNull
    private Long quartoId;

    private List<@NotNull(message = "UUID do hóspede não pode ser nulo") UUID> hospedeUUIDs;
}
