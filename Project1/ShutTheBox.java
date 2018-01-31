package Project1;

import java.util.Scanner;
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
    private boolean stop;
    static private Random rand;

    /**
     * Constructor for objects of class ShutTheBox. Creates the starting board by making an array of ints from 1 to 9, sets stop to false,
     * creates a Random object, and calls the method runSimulation.
     */
    public ShutTheBox()
    {
        board = new int[] {1,2,3,4,5,6,7,8,9};
        stop = false;
        rand = new Random();
        runSimulation();
    }

    /**
     * Provides interactions between the ShutTheBox object and the user inputs. Utilizes the ShutTheBox methods to simulate the game ShutTheBox
     * 
     */
    public void runSimulation(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Shut the Box");
        System.out.print(toString());
        rollDice();
        System.out.println("\nYour dice roll is " + getGoalNum());
        do{ //while the game has not ended
            boolean stop = false;
            setStop(false);
            System.out.println("Type in the numbers that you would like to flip");
            System.out.print("For example, 1 5 6 for a roll of 12 => ");
            String input = sc.nextLine(); //doesnt read in first number of line
            String[] numStrings = input.split(" ");
            ArrayList<Integer> nums = new ArrayList<Integer>();
            for (int i = 0; i < numStrings.length; i++){
                int number = Integer.parseInt(numStrings[i]);
                if (number > 9 || number < 1){
                    System.out.println("Invalid Input. Try again");
                    stop = true; 
                }
                for (int j = i + 1; j < numStrings.length; j++){
                    if (number == Integer.parseInt(numStrings[j]) && stop == false){
                        System.out.println("Invalid Input. Try again");
                        stop = true;
                    }
                }
                if ((number <= 9 || number >= 1) && stop == false){
                    nums.add(number);
                }
            }

            if (stop == false && getStop() == false){
                remove(nums);
                if (getStop() == false){
                    rollDice();
                }
            }
            System.out.println();
            int sum = 0;
            for (int i = 0; i < board.length; i++){
                sum += board[i];
            }
            if (sum != 0){
                System.out.print(toString());
                System.out.println("\nYour dice roll is " + getGoalNum());
            }
        } while (isDone() == false);
        int sum = 0;
        for (int i = 0; i < getBoard().length; i++){
            int[] board = getBoard();
            sum += board[i];
        }
        if (sum == 0){
            System.out.println("You have shut the box!\nYou scored 0 points.");
        }
        else{
            System.out.println("You can not flip anymore numbers\nYou scored " + sum + " points.");
        }
    }

    public int[] getBoard(){
        return board;
    }

    public int getGoalNum(){
        return goalNum;
    }

    public boolean getStop(){
        return stop;
    }

    public void setStop(boolean stop){
        this.stop = stop;
    }

    public void rollDice(){
        int dice1 = rand.nextInt(6) + 1;
        int dice2 = rand.nextInt(6) + 1;
        goalNum = dice1 + dice2;
    }

    public boolean checkSum(ArrayList<Integer> array){ //because there could be at most 4 #s
        boolean result = true;
        int sum = 0;
        for(int i = 0; i < array.size(); i++){
            int number = array.get(i);
            result = result && board[number - 1] == number; //Makes sure all nums being added are in board
            sum += number; //Makes sure the sum equals the goalNum
        }
        if (sum != goalNum){
            System.out.println("Invalid Input. Try again");
            stop = true;
        }
        if (result == false){
            System.out.println("Invalid Input. Try again");
            stop = true;
        }
        return sum == goalNum && result; //returns false if either the sum is incorrect or the num isnt in the board
    }

    /**
     * Checks that the numbers entered add up to the correct number and that the numbers are available to be used. If there
     * is an issue with the numbers, an error message is printed and the numbers are not used.
     * The remove method then sets the indexs of these numbers in the array to 0 to show that they can no longer be used.
     * 
     * @param nums The ArrayList of Integer(s) that contains the number or numbers inputed to create the sum
     */
    public void remove(ArrayList<Integer> nums){
        if (checkSum(nums) == true){
            for (int i = 0; i < nums.size(); i++){
                board[nums.get(i) - 1] = 0;
            }
        }
    }

    public boolean isDone(){
        if (goalNum < 10 && board[goalNum - 1] != 0){ //if the goalNum is in the board
            return false;
        }
        else if (goalNum < 10){ //if the goalNum is less than 10 then start recIsDone
            return recIsDone(goalNum,goalNum-1);
        }
        else{ //if the goalNum is 10, 11, or 12
            return recIsDone(goalNum, 8);
        }
    }

    public boolean recIsDone(int goalNum, int indexStart){
        boolean isDone = true;
        for (int i = indexStart; i > 0; i--){
            int result = goalNum;
            if (board[i] != 0){
                result = goalNum - board[i];
                if (result <= 0){
                    return true;
                }
                else if (board[result - 1] != 0 && board[result - 1] != board[i] && result < board[i]){
                    return false;
                }
                //else if (result == 2 && board[1] == 0){
                    //return true;
                //}
                else if (i == 0){
                    return true;
                }
                else{
                    isDone = recIsDone(result,result-1);
                }
            }
            //if (isDone == false){
                //return false;
            //}
        }

        return isDone;
    }

    public String toString(){
        String result = "Remaining Numbers: ";
        for (int i = 0; i < 9; i++){ 
            if (board[i] != 0){
                result += board[i] + " ";
            }
        }
        return result;
    }
}
