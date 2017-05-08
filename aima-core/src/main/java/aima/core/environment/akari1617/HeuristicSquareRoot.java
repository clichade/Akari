package aima.core.environment.akari1617;

import aima.core.search.framework.evalfunc.HeuristicFunction;

/**
 * this heuristic give points for each lantern and if a condition block is completely satisfied,
 * also y if the condition block has only 1 possible configuration the ehuristic gives even more points
 */
public class HeuristicSquareRoot implements HeuristicFunction {

	@Override
	public double h(Object state) {
		Akari akari = (Akari) state;
		return akari.HeuristicSquareRoot();
	}

}
