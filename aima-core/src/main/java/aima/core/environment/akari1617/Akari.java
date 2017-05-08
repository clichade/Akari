package aima.core.environment.akari1617;

import java.util.*;

import aima.core.probability.proposition.IntegerSumProposition;
import aima.core.util.datastructure.Pair;
import aima.core.util.math.Matrix;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

public class Akari {
	/**
	 * bloque donde no puede pasar la luz , no necesita un número determinado de bombillas
	 */
	public final static int B = 5;

	/**
	 * la casilla está vacia, no tien bloque ni está iluminada ni tiene bombillac
	 */
	public final static int V = 6;// V de vac�a para indicar que esa casilla no
	// tiene bombilla ni est� iluminada ni es bloque
	public final static int L = 7;// L para representar que esa casilla tiene
	// una fuente de luz
	public final static int I = 8;// I de iluminada para indicar que esa casilla
	// est� iluminada por una fuente de luz

	/* variable que va a representar el estado del problema */
	private int boardState[][];

	/* Contructores */

	/**
	 * @param board ini the initial board state
		it is a copy constructor for the board   
	 */
	public Akari(int[][] board) {
		
		boardState = new int[board.length][board[0].length];
		
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				boardState[row][col] = board[row][col];
			}
		}
	}

	/**
	 * @return the number of rows 
	 */
	public int getNrows() {
		return boardState.length ;
	}

	/**
	 * 
	 * @return del number of columns
	 */
	public int getNcols() {
		return boardState[0].length ;
	}

	/**
	 * 
	 * @return true if it is a lantern in the spot
	 */
	public boolean isLantern(int row, int col) {
		return boardState[row][col] == L;
	}

	/**
	 *
	 * 
	 * @return true if the place has light but not a lantern
	 * */
	public boolean isLight(int row, int col) {
		return boardState[row][col] == I;
	}

	/**
	 * 
	 * @return return true if is a no requirement block
	 */
	public boolean isBlock(int fil, int col) {
		return boardState[fil][col] == B;
	}

	/**
	 * 
	 * @return true if the square has no light and no object
	 */
	public boolean isEmpty(int fil, int col) {
            return boardState[fil][col] == V;
	}

	/**
	 * 
	 * @return n� de luces encendidas
	 */
	public int numberOfLanterns() {
		int lanterns = 0;
		
		for (int row = 0; row < boardState.length; row++) {
			for (int col = 0; col < boardState[0].length; col++) {
				if (isLantern(row, col)) {
					lanterns++;
				}
			}
		}
		return lanterns;
	}

	/**
	 * 
	 * @return de la lista de casillas pulsables.
	 */

	public List<Pair<Integer, Integer>> getPosiblesMovimientos() {

		List<Pair<Integer, Integer>> movList = new ArrayList<>();

		for (int row = 0; row < getNrows(); row++) {
			for (int col = 0; col < getNcols(); col++) {
				if (isEmpty(row, col) && isValid(row,col)) {
					movList.add(new Pair<Integer, Integer>(row, col));
				}
			}
		}
		return movList;
	}

	//check if any of the adjacent squares is a condition block satisfied
	public boolean isValid(int row,int col){
		boolean valid = true;
		// Left
		if (col > 0) {
			if (isConditionBlock(row, col - 1) && condBlockSatisfied(row,col -1)) {
				valid = false;
			}
		}
		// Right
		if (col < getNcols() -1) {
			if (isConditionBlock(row, col +1) && condBlockSatisfied(row,col +1)) {
				valid = false;
			}
		}
		// Top
		if (row > 0) {
			if (isConditionBlock(row -1, col) && condBlockSatisfied(row -1,col)) {
				valid = false;
			}
		}
		// Bot
		if (row < getNrows() -1) {
			if (isConditionBlock(row +1, col) && condBlockSatisfied(row +1,col)) {
				valid = false;
			}
		}

		return valid;
	}

	/**
	 * 
	 * @return devuelve la matriz del estado actual
	 */
	public int[][] getAkariState() {
		int[][] board = new int[boardState.length][boardState[0].length];
		for (int row = 0; row < boardState.length; row++) {
			for (int col = 0; col < boardState[0].length; col++) {
				board[row][col] = boardState[row][col];
			}
		}
		return board;
	}

	/**
	 * 
	 * @return da valor a la matriz
	 */
	public void setAkariState(int[][] T) {
		for (int row = 0; row < T.length; row++) {
			for (int col = 0; col < T[0].length; col++) {
				boardState[row][col] = T[row][col];
			}
		}
	}

	/**
	 * Metodo que cambia el valor de una casilla
	 * 
	 * @param row
	 *            Entero representativo de fila
	 * @param col
	 *            Entero representativo de columna
	 */

	private void putLantern(int row, int col) {
		if (isEmpty(row, col)) {
			boardState[row][col] = L;
		}
	}

	/**
	 *
	 * @return devuelve el valor numerico de una casilla si esta es numerada.
	 */
	public boolean isConditionBlock(int fil, int col) {

		if (boardState[fil][col] <= 4 && boardState[fil][col] >= 0) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Metodo que cambia el valor de una casilla y de sus alrededores
	 * 
	 * @param row
	 *            Entero representativo de fila
	 * @param col
	 *            Entero representativo de columna
	 */

	public void turnOnLight(int row, int col) {
		putLantern(row, col);
		// turn on the left
		if (col > 0) {
		    int caux = col - 1;
			while ( caux >= 0 && (isEmpty(row,caux) || isLight(row,caux))) {
				boardState[row][caux] = I;
				caux--;
			}
		}
		// turn on the right
		if (col < getNcols() - 1) {
		    int caux = col + 1;
			while (caux < getNcols() && (isEmpty(row, caux) || isLight(row, caux))) {
				boardState[row][caux] = I;
				caux++;
			}
		}
		// turn on the top
		if (row > 0) {
		    int raux = row - 1;
			while (raux >= 0 && (isEmpty(raux, col) || isLight(raux, col))) {
				boardState[raux][col] = I;
				raux--;
			}
		}
		// turn on the bot
		if (row < getNrows() -1) {
		    int raux = row + 1;
			while (raux < getNrows() && (isEmpty(raux, col) || isLight(raux, col))) {
				boardState[raux][col] = I;
				raux++;
			}
		}
	}

	/**
	 * 
	 * @return devuelve si el estado en el momento que se llame a este metodo es
	 *         estado final.
	 */
	public boolean checkFinish() {
		boolean goal = true;

		for(int x= 0 ;goal && x < getNrows();x++) {
			for(int y=0 ;goal && y < getNcols();y++) {

				if (isEmpty(x, y))
					goal = false;

				if (isConditionBlock(x, y)) {
					if (!condBlockSatisfied(x, y)) {
						goal = false;
					}
				}
			}
		}

        return goal;
	}



	/**
	 * @return devuelve si el bloque numerado tiene adyacentes las bombillas
	 *         justas
	 */
	public boolean condBlockSatisfied(int fil, int col) {
		int adjacent_lanterns = 0;
		int valor = 0;
		boolean correcto = false;
		valor = boardState[fil][col];

        // Left
		if (col > 0) {
			if (isLantern(fil, col - 1)) {
				adjacent_lanterns++;
			}
		}
		// Right
		if (col < getNcols() -1) {
			if (isLantern(fil, col + 1)) {
				adjacent_lanterns++;
			}
		}
		// Top
		if (fil > 0) {
			if (isLantern(fil - 1, col)) {
				adjacent_lanterns++;
			}
		}
		// Bot
		if (fil < getNrows() -1) {
			if (isLantern(fil + 1, col)) {
				adjacent_lanterns++;
			}
		}
		if (valor == adjacent_lanterns) {
			correcto = true;
        }
		return correcto;
	}

	/**
	 * imprime estado del tablero
     *  B = 5 B de bloque para indicar que es un bloque
        donde no puede ir una fuente de luz ni
        pasar luz

        V = 6 V de vac�a para indicar que esa casilla no
        tiene bombilla ni est� iluminada

         L = 7  L para representar que esa casilla tiene
        una fuente de luz

        int I = 8 I de iluminada para indicar que esa casilla
        est� iluminada por una fuente de luz
     /*
	 */
	public void printCurrentState() {

		for (int i= 0 ;i < getNcols();i++)
			System.out.print(" -");
		System.out.println();


		for (int fila = 0; fila < boardState.length; fila++) {
			System.out.print("|");
			for (int col = 0; col < boardState[0].length; col++) {
			    int c = boardState[fila][col];
				switch (c){
                    case 5:
                        System.out.print("■ ");//imprime bloque
                        break;
                    case 6:
                        System.out.print("  ");
                        break;
                    case 7:
                        System.out.print("! ");
                        break;
                    case 8:
                        System.out.print("* ");
                        break;
                    default:
                        System.out.print(c+" ");
                }
			}
			System.out.println("|");
		}

		for (int i= 0 ;i < getNcols();i++){
			System.out.print(" -");
		}
		System.out.println();

	}


	public int lightColumnsHeuristic(){

		int steps = 0;
		boolean iluminated = true;
		for(int col=0; col<getNcols();col++){
			iluminated = true;
			for (int row=0;row<getNrows();row++){
				if (isEmpty(row,col) && isValid(row,col)) {
					iluminated = false;
				}
			}
			if (!iluminated)
				steps++;
		}
		return steps;


	}

	/**
	 *
	 * @return the number of light and lantern squares as an integuer
	 *
	 */
	public int getTotalLight(){
		int light = 0;
		for(int row=0; row<getNrows();row++){
			for (int col=0;col<getNcols();col++){
				int state = boardState[row][col];
				if (state == L || state == I)
					light++;
			}
		}
		return light;
	}

	public int getTotalEmpty(){
		int light = 0;
		for(int row=0; row<getNrows();row++){
			for (int col=0;col<getNcols();col++){
				if (isEmpty(row,col))
					light++;
			}
		}
		return light;
	}


	public int HeuristicSquareRoot(){
		int steps = getTotalEmpty();
		int min = Math.min(getNcols(),getNrows());

		while(steps > min){
			steps = (int) Math.sqrt(steps);
		}
		return steps;
	}


	/**
	 *
	 */
	void auxMinCondHeuristic(LinkedList<Pair<Integer,Integer>> List, int row, int col){

		// Left
		if (col > 0) {
			if (isEmpty(row,col-1)) {
				Pair<Integer,Integer> a =new Pair<>(row,col-1);
				if(List.contains(a)){
					List.remove(a);
				}
				else List.addLast(a);
			}
		}
		// Right
		if (col < getNcols() -1) {
			if (isEmpty(row,col+1)) {
				Pair<Integer,Integer> a =new Pair<Integer,Integer>(row,col+1);
				if(List.contains(a)){
					List.remove(a);
				}
				else List.addLast(a);
			}
		}
		// Top
		if (row > 0) {
			if (isEmpty(row - 1,col)) {
				Pair<Integer,Integer> a =new Pair<Integer,Integer>(row -1,col);
				if(List.contains(a)){
					List.remove(a);
				}
				else List.addLast(a);
			}
		}
		// Bot
		if (row < getNrows() -1) {
			if (isEmpty(row +1,col)) {
				Pair<Integer,Integer> a =new Pair<Integer,Integer>(row +1,col);
				if(List.contains(a)){
					List.remove(a);
				}
				else List.addLast(a);
			}
		}


	}


	/**
	 * devuelve el número de pasos mínimo correspondiente a las linternas que quedan por poner
	 * alrededor de los bloques de condición
	 *
	 * @return
	 */
	public int MinCondHeursitic() {
		int steps = 0;
		LinkedList<Pair<Integer, Integer>> List = new LinkedList<Pair<Integer, Integer>>();
		for (int row = 0; row < getNrows(); row++) {
			for (int col = 0; col < getNcols(); col++) {
				if (isConditionBlock(row, col) && !satisfiedConditionBlock(row, col)) {
					steps += remainingLanternsCB(row, col);
					//auxMinCondHeuristic(List, row, col);
				}
			}

		}
		return List.size();
	}


	/**
	 * suponiendo que el bloque sea un bloque de condición y no esté satisfecho devuelve
	 * el número de linternas que todavía faltan por poner
	 * @param row
	 * @param col
	 * @return
	 */
	public int remainingLanternsCB(int row, int col){
		int adjacent_lanterns = 0;


		// Left
		if (col > 0)
			if (isLantern(row, col - 1))
				adjacent_lanterns++;

		// Right
		if (col < getNcols() -1)
			if (isLantern(row,col + 1))
				adjacent_lanterns++;

		// Top
		if (row > 0)
			if (isLantern(row - 1, col))
				adjacent_lanterns++;

		// Bot
		if (row < getNrows() -1)
			if (isLantern(row + 1, col))
				adjacent_lanterns++;



		return boardState[row][col] - adjacent_lanterns;

	}

	public boolean satisfiedConditionBlock(int row, int col){
		int adjacent_lanterns = 0;
		boolean satisfied = false;


		// Left
		if (col > 0) {
			if (isLantern(row, col - 1)) {
				adjacent_lanterns++;
			}
		}
		// Right
		if (col < getNcols() -1) {
			if (isLantern(row,col + 1)) {
				adjacent_lanterns++;
			}
		}
		// Top
		if (row > 0) {
			if (isLantern(row - 1, col)) {
				adjacent_lanterns++;
			}
		}
		// Bot
		if (row < getNrows() -1) {
			if (isLantern(row + 1, col)) {
				adjacent_lanterns++;
			}
		}
		if (boardState[row][col] == adjacent_lanterns) {
			satisfied = true;
		}
		return satisfied;

	}


	/**
	 * check if the requirement block is satisfied and only has one possible configuration
	 * it both requirements are true return 4 points, else return 0
	 * @param row
	 * @param col
	 * @return
	 */
	public int onlyOptionBlock_Satisfied(int row,int col){
		int barriers = 0;
		int lanterns = 0;
		int brequirement;
		int puntuation = 7;

		brequirement = boardState[row][col];
		//check the left
		if (col > 0){
			if (boardState[row][col-1] == B)
				barriers++;
			else if (boardState[row][col-1] == L) {
				lanterns++;
			}
		}
		else barriers++;

		//check the right
		if (col < getNcols() -1){
			if (boardState[row][col +1] == B)
				barriers++;
			else if (boardState[row][col +1] == L) {
				lanterns++;
			}
		}
		else barriers++;

		//check the bottom
		if (row < getNrows() -1){
			if (boardState[row +1][col] == B)
				barriers++;
			else if (boardState[row +1][col] == L) {

				lanterns++;
			}
		}
		else barriers++;

		//check the top
		if (row > 0){
			if (boardState[row -1][col] == B)
				barriers++;
			else if (boardState[row -1][col] == L) {
				lanterns++;
			}
		}
		else barriers++;


		if (lanterns == brequirement) {
			puntuation = 2;
			if (lanterns + barriers == 4) {//configuración unica
				puntuation = 0;
			}
		}

		return puntuation;



	}

	/**
	 * M�todo equals para comprobar objetos iguales
	 * 
	 */
	public boolean equals(Object o) {
		boolean iguales = true;
		int fila = 0, col = 0;

		// parte com�n a cualquier equals
		if (this == o)
			return true;
		if ((o == null) || (this.getClass() != o.getClass()))
			return false;
		// parte espec�fica del equals

		Akari aux = (Akari) o;
		while (iguales && fila < boardState.length) {

			while (iguales && col < boardState[0].length) {
				iguales = (boardState[fila][col] == aux.boardState[fila][col]);
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
		for (int fila = 0; fila < boardState.length; fila++) {
			for (int col = 0; col < boardState[0].length; col++) {
				result += ((result * 37 * boardState[fila][col]) + fila) * col;

			}
		}
		return result;
	}
}
