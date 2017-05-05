package aima.core.enviroment.LightsOut;

import aima.core.search.framework.evalfunc.HeuristicFunction;

public class Heuristica3 implements HeuristicFunction {

	public double h(Object state) {
		LightsOut L = (LightsOut) state;
		double x = 0;

		for (int i = 0; i < L.getNF(); i++) {
			for (int j = 0; j < L.getNC(); j++) {

				if (L.getOn(i, j))
					x += (L.lightsOn(i, j) / L.typeCell(i, j));
			}
		}
		return x;

	}

}