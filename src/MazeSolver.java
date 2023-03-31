/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        Stack<MazeCell> stack = new Stack<>();
        ArrayList<MazeCell> route = new ArrayList<>();
        MazeCell cell = maze.getEndCell();
        stack.push(cell);
        while (!cell.equals(maze.getStartCell())) {
            // Adds cell to the stack and moves on to its parent
            stack.push(cell.getParent());
            cell = cell.getParent();
        }
        // Flips the stack to return the route from start to finish
        while (!stack.empty()) {
            route.add(stack.pop());
        }
        return route;
    }


    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        Stack<MazeCell> cellsToVisit = new Stack<>();
        MazeCell current = maze.getStartCell();
        while (!current.equals(maze.getEndCell())) {
            int row = current.getRow();
            int col = current.getCol();
            // Checks for north neighbor
            if (maze.isValidCell(row - 1, col)) {
                // Adds neighbor to cells to visit and assigns its parent
                cellsToVisit.push(maze.getCell(row - 1, col));
                maze.getCell(row - 1, col).setParent(current);
            }
            // Checks for east neighbor
            if (maze.isValidCell(row,col + 1)) {
                cellsToVisit.push(maze.getCell(row,col + 1));
                maze.getCell(row, col + 1).setParent(current);
            }
            // Checks for south neighbor
            if (maze.isValidCell(row + 1, col)) {
                cellsToVisit.push(maze.getCell(row + 1, col));
                maze.getCell(row + 1, col).setParent(current);
            }
            // Checks for west neighbor
            if (maze.isValidCell(row,col - 1)) {
                cellsToVisit.push(maze.getCell(row,col - 1));
                maze.getCell(row, col - 1).setParent(current);
            }
            current.setExplored(true);
            // Moves onto next cell in cellsToVisit
            current = cellsToVisit.pop();

        }
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        Queue<MazeCell> cellsToVisit = new LinkedList<>();
        MazeCell current = maze.getStartCell();
        while (!current.equals(maze.getEndCell())) {
            int row = current.getRow();
            int col = current.getCol();
            // Checks for north neighbor
            if (maze.isValidCell(row - 1, col)) {
                // Adds neighbor to cells to visit and assigns its parent
                cellsToVisit.add(maze.getCell(row - 1,col));
                maze.getCell(row - 1, col).setParent(current);
            }
            // Checks for east neighbor
            if (maze.isValidCell(row, col + 1)) {
                cellsToVisit.add(maze.getCell(row, col + 1));
                maze.getCell(row, col + 1).setParent(current);
            }
            // Checks for south neighbor
            if (maze.isValidCell(row + 1, col)) {
                cellsToVisit.add(maze.getCell(row + 1, col));
                maze.getCell(row + 1, col).setParent(current);
            }
            // Checks for west neighbor
            if (maze.isValidCell(row, col - 1)) {
                cellsToVisit.add(maze.getCell(row, col - 1));
                maze.getCell(row, col - 1).setParent(current);
            }
            current.setExplored(true);
            // Moves onto next cell in cellsToVisit
            current = cellsToVisit.remove();
        }
        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
