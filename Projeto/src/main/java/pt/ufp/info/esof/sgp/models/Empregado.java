package pt.ufp.info.esof.sgp.models;

import pt.ufp.info.esof.sgp.models.enums.Cargo;

import java.util.ArrayList;
import java.util.List;

public class Empregado extends Utilizador {

    private List<Tarefa> tarefas = new ArrayList<>();
    private Cargo cargo;

    private void incluirTempoDedicado(int tempo)
    {
        // ir à tarefa buscar a tarefa atual e inserir tempo dedicado --> setTempodedicado
    }

}
