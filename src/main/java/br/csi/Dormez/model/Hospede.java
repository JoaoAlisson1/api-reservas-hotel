package br.csi.Dormez.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter

public class Hospede { // avaliar código dps

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera ID auto-incremento
    private long id;

    @UuidGenerator
    private UUID uuid;

    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @OneToMany(mappedBy = "hospede")
    private List<Reserva> reservas;
}
