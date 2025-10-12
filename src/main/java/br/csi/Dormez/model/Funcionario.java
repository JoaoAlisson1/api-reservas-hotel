package br.csi.Dormez.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
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
    @JsonIgnore
    private List<Reserva> reservas;

    //  Construtor personalizado para criação rápida
    public Funcionario(String nome, String email, String telefone, CargoEnum cargo) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cargo = cargo;
    }



}

