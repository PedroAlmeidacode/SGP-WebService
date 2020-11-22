package pt.ufp.info.esof.sgp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pt.ufp.info.esof.sgp.models.Empregado;
import pt.ufp.info.esof.sgp.models.Tarefa;

@SpringBootApplication
public class SgpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SgpApplication.class, args);
    }

}
