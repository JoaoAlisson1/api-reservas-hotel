package br.csi.Dormez.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "funcionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @UuidGenerator
    private UUID uuid;
    private String nome;
    private String email;
    private String telefone;

    @Enumerated(EnumType.STRING) // salva o nome do enum no DB
    private CargoEnum cargo;

    @OneToMany(mappedBy = "funcionario")
    private List<Reserva> reservas;


}

