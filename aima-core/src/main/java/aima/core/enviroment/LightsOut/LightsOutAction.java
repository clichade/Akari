package aima.core.enviroment.LightsOut;

import aima.core.agent.impl.DynamicAction;
import aima.core.util.datastructure.Pair;

public class LightsOutAction extends DynamicAction {
	
	public static final String Press_LightsOut = "pressL";

	/**
	 * Constructor que recibe el nombre de la accion y los parametros de esta
	 * 
	 * @param name Nombre de la acción
	 * @param pressLight Parametros de esta
	 */
	  public LightsOutAction(String name, Pair<Integer, Integer> pressLight) {
	 

		super(name);
		this.setAttribute(Press_LightsOut, pressLight);
	}

	@SuppressWarnings("unchecked")
	public Pair<Integer, Integer> getMove() {
		return (Pair<Integer, Integer>) getAttribute(Press_LightsOut);
	}
/**
 * 
 * @return el nº de fila
 */
	public int getF() {
		return getMove().getFirst();
	}
	
	/**
	 * 
	 * @return el nº de columna
	 */
	public int getC() {
		return getMove().getSecond();
	}

}