package pt.ufp.info.esof.sgp.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ClienteTest {

    @Test
    void adicionaProjetoTest() {
        Cliente cliente = new Cliente();
        cliente.setEmail("plsalomeida18@gmail.com");
        cliente.setNome("Pedro");

        Projeto projeto = new Projeto();


        // no caso normal
        cliente.adicionaProjeto(projeto);
        assertEquals(cliente.getProjetos().size(),1);

        // nao resulta pois ja contem o projeto
        cliente.adicionaProjeto(projeto);
        assertEquals(cliente.getProjetos().size(),1);
    }

}