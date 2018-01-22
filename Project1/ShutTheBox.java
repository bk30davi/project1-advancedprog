import java.util.Random;
import java.util.ArrayList;
/**
 * Represents a ShutTheBox game object. Do we need instructions on how
 * to play the game?
 *
 * @author Brianna Davis
 * @version 1/29/2018
 */
public class ShutTheBox
{
    // instance variables - replace the example below with your own
    private int[] board;
    private int goalNum;

    /**
     * Constructor for objects of class ShutTheBox
     */
    public ShutTheBox()
    {
        board = new int[] {1,2,3,4,5,6,7,8,9};
    }
    
    public int[] getBoard(){
        return board;
    }
    
    public int getGoalNum(){
        return goalNum;
    }
    
    public void rollDice(){
        Random rand = new Random();
        int dice1 = rand.nextInt(9) + 1;
        int dice2 = rand.nextInt(9) + 1;
        goalNum = dice1 + dice2;
    }
    
    public boolean checkSum(int num1, int num2, int num3, int num4){ //because there could be at most 4 #s
        boolean result = true;
        result = board[num1] != 0 && board[num2] != 0 && board[num3] != 0 && board[num4] != 0; //change parameters
        return num1 + num2 + num3 + num4 == goalNum && result;
    }
    
    public void remove(ArrayList<Integer> nums){
        //need to use checkSum method first
        for (int i = 0; i < nums.size(); i++){
            board[nums.get(i)] = 0;
        }
    }

    public boolean isDone(){ ////REALLY needs editing!!
        // int i = 0;
        // while (board[i] <= 10 && board[i] <= goalNum){//Edit
            // if (board[i] != 0){
                
            // }
            // i++;
        // }
        if (goalNum < 10 && board[goalNum - 1] != 0){
            return false;
        }
        else if (goalNum < 10){
            for (int i = goalNum - 1; i > 0; i--){//This gets repeated, put in separate method? Recursive?
                if (board[i] != 0){
                    int result = goalNum - board[i];
                    if (board[result] != 0){
                        return false;
                    }
                    else{
                        
                    }
                }
            }
        }
        else{
            for( int i = 8; i > 0; i--){
                //pretty much same as what happens in above for loop
            }
        }
        
        return true; //Should the program check for the user if they can not make any more moves?
    }

    public String toString(){
        String result = "Numbers left: ";
        for (int i = 1; i < 10; i++){ //check if this is the most efficient way
            if (i != 0){
                result += board[i] + "  ";
            }
        }
        return result + "\nThe number you are trying to get is " + goalNum; //Take out last part?
    }
}
