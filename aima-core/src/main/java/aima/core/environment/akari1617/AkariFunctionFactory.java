package aima.core.environment.akari1617;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.search.framework.problem.ActionsFunction;
import aima.core.search.framework.problem.ResultFunction;
import aima.core.util.datastructure.Pair;

public class 	AkariFunctionFactory {

	private static ActionsFunction actionsFunction = null;
	private static ResultFunction resultFunction = null;

	public static ActionsFunction getActionsFunction() {
		if (null == actionsFunction)
			actionsFunction = new AkariActionsFunction();
		return actionsFunction;
	}

	public static ResultFunction getResultFunction() {
		if (null == resultFunction)
			resultFunction = new AkariResultFunction();
		return resultFunction;
	}

	// M�todo que devolver� las acciones disponibles en un estado concreto del
	// problema
	private static class AkariActionsFunction implements ActionsFunction {
		@Override
		public Set<Action> actions(Object s) {
			System.gc();
			Set<Action> actions = new LinkedHashSet<Action>();

			Akari Akari = (Akari) s;
			// obtener movimientos
			List<Pair<Integer, Integer>> movimientos = Akari.getMovements();
			// para cada movimiento valido crear accion y anadirla a conjunto
			for (Pair<Integer, Integer> movimiento : movimientos)
				actions.add(new AkariAction(AkariAction.ACCION_AKARI, movimiento));

			return actions;
		}
	}

	// m�todo que devuelve el estado al que pasa un problema tras aplicar una
	// acci�n concreta a sobre un estado s
	private static class AkariResultFunction implements ResultFunction {
		@Override
		public Object result(Object s, Action a) {
			if (a instanceof AkariAction) {
				AkariAction sAction = (AkariAction) a;
				Akari state = (Akari) s;
				Akari newState = new Akari(state.getAkariState());
				if (sAction.getName() == AkariAction.ACCION_AKARI)
					newState.turnOnLight(sAction.getFila(), sAction.getColumna());

				s = newState;

			}

			return s;
		}
	}

}
