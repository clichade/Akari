package aima.core.environment.akari1617;

import aima.core.search.framework.evalfunc.HeuristicFunction;

public class HeuristicaForbiddenPath implements HeuristicFunction {

	@Override
	public double h(Object state) {
		Akari akari = (Akari) state;
		return akari.ForbiddenPathHeuristic();
	}

}
