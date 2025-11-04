package br.csi.Dormez.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HospedeRequestDTO {
    @NotBlank
    @Schema(description = "Nome completo do hóspede", example = "Carlos Freitas")
    private String nome;
    @Email(message = "Email inválido")
    @Schema(description = "Email válido do hóspede", example = "carlos@email.com")
    private String email;
    //@Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone inválido")
    @Schema(description = "Telefone de contato do hóspede", example = "(11) 91234-5678")
    private String telefone;
    @NotBlank
    @Schema(description = "CPF do hóspede", example = "123.456.789-00")
    @Column(length = 14)
    private String cpf;
}
