package br.csi.Dormez.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Min(value = 1, message = "Número do quarto deve ser maior que zero")
    private int numero;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoQuarto tipo;

    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusQuarto status;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "Diária deve ser maior que zero")
    private BigDecimal diaria;

    @OneToMany(mappedBy = "quarto")
    @JsonIgnore
    private List<Reserva> reservas;

}
