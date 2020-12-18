package pt.ufp.info.esof.sgp.dtos.conversores;

import pt.ufp.info.esof.sgp.dtos.EstadoDescritivoProjetoDTO;
import pt.ufp.info.esof.sgp.dtos.EstadoTarefaDTO;
import pt.ufp.info.esof.sgp.models.Projeto;

import java.util.stream.Collectors;

public class ConverterProjetoParaEstadoDescritivoDTO implements Conversor<EstadoDescritivoProjetoDTO, Projeto> {

    @Override
    public EstadoDescritivoProjetoDTO converter(Projeto projeto) {

        EstadoDescritivoProjetoDTO responseDTO = new EstadoDescritivoProjetoDTO();

        // percorre cada tarefa do projeto
        responseDTO.setEstadoTarefas(projeto.getTarefas().stream().map(tarefa -> {
            EstadoTarefaDTO tarefaDTO = new EstadoTarefaDTO();

            //insere dados para preencher EstadoTarefaDTO para cada tarefa
            tarefaDTO.setNomeEmpregado(tarefa.getEmpregado().getNome());
            int duracao = tarefa.getDuracaoEstimada();
            tarefaDTO.setDuracaoEstimada(duracao/24/60 + " dias, " + duracao/60%24 + " horas, " + duracao%60 + " minutos");
            tarefaDTO.setDataIniciacao(tarefa.getDataIniciacao());
            tarefaDTO.setTitulo(tarefa.getTitulo());
            tarefaDTO.setPercentualConclusao(tarefa.getTarefaAtual().getPercentualConclusao());
            tarefaDTO.setEstadoTarefa(tarefa.getEstadoTarefa());
            return tarefaDTO;
        }).collect(Collectors.toList()));

        return responseDTO;
    }
}
