package br.csi.Dormez.DTO;

import br.csi.Dormez.model.CargoEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioRequestDTO {

    @NotBlank
    private String nome;
    @Email(message = "Email inválido")
    private String email;
    @NotBlank
    private String telefone;
    @NotNull(message = "Cargo é obrigatório")
    private CargoEnum cargo;
}
