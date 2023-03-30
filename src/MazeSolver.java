/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.Stack;

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
            stack.push(cell.getParent());
            cell = cell.getParent();
        }
        while (!stack.empty()) {
            route.add(stack.pop());
        }
        // Should be from start to end cells
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
            if (!cellsToVisit.empty()) {
                current = cellsToVisit.pop();
            }

            int row = current.getRow();
            int col = current.getCol();
            if (maze.isValidCell(row,col - 1)) {
                cellsToVisit.push(maze.getCell(row,col - 1));
            }
            if (maze.isValidCell(row + 1, col)) {
                cellsToVisit.push(maze.getCell(row + 1, col));
            }
            if (maze.isValidCell(row,col + 1)) {
                cellsToVisit.push(maze.getCell(row,col + 1));
            }
            if (maze.isValidCell(row - 1, col)) {
                cellsToVisit.push(maze.getCell(row - 1, col));
            }
            cellsToVisit.peek().setParent(current);
            current.setExplored(true);

        }
        maze.getEndCell().setParent(current);

        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        return null;
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
