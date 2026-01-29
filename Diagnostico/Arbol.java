package diagnostico;

public class Arbol {
	private Nodo raiz;
	
	public Arbol() {
		this.raiz = null;
	}
	
	public boolean vacio() {
		return raiz == null;
	}
	
	public void insertar(String nombre) {
		raiz = insertarNodoNombre(raiz, nombre);
	}
	private Nodo insertarNodoNombre(Nodo NodoActual, String nombre) {
		if(NodoActual == null) {
			return new Nodo(nombre);
		}
		if(nombre.compareTo(NodoActual.nombre) < 0) {
			NodoActual.izquierdo = insertarNodoNombre(NodoActual.izquierdo, nombre);
		}
		else if(nombre.compareTo(NodoActual.nombre) > 0) {
			NodoActual.derecho = insertarNodoNombre(NodoActual.derecho, nombre);
		}
		return NodoActual;
	}
	public Nodo buscarNodo(String nombre) {
		return busquedaPreOrden(raiz, nombre);
	}
	
	private Nodo busquedaPreOrden(Nodo NodoActual, String nombre) {
		if(NodoActual==null) {
			return null;
		}
		if(NodoActual.nombre.equalsIgnoreCase(nombre)) {
			return NodoActual;
		}
		Nodo nodoEncontrado = busquedaPreOrden(NodoActual.izquierdo, nombre);
		if(nodoEncontrado != null) {
			return	nodoEncontrado;
		}
		return busquedaPreOrden(NodoActual.derecho, nombre);
	}
	public void imprimirArbol() {
		imprimirEnPreorden(raiz, "RAIZ");
	}
	private void imprimirEnPreorden(Nodo NodoActual, String posicionArbol) {
		if(NodoActual != null) {
			System.out.println(posicionArbol + " "+ NodoActual.nombre);
			imprimirEnPreorden(NodoActual.izquierdo, "A la IZQUIERDA de "+ NodoActual.nombre);
			imprimirEnPreorden(NodoActual.derecho,  "A la DERECHA de "+ NodoActual.nombre);
		}
	}
}
