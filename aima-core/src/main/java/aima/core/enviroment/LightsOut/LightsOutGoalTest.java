package aima.core.enviroment.LightsOut;

import aima.core.search.framework.problem.GoalTest;

public class LightsOutGoalTest implements GoalTest {

	@Override

	/*
	 * M�todo que devuelve cuando hemos conseguido alcanzar un estado objetivo
	 * del problema
	 */
	public boolean isGoalState(Object state) {

		LightsOut luces = (LightsOut) state;

		return luces.lightsOn() == 0;
	}

}