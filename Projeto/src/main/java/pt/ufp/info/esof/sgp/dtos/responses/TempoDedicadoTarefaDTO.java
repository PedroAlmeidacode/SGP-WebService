package pt.ufp.info.esof.sgp.dtos.responses;

import lombok.Data;

@Data
public class TempoDedicadoTarefaDTO {
    private int tempoDedicado;

    public int converter() {
        return tempoDedicado;
    }
}
