package aima.core.environment.ochopuzzle;

import aima.core.search.framework.problem.GoalTest;

/**
@author aeprieto
*/

 
public class PuzzleGoalTest implements GoalTest {
	
	@Override

	/* Método que devuelve cuando hemos conseguido alcanzar un estado objetivo del problema*/
	public boolean isGoalState(Object state) {
		Puzzle puzzle = (Puzzle) state;
		return puzzle.contarFichasNOEnObjetivo()==0;
	} 
	
}
