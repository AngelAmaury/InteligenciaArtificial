package diagnostico;

public class ArbolBinario {

	public static void main(String[] args) {
		Arbol arbol = new Arbol();
		arbol.insertar("Juan");
		arbol.insertar("Pedro");
		arbol.insertar("Kevin");
		arbol.insertar("Amaury");
		
		System.out.println("Impresión del árbol en preorden: ");
		arbol.imprimirArbol();
	}

}
