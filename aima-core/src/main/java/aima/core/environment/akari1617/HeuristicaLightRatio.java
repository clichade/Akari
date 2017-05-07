package aima.core.environment.akari1617;

import aima.core.search.framework.evalfunc.HeuristicFunction;

/**
 * The Heuristic works depending on the ratio light/board size
 */
public class HeuristicaLightRatio implements HeuristicFunction {

	@Override
	public double h(Object state) {
		Akari akari = (Akari) state;
		int size = akari.getNcols()*akari.getNrows();

		return (akari.getTotalLight()/size)*100;
	}

}
