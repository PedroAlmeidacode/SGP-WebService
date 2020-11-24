package pt.ufp.info.esof.sgp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pt.ufp.info.esof.sgp.models.Cliente;
import pt.ufp.info.esof.sgp.models.Empregado;
import pt.ufp.info.esof.sgp.models.Projeto;
import pt.ufp.info.esof.sgp.models.Tarefa;
import pt.ufp.info.esof.sgp.repositories.*;

@Component
public class Inicializacao implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EmpregadoRepository empregadoRepository;
    @Autowired
    private GestorDeProjetoRepository gestorDeProjetoRepository;
    @Autowired
    private ProjetoRepository projetoRepository;
    @Autowired
    private TarefaAtualRepository tarefaAtualRepository;
    @Autowired
    private TarefaRepository tarefaRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("\n\n\nInicializou\n\n\n");


        Cliente cliente = new Cliente();
        cliente.setNome("Jorge");

        Projeto projeto = new Projeto();

        //TAREFA ESTADO NORMAL
        Tarefa tarefa = new Tarefa();
        Empregado empregado = new Empregado();
        tarefa.atribuirEmpregadoaTarefa(empregado);
        // duaracao estimada = 1000 min
        tarefa.setDuracaoEstimada(1000);
        // percentual de conclusao = 50
        tarefa.getTarefaAtual().setPercentualConclusao(50);
        // tempo dedicado = 500 min
        tarefa.getTarefaAtual().setTempoDedicado(500);


        //TAREFA ESTADO NORMAL
        Tarefa tarefa1 = new Tarefa();
        tarefa1.atribuirEmpregadoaTarefa(empregado);
        // duaracao estimada = 1000 min
        tarefa1.setDuracaoEstimada(1000);
        // percentual de conclusao = 50
        tarefa1.getTarefaAtual().setPercentualConclusao(50);
        // esperado um estado de tarefa normal , diferencial de menos de 10 entre
        // a percentagem de tempo usada em relacao ao tempo que tem e a percentagem de conclusao
        tarefa1.getTarefaAtual().setTempoDedicado(580);


        //TAREFA ESTADO ATRASADA
        Tarefa tarefa2 = new Tarefa();
        tarefa2.atribuirEmpregadoaTarefa(empregado);
        // duaracao estimada = 1000 min
        tarefa2.setDuracaoEstimada(1000);
        // percentual de conclusao = 50
        tarefa2.getTarefaAtual().setPercentualConclusao(50);
        // esperado um estado de tarefa atrasada visto que conclui menos do que o tempo que usou
        // e o diferencial entre o tempo usado e o feito é maior que 10
        tarefa2.getTarefaAtual().setTempoDedicado(700);


        //TAREFA ESTADO ADIANTADA
        Tarefa tarefa3 = new Tarefa();
        tarefa3.atribuirEmpregadoaTarefa(empregado);
        // duaracao estimada = 1000 min
        tarefa3.setDuracaoEstimada(1000);
        // percentual de conclusao = 50
        tarefa3.getTarefaAtual().setPercentualConclusao(50);
        // esperado um estado de tarefa adiantada visto que conclui mais do que o tempo que usou
        tarefa3.getTarefaAtual().setTempoDedicado(400);


        // adiconar 4 tarefas ao projeto
        projeto.getTarefas().add(tarefa);
        projeto.getTarefas().add(tarefa1);
        projeto.getTarefas().add(tarefa2);
        projeto.getTarefas().add(tarefa3);


        cliente.getProjetos().add(projeto);

        this.projetoRepository.save(projeto);
        this.empregadoRepository.save(empregado);


    }
}
