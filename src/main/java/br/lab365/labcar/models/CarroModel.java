package br.lab365.labcar.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "carro")
public class CarroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String marca;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String modelo;

    @Column(nullable = false)
    private Integer ano;


    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal preco;

    @NotBlank
    @Column(columnDefinition = "text", nullable = false)
    private String foto;

}
