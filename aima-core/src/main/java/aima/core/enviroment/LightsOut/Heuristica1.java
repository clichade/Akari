package aima.core.enviroment.LightsOut;

import aima.core.search.framework.evalfunc.HeuristicFunction;

public class Heuristica1 implements HeuristicFunction {

	public double h(Object state) {
		LightsOut L = (LightsOut) state;
		return L.lightsOn();
	}


}