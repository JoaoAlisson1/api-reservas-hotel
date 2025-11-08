package br.csi.Dormez.DTO;

import br.csi.Dormez.model.enums.StatusQuarto;
import br.csi.Dormez.model.enums.TipoQuarto;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Número identificador do quarto", example = "101")
    @NotNull(message = "Número do quarto é obrigatório")
    private int numero;

    @NotNull(message = "Tipo do quarto é obrigatório")
    @Schema(description = "Tipo do quarto", example = "Solteiro")
    private TipoQuarto tipo;

    @NotNull(message = "Status do quarto é obrigatório")
    @Schema(description = "Status atual do quarto", example = "Disponivel")
    private StatusQuarto status;

    @NotNull(message = "Diária é obrigatória")
    @DecimalMin(value = "0.0", inclusive = false, message = "Diária deve ser maior que zero")
    @Schema(description = "Valor da diária do quarto", example = "399.90")
    private BigDecimal diaria;
}
