package br.csi.Dormez.model;

import br.csi.Dormez.model.enums.StatusQuarto;
import br.csi.Dormez.model.enums.TipoQuarto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
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

    private int numero;

    @Enumerated(EnumType.STRING)
    private TipoQuarto tipo;

    @Enumerated(EnumType.STRING)

    private StatusQuarto status;

    private BigDecimal diaria;

    @OneToMany(mappedBy = "quarto")
    @JsonIgnore
    private List<Reserva> reservas;

}
