package br.csi.Dormez.DTO;

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
    private String nome;
    @Email(message = "Email inválido")
    private String email;
    private String telefone;
    @NotBlank
    private String cpf;
}
