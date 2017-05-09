package aima.core.environment.akari1617;

import aima.core.search.framework.evalfunc.HeuristicFunction;

/**
 * The Heuristic works depending on the ratio light/board size
 */
public class HeuristicMaxIlumination implements HeuristicFunction {

	@Override
	public double h(Object state) {
		Akari akari = (Akari) state;
		int maxLight = akari.getNrows()+akari.getNcols() -1;

		return  akari.getTotalEmpty()/maxLight;
	}

}
