package aima.core.enviroment.LightsOut;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;
import aima.core.search.framework.SearchAgent;
import aima.core.search.framework.SearchForActions;
import aima.core.search.framework.problem.ActionsFunction;
import aima.core.search.framework.problem.Problem;
import aima.core.search.framework.problem.ResultFunction;
import aima.core.search.framework.problem.GoalTest;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthFirstSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;

public class LightsOutDemoEjemplo3x3 {
	public final static int X = 1;// para indicar que esa luz está apagada
	public final static int O = 0;// para indicar que esa luz está encendida

	// Nivel simple de 3x3 con solución optima en 3 pasos
	private static int[][] LIGHTSOUT = { { X, X, X }, { O, X, O }, { X, X, O } };

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		newLightsOutDemo();

	}

	private static void newLightsOutDemo() {
		System.out.println("\nSignificado de los símbolos de representación:");
		System.out.println("O=Encendida");
		System.out.println("X=Apagada");

		LightsOut lightsout;
		lightsout = new LightsOut(LIGHTSOUT);

		lightsout.printCurrentState();
		System.out.println("\n<------------------------>");
		System.out.println("Profundidad (DFS (Graph))");
		System.out.println("<------------------------>");
		uninformedAndInformedSearchDemo(lightsout, LightsOutFunctionFactory.getActionsFunction(),
				LightsOutFunctionFactory.getResultFunction(), new LightsOutGoalTest(),
				new DepthFirstSearch(new GraphSearch()));

		System.out.println("\n<------------------------>");
		System.out.println("Anchura (BFS (Graph))");
		System.out.println("<------------------------>");
		lightsout = new LightsOut(LIGHTSOUT);
		uninformedAndInformedSearchDemo(lightsout, LightsOutFunctionFactory.getActionsFunction(),
				LightsOutFunctionFactory.getResultFunction(), new LightsOutGoalTest(),
				new BreadthFirstSearch(new GraphSearch()));

		System.out.println("\n<------------------------>");
		System.out.println("Profundidad Limitada a Solución Optima");
		System.out.println("<------------------------>");
		lightsout = new LightsOut(LIGHTSOUT);
		uninformedAndInformedSearchDemo(lightsout, LightsOutFunctionFactory.getActionsFunction(),
				LightsOutFunctionFactory.getResultFunction(), new LightsOutGoalTest(), new DepthLimitedSearch(3));

		System.out.println("\n<------------------------>");
		System.out.println("Profundidad Limitada a Solución Optima + 1 nivel");
		System.out.println("<------------------------>");
		lightsout = new LightsOut(LIGHTSOUT);
		uninformedAndInformedSearchDemo(lightsout, LightsOutFunctionFactory.getActionsFunction(),
				LightsOutFunctionFactory.getResultFunction(), new LightsOutGoalTest(), new DepthLimitedSearch(4));

		System.out.println("\n<------------------------>");
		System.out.println("Iterativa Profundidad");
		System.out.println("<------------------------>");
		lightsout = new LightsOut(LIGHTSOUT);
		uninformedAndInformedSearchDemo(lightsout, LightsOutFunctionFactory.getActionsFunction(),
				LightsOutFunctionFactory.getResultFunction(), new LightsOutGoalTest(), new IterativeDeepeningSearch());

		System.out.println("\n<------------------------>");
		System.out.println("Heurística1 con Avaricioso (Greedy Best First (Graph)");
		System.out.println("<------------------------>");
		lightsout = new LightsOut(LIGHTSOUT, LIGHTSOUT.length, LIGHTSOUT[0].length);
		uninformedAndInformedSearchDemo(lightsout, LightsOutFunctionFactory.getActionsFunction(),
				LightsOutFunctionFactory.getResultFunction(), new LightsOutGoalTest(),
				new GreedyBestFirstSearch(new GraphSearch(), new Heuristica1()));

		System.out.println("\n<------------------------>");
		System.out.println("Heurística1 con A* (Graph)");
		System.out.println("<------------------------>");
		lightsout = new LightsOut(LIGHTSOUT, LIGHTSOUT.length, LIGHTSOUT[0].length);
		uninformedAndInformedSearchDemo(lightsout, LightsOutFunctionFactory.getActionsFunction(),
				LightsOutFunctionFactory.getResultFunction(), new LightsOutGoalTest(),
				new AStarSearch(new GraphSearch(), new Heuristica1()));

		System.out.println("\n<------------------------>");
		System.out.println("Heurística2 con Avaricioso (Greedy Best First (Graph)");
		System.out.println("<------------------------>");
		lightsout = new LightsOut(LIGHTSOUT, LIGHTSOUT.length, LIGHTSOUT[0].length);
		uninformedAndInformedSearchDemo(lightsout, LightsOutFunctionFactory.getActionsFunction(),
				LightsOutFunctionFactory.getResultFunction(), new LightsOutGoalTest(),
				new GreedyBestFirstSearch(new GraphSearch(), new Heuristica2()));

		System.out.println("\n<------------------------>");
		System.out.println("Heurística2 con A* (Graph)");
		System.out.println("<------------------------>");
		lightsout = new LightsOut(LIGHTSOUT, LIGHTSOUT.length, LIGHTSOUT[0].length);
		uninformedAndInformedSearchDemo(lightsout, LightsOutFunctionFactory.getActionsFunction(),
				LightsOutFunctionFactory.getResultFunction(), new LightsOutGoalTest(),
				new AStarSearch(new GraphSearch(), new Heuristica2()));

		System.out.println("\n<------------------------>");
		System.out.println("Heurística3 con Avaricioso (Greedy Best First (Graph)");
		System.out.println("<------------------------>");
		lightsout = new LightsOut(LIGHTSOUT, LIGHTSOUT.length, LIGHTSOUT[0].length);
		uninformedAndInformedSearchDemo(lightsout, LightsOutFunctionFactory.getActionsFunction(),
				LightsOutFunctionFactory.getResultFunction(), new LightsOutGoalTest(),
				new GreedyBestFirstSearch(new GraphSearch(), new Heuristica3()));

		System.out.println("\n<------------------------>");
		System.out.println("Heurística3 con A* (Graph)");
		System.out.println("<------------------------>");
		lightsout = new LightsOut(LIGHTSOUT, LIGHTSOUT.length, LIGHTSOUT[0].length);
		uninformedAndInformedSearchDemo(lightsout, LightsOutFunctionFactory.getActionsFunction(),
				LightsOutFunctionFactory.getResultFunction(), new LightsOutGoalTest(),
				new AStarSearch(new GraphSearch(), new Heuristica3()));

	}

	/******************* search without and with heuristic ********************/

	/**
	 * 
	 */

	private static void uninformedAndInformedSearchDemo(Object initialState, ActionsFunction actionsFunction,
			ResultFunction resultFunction, GoalTest goalTest, SearchForActions searchType) {
		try {
			Problem problem = new Problem(initialState, actionsFunction, resultFunction, goalTest);

			SearchForActions search = searchType;
			long timeI = System.currentTimeMillis();
			SearchAgent agent = new SearchAgent(problem, search);
			long timeF = System.currentTimeMillis();
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
			printTimeExecution(timeI, timeF);
			printGraphicActions(agent.getActions(), initialState);// -> Método
																	// opcional
																	// para ver
																	// el
																	// resultado
																	// al
																	// detalle
																	// de forma
																	// "gráfica"

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/******************* otros metodos ********************/
	private static void printInstrumentation(Properties properties) {
		double pathcost = 0;
		double nodesexpanded = 0;
		double branching = 0;
		Iterator<Object> keys = properties.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String property = properties.getProperty(key);
			if (key == "pathCost")
				pathcost = Double.parseDouble(property);
			if (key == "nodesExpanded")
				nodesexpanded = Double.parseDouble(property);

			System.out.println(key + " : " + property);
		}

		if (pathcost > 0 && nodesexpanded > 0) {
			branching = Math.pow(nodesexpanded, 1.0 / pathcost);
			System.out.println("branchingfactor: " + branching);
		}

	}

	private static void printActions(List<Action> actions) {
		for (int i = 0; i < actions.size(); i++) {
			String action = actions.get(i).toString();
			System.out.println(action);
		}
	}

	private static void printTimeExecution(long timeI, long timeF) {
		long milisegundos = timeF - timeI;
		long hora = milisegundos / 3600000;
		long restohora = milisegundos % 3600000;
		long minuto = restohora / 60000;
		long restominuto = restohora % 60000;
		long segundo = restominuto / 1000;
		long restosegundo = restominuto % 1000;
		System.out.println("\nTimeExecution---> " + hora + ":" + minuto + ":" + segundo + "." + restosegundo);
	}

	// Método opcional para ver el resultado al detalle de forma "gráfica"
	private static void printGraphicActions(List<Action> actions, Object initialState) {
		System.out.println("\n Estado inicial:   ");
		LightsOut lightsOut = (LightsOut) initialState;
		lightsOut.printCurrentState();
		for (int i = 0; i < actions.size(); i++) {
			String action = actions.get(i).toString();
			System.out.println("\nAcción número " + (i + 1));
			System.out.println(action);
			LightsOutAction lightsOutAction = (LightsOutAction) actions.get(i);
			lightsOut.changeLine(lightsOutAction.getF(), lightsOutAction.getC());
			lightsOut.printCurrentState();
		}
		System.out.println();
	}

}
