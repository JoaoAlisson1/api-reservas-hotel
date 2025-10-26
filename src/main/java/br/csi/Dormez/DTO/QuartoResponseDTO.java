package br.csi.Dormez.DTO;

import br.csi.Dormez.model.StatusQuarto;
import br.csi.Dormez.model.TipoQuarto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuartoResponseDTO {

    private Long id;
    private int numero;
    private TipoQuarto tipo;
    private StatusQuarto status;
    private BigDecimal diaria;
}
