package pt.ufp.info.esof.sgp.dtos.responses;

import lombok.Data;


@Data
public class PercentualTarefaDTO {
    private Float percentual;

    public float converter() {
        return percentual;
    }
}

