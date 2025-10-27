package br.csi.Dormez.DTO;

import br.csi.Dormez.model.CargoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioResponseDTO {

    private UUID uuid;
    private String nome;
    private String email;
    private String telefone;
    private CargoEnum cargo;
}
