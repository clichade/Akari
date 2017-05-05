package aima.core.enviroment.LightsOut;

import aima.core.search.framework.evalfunc.HeuristicFunction;

public class Heuristica2 implements HeuristicFunction {

	@Override
	public double h(Object state) {
		// TODO Auto-generated method stub
		LightsOut N = (LightsOut) state;
		int equis = 0;
		for (int i = 0; i < N.getNF(); i++) {
			if (N.getFOn(i)) {
				if (i == 0 || i == N.getNF()) {
					equis += 2;
				} else if (i == 1 || i == N.getNF() - 1) {
					equis += 4;
				} else {
					equis += 10;
				}
			}
		}
		return equis;

	}
}
