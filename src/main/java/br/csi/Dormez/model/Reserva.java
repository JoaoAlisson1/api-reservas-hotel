package br.csi.Dormez.model;

import br.csi.Dormez.model.enums.StatusReserva;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidade que representa uma reserva feita por um hóspede")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @UuidGenerator
    @Column(unique = true, updatable = false, nullable = false)
    private UUID uuid;

    private LocalDateTime checkIn;

    private LocalDateTime checkOut;

    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    private StatusReserva status;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")

    private Funcionario funcionario;

    @ManyToMany
    @JoinTable(
            name = "reserva_hospede",
            joinColumns = @JoinColumn(name = "reserva_id"),
            inverseJoinColumns = @JoinColumn(name = "hospede_id")
    )

    private List<Hospede> hospedes =  new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "quarto_id")

    private Quarto quarto;

}
