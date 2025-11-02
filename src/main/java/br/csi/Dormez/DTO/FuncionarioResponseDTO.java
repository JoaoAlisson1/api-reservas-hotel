package br.csi.Dormez.DTO;

import br.csi.Dormez.model.CargoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto de resposta com dados do funcionário")
public class FuncionarioResponseDTO {

    private UUID uuid;
    private String nome;
    private String email;
    private String telefone;
    private CargoEnum cargo;
}
