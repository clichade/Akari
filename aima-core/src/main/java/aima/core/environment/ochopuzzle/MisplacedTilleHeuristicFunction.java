package aima.core.environment.ochopuzzle;

import aima.core.search.framework.evalfunc.HeuristicFunction;

/**
 * @author Ravi Mohan
 * 
 */
public class MisplacedTilleHeuristicFunction implements HeuristicFunction {

	public double h(Object state) {
		Puzzle puzzle = (Puzzle) state;
		return puzzle.contarFichasNOEnObjetivo();
	}

	
}