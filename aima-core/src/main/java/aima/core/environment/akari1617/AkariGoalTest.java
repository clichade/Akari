package aima.core.environment.akari1617;

import aima.core.search.framework.problem.GoalTest;

public class AkariGoalTest implements GoalTest {

	/*
	 * Método que devuelve cuando hemos conseguido alcanzar un estado objetivo
	 * del problema
	 */
	public boolean isGoalState(Object state) {
		Akari Akari = (Akari) state;
		return Akari.checkFinish();
	}
}
