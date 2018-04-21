/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs600_assignment3_problem1;

/**
 *
 * @author Elias
 */
import java.io.*;
import java.util.*;

class cell {

    public int row;
    public int column;
    public char value;
}

public class Maze {

    /**
     * @param args the command line arguments
     */
    //function to print out the maze array
    public static void printMaze(cell array[][]) {
        System.out.println("Maze:");
        for (int i = 0; i < 22; i++) {
            for (int j = 0; j < 22; j++) {
                System.out.print(array[i][j].value);
            }
            System.out.println("");
        }
    }

    //checks adjacent squares for 0 or E and pushes them into the stack
    public static void step1(int row, int column, cell maze[][], Stack stack) {
        //check above
        if (maze[row + 1][column].value == '0' || maze[row + 1][column].value == 'E') {
            stack.push(maze[row + 1][column]);
        }
        //check below
        if (maze[row - 1][column].value == '0' || maze[row - 1][column].value == 'E') {
            stack.push(maze[row - 1][column]);
        }
        //check right
        if (maze[row][column + 1].value == '0' || maze[row][column + 1].value == 'E') {
            stack.push(maze[row][column + 1]);
        }
        //check left
        if (maze[row][column - 1].value == '0' || maze[row][column - 1].value == 'E') {
            stack.push(maze[row][column - 1]);
        }
    }

    public static void main(String[] args) {
        // Initialize the maze array
        cell[][] maze = new cell[22][22];

        // Boundary condition for maze array
        for (int i = 0; i < 22; i++) {
            maze[0][i] = new cell();
            maze[0][i].value = '1';
            maze[0][i].row = 0;
            maze[0][i].column = i;
            maze[21][i] = new cell();
            maze[21][i].value = '1';
            maze[21][i].row = 21;
            maze[21][i].column = i;
            maze[i][0] = new cell();
            maze[i][0].value = '1';
            maze[i][0].row = i;
            maze[i][0].column = 0;
            maze[i][21] = new cell();
            maze[i][21].value = '1';
            maze[i][21].row = i;
            maze[i][21].column = 21;
        }

        // Get data from file for maze
        try {
            Scanner scanner = new Scanner(new File("maze.txt"));
            int x = 1, y = 1;
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                for (char c : s.toCharArray()) {
                    maze[x][y] = new cell();
                    maze[x][y].value = c;
                    maze[x][y].row = x;
                    maze[x][y].column = y;
                    y++;
                    if (y == 21) {
                        x++;
                        y = 1;
                    }
                }
            }
            printMaze(maze);
        } catch (IOException ex) {
            System.out.println("Error: File not found");
        }

        Scanner scannerInput = new Scanner(System.in);
        int row, column;

        //Get starting point from I/O
        while (true) {
            System.out.println("Enter your starting coordinates (row then column seperated by space: ex 1 2), starting point must land on a 0");
            row = scannerInput.nextInt() - 1;
            column = scannerInput.nextInt() - 1;

            if (maze[row][column].value != '0') {
                System.out.println("Starting point is at a 1 or E, please enter a new starting locaiton");
                System.out.println(maze[row][column]);
            } else {
                maze[row][column].value = 'S';
                printMaze(maze);
                break;
            }
        }

        //initialize stack
        Stack<cell> stack = new Stack<cell>();

        while (true) {
            //step1: examine adjacent squares and push 0 or E into stack. Function step1 will do this.
            step1(row, column, maze, stack);

            //if cell popped is E. Exit.
            cell popped = stack.pop();
            if (popped.value == 'E') {
                System.out.println("I am free!");
                break;
            }

            //step2: pop stack and move. If stack is empty you are trapped.
            if (stack.empty()) {
                System.out.println("Help, I am trapped!");
                break;
            }

            //popped value is saved and recursion occurs until the stack is empty.
            row = popped.row;
            column = popped.column;
            popped.value = '+';
            printMaze(maze);
        }
    }
}
