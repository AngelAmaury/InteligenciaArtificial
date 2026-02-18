package Busquedas;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class ArbolBusqueda {
	private Nodo raiz;
	public ArbolBusqueda(Nodo raiz) {
		this.raiz = raiz;
	}
	
	public Nodo busquedaPrimeroAnchura(String estadoObjetivo){
		if(raiz == null) return null;
		Nodo actual = null;
		HashSet<String> visitados = new HashSet<String>();
		Queue<Nodo> cola = new LinkedList<Nodo>();
		//Stack<Nodo> stack = new Stack<Nodo>();
		cola.add(raiz);
		while(!cola.isEmpty()){
			actual = cola.poll();
			if(actual.estado.equals(estadoObjetivo)) {
				return actual;
			}else {
				visitados.add(actual.estado);
				List<Nodo> sucesores = actual.generarSucesores();
				for(Nodo sucesor: sucesores) {
					if(!visitados.contains(sucesor.estado)) {
						cola.add(sucesor);
						visitados.add(sucesor.estado);
						//busquedaPrimeroAnchura(sucesor.estado);
					}
				}
			}
		}
		return null;
	}
}
