package Busquedas;

import java.util.ArrayList;
import java.util.List;

public class Nodo {
	String estado;
	private Nodo padre;
	private int nivel, costo;
	public Nodo(String estado) {
		this.estado = estado;
	}
	public Nodo(String estado, Nodo padre) {
		this.estado = estado;
		this.padre = padre;
	}
	public Nodo(String estado, Nodo padre, int nivel) {
		this.estado = estado;
		this.padre = padre;
		this.nivel = nivel;
	}
	public String getEstado() {
		return estado;
	}
	public Nodo getPadre() {
		return padre;
	}
	public int getNivel() {
		return nivel;
	}
	public int getCosto() {
		return costo;
	}
	
	public List<Nodo> generarSucesores() {
		String[] estadoHijos = Puzzle8.generarSucesores(estado);
		List<Nodo> sucesores = new ArrayList<Nodo>();
		for(String estadoHijo: estadoHijos) {
			if(estadoHijo != null) {
				Nodo nodoHijo = new Nodo(estadoHijo, this, this.nivel+1);
				sucesores.add(nodoHijo);
			}
		}
		return sucesores;
	}
	public void impirmir() {
		if(this.padre != null) {
			this.padre.impirmir();
		}
		for(int i=0; i<9; i++) {
			System.out.print(this.estado.charAt(i));
			if((i+1) % 3 == 0 ) {
				System.out.println();
			}
		}
		System.out.println("Nivel: "+this.nivel);
		System.out.println("*********************");
	}
}
