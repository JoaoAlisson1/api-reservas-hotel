package br.csi.Dormez.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
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

@Schema(description = "Entidade que representa um hospede no sistema do hotel")
public class Hospede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera ID auto-incremento
    private long id;

    @UuidGenerator
    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;

    @NotBlank
    @Schema(description = "Nome completo do hóspede", example = "Carlos Freitas")
    private String nome;
    @Email(message = "Email inválido")
    @Schema(description = "Email válido do hóspede", example = "carlos@email.com")
    private String email;
    //@Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone inválido")
    @Schema(description = "Telefone de contato do hóspede", example = "(11) 91234-5678")
    private String telefone;
    @Schema(description = "CPF do hóspede", example = "123.456.789-00")
    private String cpf;

    @ManyToMany(mappedBy = "hospedes")
    @JsonIgnore
    @Schema(description = "Reservas associadas ao hóspede", accessMode = Schema.AccessMode.READ_ONLY)
    private List<Reserva> reservas;
}
