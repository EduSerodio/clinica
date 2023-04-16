package com.dh.clinica.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tb_consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    @OneToOne
    @JoinColumn(name = "dentista_id")
    private Dentista dentista;

    private Date date;

}
