package pt.ufp.info.esof.sgp.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.ufp.info.esof.sgp.models.Empregado;
import pt.ufp.info.esof.sgp.repositories.EmpregadoRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = EmpregadoServiceImpl.class)
class EmpregadoServiceImplTest {
    @Autowired
    private EmpregadoService empregadoService;

    @MockBean
    private EmpregadoRepository empregadoRepository;

    @Test
    void createEmpregado(){

        String mail = "plsalmeida@gmail.com";
        Empregado empregado = new Empregado();
        empregado.setEmail(mail);

        // teste normal
        when(empregadoRepository.save(empregado)).thenReturn(empregado);
        assertTrue(empregadoService.createEmpregado(empregado).isPresent());


        // caso em que o empregado ja existe na bd
        when(empregadoRepository.findByEmail(mail)).thenReturn(Optional.of(empregado));
        assertTrue(empregadoService.createEmpregado(empregado).isEmpty());

    }

}