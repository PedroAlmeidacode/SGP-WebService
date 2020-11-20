package pt.ufp.info.esof.sgp.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Cliente extends Utilizador
{
    private List<Projeto> projetos = new ArrayList<>();
}
