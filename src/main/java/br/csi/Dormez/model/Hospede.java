package br.csi.Dormez.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter

public class Hospede { // avaliar código dps

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera ID auto-incremento
    private long id;

    @UuidGenerator
    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;

    @NotBlank
    private String nome;
    @Email(message = "Email inválido")
    private String email;
    //@Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone inválido")
    private String telefone;
    private String cpf;

    @ManyToMany(mappedBy = "hospedes")
    @JsonIgnore
    private List<Reserva> reservas;
}
