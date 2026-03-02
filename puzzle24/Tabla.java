package puzzle24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tabla {
	public static final int TAMANO = 5;
	private final byte[] fichas;
	private final int posicionCero;
	private final int h; 

	public Tabla(byte[] fichas, boolean usarConflictoLineal) {
		this.fichas = fichas;
		int p0 = -1;
		for (int i = 0; i < fichas.length; i++) {
			if (fichas[i] == 0) { p0 = i; break; }
		}
		this.posicionCero = p0;
		this.h = usarConflictoLineal ? 
				Heuristica.conflictoLineal(this.fichas) : 
					Heuristica.distanciaManhattan(this.fichas);
	}

	public List<Tabla> obtenerVecinos(boolean usarConflictoLineal) {
		List<Tabla> vecinos = new ArrayList<>();
		int fila = posicionCero / TAMANO;
		int columna = posicionCero % TAMANO;
		int[] movimientos = {-TAMANO, TAMANO, -1, 1};

		for (int i = 0; i < movimientos.length; i++) {
			if (i == 0 && fila == 0) continue;
			if (i == 1 && fila == TAMANO - 1) continue;
			if (i == 2 && columna == 0) continue;
			if (i == 3 && columna == TAMANO - 1) continue;

			byte[] nuevasFichas = fichas.clone();
			nuevasFichas[posicionCero] = nuevasFichas[posicionCero + movimientos[i]];
			nuevasFichas[posicionCero + movimientos[i]] = 0;
			vecinos.add(new Tabla(nuevasFichas, usarConflictoLineal));
		}
		return vecinos;
	}

	public int obtenerH() { return h; }
	public byte[] obtenerFichas() { return fichas; }
	public boolean esObjetivo() { return Arrays.equals(this.fichas, Heuristica.ESTADO_OBJETIVO); }

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Tabla)) return false;
		return Arrays.equals(this.fichas, ((Tabla) o).fichas);
	}

	@Override
	public int hashCode() { 
		return Arrays.hashCode(fichas); 
	}

	public void imprimirTablero(int paso, int g) {
		System.out.println("PASO: " + paso + " Costo g(n): " + g + " Heurística h(n): " + h + " Total f(n): " + (g + h));
		for (int i = 0; i < TAMANO; i++) {
			for (int j = 0; j < TAMANO; j++) {
				int ficha = fichas[i * TAMANO + j];
				System.out.printf("%2d ", ficha);
			}
			System.out.println();
		}
		System.out.println("-------------------------------------------------------------");
	}
}