import java.io.*;
import java.util.Scanner;
import java.util.ArrayDeque;

//Recursive class to represent a position in a path
class Position{
	public int i;     //row
	public int j;     //column
	public char val;  //1, 0, or 'X'

	// reference to the previous position (parent) that leads to this position on a path
	Position parent;

	Position(int x, int y, char v){
		i=x; j = y; val=v;
	}

	Position(int x, int y, char v, Position p){
		i=x; j = y; val=v;
		parent=p;
	}

}


public class PathFinder {

	public static void main(String[] args) throws IOException {
		if(args.length<1){
			System.err.println("***Usage: java PathFinder maze_file");
			System.exit(-1);
		}

		char [][] maze;
		maze = readMaze(args[0]);
		printMaze(maze);
		Position [] path = stackSearch(maze);
		System.out.println("stackSearch Solution:");
		printPath(path);
		printMaze(maze);

		char [][] maze2 = readMaze(args[0]);
		path = queueSearch(maze2);
		System.out.println("queueSearch Solution:");
		printPath(path);
		printMaze(maze2);
	}


	public static Position [] stackSearch(char [] [] maze){
		/*stack path finding algorithm manages search list,
		marks path in the maze, and returns array of Position
		objects coressponding to path, or null if no path found*/

		//stack for positions yet to explore
		ArrayDeque<Position> stack = new ArrayDeque<Position>();

		char[][] mazeCopy = new char[maze.length][maze.length];

		//copies the maze
		for(int r = 0; r < maze.length; r++){
			for(int c = 0; c < maze[r].length; c++){
				mazeCopy[r][c] = maze[r][c];
			}
		}

		//add the entrance position, (0,0) to stack search list
		if(mazeCopy[0][0] == '0')
			stack.push(new Position(0, 0, '0', null));

		// while the list is not empty do...
		while(stack.size() > 0){
			//remove next position from stack search list
			Position current = stack.pop();

			//if it's the exit position [n-1][n-1], then a path is found --> construct and return the path
			if(current.i == maze.length - 1 && current.j == maze.length - 1){
				//temporary stack list to store Positions in path
				ArrayDeque<Position> path = new ArrayDeque<Position>();
				int count = 0;

				//counts the number of Positions in the path so an array of that fixed size can be made
				while(current != null){
					current.val = 'X';
					//maze position in path is updated/marked as 'X'
					maze[current.i][current.j] = 'X';
					count++;
					path.push(current);
					//move on to the next Position behind it
					current = current.parent;
				}
				/*pops Positions from temp stack list called "path" into an array
				of Positions in the correct path order*/
				Position[] p = new Position[count];
				int ind = 0;
				while(ind < count){
					p[ind] = path.pop();
					ind++;
				}
				return p;
			//else: look for valid neighboring spots
			}else{
				//mark position as visted in maze copy
				mazeCopy[current.i][current.j] = 'X';

				//add all valid up, down, left, or right neighbor positions to the search list
				int[] neighbors = {current.j - 1, current.j + 1, current.i - 1, current.i + 1};
				for(int i = 0; i < neighbors.length; i++){
					if(neighbors[i] >= 0 && neighbors[i] < maze.length){
						if((i == 2 || i == 3) && (mazeCopy[neighbors[i]][current.j] != 'X' && mazeCopy[neighbors[i]][current.j] == '0'))
							stack.push(new Position(neighbors[i], current.j, '0', current));
						if((i == 0 || i == 1) && (mazeCopy[current.i][neighbors[i]] != 'X' && mazeCopy[current.i][neighbors[i]] == '0'))
							stack.push(new Position(current.i, neighbors[i], '0', current));
					}
				}//for
			}//if-else
		}//while

		//if the list is empty, and the method still hasn't returned anything, there is no path
		return null;
	}


	public static Position [] queueSearch(char [] [] maze){
		/*queue path finding algorithm manages search list,
		marks path in the maze, and returns array of Position
		objects coressponding to path, or null if no path found*/

		//queue for positions yet to explore
		ArrayDeque<Position> queue = new ArrayDeque<Position>();

		char[][] mazeCopy = new char[maze.length][maze.length];

		//copies the maze
		for(int r = 0; r < maze.length; r++){
			for(int c = 0; c < maze[r].length; c++){
				mazeCopy[r][c] = maze[r][c];
			}
		}

		//add the entrance position, (0,0) to queue search list
		if(mazeCopy[0][0] == '0')
			queue.add(new Position(0, 0, '0', null));

		// while the list is not empty do...
		while(queue.size() > 0){
			//remove next position from queue search list
			Position current = queue.remove();

			//if it's the exit position [n-1][n-1], then a path is found --> construct and return the path
			if(current.i == maze.length - 1 && current.j == maze.length - 1){
				//temporary queue list to store Positions in path
				ArrayDeque<Position> path = new ArrayDeque<Position>();
				int count = 0;

				//counts the number of Positions in the path so an array of that fixed size can be made
				while(current != null){
					current.val = 'X';
					//maze position in path is updated/marked as 'X'
					maze[current.i][current.j] = 'X';
					count++;
					path.add(current);
					//move on to the next Position behind it
					current = current.parent;
				}
				/*removes Positions from temp queue list called "path" into an array
				of Positions in the correct path order*/
				Position[] p = new Position[count];
				int ind = 0;
				while(ind < count){
					p[ind] = path.removeLast();
					ind++;
				}
				return p;
			//else: look for valid neighboring spots
			}else{
				//mark position as visted in maze copy
				mazeCopy[current.i][current.j] = 'X';

				//add all valid up, down, left, or right neighbor positions to the search list
				int[] neighbors = {current.j - 1, current.j + 1, current.i - 1, current.i + 1};
				for(int i = 0; i < neighbors.length; i++){
					if(neighbors[i] >= 0 && neighbors[i] < maze.length){
						if((i == 2 || i == 3) && (mazeCopy[neighbors[i]][current.j] != 'X' && mazeCopy[neighbors[i]][current.j] == '0'))
							queue.add(new Position(neighbors[i], current.j, '0', current));
						if((i == 0 || i == 1) && (mazeCopy[current.i][neighbors[i]] != 'X' && mazeCopy[current.i][neighbors[i]] == '0'))
							queue.add(new Position(current.i, neighbors[i], '0', current));
					}
				}//for
			}//if-else
		}//while

		//if the list is empty, and the method still hasn't returned anything, there is no path
		return null;
	}

	public static void printPath(Position [] path){
		//prints the path to the stdout

		//if no path was found
		if(path == null){
			System.out.println("No valid path :(");
		//if a path was found
		}else{
			String x = "Path: (";
			for(int i = 0; i < path.length; i++){
				if(i == path.length - 1){
					x += "[" + path[i].i + "][" + path[i].j + "])";
				}else{
					x += "[" + path[i].i + "][" + path[i].j + "], ";
				}
			}
			System.out.println(x);
		}
	}

	/**
	 * Reads maze file in format:
	 * N  -- size of maze
	 * 0 1 0 1 0 1 -- space-separated
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static char [][] readMaze(String filename) throws IOException{
		char [][] maze;
		Scanner scanner;
		try{
			scanner = new Scanner(new FileInputStream(filename));
		}
		catch(IOException ex){
			System.err.println("*** Invalid filename: " + filename);
			return null;
		}

		int N = scanner.nextInt();
		scanner.nextLine();
		maze = new char[N][N];
		int i=0;
		while(i < N && scanner.hasNext()){
			String line =  scanner.nextLine();
			String [] tokens = line.split("\\s+");
			int j = 0;
			for (; j< tokens.length; j++){
				maze[i][j] = tokens[j].charAt(0);
			}
			if(j!=N){
				System.err.println("*** Invalid line: " + i + " has wrong # columns: " + j);
				return null;
			}
			i++;
		}
		if(i!=N){
			System.err.println("*** Invalid file: has wrong number of rows: " + i);
			return null;
		}
		return maze;
	}

	public static void printMaze(char[][] maze){

		if(maze==null || maze[0] == null){
			System.err.println("*** Invalid maze array");
			return;
		}

		for(int i=0; i< maze.length; i++){
			for(int j = 0; j< maze[0].length; j++){
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println();
	}

}
