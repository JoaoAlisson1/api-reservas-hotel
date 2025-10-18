package br.csi.Dormez.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "funcionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Schema(description = "Entidade que representa um funcionário no sistema")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @UuidGenerator

    private UUID uuid;

    @NotBlank
    private String nome;

    @Email(message = "Email inválido")
    private String email;
    @NotBlank
    //@Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone inválido")
    @Schema(description = "Telefone para contato", example = "(11) 91234-5678")
    private String telefone;

    @NotNull(message = "Cargo é obrigatório")
    @Enumerated(EnumType.STRING) // salva o nome do enum no DB
    @Schema(description = "Cargo ocupado pelo funcionário", example = "RECEPCIONISTA")
    private CargoEnum cargo;

    @OneToMany(mappedBy = "funcionario")
    @JsonIgnore
    @Schema(description = "Reservas associadas ao funcionário", hidden = true)
    private List<Reserva> reservas;

    //  Construtor personalizado para criação rápida
    public Funcionario(String nome, String email, String telefone, CargoEnum cargo) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cargo = cargo;
    }



}

