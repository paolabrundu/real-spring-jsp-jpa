package it.nextre.academy.realspring.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="film")
//Table serve per dare alla tab un nome diverso dalla classe
public class Film {

    //Id indica che è una primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idfilm", length = 3)
    @NotNull(message="Questo campo è obbligatorio")
    //  @Transient non porta le colonne su DB
    private long id;
    //  @Size(min=3, max=255)
    @NotNull(message="Questo campo è obbligatorio")
    @Column(length=255)
    private String titolo;
    private String regista;
    @Size(min=1900, max=2100, message="Questo campo è obbligatorio")
    private int anno;
}//end class
