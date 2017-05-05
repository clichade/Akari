package aima.core.environment.ochopuzzle;


import java.util.List;


import aima.core.search.framework.evalfunc.HeuristicFunction;
import aima.core.util.datastructure.Pair;

/**
 *
 * @author markel
 */
public class HeuristicaManhattan implements HeuristicFunction{

	public double h(Object state) {
		
		Puzzle puzzle = (Puzzle) state;
        
		int distancia=0;
		List<Pair<Integer,Integer>> posicionesFichaNoEnObjetivo = puzzle.getFichasNOEnObjetivo();
		for(Pair<Integer,Integer> posicionFicha: posicionesFichaNoEnObjetivo)
		{
			int valorFicha=puzzle.getState()[posicionFicha.getFirst()][posicionFicha.getSecond()];
			
			Pair<Integer,Integer> posicionObjetivo= puzzle.getPosicionObjetivoFicha(valorFicha);
			
			distancia+= Math.abs(posicionFicha.getFirst()-posicionObjetivo.getFirst())
					+Math.abs(posicionFicha.getSecond()-posicionObjetivo.getSecond());
		}
			
		
		
		return distancia;
    }

}
