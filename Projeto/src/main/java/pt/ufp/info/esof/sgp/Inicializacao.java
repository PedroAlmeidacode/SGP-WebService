package pt.ufp.info.esof.sgp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pt.ufp.info.esof.sgp.models.*;
import pt.ufp.info.esof.sgp.models.enums.Cargo;
import pt.ufp.info.esof.sgp.repositories.*;

@Component
public class Inicializacao implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EmpregadoRepository empregadoRepository;
    @Autowired
    private ProjetoRepository projetoRepository;
    @Autowired
    private TarefaAtualRepository tarefaAtualRepository;
    @Autowired
    private TarefaRepository tarefaRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("\n\n\nSGP - Web Service a iniciar ... \n\n\n");


        Cliente cliente = new Cliente();
        cliente.setNome("Jorge");
        cliente.setEmail("jorge.lindo@gmai9l.xom");

        Projeto projeto = new Projeto();
        cliente.adicionaProjeto(projeto);


        //TAREFA ESTADO NORMAL
        Tarefa tarefa = new Tarefa();
        Tarefa tarefa1 = new Tarefa();
        Tarefa tarefa2 = new Tarefa();
        Tarefa tarefa3 = new Tarefa();

        projeto.adicionarTarefa(tarefa);
        projeto.adicionarTarefa(tarefa1);
        projeto.adicionarTarefa(tarefa2);
        projeto.adicionarTarefa(tarefa3);

        this.clienteRepository.save(cliente);

        Empregado empregado = new Empregado();
        Empregado empregado1 = new Empregado();
        empregado.setCargo(Cargo.ANALISTA_JUNIOR);
        empregado1.setCargo(Cargo.ANLISTA_SENIOR);

        empregado.adicionarTarefa(tarefa);
        empregado.adicionarTarefa(tarefa1);
        empregado1.adicionarTarefa(tarefa2);
        empregado1.adicionarTarefa(tarefa3);

        //tarefa.atribuirEmpregadoaTarefa(empregado);
        // duaracao estimada = 1000 min
        tarefa.setDuracaoEstimada(1000);
        // percentual de conclusao = 50
        tarefa.getTarefaAtual().setPercentualConclusao(50);
        // tempo dedicado = 500 min
        tarefa.getTarefaAtual().setTempoDedicado(500);


        //TAREFA ESTADO NORMAL


        // duaracao estimada = 1000 min
        tarefa1.setDuracaoEstimada(1000);
        // percentual de conclusao = 50
        tarefa1.getTarefaAtual().setPercentualConclusao(50);
        // esperado um estado de tarefa normal , diferencial de menos de 10 entre
        // a percentagem de tempo usada em relacao ao tempo que tem e a percentagem de conclusao
        tarefa1.getTarefaAtual().setTempoDedicado(580);


        //TAREFA ESTADO ATRASADA

        // duaracao estimada = 1000 min
        tarefa2.setDuracaoEstimada(1000);
        // percentual de conclusao = 50
        tarefa2.getTarefaAtual().setPercentualConclusao(50);
        // esperado um estado de tarefa atrasada visto que conclui menos do que o tempo que usou
        // e o diferencial entre o tempo usado e o feito é maior que 10
        tarefa2.getTarefaAtual().setTempoDedicado(700);


        //TAREFA ESTADO ADIANTADA

        // duaracao estimada = 1000 min
        tarefa3.setDuracaoEstimada(1000);
        // percentual de conclusao = 50
        tarefa3.getTarefaAtual().setPercentualConclusao(50);
        // esperado um estado de tarefa adiantada visto que conclui mais do que o tempo que usou
        tarefa3.getTarefaAtual().setTempoDedicado(400);


        this.empregadoRepository.save(empregado);
        this.empregadoRepository.save(empregado1);

        this.tarefaRepository.save(tarefa1);
        this.tarefaRepository.save(tarefa2);
        this.tarefaRepository.save(tarefa3);
        this.tarefaRepository.save(tarefa);


    }
}
