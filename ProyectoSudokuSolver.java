/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectosudokusolver;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ProyectoSudokuSolver {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SudokuFrame sudokuFrame = new SudokuFrame();
            sudokuFrame.setVisible(true);
        });
    }

    public static class Sudoku {

        public Integer[][] tablero;

        public Sudoku() {
            tablero = new Integer[9][9];
            inicializarTablero();
            generarSudoku(0, 0);
        }
        
        public void inicializarTablero() {
            for (int i = 0; i < 9; i++) {
                Arrays.fill(tablero[i], 0);
            }
        }

        public boolean esJugadaValida(int fila, int columna, int numero) {
            // Verificar si el número ya está en la fila o columna
            for (int i = 0; i < 9; i++) {
                if (tablero[fila][i] == numero || tablero[i][columna] == numero) {
                    return false;
                }
            }

            // Verificar si el número ya está en el cuadrante 3x3
            int inicioFila = (fila / 3) * 3;
            int inicioColumna = (columna / 3) * 3;
            for (int i = inicioFila; i < inicioFila + 3; i++) {
                for (int j = inicioColumna; j < inicioColumna + 3; j++) {
                    if (tablero[i][j] == numero) {
                        return false;
                    }
                }
            }

            return true;
        }

        public boolean generarSudoku(int fila, int columna) {
            if (fila == 9) {
                return true; // Se ha generado el Sudoku completo
            }

            if (columna == 9) {
                return generarSudoku(fila + 1, 0); // Moverse a la siguiente fila
            }
            // Lógica para generar el Sudoku utilizando recursividad
            for (int numero = 1; numero <= 9; numero++) {
                if (esJugadaValida(fila, columna, numero)) {
                    tablero[fila][columna] = numero;

                    if (generarSudoku(fila, columna + 1)) {
                        return true;
                    }

                    tablero[fila][columna] = 0; // Deshacer la jugada si no conduce a una solución
                }
            }

            return false;
        }

        public Integer[][] getTablero() {
            return tablero;
        }
    }

    public static class SudokuFrame extends JFrame {

        private JLabel[][] board;
        //Private es el modificador de acceso que indica que el método es privado 
        //y solo es accesible dentro de la misma clase.
        //Asi podemos controlar mejor cómo se accede y se modifica ese atributo

        public SudokuFrame() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(500, 500);
            setTitle("Sudoku Solver");

            initializeBoard();
        }

        private void initializeBoard() {
            //utilizamos private ya que no está destinado a ser utilizado externamente.
            board = new JLabel[9][9];
            JPanel Board = new JPanel(new GridLayout(9, 9));

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    board[i][j] = new JLabel();
                    board[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                    Font font = new Font("Arial", Font.PLAIN, 20);
                    board[i][j].setFont(font);
                    board[i][j].setForeground(Color.BLACK); // Cambiado a negro para mejor visibilidad
                    board[i][j].setBackground(Color.WHITE);
                    board[i][j].setOpaque(true);
                    board[i][j].setHorizontalAlignment(JTextField.CENTER);
                    Board.add(board[i][j]);
                }
            }

            Sudoku sudoku = new Sudoku();
            Integer[][] tablero = sudoku.getTablero();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (tablero[i][j] != 0) {
                        board[i][j].setText(String.valueOf(tablero[i][j]));
                    }
                }
            }

            add(Board);
        }
    }
}
 // Nota: En el futuro, se podría agregar la opción de ingresar números y ver la respuesta,
    // así como la generación aleatoria del Sudoku.


    
    
