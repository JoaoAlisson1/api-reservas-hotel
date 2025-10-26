package br.csi.Dormez.DTO;

import br.csi.Dormez.model.StatusQuarto;
import br.csi.Dormez.model.TipoQuarto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuartoRequestDTO {

    @Min(value = 1, message = "Número do quarto deve ser maior que zero")
    private int numero;

    @NotNull(message = "Tipo do quarto é obrigatório")
    private TipoQuarto tipo;

    @NotNull(message = "Status do quarto é obrigatório")
    private StatusQuarto status;

    @NotNull(message = "Diária é obrigatória")
    @DecimalMin(value = "0.0", inclusive = false, message = "Diária deve ser maior que zero")
    private BigDecimal diaria;
}
