package aima.core.environment.akari1617;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;
import aima.core.search.framework.SearchAgent;
import aima.core.search.framework.SearchForActions;
import aima.core.search.framework.problem.ActionsFunction;
import aima.core.search.framework.problem.Problem;
import aima.core.search.framework.problem.ResultFunction;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthFirstSearch;

public class AkariGenericDemo {
	public final static int B = 5;// B de bloque para indicar que es un bloque
									// donde no puede ir una fuente de luz ni
									// pasar luz
	public final static int V = 6;// V de vac�a para indicar que esa casilla no
									// tiene bombilla ni est� iluminada
	public final static int L = 7;// L para representar que esa casilla tiene
									// una fuente de luz
	public final static int I = 8;// I de iluminada para indicar que esa casilla
									// est� iluminada por una fuente de luz
	/*
	 * Adem�s: - 0 en una casilla indica que las adyacentes no pueden tener
	 * luces - 1 en una casilla indica que las adyacentes solo pueden tener una
	 * luz - 2 en una casilla indica que las adyacentes solo pueden tener 2
	 * luces - 3 en una casilla indica que las adyacentes solo pueden tener 3
	 * luces - 4 en una casilla indica que las adyacentes deben tener las 4
	 * luces adyacentes
	 *
	 * 
	 */
									//Nivel con soluci�n UNICA optima en 5 pasos
	private static int[][] AKARI = {
			{ B,1,V,V,B,B},
			{ B,V,V,V,V,0},
			{ V,V,V,V,V,V},
			{ V,V,V,V,V,V},
			{ V,V,1,2,V,V}
			};


									//Nivel con soluci�n optima en 5 pasos variables
	/*private static int[][] LIGHTCROSS = {
			{ B,1,V,V,B,B},
			{ B,V,V,V,V,0},
			{ V,V,V,B,V,V},
			{ V,V,V,V,V,V},
			{ V,V,B,1,V,V}
			};
	*/
								//Nivel con soluci�n UNICA optima en 6 pasos
						/*			private static int[][] AKARI = {
											{ 1,V,V,V,V,V},
											{ V,V,V,2,V,V},
											{ V,B,0,V,V,V},
											{ V,B,V,V,B,1},
											{ V,B,V,V,V,V},
											{ V,B,2,V,V,V}
									};*/

	//Nivel con soluci�n optima en 6 pasos variables
	/*	private static int[][] AKARI = {
			{ B,V,V,V,1,V},
			{ V,V,V,V,B,B},
			{ V,V,V,V,V,V},
			{ V,V,V,V,V,V},
			{ V,V,V,V,2,V},
			{ V,V,V,V,V,V}
			};*/

	//Nivel con soluci�n UNICA optima en 7 pasos
	/*private static int[][] AKARI = {
			{ B,V,V,V,V,2},
		    { 0,V,V,V,V,V},
		    { V,V,V,V,B,V},
		    { V,V,V,V,V,V},
		    { B,V,2,V,V,1},
		    { V,V,B,V,B,B}
	};*/
	//Nivel con soluci�n UNICA optima en 8 pasos
/*	private static int[][] AKARI ={
		   { 1,V,V,V,V,V},
		   { V,V,V,B,2,V},
		   { V,V,V,B,B,V},
		   { V,V,V,V,V,V},
		   { V,0,V,V,V,V},
		   { V,V,V,V,V,V},
		   { V,V,3,V,V,V},
		   { V,V,V,V,V,1}
	};*/

	/*// Variaci�n de nivel simple First Step 4 con soluci�n optima en 5 pasos
	private static int[][] AKARI = {
			{ B, 1, V, V, B, B },
			{ B, V, V, V, V, 0 },
			{ V, V, V, B, V, V },
			{ V, V, V, V, V, V },
			{ V, V, B, 1, V, V } };*/
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		newLightCrossDemo();

	}

	private static void newLightCrossDemo() {
		System.out.println("\nSignificado de los simbolos de representacion:");
		System.out.println("■=Bloque");
		System.out.println(" =Vacia");
		System.out.println("!=Fuente de luz");
		System.out.println("*=Iluminada");
		System.out.println("0-4=Bloque con requisito");

		Akari Akari;

		 System.out.println("\n<------------------------>");
		 System.out.println("Profundidad (DFS (Graph))");
		 System.out.println("<------------------------>");
		 Akari = new Akari(AKARI);
		 uninformedAndInformedSearchDemo(Akari,
		 AkariFunctionFactory.getActionsFunction(),
		 AkariFunctionFactory.getResultFunction(), new AkariGoalTest(),
				 new DepthFirstSearch(new GraphSearch()));

		System.out.println("\n<------------------------>");
		System.out.println("Anchura (BFS (Graph))");
		System.out.println("<------------------------>");
		Akari = new Akari(AKARI);
		uninformedAndInformedSearchDemo(Akari, AkariFunctionFactory.getActionsFunction(),
				AkariFunctionFactory.getResultFunction(), new AkariGoalTest(),
				new BreadthFirstSearch(new GraphSearch()));

		System.out.println("\n<------------------------>");
		System.out.println("MaxLight con Avaricioso (Greedy Best First (Graph)");
		System.out.println("<------------------------>");
		Akari = new Akari(AKARI);
		uninformedAndInformedSearchDemo(Akari, AkariFunctionFactory.getActionsFunction(),
				AkariFunctionFactory.getResultFunction(), new AkariGoalTest(),
				new GreedyBestFirstSearch(new GraphSearch(), new HeuristicMaxIlumination()));

		System.out.println("\n<------------------------>");
		System.out.println("MaxLight con A* (Graph)");
		System.out.println("<------------------------>");
		Akari = new Akari(AKARI);
		uninformedAndInformedSearchDemo(Akari, AkariFunctionFactory.getActionsFunction(),
				AkariFunctionFactory.getResultFunction(), new AkariGoalTest(),
				new AStarSearch(new GraphSearch(), new HeuristicMaxIlumination()));

		 System.out.println("\n<------------------------>");
		 System.out.println("SquareRoot con Avaricioso (Greedy Best First (Graph)");
		 System.out.println("<------------------------>");
		 Akari = new Akari(AKARI);
		 uninformedAndInformedSearchDemo(Akari,
		 AkariFunctionFactory.getActionsFunction(),
		 AkariFunctionFactory.getResultFunction(), new AkariGoalTest(),
		 new GreedyBestFirstSearch(new GraphSearch(), new HeuristicSquareRoot()));


		 System.out.println("\n<------------------------>");
		 System.out.println("SquareRoot con A* (Graph)");
		 System.out.println("<------------------------>");
		 Akari = new Akari(AKARI);
		 uninformedAndInformedSearchDemo(Akari,
		 AkariFunctionFactory.getActionsFunction(),
		 AkariFunctionFactory.getResultFunction(), new AkariGoalTest(),
		 new AStarSearch(new GraphSearch(), new HeuristicSquareRoot()));

		System.out.println("\n<------------------------>");
		 System.out.println("MinCondBlock con Avaricioso (Greedy Best First (Graph)");
		 System.out.println("<------------------------>");
		 Akari = new Akari(AKARI);
		 uninformedAndInformedSearchDemo(Akari,
		 AkariFunctionFactory.getActionsFunction(),
		 AkariFunctionFactory.getResultFunction(), new AkariGoalTest(),
		 new GreedyBestFirstSearch(new GraphSearch(), new HeuristicMinCondBlock()));

		 System.out.println("\n<------------------------>");
		 System.out.println("MinCondBlock con A* (Graph)");
		 System.out.println("<------------------------>");
		 Akari = new Akari(AKARI);
		 uninformedAndInformedSearchDemo(Akari,
		 AkariFunctionFactory.getActionsFunction(),
		 AkariFunctionFactory.getResultFunction(), new AkariGoalTest(),
		 new AStarSearch(new GraphSearch(), new HeuristicMinCondBlock()));

		// System.out.println("\n<------------------------>");
		 //System.out.println("Heur�sticaD con Avaricioso (Greedy Best First (Graph)");
		// System.out.println("<------------------------>");
		// Akari = new Akari(AKARI);
		// uninformedAndInformedSearchDemo(Akari,
		// AkariFunctionFactory.getActionsFunction(),
		// AkariFunctionFactory.getResultFunction(), new AkariGoalTest(),
		// new GreedyBestFirstSearch(new GraphSearch(), new HeuristicaD()));
		//
		// System.out.println("\n<------------------------>");
		// System.out.println("Heur�sticaD con A* (Graph)");
		// System.out.println("<------------------------>");
		// Akari = new Akari(AKARI);
		// uninformedAndInformedSearchDemo(Akari,
		// AkariFunctionFactory.getActionsFunction(),
		// AkariFunctionFactory.getResultFunction(), new AkariGoalTest(),
		// new AStarSearch(new GraphSearch(), new HeuristicaD()));

	}

	/******************* search without and with heuristic ********************/

	/**
	 * 
	 */

	private static void uninformedAndInformedSearchDemo(Object initialState, ActionsFunction actionsFunction,
			ResultFunction resultFunction, AkariGoalTest akariGoalTest, SearchForActions searchType) {

		try {

			Problem problem = new Problem(initialState, actionsFunction, resultFunction, akariGoalTest);


			SearchForActions search = searchType;


			long timeI = System.currentTimeMillis();


			SearchAgent agent;

			agent = new SearchAgent(problem, search);

			long timeF = System.currentTimeMillis();
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
			printTimeExecution(timeI, timeF);
		//	printGraphicActions(agent.getActions(), initialState);// -> M�todo
			// opcional para ver el resultado al detalle de forma "gr�fica"
			printLastActions(agent.getActions(), initialState);// -> M�todo
																// opcional para
																// ver el
																// resultado
																// final forma
																// "gr�fica"
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

			if (key != "maxQueueSize")
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

	// M�todo opcional para ver el resultado al detalle de forma "gr�fica"
	private static void printGraphicActions(List<Action> actions, Object initialState) {
		System.out.println("\n Estado inicial:   ");
		Akari Akari = (Akari) initialState;
		Akari.printCurrentState();
		for (int i = 0; i < actions.size(); i++) {
			String action = actions.get(i).toString();
			System.out.println("\nAcci�n n�mero " + (i + 1));
			System.out.println(action);
			AkariAction AkariAction = (AkariAction) actions.get(i);
			Akari.turnOnLight(AkariAction.getFila(), AkariAction.getColumna());
			Akari.printCurrentState();
		}
		System.out.println();
	}

	// M�todo opcional para ver el estado soluci�n de forma "gr�fica"
	private static void printLastActions(List<Action> actions, Object initialState) {
		System.out.println("\n Estado inicial:   ");
		Akari Akari = (Akari) initialState;
		Akari.printCurrentState();
		for (int i = 0; i < actions.size(); i++) {
			String action = actions.get(i).toString();
			System.out.print("\nAcci�n n�mero " + (i + 1) + " " + action);
			AkariAction AkariAction = (AkariAction) actions.get(i);
			Akari.turnOnLight(AkariAction.getFila(), AkariAction.getColumna());
			if (i == actions.size() - 1) {
				System.out.println("\n Estado final:   ");
				Akari.printCurrentState();
			}
		}
		System.out.println();
	}
}
