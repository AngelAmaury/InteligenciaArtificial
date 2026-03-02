package puzzle24;
import java.util.*;

public class IDA {
	private final boolean usarConflictoLineal;
	private long nodosExpandidos = 0;

	public IDA(boolean usarConflictoLineal) {
		this.usarConflictoLineal = usarConflictoLineal;
	}

	public Resultado resolver(byte[] fichasIniciales) {

		nodosExpandidos = 0;

		Tabla raiz = new Tabla(fichasIniciales, usarConflictoLineal);
		int limite = raiz.obtenerH();
		List<Tabla> camino = new ArrayList<>();
		camino.add(raiz);

		long tiempoInicio = System.currentTimeMillis();

		while (true) {

			int resultado = buscar(camino, 0, limite);

			if (resultado == -1) {

				long tiempoFin = System.currentTimeMillis();
				double tiempoTotal = (tiempoFin - tiempoInicio) / 1000.0;

				imprimirCaminoSolucion(camino);

				int pasos = camino.size() - 1;
				String nombre = usarConflictoLineal ? 
						"Conflicto Lineal" : 
							"Manhattan";

				return new Resultado(
						nombre,
						nodosExpandidos,
						tiempoTotal,
						pasos
						);
			}

			if (resultado == Integer.MAX_VALUE) {
				System.out.println("No hay solución.");
				return null;
			}

			limite = resultado;
		}
	}

	private int buscar(List<Tabla> camino, int g, int limite) {

		Tabla actual = camino.get(camino.size() - 1);
		int f = g + actual.obtenerH();

		if (f > limite) return f;
		if (actual.esObjetivo()) return -1;

		nodosExpandidos++;
		int min = Integer.MAX_VALUE;

		for (Tabla vecino : actual.obtenerVecinos(usarConflictoLineal)) {

			if (!camino.contains(vecino)) {

				camino.add(vecino);

				int t = buscar(camino, g + 1, limite);

				if (t == -1) return -1;
				if (t < min) min = t;

				camino.remove(camino.size() - 1);
			}
		}

		return min;
	}

	private void imprimirCaminoSolucion(List<Tabla> camino) {

		System.out.println("\nRECORRIDO DE LA SOLUCIÓN (" +
				(usarConflictoLineal ? "Conflicto Lineal" : "Manhattan") +
				")");

		for (int i = 0; i < camino.size(); i++) {
			camino.get(i).imprimirTablero(i, i);
		}
	}
}