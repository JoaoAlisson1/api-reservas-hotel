package br.csi.Dormez.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {

    @NotNull
    private LocalDateTime checkIn;
    @NotNull
    private LocalDateTime checkOut;
    @NotBlank
    private String status;
    @NotNull
    private Long funcionarioId;
    @NotNull
    private Long quartoId;
    private List<@NotNull(message = "UUID do hóspede não pode ser nulo")UUID> hospedeUUIDs;
}
