package Busquedas;

public class Puzzle {

	public static void main(String[] args) {
		Nodo nodo = new Nodo("1238 4765");
		ArbolBusqueda puzzle = new ArbolBusqueda(nodo);
		Nodo n = puzzle.busquedaPrimeroAnchura("1284376 5");
		n.impirmir();
	}

}
