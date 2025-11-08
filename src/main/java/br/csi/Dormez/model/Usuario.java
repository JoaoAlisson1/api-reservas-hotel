package br.csi.Dormez.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidade que representa um usuário no sistema do hotel")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    @Email(message = "O login deve ser um email válido")
    private String login;
    @NotNull
    @NotBlank(message = "A senha não pode estar vazia")
    private String senha;
    @NotBlank(message = "A permissão não pode estar vazia")
    @NotNull(message = "A permissão é obrigatória")
    private String permissao;
}
