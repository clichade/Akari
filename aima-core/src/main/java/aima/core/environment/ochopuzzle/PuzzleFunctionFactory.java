package aima.core.environment.ochopuzzle;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.search.framework.problem.ActionsFunction;
import aima.core.search.framework.problem.ResultFunction;
import aima.core.util.datastructure.Pair;

/**
@author aeprieto
*/


public class PuzzleFunctionFactory {
	

	private static ActionsFunction actionsFunction = null;
	private static ResultFunction resultFunction = null;

	public static ActionsFunction getActionsFunction() {
		if(null == actionsFunction)
			actionsFunction = new PuzzleActionsFunction();
		return actionsFunction;
	}

	public static ResultFunction getResultFunction() {
		if(null == resultFunction)
			resultFunction = new PuzzleResultFunction();
		return resultFunction;
	}
	
	//Método que devolverá las acciones disponibles en un estado concreto del problema
	private static class PuzzleActionsFunction implements ActionsFunction{

		@Override
		public Set<Action> actions(Object s) {
			Set<Action> actions = new LinkedHashSet<Action>();
			
			Puzzle puzzle = (Puzzle) s;
			//obtener movimientos
			List<Pair<Pair<Integer,Integer>, Pair<Integer,Integer>>> movimientos =
					puzzle.getMovimientos();
			//para cada movimiento valido crear accion y anadirla a conjunto
			for(Pair<Pair<Integer,Integer>, Pair<Integer,Integer>> movimiento: movimientos)
					actions.add(new PuzzleAction(PuzzleAction.MOVER_HUECO,movimiento));
			return actions;
		}
	}	
	private static class PuzzleResultFunction implements ResultFunction{
		//método que devuelve el estado al que pasa un problema tras aplicar una acción concreta a sobre un estado s
		@Override
		public Object result(Object s, Action a) {
			if (a instanceof PuzzleAction){
				PuzzleAction sAction = (PuzzleAction) a;
				Puzzle state = (Puzzle) s;
				Puzzle newState = new Puzzle(state.getState());
				if (sAction.getName() == PuzzleAction.MOVER_HUECO)
					newState.moverHuecoACasilla(sAction.getOrigen(),sAction.getDestino());
								s = newState;									
			}				
			return s;
		}
		
	}
		
}
	
