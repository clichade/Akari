package aima.core.environment.akari1617;

import aima.core.agent.impl.DynamicAction;
import aima.core.util.datastructure.Pair;

//clase que almacena el movimiento del juego en funci�n de la estructura Action de AIMA-JAVA
//ser� muy �til tambi�n para mostrar la soluci�n del problema
public class AkariAction extends DynamicAction {

	public static final String ACCION_AKARI = "accionAkari";

	/**
	 * 
	 * @param name
	 *            -> Nombre de la accion
	 * @param paramAccion
	 *            -> Parametros de la accion
	 */

	public AkariAction(String name, Pair<Integer, Integer> paramAccion) {
		super(name);
		this.setAttribute(ACCION_AKARI, paramAccion);
	}

	@SuppressWarnings("unchecked")
	public Pair<Integer, Integer> getMovimiento() {
		return (Pair<Integer, Integer>) getAttribute(ACCION_AKARI);
	}

	/**
	 * @return n� de fila
	 */
	public int getFila() {
		return getMovimiento().getFirst();
	}

	/**
	 * @return n� de columna
	 */
	public int getColumna() {
		return getMovimiento().getSecond();
	}
}
