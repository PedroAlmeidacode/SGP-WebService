package pt.ufp.info.esof.sgp.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.implementation.bind.annotation.Super;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class Utilizador {
    private String nome;
}
