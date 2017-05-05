package aima.core.enviroment.LightsOut;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.search.framework.problem.ActionsFunction;
import aima.core.search.framework.problem.ResultFunction;
import aima.core.util.datastructure.Pair;

public class LightsOutFunctionFactory {

	private static ActionsFunction actionsFunction = null;
	private static ResultFunction resultFunction = null;

	public static ActionsFunction getActionsFunction() {
		if (null == actionsFunction)
			actionsFunction = new LightsOutActionsFunction();
		return actionsFunction;
	}

	public static ResultFunction getResultFunction() {
		if (null == resultFunction)
			resultFunction = new PuzzleResultFunction();
		return resultFunction;
	}

	private static class LightsOutActionsFunction implements ActionsFunction {

		@Override
		public Set<Action> actions(Object o) {
			Set<Action> actions = new LinkedHashSet<Action>();

			LightsOut L = (LightsOut) o;
			List<Pair<Integer, Integer>> moves = L.getMovesXY();

			for (Pair<Integer, Integer> move : moves) // for each
				actions.add(new LightsOutAction(LightsOutAction.Press_LightsOut, move));

			return actions;
		}
	}

	private static class PuzzleResultFunction implements ResultFunction {

		@Override
		public Object result(Object s, Action a) {
			if (a instanceof LightsOutAction) {
				LightsOutAction sAction = (LightsOutAction) a;
				LightsOut state = (LightsOut) s;

				LightsOut newState = new LightsOut(state.getLightsOutState());

				if (sAction.getName() == LightsOutAction.Press_LightsOut) {
					newState.changeLine(sAction.getF(), sAction.getC());
				}
				s = newState;
			}

			return s;
		}

	}

}