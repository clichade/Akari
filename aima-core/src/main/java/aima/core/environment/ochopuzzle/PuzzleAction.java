package aima.core.environment.ochopuzzle;

import aima.core.agent.impl.DynamicAction;
import aima.core.util.datastructure.Pair;

/**
@author aeprieto
*/

//clase que almacena el movimiento del juego en funci�n de la estructura Action de AIMA-JAVA
//ser� muy �til tambi�n para mostrar la soluci�n del problema

public class PuzzleAction extends DynamicAction {
	
	//Constantes para guardar nombre de la acci�n y par�metro
	//Action names
	public static final String MOVER_HUECO = "moverHuecoACasilla";

	//Action params
	public static final String ATRIBUTO_MOVIMIENTO = "movimiento(origen(x,y),destino(x,y))";
	
	
	//constructor siempre recibe el nombre de la acci�n que se puede realizar en el problema y los par�metros de dicha acci�n
	public PuzzleAction(String name, Pair<Pair<Integer,Integer>,Pair<Integer,Integer>> movimiento) {
		super(name);
		this.setAttribute(ATRIBUTO_MOVIMIENTO, movimiento);
	}
	
	@SuppressWarnings("unchecked")
	//m�todos auxiliares para devolver todos o parte de los par�metros de la acci�n
	public Pair<Pair<Integer,Integer>,Pair<Integer,Integer>> getMovimiento() {
	   return (Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>) getAttribute(ATRIBUTO_MOVIMIENTO);
	}
	
	public Pair<Integer,Integer> getOrigen(){
		return getMovimiento().getFirst();
	}
	
	public Pair<Integer,Integer> getDestino(){
		return getMovimiento().getSecond();
	}
	
}
