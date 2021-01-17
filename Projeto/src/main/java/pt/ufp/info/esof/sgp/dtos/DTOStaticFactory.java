package pt.ufp.info.esof.sgp.dtos;

import pt.ufp.info.esof.sgp.dtos.creators.EmpregadoCreateDTO;
import pt.ufp.info.esof.sgp.dtos.creators.ProjetoCreateDTO;
import pt.ufp.info.esof.sgp.dtos.creators.TarefaCreateDTO;
import pt.ufp.info.esof.sgp.dtos.responses.*;
import pt.ufp.info.esof.sgp.models.*;
import pt.ufp.info.esof.sgp.models.enums.Estado;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DTOStaticFactory {

    //lógica para implemntar apenas uma instância de fábrica estática
    private static DTOStaticFactory dtoStaticFactory;

    private DTOStaticFactory() {//
    }

    //contrutor que apenas constroi uma unica vez
    public static DTOStaticFactory getInstance() {
        if (dtoStaticFactory == null) //caso se encontre null, instancia o objeto uma vez e envia o mesmo
            dtoStaticFactory = new DTOStaticFactory();
        return dtoStaticFactory;
    }

    //-------------------CREATORS------------------------
    public EmpregadoCreateDTO empregadoCreateDTO(Empregado empregado) {
        return EmpregadoCreateDTO.builder()
                .nome(empregado.getNome())
                .cargo(empregado.getCargo())
                .email(empregado.getEmail())
                .build();
    }

    public ProjetoCreateDTO projetoCreateDTO(Projeto projeto) {
        return ProjetoCreateDTO.builder()
                .nome(projeto.getNome())
                .clienteId(projeto.getCliente().getId())
                .build();

    }

    public TarefaCreateDTO tarefaCreateDTO(Tarefa tarefa) {
        return TarefaCreateDTO.builder()
                .titulo(tarefa.getTitulo())
                .descricao(tarefa.getDescricao())
                .duracaoEstimada(tarefa.getDuracaoEstimada())
                .build();

    }

    public ClienteDTO clienteDTO(Cliente cliente) {

        return ClienteDTO.builder()
                .email(cliente.getEmail())
                .build();
    }
    //-------------------RESPONSES------------------------

    public CustoResponseDTO custoResponseDTO(Double custo)
    {
        DecimalFormat df = new DecimalFormat("###.##");
        return CustoResponseDTO.builder()
                .custo(df.format(custo))
                .build();
    }

    public DuracaoResponseDTO duracaoResponseDTO (Integer duracao)
    {
        return DuracaoResponseDTO.builder()
                .duracao(duracao/24/60 + " dias, " + duracao/60%24 + " horas, " + duracao%60 + " minutos")
                .build();
    }

    //estado geral de uma tarefa para DTO
    public EstadoTarefaDTO estadoTarefaDTO (Tarefa tarefa)
    {
        int duracao=tarefa.getDuracaoEstimada();
        if(tarefa.getTarefaAtual()==null)
        {
            return EstadoTarefaDTO.builder()
                    .duracaoEstimada(duracao/24/60 + " dias, " + duracao/60%24 + " horas, " + duracao%60 + " minutos")
                    .dataIniciacao(tarefa.getDataIniciacao())
                    .titulo(tarefa.getTitulo())
                    .estadoTarefa(tarefa.getEstadoTarefa())
                    .build();

        }
        return EstadoTarefaDTO.builder()
                .nomeEmpregado(tarefa.getEmpregado().getNome())
                .duracaoEstimada(duracao/24/60 + " dias, " + duracao/60%24 + " horas, " + duracao%60 + " minutos")
                .dataIniciacao(tarefa.getDataIniciacao())
                .titulo(tarefa.getTitulo())
                .percentualConclusao(tarefa.getTarefaAtual().getPercentualConclusao())
                .estadoTarefa(tarefa.getEstadoTarefa())
                .build();
    }

    //estado descritivo do projeto inteiro retorna todas as tarefas com o seu estado
    public EstadoDescritivoProjetoDTO estadoDescritivoProjetoDTO(Projeto projeto)
    {
        List<EstadoTarefaDTO>estadoTarefas=new ArrayList<>();
        List<Tarefa> tarefas = projeto.getTarefas();
        for (Tarefa tarefa: tarefas)
        {
            estadoTarefas.add(estadoTarefaDTO(tarefa));
        }
        return EstadoDescritivoProjetoDTO.builder()
                .estadoTarefas(estadoTarefas)
                .build();
    }

    //resposto do projeto em dto
    public ProjetoResponseDTO projetoResponseDTO(Projeto projeto)
    {
         return ProjetoResponseDTO.builder()
                 .nome(projeto.getNome())
         .cliente(clienteDTO(projeto.getCliente()))
         .tarefas(projeto.getTarefas().stream().map(this::tarefaCreateDTO).collect(Collectors.toList()))
                 .build();
    }


    public EstadoResponseDTO estadoResponseDTO(Estado estado)
    {
        return  EstadoResponseDTO.builder()
                .estado(estado)
                .build();
    }

    public TarefaAtualDTO tarefaAtualDTO(TarefaAtual tarefaAtual) {

        return TarefaAtualDTO.builder()
                .tempoDedicado(tarefaAtual.getTempoDedicado())
                .percentualConclusao(tarefaAtual.getPercentualConclusao())
                .ultimaAtualizacao(tarefaAtual.getUltimaAtualizacao())
                .build();
    }



    public TarefaResponseDTO tarefaResponseDTO (Tarefa tarefa)
    {
        if(tarefa.getEmpregado()!=null && tarefa.getTarefaAtual()!=null)
        return TarefaResponseDTO.builder()
                .duracaoEstimada(tarefa.getDuracaoEstimada())
                .titulo(tarefa.getTitulo())
                .descricao(tarefa.getDescricao())
                .empregado(this.empregadoCreateDTO(tarefa.getEmpregado()))
                .tarefaAtual(this.tarefaAtualDTO(tarefa.getTarefaAtual()))
                .build();

        return TarefaResponseDTO.builder()
                .duracaoEstimada(tarefa.getDuracaoEstimada())
                .titulo(tarefa.getTitulo())
                .descricao(tarefa.getDescricao())
                .build();

    }




}

