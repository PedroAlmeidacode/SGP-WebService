package pt.ufp.info.esof.sgp.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pt.ufp.info.esof.sgp.models.TarefaAtual;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@DataJpaTest
class TarefaAtualRepositoryTest {

    @Autowired
    private TarefaAtualRepository tarefaAtualRepository;

    @Test
    public void criacaoTarefaAtual()
    {
        TarefaAtual tarefaAtual = new TarefaAtual();
        tarefaAtual.setPercentualConclusao(50);
        tarefaAtual.setUltimaAtualizacaoParaAgora();
        tarefaAtual.setTempoDedicado(60);

        //antes de salvar tem de dar null
        assertNull(tarefaAtual.getId());
        tarefaAtualRepository.save(tarefaAtual);
        assertEquals(1,tarefaAtualRepository.count());
    }
}