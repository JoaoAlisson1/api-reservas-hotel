package br.csi.Dormez.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidade que representa um quarto de hotel")
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Min(value = 1, message = "Número do quarto deve ser maior que zero")
    @Schema(description = "Número identificador do quarto", example = "101")
    private int numero;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Schema(description = "Tipo do quarto", example = "Solteiro")
    private TipoQuarto tipo;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Schema(description = "Status atual do quarto", example = "Disponivel")
    private StatusQuarto status;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "Diária deve ser maior que zero")
    @Schema(description = "Valor da diária do quarto", example = "199.90")
    private BigDecimal diaria;

    @OneToMany(mappedBy = "quarto")
    @JsonIgnore
    private List<Reserva> reservas;

}
