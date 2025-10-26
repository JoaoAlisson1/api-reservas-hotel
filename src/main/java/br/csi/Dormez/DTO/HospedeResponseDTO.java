package br.csi.Dormez.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HospedeResponseDTO {

    private UUID uuid;
    private String nome;
    private String email;
    private String telefone;
}
