package pt.ufp.info.esof.sgp.dtos.conversores;

import pt.ufp.info.esof.sgp.dtos.EmpregadoCreateDTO;
import pt.ufp.info.esof.sgp.dtos.TarefaAtualDTO;
import pt.ufp.info.esof.sgp.dtos.TarefaCreateDTO;
import pt.ufp.info.esof.sgp.dtos.TarefaResponseDTO;
import pt.ufp.info.esof.sgp.models.Tarefa;

import java.util.stream.Collectors;

public class ConverterTarefaParaDTO implements Conversor<TarefaResponseDTO, Tarefa>{

    @Override
    public TarefaResponseDTO converter(Tarefa tarefa){
        TarefaResponseDTO responseDTO = new TarefaResponseDTO();
        responseDTO.setDescricao(tarefa.getDescricao());
        responseDTO.setTitulo(tarefa.getTitulo());
        responseDTO.setDuracaoEstimada(tarefa.getDuracaoEstimada());


        EmpregadoCreateDTO empregadoCreateDTO = new EmpregadoCreateDTO();
        empregadoCreateDTO.setEmail(tarefa.getEmpregado().getEmail());
        empregadoCreateDTO.setCargo(tarefa.getEmpregado().getCargo());
        empregadoCreateDTO.setNome(tarefa.getEmpregado().getNome());
        responseDTO.setEmpregado(empregadoCreateDTO);

        TarefaAtualDTO tarefaAtualDTO = new TarefaAtualDTO();
        tarefaAtualDTO.setUltimaAtualizacao(tarefa.getTarefaAtual().getUltimaAtualizacao());
        tarefaAtualDTO.setPercentualConclusao(tarefa.getTarefaAtual().getPercentualConclusao());
        tarefaAtualDTO.setTempoDedicado(tarefa.getTarefaAtual().getTempoDedicado());
        responseDTO.setTarefaAtual(tarefaAtualDTO);

        return responseDTO;
    }

}
