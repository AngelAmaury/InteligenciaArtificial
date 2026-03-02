package puzzle24;

public class Heuristica {
	public static final int TAMANO = 5;
	public static final byte[] ESTADO_OBJETIVO = new byte[25];
	private static final int[] FILA_OBJETIVO = new int[25];
	private static final int[] COL_OBJETIVO = new int[25];

	static {
		for (int i = 0; i < 24; i++) {
			ESTADO_OBJETIVO[i] = (byte)(i + 1);
			FILA_OBJETIVO[i+1] = i / TAMANO;
			COL_OBJETIVO[i+1] = i % TAMANO;
		}
		ESTADO_OBJETIVO[24] = 0;
	}

	public static int distanciaManhattan(byte[] fichas) {
		int distancia = 0;
		for (int i = 0; i < fichas.length; i++) {
			if (fichas[i] != 0) {
				distancia += Math.abs(i / TAMANO - FILA_OBJETIVO[fichas[i]]) + 
						Math.abs(i % TAMANO - COL_OBJETIVO[fichas[i]]);
			}
		}
		return distancia;
	}

	public static int conflictoLineal(byte[] fichas) {
		int distancia = distanciaManhattan(fichas);
		int conflictos = 0;

		// Conflictos en filas
		for (int f = 0; f < TAMANO; f++) {
			for (int c1 = 0; c1 < TAMANO; c1++) {
				for (int c2 = c1 + 1; c2 < TAMANO; c2++) {
					byte t1 = fichas[f * TAMANO + c1];
					byte t2 = fichas[f * TAMANO + c2];
					if (t1 != 0 && t2 != 0 && FILA_OBJETIVO[t1] == f && FILA_OBJETIVO[t2] == f) {
						if (COL_OBJETIVO[t1] > COL_OBJETIVO[t2]) conflictos += 2;
					}
				}
			}
		}
		// Conflictos en columnas
		for (int c = 0; c < TAMANO; c++) {
			for (int f1 = 0; f1 < TAMANO; f1++) {
				for (int f2 = f1 + 1; f2 < TAMANO; f2++) {
					byte t1 = fichas[f1 * TAMANO + c];
					byte t2 = fichas[f2 * TAMANO + c];
					if (t1 != 0 && t2 != 0 && COL_OBJETIVO[t1] == c && COL_OBJETIVO[t2] == c) {
						if (FILA_OBJETIVO[t1] > FILA_OBJETIVO[t2]) conflictos += 2;
					}
				}
			}
		}
		return distancia + conflictos;
	}
}
