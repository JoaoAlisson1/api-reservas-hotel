package br.csi.Dormez.DTO;

import br.csi.Dormez.model.CargoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    @Email(message = "Email inválido")
    private String email;
    @NotBlank
    //@Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone inválido")
    @Schema(description = "Telefone para contato", example = "(11) 91234-5678")
    private String telefone;
    @NotNull(message = "Cargo é obrigatório")
    @Schema(description = "Cargo ocupado pelo funcionário", example = "RECEPCIONISTA")
    private CargoEnum cargo;
}
