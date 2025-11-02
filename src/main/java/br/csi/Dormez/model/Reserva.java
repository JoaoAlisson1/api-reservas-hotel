package br.csi.Dormez.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Schema(description = "Data e hora de check-in", example = "2025-12-20T14:00:00")
    private LocalDateTime checkIn;
    @Schema(description = "Data e hora do check-out.", example = "2025-12-22T12:00:00")
    private LocalDateTime checkOut;

    @Schema(description = "Valor total da reserva em reais.", example = "599.90")
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    private StatusReserva status;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    @Schema(description = "Funcionário responsável pelo atendimento ou cadastro da reserva.")
    private Funcionario funcionario;

    @ManyToMany
    @JoinTable(
            name = "reserva_hospede",
            joinColumns = @JoinColumn(name = "reserva_id"),
            inverseJoinColumns = @JoinColumn(name = "hospede_id")
    )
    @Schema(description = "Lista de hóspedes associados à reserva.")
    private List<Hospede> hospedes =  new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "quarto_id")
    @Schema(description = "Quarto reservado.")
    private Quarto quarto;

}
