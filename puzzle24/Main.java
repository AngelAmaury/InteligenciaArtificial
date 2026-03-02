package puzzle24;

import java.util.*;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		byte[] fichasIniciales;

		System.out.println("=================================");
		System.out.println("        24 - PUZZLE (5x5)");
		System.out.println("=================================");
		System.out.println("1. Ingresar tablero manual");
		System.out.println("2. Generar tablero aleatorio resoluble");
		System.out.print("Seleccione opción: ");

		int opcion = sc.nextInt();

		if (opcion == 1) {
			fichasIniciales = leerTableroManual(sc);
		} else {
			fichasIniciales = generarAleatorio();
			System.out.println("\nTablero generado:");
			imprimirTablero(fichasIniciales);
		}

		if (!esResoluble(fichasIniciales)) {
			System.out.println("El tablero no tiene solución.");
			return;
		}

		System.out.println("\n=================================");
		System.out.println("Comparando heurísticas...");
		System.out.println("=================================\n");

		// Manhattan
		IDA idasManhattan = new IDA(false);
		Resultado r1 = idasManhattan.resolver(fichasIniciales);

		System.out.println("\n---------------------------------\n");

		// Conflicto Lineal
		IDA idasConflicto = new IDA(true);
		Resultado r2 = idasConflicto.resolver(fichasIniciales);

		imprimirTablaComparativa(r1, r2);
	}
	// TABLA COMPARATIVA
	public static void imprimirTablaComparativa(Resultado r1, Resultado r2) {

		System.out.println("\n===============================================");
		System.out.printf("%-20s %-15s %-12s %-10s\n",
				"Heurística", "Nodos", "Tiempo(s)", "Pasos");
		System.out.println("------------------------------------------------");

		imprimirResultado(r1);
		imprimirResultado(r2);

		System.out.println("===============================================");
	}

	private static void imprimirResultado(Resultado r) {
		if (r == null) return;

		System.out.printf("%-20s %-15d %-12.3f %-10d\n",
				r.heuristica,
				r.nodosExpandidos,
				r.tiempoSegundos,
				r.pasos);
	}

	// Leer tablero manual
	public static byte[] leerTableroManual(Scanner sc) {
		byte[] tablero = new byte[25];

		System.out.println("\nIngrese los 25 valores (0–24) separados por espacio:");

		for (int i = 0; i < 25; i++) {
			tablero[i] = sc.nextByte();
		}

		return tablero;
	}

	// Generar tablero aleatorio resoluble
	public static byte[] generarAleatorio() {

		List<Byte> lista = new ArrayList<>();
		for (byte i = 0; i < 25; i++)
			lista.add(i);

		byte[] tablero = new byte[25];
		Random rand = new Random();

		do {
			Collections.shuffle(lista, rand);
			for (int i = 0; i < 25; i++)
				tablero[i] = lista.get(i);
		} while (!esResoluble(tablero));

		return tablero;
	}

	// Verificar resolubilidad
	public static boolean esResoluble(byte[] fichas) {

		int inversiones = 0;

		for (int i = 0; i < fichas.length; i++) {
			if (fichas[i] == 0) continue;

			for (int j = i + 1; j < fichas.length; j++) {
				if (fichas[j] != 0 && fichas[i] > fichas[j])
					inversiones++;
			}
		}

		return inversiones % 2 == 0;
	}

	// Imprimir tablero
	public static void imprimirTablero(byte[] tablero) {
		for (int i = 0; i < 25; i++) {
			System.out.printf("%3d", tablero[i]);
			if ((i + 1) % 5 == 0)
				System.out.println();
		}
		System.out.println();
	}
}