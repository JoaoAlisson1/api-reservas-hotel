package br.csi.Dormez.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {

    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private double valorTotal;
    private String status;
    private Long funcionarioId;
    private Long quartoId;
    private List<UUID> hospedeUUIDs;
}
