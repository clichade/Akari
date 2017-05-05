package aima.core.environment.ochopuzzle;

import java.util.ArrayList;
import java.util.List;

import aima.core.util.datastructure.Pair;

/**
 * @author aeprieto
 */

public class Puzzle {

	/* constantes útiles para la implementación del problema */
	public final static int FICHA_MIN = 1; // para indicar el valor de la ficha
											// más pequeña en el puzzle-8 de 3x3
	public final static int FICHA_MAX = 8; // para indicar el valor de la ficha
											// más grande en el puzzle-8 de 3x3

	/* variable que va a representar el estado del problema */
	private int puzzleState[][];

	/* configuración inicial del problema para las pruebas internas */

	private static int[][] PUZZLE = { { 7, 2, 4 }, { 5, 0, 6 }, { 8, 3, 1 } };

	/* Contructores */

	/*
	 * Constructor por defecto utilizando configuración inicial del problema
	 * para pruebas internas
	 */
	public Puzzle() {
		puzzleState = new int[PUZZLE.length][PUZZLE[0].length];
		for (int fila = 0; fila < PUZZLE.length; fila++) {
			for (int columna = 0; columna < PUZZLE[0].length; columna++) {
				puzzleState[fila][columna] = PUZZLE[fila][columna];
			}
		}
	}

	/*
	 * Constructor parametrizado que recibe una variable que represente el
	 * estado inicial del problema
	 */
	public Puzzle(int[][] estadoInicialPuzzle) {
		puzzleState = new int[estadoInicialPuzzle.length][estadoInicialPuzzle[0].length];
		for (int fila = 0; fila < estadoInicialPuzzle.length; fila++) {
			for (int columna = 0; columna < estadoInicialPuzzle[0].length; columna++) {
				puzzleState[fila][columna] = estadoInicialPuzzle[fila][columna];
			}
		}
	}

	/* Método que devuelve el estado del problema en un momento dado */
	public int[][] getState() {
		int[][] retorno = new int[puzzleState.length][puzzleState[0].length];
		for (int fila = 0; fila < puzzleState.length; fila++) {
			for (int columna = 0; columna < puzzleState[0].length; columna++) {
				retorno[fila][columna] = puzzleState[fila][columna];
			}
		}

		return retorno;
	}

	/* Método que fija un estado para el problema */
	public void setState(int[][] estadoPuzzle) {
		for (int fila = 0; fila < estadoPuzzle.length; fila++) {
			for (int columna = 0; columna < estadoPuzzle[0].length; columna++) {
				puzzleState[fila][columna] = estadoPuzzle[fila][columna];
			}
		}
	}

	/* Métodos auxiliares relacionados con el problema a resolver */

	/* Métodos relacionados con el hueco del puzzle */

	// Método que nos dice si el hueco está en unas coordenadas dadas en forma
	// de dos variables de tipo int
	public boolean huecoEstaEnCasilla(int fila, int columna) {
		return (puzzleState[fila][columna] == 0);
	}

	// Método que nos dice si el hueco está en unas coordenadas dadas en forma
	// de una variable que contiene un par de tipo Integer
	public boolean huecoEstaEnCasilla(Pair<Integer, Integer> coordenadas) {
		return huecoEstaEnCasilla(coordenadas.getFirst(), coordenadas.getSecond());
	}

	// Método que nos devuelve un par de tipo Integer con las coordenadas donde
	// se encuentra el hueco
	public Pair<Integer, Integer> getHuecoPosition() {
		for (int fila = 0; fila < puzzleState.length; fila++)
			for (int columna = 0; columna < puzzleState[0].length; columna++)
				if (huecoEstaEnCasilla(fila, columna))
					return new Pair<Integer, Integer>(fila, columna);
		return null;
	}

	/* Métodos relacionados con las fichas del puzzle */

	// Método que nos dice si hay una ficha en unas coordenadas dadas en forma
	// de dos variables de tipo int
	private boolean fichaEstaEnCasilla(int fila, int columna) {
		return (puzzleState[fila][columna] >= FICHA_MIN && puzzleState[fila][columna] <= FICHA_MAX);
	}

	// Método que nos dice si hay una ficha en unas coordenadas dadas en forma
	// de una variable que contiene un par de tipo Integer
	public boolean fichaEstaEnCasilla(Pair<Integer, Integer> coordenadas) {
		return (fichaEstaEnCasilla(coordenadas.getFirst(), coordenadas.getSecond()));
	}

	// Método que nos dice si una ficha está en su casilla objetivo a partir de
	// unas coordenadas dadas en forma de dos variables de tipo int
	private boolean fichaEstaEnObjetivo(int fila, int columna) {
		return (puzzleState[fila][columna] == (fila * puzzleState[0].length) + columna);
	}

	// Método que nos dice si una ficha está en su casilla objetivo a partir de
	// unas coordenadas dadas en forma de una variable que contiene un par de
	// tipo Integer
	public boolean fichaEstaEnObjetivo(Pair<Integer, Integer> coordenadas) {
		return (fichaEstaEnObjetivo(coordenadas.getFirst(), coordenadas.getSecond()));
	}

	// Método que nos dice el número de fichas que se encuentran en su casilla
	// objetivo

	public int contarFichasEnObjetivo() {
		int fichasEnObjetivo = 0;
		for (int fila = 0; fila < puzzleState.length; fila++) {
			for (int columna = 0; columna < puzzleState[0].length; columna++) {
				if (fichaEstaEnObjetivo(fila, columna) && !huecoEstaEnCasilla(fila, columna))
					fichasEnObjetivo++;
			}
		}
		return fichasEnObjetivo;

	}

	// Método que nos dice el número de fichas que NO se encuentran en su
	// casilla objetivo

	public int contarFichasNOEnObjetivo() {
		int fichasNOEnObjetivo = 0;
		for (int fila = 0; fila < puzzleState.length; fila++) {
			for (int columna = 0; columna < puzzleState[0].length; columna++) {
				if (!fichaEstaEnObjetivo(fila, columna) && !huecoEstaEnCasilla(fila, columna))
					fichasNOEnObjetivo++;
			}
		}
		return fichasNOEnObjetivo;

	}

	// Método que nos devuelve una lista con las coordenadas de las fichas que
	// se encuentran en su objetivo en forma de una variable que contiene un par
	// de tipo Integer

	public List<Pair<Integer, Integer>> getFichasEnObjetivo() {
		List<Pair<Integer, Integer>> result = new ArrayList<Pair<Integer, Integer>>();
		for (int fila = 0; fila < puzzleState.length; fila++) {
			for (int columna = 0; columna < puzzleState[0].length; columna++) {
				if (fichaEstaEnObjetivo(fila, columna) && !huecoEstaEnCasilla(fila, columna))
					result.add(new Pair<Integer, Integer>(fila, columna));
			}
		}
		return result;
	}

	// Método que nos devuelve una lista con las coordenadas de las fichas que
	// NO se encuentran en su objetivo en forma de una variable que contiene un
	// par de tipo Integer

	public List<Pair<Integer, Integer>> getFichasNOEnObjetivo() {
		List<Pair<Integer, Integer>> result = new ArrayList<Pair<Integer, Integer>>();
		for (int fila = 0; fila < puzzleState.length; fila++) {
			for (int columna = 0; columna < puzzleState[0].length; columna++) {
				if (!fichaEstaEnObjetivo(fila, columna) && !huecoEstaEnCasilla(fila, columna))
					result.add(new Pair<Integer, Integer>(fila, columna));
			}
		}
		return result;
	}

	// Método que nos devuelve las coordenadas de la casilla objetivo para una
	// ficha en forma de una variable que contiene un par de tipo Integer

	public Pair<Integer, Integer> getPosicionObjetivoFicha(int valorFicha) {
		for (int fila = 0; fila < puzzleState.length; fila++)
			for (int columna = 0; columna < puzzleState[0].length; columna++)
				if (((fila * puzzleState[0].length) + columna) == valorFicha)
					return new Pair<Integer, Integer>(fila, columna);
		return null;
	}

	/* Métodos relacionados con la mecánica del problema a resolver */

	// Método que recibe las coordenadas de origen y destino del hueco (en forma
	// de variable que contiene un par de tipo Integer) del puzzle y realiza el
	// movimiento

	public void moverHuecoACasilla(Pair<Integer, Integer> from, Pair<Integer, Integer> to) {
		puzzleState[from.getFirst()][from.getSecond()] = puzzleState[to.getFirst()][to.getSecond()];
		puzzleState[to.getFirst()][to.getSecond()] = 0;

	}

	// Método que recibe las coordenadas de origen y destino del hueco (en forma
	// de variable que contiene un par de tipo Integer) del puzzle y nos dice si
	// el movimiento es correcto

	public boolean movimientoCorrecto(Pair<Integer, Integer> from, Pair<Integer, Integer> to) {
		if (huecoEstaEnCasilla(from) && fichaEstaEnCasilla(to))
			return true;
		else
			return false;
	}

	// Método que nos devuelve una lista con los movimientos correctos (en forma
	// de variable par que contiene origen y destino del movimiento como par de
	// tipo Integer) para la situación del problema en ese momento

	public List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> getMovimientos() {
		List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> listaMovimientos = new ArrayList<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>>();
		Pair<Integer, Integer> posicionHueco = getHuecoPosition();
		Pair<Integer, Integer> posibleDestinoDerecha = new Pair<Integer, Integer>(posicionHueco.getFirst(),
				posicionHueco.getSecond() + 1);
		Pair<Integer, Integer> posibleDestinoIzquierda = new Pair<Integer, Integer>(posicionHueco.getFirst(),
				posicionHueco.getSecond() - 1);
		Pair<Integer, Integer> posibleDestinoAbajo = new Pair<Integer, Integer>(posicionHueco.getFirst() + 1,
				posicionHueco.getSecond());
		Pair<Integer, Integer> posibleDestinoEncima = new Pair<Integer, Integer>(posicionHueco.getFirst() - 1,
				posicionHueco.getSecond());

		if ((posibleDestinoDerecha.getSecond() < puzzleState[0].length)
				&& movimientoCorrecto(posicionHueco, posibleDestinoDerecha))
			listaMovimientos.add(
					new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>(posicionHueco, posibleDestinoDerecha));
		if ((posibleDestinoAbajo.getFirst() < puzzleState.length)
				&& movimientoCorrecto(posicionHueco, posibleDestinoAbajo))
			listaMovimientos
					.add(new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>(posicionHueco, posibleDestinoAbajo));
		if ((posibleDestinoIzquierda.getSecond() >= 0) && movimientoCorrecto(posicionHueco, posibleDestinoIzquierda))
			listaMovimientos.add(
					new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>(posicionHueco, posibleDestinoIzquierda));
		if ((posibleDestinoEncima.getFirst() >= 0) && movimientoCorrecto(posicionHueco, posibleDestinoEncima))
			listaMovimientos
					.add(new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>(posicionHueco, posibleDestinoEncima));
		return listaMovimientos;
	}

	/*
	 * Métodos a implementar OBLIGATORIAMENTE independientemente del tipo de
	 * problema
	 */

	/* Método que muestra el estado actual */

	public void printCurrentState() {
		// Pinto la cabecera
		System.out.print(" |");
		for (int columna = 0; columna < puzzleState[0].length; columna++)
			System.out.print(columna);
		// Pinto la línea de separación
		System.out.println();
		System.out.print("  ");
		for (int columna = 0; columna < puzzleState[0].length; columna++)
			System.out.print("-");

		for (int fila = 0; fila < puzzleState.length; fila++) {
			System.out.println();
			System.out.print(fila + "|");
			for (int columna = 0; columna < puzzleState[0].length; columna++) {
				if (this.puzzleState[fila][columna] == 0)
					System.out.print(" ");
				else
					System.out.print(this.puzzleState[fila][columna]);

			}

		}

		System.out.println();

	}

	@Override

	/* Método que implementa el hashCode en función del estado del problema */
	public int hashCode() {
		int result = 17;
		for (int fila = 0; fila < puzzleState.length; fila++) {
			for (int columna = 0; columna < puzzleState[0].length; columna++) {
				result += ((result * 37 * puzzleState[fila][columna]) + fila) * columna;
			}
		}
		return result;
	}

	/*
	 * Método que implementa el método equals en función del estado del problema
	 */
	@Override
	public boolean equals(Object o) {
		boolean iguales = true;
		int fila = 0;
		int columna = 0;
		// parte común a cualquier equals
		if (this == o)
			return true;
		if ((o == null) || (this.getClass() != o.getClass()))
			return false;
		// parte específica del equals
		Puzzle aState = (Puzzle) o;
		while (iguales && fila < puzzleState.length) {
			while (iguales && columna < puzzleState[0].length) {
				iguales = puzzleState[fila][columna] == aState.getState()[fila][columna];
				columna++;
			}
			columna = 0;
			fila++;
		}
		return iguales;
	}

	/*
	 * Método que implementa el método toString en función del estado del
	 * problema
	 */

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();

		buf.append(" |");
		for (int columna = 0; columna < puzzleState[0].length; columna++)
			buf.append(columna);

		buf.append("\n");
		buf.append(" ");
		for (int columna = 0; columna < puzzleState[0].length; columna++)
			buf.append("-");

		for (int fila = 0; fila < puzzleState.length; fila++) {
			buf.append("\n");
			buf.append(fila);
			for (int columna = 0; columna < puzzleState[0].length; columna++) {
				if (this.puzzleState[fila][columna] == 0)
					buf.append(" ");
				else
					buf.append(this.puzzleState[fila][columna]);

			}
		}

		buf.append("\n");
		return buf.toString();
	}

	/* Main de la clase para realizar pruebas internas */

	public static void main(String[] args) {
		Puzzle puzzle = new Puzzle();

		System.out.println("Cantidad de filas de la matriz:" + puzzle.getState().length);
		System.out.println("Cantidad de elementos de la primer fila:" + puzzle.getState()[0].length);

		System.out.println("\n Estado inicial:   ");
		puzzle.printCurrentState();

		System.out.println("La coordenada objetivo de la ficha 1 es " + puzzle.getPosicionObjetivoFicha(1));
		System.out.println("La coordenada objetivo de la ficha 2 es " + puzzle.getPosicionObjetivoFicha(2));
		System.out.println("La coordenada objetivo de la ficha 3 es " + puzzle.getPosicionObjetivoFicha(3));
		System.out.println("La coordenada objetivo de la ficha 4 es " + puzzle.getPosicionObjetivoFicha(4));
		System.out.println("La coordenada objetivo de la ficha 5 es " + puzzle.getPosicionObjetivoFicha(5));
		System.out.println("La coordenada objetivo de la ficha 6 es " + puzzle.getPosicionObjetivoFicha(6));
		System.out.println("La coordenada objetivo de la ficha 7 es " + puzzle.getPosicionObjetivoFicha(7));
		System.out.println("La coordenada objetivo de la ficha 8 es " + puzzle.getPosicionObjetivoFicha(8));

		System.out.println("El hueco está en" + puzzle.getHuecoPosition());
		System.out.println("Número de fichas en objetivo " + puzzle.contarFichasEnObjetivo());
		System.out.println("Número de fichas NO en objetivo  " + puzzle.contarFichasNOEnObjetivo());

		List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> listaMovimientos = puzzle.getMovimientos();
		System.out.println("Posibles movimientos " + listaMovimientos);

		System.out.println("La coordenada de la ficha 7 es " + puzzle.getPosicionObjetivoFicha(7));

		puzzle.moverHuecoACasilla(new Pair<Integer, Integer>(1, 1), new Pair<Integer, Integer>(1, 0));

		puzzle.printCurrentState();

		System.out.println("El hueco está en" + puzzle.getHuecoPosition());
		System.out.println("Número de fichas en objetivo " + puzzle.contarFichasEnObjetivo());
		System.out.println("Número de fichas NO en objetivo  " + puzzle.contarFichasNOEnObjetivo());
		System.out.println("fichas NO en objetivo  " + puzzle.getFichasNOEnObjetivo());

		listaMovimientos = puzzle.getMovimientos();
		System.out.println("Posibles movimientos " + listaMovimientos);

		puzzle.moverHuecoACasilla(new Pair<Integer, Integer>(1, 0), new Pair<Integer, Integer>(0, 0));

		puzzle.printCurrentState();

		System.out.println("El hueco está en" + puzzle.getHuecoPosition());
		System.out.println("Número de fichas en objetivo " + puzzle.contarFichasEnObjetivo());
		System.out.println("Número de fichas NO en objetivo  " + puzzle.contarFichasNOEnObjetivo());

		listaMovimientos = puzzle.getMovimientos();
		System.out.println("Posibles movimientos " + listaMovimientos);

		int[][] PUZZLE2 = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };

		System.out.println("Puzzle2 ");

		Puzzle puzzle2 = new Puzzle(PUZZLE2);

		puzzle2.printCurrentState();

		System.out.println("Número de fichas en objetivo de puzzle2 " + puzzle2.contarFichasEnObjetivo());
		System.out.println("Número de fichas NO en objetivo de puzzle2 " + puzzle2.contarFichasNOEnObjetivo());

		System.out.println("Iguales " + puzzle2.equals(puzzle));

		puzzle2.setState(puzzle.getState());

		puzzle2.printCurrentState();

		System.out.println("Iguales " + puzzle2.equals(puzzle));

	}
}
