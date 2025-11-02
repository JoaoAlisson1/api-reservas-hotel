package br.csi.Dormez.DTO;

import br.csi.Dormez.model.StatusReserva;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Resposta detalhada de uma reserva")
public class ReservaResponseDTO {

    private UUID uuid;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private BigDecimal valorTotal;
    private StatusReserva status;
    private FuncionarioResponseDTO funcionario;
    private List<HospedeResponseDTO> hospedes;
    private QuartoResponseDTO quarto;
}
