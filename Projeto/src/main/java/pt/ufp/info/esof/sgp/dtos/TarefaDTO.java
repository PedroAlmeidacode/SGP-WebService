package pt.ufp.info.esof.sgp.dtos;

import lombok.Data;
import pt.ufp.info.esof.sgp.models.Empregado;
import pt.ufp.info.esof.sgp.models.Projeto;
import pt.ufp.info.esof.sgp.models.TarefaAtual;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Data
public class TarefaDTO {
    private int duracaoEstimada;
    private LocalDateTime dataIniciacao;
    private String titulo;
    private String descricao;
    private String projetoNome;
}
