package puzzle24;

public class Resultado {

    public String heuristica;
    public long nodosExpandidos;
    public double tiempoSegundos;
    public int pasos;

    public Resultado(String heuristica, long nodosExpandidos, double tiempoSegundos, int pasos) {
        this.heuristica = heuristica;
        this.nodosExpandidos = nodosExpandidos;
        this.tiempoSegundos = tiempoSegundos;
        this.pasos = pasos;
    }
}
