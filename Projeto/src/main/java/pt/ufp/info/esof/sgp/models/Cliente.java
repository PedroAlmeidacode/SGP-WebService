package pt.ufp.info.esof.sgp.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Utilizador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Projeto> projetos = new ArrayList<>();

    public void adicionaProjeto(Projeto projeto) {
        if (!this.projetos.contains(projeto)) {
            this.projetos.add(projeto);
            projeto.setCliente(this);
        }
    }
}
