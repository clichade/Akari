package aima.core.enviroment.LightsOut;

import aima.core.search.framework.problem.GoalTest;

public class LightsOutGoalTest5x5 implements GoalTest {

	@Override

	/*
	 * M�todo que devuelve cuando hemos conseguido alcanzar un estado objetivo
	 * del problema
	 */
	public boolean isGoalState(Object state) {
		LightsOut L = (LightsOut) state;
		@SuppressWarnings("unused")
		LightsOut p = new LightsOut(L.getLightsOutState());
		int[][] m = { { 1, 1, 1, 0, 1 }, { 0, 0, 1, 0, 1 }, { 1, 1, 1, 1, 1 }, { 1, 0, 1, 0, 0 }, { 1, 0, 1, 1, 1 } };
		return L.getLightsOutState() == m;
	}

}