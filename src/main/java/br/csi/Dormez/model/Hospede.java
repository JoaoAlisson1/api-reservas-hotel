package br.csi.Dormez.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter

@Schema(description = "Entidade que representa um hospede no sistema do hotel")
public class Hospede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera ID auto-incremento
    private long id;

    @UuidGenerator
    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;

    private String nome;

    private String email;

    private String telefone;

    private String cpf;

    @ManyToMany(mappedBy = "hospedes")
    @JsonIgnore
    @Schema(description = "Reservas associadas ao hóspede", accessMode = Schema.AccessMode.READ_ONLY)
    private List<Reserva> reservas;
}
