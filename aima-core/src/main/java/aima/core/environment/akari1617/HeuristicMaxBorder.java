package aima.core.environment.akari1617;

import aima.core.search.framework.evalfunc.HeuristicFunction;

public class HeuristicMaxBorder implements HeuristicFunction {

	@Override
	public double h(Object state) {
		Akari akari = (Akari) state;
		int max = Math.max(akari.getNcols(),akari.getNrows());

		return akari.getTotalEmpty()/max;
	}

}
