package pt.ufp.info.esof.sgp.dtos.conversores;

import pt.ufp.info.esof.sgp.dtos.EmpregadoResponseDTO;
import pt.ufp.info.esof.sgp.dtos.TarefaResponseDTO;
import pt.ufp.info.esof.sgp.models.Empregado;
import pt.ufp.info.esof.sgp.models.Tarefa;

public class ConverterTarefaParaDTO implements Conversor<TarefaResponseDTO, Tarefa>{

    @Override
    public TarefaResponseDTO converter(Tarefa tarefa){
        TarefaResponseDTO responseDTO = new TarefaResponseDTO();
        responseDTO.setDescricao(tarefa.getDescricao());
        responseDTO.setTitulo(tarefa.getTitulo());
        responseDTO.setDuracaoEstimada(tarefa.getDuracaoEstimada());
        return responseDTO;
    }

}
