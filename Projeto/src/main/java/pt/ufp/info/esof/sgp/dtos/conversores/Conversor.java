package pt.ufp.info.esof.sgp.dtos.conversores;

public interface Conversor<O,I> {
    O converter(I i);
}
