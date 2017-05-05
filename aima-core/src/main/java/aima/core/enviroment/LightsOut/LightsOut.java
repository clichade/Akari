package aima.core.enviroment.LightsOut;

import java.util.ArrayList;
import java.util.List;

import aima.core.util.datastructure.Pair;

public class LightsOut {

	private int[][] lightsOutState;
	public final static int X = 1;// para indicar que esa luz está apagada
	public final static int O = 0;// para indicar que esa luz está encendida

	/**
	 * Constructor Parametrizado por un valor int[][]
	 * 
	 * @param L
	 *            Objeto de LightsOut por ejemplo
	 * 
	 */
	public LightsOut(int[][] L) {
		lightsOutState = new int[L.length][L[0].length];
		for (int fila = 0; fila < L.length; fila++) {
			for (int col = 0; col < L[0].length; col++) {
				lightsOutState[fila][col] = L[fila][col];
			}
		}

		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor parametrizado por tres valores, matriz y 2 enteros
	 * 
	 * @param L
	 *            Objeto de LightsOut por ejemplo
	 * @param x
	 *            Entero para definir columnas
	 * @param v
	 *            Entero para definir filas
	 * 
	 */
	public LightsOut(int[][] L, int x, int v) {
		lightsOutState = new int[x][v];
		for (int fila = 0; fila < L.length; fila++) {
			for (int col = 0; col < L[0].length; col++) {
				lightsOutState[fila][col] = L[fila][col];
			}
		}

		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @return el nº de filas
	 */
	public int getNF() {
		return getLightsOutState().length - 1;
	}

	/**
	 * 
	 * @return el nº de columnas
	 */
	public int getNC() {
		return getLightsOutState()[0].length - 1;
	}

	/**
	 * Pasando por parametro un nº de fila y columna
	 * 
	 * @param x:
	 *            entero de fila
	 * @param y:
	 *            entero de columna
	 * @return devuelve true en caso de estar encendida
	 */
	public boolean getOn(int x, int y) {
		return getLightsOutState()[x][y] == O;
	}

	/**
	 *
	 * @param x:
	 *            entero que indica el nº de la fila
	 * @return devuelve true en caso de que haya al menos una luz encendida
	 */
	public boolean getFOn(int x) {
		boolean p = false;
		int m = 0;
		while (!p && m < getNC()) {
			p = getOn(x, m);
			m++;
		}

		return p;
	}

	/**
	 * Desc: Imprime por consola el estado del tablero de lightsout
	 */
	public void printCurrentState() {

		for (int fila = 0; fila < getLightsOutState().length; fila++) {
			for (int col = 0; col < getLightsOutState()[0].length; col++) {
				System.out.print(getLightsOutState()[fila][col]);
			}
			System.out.println("");
		}
	}

	/**
	 * 
	 * @return nº de luces encendidas
	 */
	public int lightsOn() {
		int x = 0;
		for (int fila = 0; fila < getLightsOutState().length; fila++) {
			for (int col = 0; col < getLightsOutState()[0].length; col++) {
				if (getLightsOutState()[fila][col] == O) {
					x++;
				}
			}
		}
		return x;
	}

	/**
	 * 
	 * @return nº de luces apagadas
	 */
	public int lightsOff() {
		int x = 0;
		for (int fila = 0; fila < getLightsOutState().length; fila++) {
			for (int col = 0; col < getLightsOutState()[0].length; col++) {
				if (getLightsOutState()[fila][col] == X) {
					x++;
				}
			}
		}
		return x;
	}

	/**
	 * 
	 * @return de una lista con los parametros (Fila, columna) sobre los cuales
	 *         puede pulsar
	 */
	public List<Pair<Integer, Integer>> getMovesXY() {
		List<Pair<Integer, Integer>> n = new ArrayList<Pair<Integer, Integer>>();
		Pair<Integer, Integer> x = null;
		// LightsOut aux = new LightsOut(getLightsOutState(),
		// getLightsOutState().length, getLightsOutState()[0].length);
		for (int fila = 0; fila < getLightsOutState().length; fila++) {
			for (int col = 0; col < getLightsOutState()[0].length; col++) {
				x = new Pair<Integer, Integer>(fila, col);
				n.add(x);
			}
		}
		return n;
	}

	/**
	 * 
	 * @return devuelve la matriz del estado actual
	 */
	public int[][] getLightsOutState() {
		int[][] x = new int[lightsOutState.length][lightsOutState[0].length];
		for (int fila = 0; fila < lightsOutState.length; fila++) {
			for (int col = 0; col < lightsOutState[0].length; col++) {
				x[fila][col] = lightsOutState[fila][col];
			}
		}
		return x;
	}

	/**
	 * 
	 * @return da valor a la matriz
	 */
	public void setLightsOutState(int[][] x) {
		for (int fila = 0; fila < x.length; fila++) {
			for (int col = 0; col < x[0].length; col++) {
				lightsOutState[fila][col] = x[fila][col];
			}
		}
		;
	}

	/**
	 * Metodo que cambia el valor de una casilla
	 * 
	 * @param f
	 *            Entero representativo de fila
	 * @param c
	 *            Entero representativo de columna
	 */
	private void doAction(int f, int c) {
		if (lightsOutState[f][c] == O) {
			lightsOutState[f][c] = X;
		} else {
			lightsOutState[f][c] = O;
		}

	}

	/**
	 * Metodo que cambia el valor de una casilla y de sus alrededores
	 * 
	 * @param f
	 *            Entero representativo de fila
	 * @param c
	 *            Entero representativo de columna
	 */
	public void changeLine(int f, int c) {
		doAction(f, c);
		// Izquierda
		// System.out.println(c);
		if (c > 0) {
			doAction(f, c - 1);
		}
		// Derecha
		if (c < getNC()) {
			doAction(f, c + 1);
		}
		// Arriba
		if (f != 0) {
			doAction(f - 1, c);
		}
		// Abajo
		if (f < getNF()) {

			doAction(f + 1, c);

		}
	}

	/**
	 * 
	 * @return Si está finalizada correctamente o no
	 */
	public boolean isDone() {
		boolean done = true;
		int x = 0, y = 0;
		while (done && x < getNF()) {
			while (done && y < getNC()) {
				done = (getLightsOutState()[x][y] == X);
				y++;
			}
			x++;
		}
		return done;
	}

	/**
	 * Devuelve el tipo de casilla que es (esquina, centro, lateral) cada una
	 * con un valor
	 * 
	 * @param x:
	 *            entero que representa la fila
	 * @param j:
	 *            entero que representa la columna
	 * @return devuelve el valor del tipo casilla (2-3-4)
	 */
	public int typeCell(int x, int j) {
		int tipo = 4;
		if (x == 0 || x == getNF()) {
			tipo--;
		}
		if (j == 0 || x == getNC()) {
			tipo--;
		}

		return tipo;
	}

	/**
	 * 
	 * @param x:
	 *            entero que representa la fila
	 * @param j:
	 *            entero que representa la columna
	 * @return devuelve true en caso de estar encendida la casilla
	 */


	/**
	 * 
	 * @param x:
	 *            entero que representa la fila
	 * @param j:
	 *            entero que representa la columna
	 * @return devuelve el numero de luces encendidas alrededor de X casilla
	 */
	public int lightsOn(int x, int j) {
		int m = 0;
		if (getOn(x, j)) {
			m++;
		}
		if (j > 0) {
			if (getOn(x, j - 1)) {
				m++;
			}
		}
		// Derecha
		if (j < getNC()) {
			if (getOn(x, j + 1)) {
				m++;
			}
		}
		// Arriba
		if (x != 0) {
			if (getOn(x - 1, j)) {
				m++;
			}
		}
		// Abajo
		if (x < getNF()) {
			if (getOn(x + 1, j)) {
				m++;
			}

		}
		return m;
	}

	/**
	 * Método equals para comprobar objetos iguales
	 * 
	 */
	public boolean equals(Object o) {
		boolean iguales = true;
		int fila = 0, col = 0;

		// parte común a cualquier equals
		if (this == o)
			return true;
		if ((o == null) || (this.getClass() != o.getClass()))
			return false;
		// parte específica del equals

		LightsOut aux = (LightsOut) o;
		while (iguales && fila < getLightsOutState().length) {

			while (iguales && col < getLightsOutState()[0].length) {
				iguales = (getLightsOutState()[fila][col] == aux.getLightsOutState()[fila][col]);
				col++;
			}
			col = 0;
			fila++;
		}
		return iguales;
	}

	/**
	 * HashCode
	 */
	public int hashCode() {
		int result = 17;
		for (int fila = 0; fila < getLightsOutState().length; fila++) {
			for (int col = 0; col < getLightsOutState()[0].length; col++) {
				result += ((result * 37 * getLightsOutState()[fila][col]) + fila) * col;

			}
		}
		return result;
	}
}
