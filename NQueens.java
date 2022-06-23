import java.util.Stack;

public class NQueens {

  //finds and prints out all solutions to the n-queens problem
  public static int solve(int n) {
    int numSol = 0;
    Stack<Integer> stack = new Stack<Integer>();

    //pushes first queen to initial column position 0 by default
    stack.push(0);

    /*continues to find solutions until the stack is empty (all solutions have been found,
    and row 1 queen has been incremented off the board)*/
    while(!stack.empty()){
      outerloop:
      for(int col = 0; col <= n; col++){

        //this determines if a temp queen has been pushed onto the stack
        boolean pushed = false;

        /*if temp queen is still on the board and the last queen has not been placed yet,
        see if there is a valid position for temp queen in its row*/
        if(col < n && stack.size() < n){
          pushed = isPushed(stack, col, n);
          //if there is a valid position, exit out of the outer for loop and continue to the next row
          if(pushed)
            break outerloop;
        }

        /*if the temp queen goes off the board OR the last queen has been placed on the board
        but not all solutions have been found yet, backtrack and pop the previous queen off the stack*/
        if(col == n || stack.size() == n){
          /*if the last queen has been placed on the board (indicating current stack is
          a valid solution), print it*/
          if(stack.size() == n){
            numSol++;
            printSolution(stack);
          }
          /*continue to pop queens off stack until stack is either empty
          or until a popped queen can be placed in another valid position in its row*/
          while(pushed == false){
            if(stack.empty())
              break outerloop;
            //pops queen from stack and sees if it can be placed in another position in its current row
            int topQueen = stack.pop() + 1;
            for(int c = topQueen; c < n; c++){
              pushed = isPushed(stack, c, n);
              /*if there is another valid position, exit out of the outer for loop
              and continue to place queens on the next rows*/
              if(pushed)
                break outerloop;
            }
          }//while
        }//if
      }//for
    }//while

    return numSol;

  }//solve()


  /*returns true and pushes temp queen onto stack if current temporary queen's position
  doesn't conflict with a previous queen's column or diagonal paths; otherwise, returns false*/
  private static boolean isPushed(Stack<Integer> s, int tempCol, int n){
    if((s.search(tempCol) == -1) && !crossesDiag(s, tempCol, n)){
      s.push(tempCol);
      return true;
    }
    return false;
  }

  /*returns true if position of current temporary queen conflicts with a diagonal
  path of any previous queen; otherwise, returns false*/
  private static boolean crossesDiag(Stack<Integer> s, int tempCol, int n){
    for(int col = 0; col < n; col ++){
      if(s.search(col) != -1){
        if((tempCol == (col + s.search(col))) || (tempCol == (col - s.search(col))))
          return true;
      }
    }
    return false;
  }

  //this method prints out a solution from the current stack
  //(you should not need to modify this method)
  private static void printSolution(Stack<Integer> s) {
    for (int i = 0; i < s.size(); i ++) {
      for (int j = 0; j < s.size(); j ++) {
        if (j == s.get(i))
          System.out.print("Q ");
        else
          System.out.print("* ");
      }//for
      System.out.println();
    }//for
    System.out.println();
  }//printSolution()

  // ----- the main method -----
  // (you shouldn't need to change this method)
  public static void main(String[] args) {

  int n = 8;

  // pass in parameter n from command line
  if (args.length == 1) {
    n = Integer.parseInt(args[0].trim());
    if (n < 1) {
      System.out.println("Incorrect parameter");
      System.exit(-1);
    }//if
  }//if

  int number = solve(n);
  System.out.println("There are " + number + " solutions to the " + n + "-queens problem.");
 }//main()

}
