package aima.core.environment.akari1617;

import aima.core.search.framework.evalfunc.HeuristicFunction;

public class Heuristica1 implements HeuristicFunction {

	@Override
	public double h(Object state) {
		Akari A = (Akari) state;
		return A.numberOfLanterns();
	}

}
