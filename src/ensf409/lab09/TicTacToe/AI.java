package ensf409.lab09.TicTacToe;
import java.util.*;

public class AI {
    private char[][] grid;
    int counter;
    
    public AI() {
        grid = new char[3][3];
        grid[0][0] = '1';
        grid[1][0] = '2';
        grid[2][0] = '3';
        grid[0][1] = '4';
        grid[1][1] = '5';
        grid[2][1] = '6';
        grid[0][2] = '7';
        grid[1][2] = '8';
        grid[2][2] = '9';
        counter = 0;
    }
    public char get_value(char coordinate) {
        if (coordinate == '1')
            return grid[0][0];
        else if (coordinate == '2')
            return grid[1][0];
        else if (coordinate == '3')
            return grid[2][0];
        else if (coordinate == '4')
            return grid[0][1];
        else if (coordinate == '5')
            return grid[1][1];
        else if (coordinate == '6')
            return grid[2][1];
        else if (coordinate == '7')
            return grid[0][2];
        else if (coordinate == '8')
            return grid[1][2];
        else
            return grid[2][2];
    }
    public void set_value(char coordinate, char value) {
        if (coordinate == '1')
            grid[0][0] = value;
        else if (coordinate == '2')
            grid[1][0] = value;
        else if (coordinate == '3')
            grid[2][0] = value;
        else if (coordinate == '4')
            grid[0][1] = value;
        else if (coordinate == '5')
            grid[1][1] = value;
        else if (coordinate == '6')
            grid[2][1] = value;
        else if (coordinate == '7')
            grid[0][2] = value;
        else if (coordinate == '8')
            grid[1][2] = value;
        else
            grid[2][2] = value;
        counter++;
    }
    public char AI_easy(char player) {
        Random generator = new Random();
        while(true) {
            char temp = Integer.toString(generator.nextInt(9) + 1).charAt(0);
            if (get_value(temp) != 'X' && get_value(temp) != 'O') {
                set_value(temp, player);
                return temp;
                //break;
            }
        }
    }
    public char AI_medium(char player) {
        char enemy = 'X';
        boolean played = false;
        if (player == 'X')
            enemy = 'O';
        for (int i = 1; i < 10; i++)
            if (get_value(Integer.toString(i).charAt(0)) != 'X' && get_value(Integer.toString(i).charAt(0)) != 'O' && played == false)
            {
                set_value(Integer.toString(i).charAt(0), enemy);
                if (check_victory() == enemy) {
                    set_value(Integer.toString(i).charAt(0), player);
                    played = true;
                    return Integer.toString(i).charAt(0);
                }
                else
                    set_value(Integer.toString(i).charAt(0), Integer.toString(i).charAt(0));
         
            }
        if (played == false)
           return AI_easy(player);
        return 'E';
    }
    public char AI_hard(char player) {
        boolean played = false;
        for (int i = 1; i < 10; i++)
            if (get_value(Integer.toString(i).charAt(0)) != 'X' && get_value(Integer.toString(i).charAt(0)) != 'O' && played == false) 
            {
                set_value(Integer.toString(i).charAt(0), player);
                if (check_victory() == player) {
                    set_value(Integer.toString(i).charAt(0), player);
                    played = true;
                    return Integer.toString(i).charAt(0);
                }
                else
                    set_value(Integer.toString(i).charAt(0), Integer.toString(i).charAt(0));
            }
        if (played == false)
            return AI_medium(player);
        return 'E';
    }
    public char AI_impossible(char player) {
        char temp, play_spot = '1';
        for (int i = 1; i < 10; i++)
            if (get_value(Integer.toString(i).charAt(0)) != 'X' && get_value(Integer.toString(i).charAt(0)) != 'O') {
                temp = AI_recursive(i, player, player); 
                if (temp == 'w') {
                    set_value(Integer.toString(i).charAt(0), player);
                    return Integer.toString(i).charAt(0);
                }
                else if (temp == 't') {
                    play_spot = Integer.toString(i).charAt(0);
                }
            }
        set_value(play_spot, player);
        return play_spot;
    }
    public char AI_recursive(int play_at, char player, char current_move) {
        char return_value, temp, enemy = 'X';
        if (player == 'X')
            enemy = 'O';
        set_value(Integer.toString(play_at).charAt(0), current_move);
        if (check_victory() == player) {
            set_value(Integer.toString(play_at).charAt(0), Integer.toString(play_at).charAt(0));
            return 'w';
        }
        else if (check_victory() == 't') {
            set_value(Integer.toString(play_at).charAt(0), Integer.toString(play_at).charAt(0));
            return 't';
        }
        else if (check_victory() == enemy) {
            set_value(Integer.toString(play_at).charAt(0), Integer.toString(play_at).charAt(0));
            return 'l';
        }
        if (current_move == player) {
            return_value = 'w';
            for (int i = 1; i < 10; i++)
                if (get_value(Integer.toString(i).charAt(0)) != 'X' && get_value(Integer.toString(i).charAt(0)) != 'O') {
                    temp = AI_recursive(i, player, enemy);
                    if (temp == 't' && return_value != 'l')
                        return_value = 't';
                    else if (temp == 'l')
                        return_value = 'l';
                }
        }
        else {
            return_value = 'l';
            for (int i = 1; i < 10; i++)
                if (get_value(Integer.toString(i).charAt(0)) != 'X' && get_value(Integer.toString(i).charAt(0)) != 'O') {
                    temp = AI_recursive(i, player, player);
                    if (temp == 't' && return_value != 'w')
                        return_value = 't';
                    if (temp == 'w')
                        return_value = 'w';
                }
        }
        set_value(Integer.toString(play_at).charAt(0), Integer.toString(play_at).charAt(0));
        return return_value;
    }
    public char AI_Hint(char player) 
    {
        char enemy = 'X';
        boolean played = false;
        if (player == 'X')
            enemy = 'O';
        for (int i = 0; i < 3; i++) 
        {
            if (grid[i][0] == enemy && grid[i][1] == enemy && grid[i][2] != player && played == false) 
            {
                set_value(Integer.toString(7 + i).charAt(0), player);
                played = true;
                return Integer.toString(7 + i).charAt(0);
            }
            if (grid[i][0] == enemy && grid[i][2] == enemy && grid[i][1] != player && played == false) 
            {
                set_value(Integer.toString(4 + i).charAt(0), player);
                played = true;
                return Integer.toString(4 + i).charAt(0);
            }
            if (grid[i][1] == enemy && grid[i][2] == enemy && grid[i][0] != player && played == false) 
            {
                set_value(Integer.toString(1 + i).charAt(0), player);
                played = true;
                return Integer.toString(1 + i).charAt(0);
            }
            if (grid[0][i] == enemy && grid[1][i] == enemy && grid[2][i] != player && played == false) 
            {
                set_value(Integer.toString(3*i + 3).charAt(0), player);
                played = true;
                return Integer.toString(3*i + 3).charAt(0);
            }
            if (grid[0][i] == enemy && grid[2][i] == enemy && grid[1][i] != player && played == false) 
            {
                set_value(Integer.toString(3*i + 2).charAt(0), player);
                played = true;
                return Integer.toString(3*i + 2).charAt(0);
            }
            if (grid[1][i] == enemy && grid[2][i] == enemy && grid[0][i] != player && played == false) 
            {
                set_value(Integer.toString(3*i + 1).charAt(0), player);
                played = true;
                return Integer.toString(3*i + 1).charAt(0);
            }
        }
        if (grid[0][0] == enemy && grid[1][1] == enemy && grid[2][2] != player && played == false) {
            set_value(Integer.toString(9).charAt(0), player);
            played = true;
            return Integer.toString(9).charAt(0);
        }
        if (grid[0][0] == enemy && grid[2][2] == enemy && grid[1][1] != player && played == false) {
            set_value(Integer.toString(5).charAt(0), player);
            played = true;
            return Integer.toString(5).charAt(0);
        }
        if (grid[1][1] == enemy && grid[2][2] == enemy && grid[0][0] != player && played == false) {
            set_value(Integer.toString(1).charAt(0), player);
            played = true;
            return Integer.toString(1).charAt(0);
        }
        if (grid[0][2] == enemy && grid[1][1] == enemy && grid[2][0] != player && played == false) {
            set_value(Integer.toString(3).charAt(0), player);
            played = true;
            return Integer.toString(3).charAt(0);
        }
        if (grid[0][2] == enemy && grid[2][0] == enemy && grid[1][1] != player && played == false) {
            set_value(Integer.toString(5).charAt(0), player);
            played = true;
            return Integer.toString(5).charAt(0);
        }
        if (grid[1][1] == enemy && grid[2][0] == enemy && grid[1][1] != player && played == false) {
            set_value(Integer.toString(7).charAt(0), player);
            played = true;
            return Integer.toString(7).charAt(0);
        }
        // return types for hint to say hint did not play and player should go
        if (played == false)
        {
        	if (player == 'X')
        	{
        		return 'X';
        	}
        	else
        	{
        		return 'O';
        	}
        }
        return ' ';//cannot be reached
    }
    public char check_victory() {
        for (int i = 0; i < 3; i++) {
            if (grid[0][i] == 'X' && grid[1][i] == 'X' && grid[2][i] == 'X')
                return 'X';
            if (grid[i][0] == 'X' && grid[i][1] == 'X' && grid[i][2] == 'X')
                return 'X';
            if (grid[0][i] == 'O' && grid[1][i] == 'O' && grid[2][i] == 'O')
                return 'O';
            if (grid[i][0] == 'O' && grid[i][1] == 'O' && grid[i][2] == 'O')
                return 'O';
        }
        if (grid[0][0] == 'X' && grid[1][1] == 'X' && grid[2][2] == 'X')
            return 'X';
        if (grid[0][2] == 'X' && grid[1][1] == 'X' && grid[2][0] == 'X')
            return 'X';
        if (grid[0][0] == 'O' && grid[1][1] == 'O' && grid[2][2] == 'O')
            return 'O';
        if (grid[0][2] == 'O' && grid[1][1] == 'O' && grid[2][0] == 'O')
            return 'O';
        boolean tie = true;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (grid[i][j] != 'X' && grid[i][j] != 'O')
                    tie = false;
        if (tie == true)
            return 't';
        return ' ';
    }
    public void display() {
        System.out.println("\n\n\n\n\n\n");
        System.out.println(" " + grid[0][2] + " | " + grid[1][2] + " | " + grid[2][2]);
        System.out.println("-----------          Enter the number of the space you would like to play in");
        System.out.println(" " + grid[0][1] + " | " + grid[1][1] + " | " + grid[2][1] + "           Or enter 0 to have the computer decide for you");
        System.out.println("-----------          Or enter E to exit");
        System.out.println(" " + grid[0][0] + " | " + grid[1][0] + " | " + grid[2][0]);
    }
    /*
    public static void main(String[] args) 
    {
        Main grid = new Main();
        Scanner scanner = new Scanner(System.in);
        int difficulty = 0;
        boolean b = false;
        String s = "";
        System.out.println("Enter the difficulty you would like to play against:");
        System.out.println("  1 - Easy");
        System.out.println("  2 - Medium");
        System.out.println("  3 - Hard");
        System.out.println("  4 - Impossible");
        while(!b) {
            s = scanner.next();
            if (s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4")) {
                difficulty = Integer.parseInt(s);
                b = true;
            }
        }
        while(true) {
            grid.display();
            b = false;
            s = "";
            while(!b)
            {
                s = scanner.next();
                if(s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4") || s.equals("5"))
                    if (grid.get_value(s.charAt(0)) != 'X' && grid.get_value(s.charAt(0)) != 'O')
                        b = true;
                if(s.equals("6") || s.equals("7") || s.equals("8") || s.equals("9"))
                    if (grid.get_value(s.charAt(0)) != 'X' && grid.get_value(s.charAt(0)) != 'O')
                        b = true;
                if (s.equals("e") || s.equals("E") || s.equals("0"))
                    b = true;
            }
            if (s.charAt(0) == 'e' || s.charAt(0) == 'E')
                break;
            else if (s.charAt(0) == '0') {
                if (difficulty == 1)
                    grid.AI_easy('X');
                else if (difficulty == 2)
                    grid.AI_medium('X');
                else if (difficulty == 3)
                    grid.AI_hard('X');
                else
                    grid.AI_impossible('X');
            }
            else
                grid.set_value(s.charAt(0), 'X');
            if (grid.check_victory() == 'X' || grid.check_victory() == 'O' || grid.check_victory() == 't') {
                if (grid.check_victory() == 't') {
                    grid.display();
                    System.out.println("The game is a tie");
                }
                else {
                    grid.display();
                    System.out.println(grid.check_victory() + " wins!");
                    
                }
                break;
            }
            if (difficulty == 1)
                grid.AI_easy('O');
            else if (difficulty == 2)
                grid.AI_medium('O');
            else if (difficulty == 3)
                grid.AI_hard('O');
            else
                grid.AI_impossible('O');
            if (grid.check_victory() == 'X' || grid.check_victory() == 'O' || grid.check_victory() == 't') {
                if (grid.check_victory() == 't') {
                    grid.display();
                    System.out.println("The game is a tie");
                }
                else {
                    grid.display();
                    System.out.println(grid.check_victory() + " wins!");
                }
                break;
            }
        }
    }
    */
}

/*
import java.util.Random;
import java.util.Scanner;


public class AI 
{
    private char[][] grid;
    int counter;
   
    public AI() 
    {
        grid = new char[3][3];
        grid[0][0] = '1';
        grid[1][0] = '2';
        grid[2][0] = '3';
        grid[0][1] = '4';
        grid[1][1] = '5';
        grid[2][1] = '6';
        grid[0][2] = '7';
        grid[1][2] = '8';
        grid[2][2] = '9';
        counter = 0;
    }
    
    public char get_value(char coordinate) 
    {
        if (coordinate == '1')
            return grid[0][0];
        else if (coordinate == '2')
            return grid[1][0];
        else if (coordinate == '3')
            return grid[2][0];
        else if (coordinate == '4')
            return grid[0][1];
        else if (coordinate == '5')
            return grid[1][1];
        else if (coordinate == '6')
            return grid[2][1];
        else if (coordinate == '7')
            return grid[0][2];
        else if (coordinate == '8')
            return grid[1][2];
        else
            return grid[2][2];
    }
    
    public void set_value(char coordinate, char value) 
    {
        if (coordinate == '1')
            grid[0][0] = value;
        else if (coordinate == '2')
            grid[1][0] = value;
        else if (coordinate == '3')
            grid[2][0] = value;
        else if (coordinate == '4')
            grid[0][1] = value;
        else if (coordinate == '5')
            grid[1][1] = value;
        else if (coordinate == '6')
            grid[2][1] = value;
        else if (coordinate == '7')
            grid[0][2] = value;
        else if (coordinate == '8')
            grid[1][2] = value;
        else
            grid[2][2] = value;
        counter++;
    }
    
    public char AI_easy(char player) 
    {
        Random generator = new Random();
        while(true) 
        {
            char temp = Integer.toString(generator.nextInt(9) + 1).charAt(0);
            if (get_value(temp) != 'X' && get_value(temp) != 'O')
            {
                set_value(temp, player);
                return temp;
                //break;
            }
        }
    }
    
    public char AI_medium(char player) 
    {
        char enemy = 'X';
        boolean played = false;
        if (player == 'X')
            enemy = 'O';
        for (int i = 0; i < 3; i++) 
        {
            if (grid[i][0] == enemy && grid[i][1] == enemy && grid[i][2] != player && played == false) 
            {
                set_value(Integer.toString(7 + i).charAt(0), player);
                played = true;
                return Integer.toString(7 + i).charAt(0);
            }
            if (grid[i][0] == enemy && grid[i][2] == enemy && grid[i][1] != player && played == false) 
            {
                set_value(Integer.toString(4 + i).charAt(0), player);
                played = true;
                return Integer.toString(4 + i).charAt(0);
            }
            if (grid[i][1] == enemy && grid[i][2] == enemy && grid[i][0] != player && played == false) 
            {
                set_value(Integer.toString(1 + i).charAt(0), player);
                played = true;
                return Integer.toString(1 + i).charAt(0);
            }
            if (grid[0][i] == enemy && grid[1][i] == enemy && grid[2][i] != player && played == false) 
            {
                set_value(Integer.toString(3*i + 3).charAt(0), player);
                played = true;
                return Integer.toString(3*i + 3).charAt(0);
            }
            if (grid[0][i] == enemy && grid[2][i] == enemy && grid[1][i] != player && played == false) 
            {
                set_value(Integer.toString(3*i + 2).charAt(0), player);
                played = true;
                return Integer.toString(3*i + 2).charAt(0);
            }
            if (grid[1][i] == enemy && grid[2][i] == enemy && grid[0][i] != player && played == false) 
            {
                set_value(Integer.toString(3*i + 1).charAt(0), player);
                played = true;
                return Integer.toString(3*i + 1).charAt(0);
            }
        }
        if (grid[0][0] == enemy && grid[1][1] == enemy && grid[2][2] != player && played == false) {
            set_value(Integer.toString(9).charAt(0), player);
            played = true;
            return Integer.toString(9).charAt(0);
        }
        if (grid[0][0] == enemy && grid[2][2] == enemy && grid[1][1] != player && played == false) {
            set_value(Integer.toString(5).charAt(0), player);
            played = true;
            return Integer.toString(5).charAt(0);
        }
        if (grid[1][1] == enemy && grid[2][2] == enemy && grid[0][0] != player && played == false) {
            set_value(Integer.toString(1).charAt(0), player);
            played = true;
            return Integer.toString(1).charAt(0);
        }
        if (grid[0][2] == enemy && grid[1][1] == enemy && grid[2][0] != player && played == false) {
            set_value(Integer.toString(3).charAt(0), player);
            played = true;
            return Integer.toString(3).charAt(0);
        }
        if (grid[0][2] == enemy && grid[2][0] == enemy && grid[1][1] != player && played == false) {
            set_value(Integer.toString(5).charAt(0), player);
            played = true;
            return Integer.toString(5).charAt(0);
        }
        if (grid[1][1] == enemy && grid[2][0] == enemy && grid[1][1] != player && played == false) {
            set_value(Integer.toString(7).charAt(0), player);
            played = true;
            return Integer.toString(7).charAt(0);
        }
        if (played == false)
           return AI_easy(player);
        return ' ';//cannot be reached
    }
    
    public char AI_Hint(char player) 
    {
        char enemy = 'X';
        boolean played = false;
        if (player == 'X')
            enemy = 'O';
        for (int i = 0; i < 3; i++) 
        {
            if (grid[i][0] == enemy && grid[i][1] == enemy && grid[i][2] != player && played == false) 
            {
                set_value(Integer.toString(7 + i).charAt(0), player);
                played = true;
                return Integer.toString(7 + i).charAt(0);
            }
            if (grid[i][0] == enemy && grid[i][2] == enemy && grid[i][1] != player && played == false) 
            {
                set_value(Integer.toString(4 + i).charAt(0), player);
                played = true;
                return Integer.toString(4 + i).charAt(0);
            }
            if (grid[i][1] == enemy && grid[i][2] == enemy && grid[i][0] != player && played == false) 
            {
                set_value(Integer.toString(1 + i).charAt(0), player);
                played = true;
                return Integer.toString(1 + i).charAt(0);
            }
            if (grid[0][i] == enemy && grid[1][i] == enemy && grid[2][i] != player && played == false) 
            {
                set_value(Integer.toString(3*i + 3).charAt(0), player);
                played = true;
                return Integer.toString(3*i + 3).charAt(0);
            }
            if (grid[0][i] == enemy && grid[2][i] == enemy && grid[1][i] != player && played == false) 
            {
                set_value(Integer.toString(3*i + 2).charAt(0), player);
                played = true;
                return Integer.toString(3*i + 2).charAt(0);
            }
            if (grid[1][i] == enemy && grid[2][i] == enemy && grid[0][i] != player && played == false) 
            {
                set_value(Integer.toString(3*i + 1).charAt(0), player);
                played = true;
                return Integer.toString(3*i + 1).charAt(0);
            }
        }
        if (grid[0][0] == enemy && grid[1][1] == enemy && grid[2][2] != player && played == false) {
            set_value(Integer.toString(9).charAt(0), player);
            played = true;
            return Integer.toString(9).charAt(0);
        }
        if (grid[0][0] == enemy && grid[2][2] == enemy && grid[1][1] != player && played == false) {
            set_value(Integer.toString(5).charAt(0), player);
            played = true;
            return Integer.toString(5).charAt(0);
        }
        if (grid[1][1] == enemy && grid[2][2] == enemy && grid[0][0] != player && played == false) {
            set_value(Integer.toString(1).charAt(0), player);
            played = true;
            return Integer.toString(1).charAt(0);
        }
        if (grid[0][2] == enemy && grid[1][1] == enemy && grid[2][0] != player && played == false) {
            set_value(Integer.toString(3).charAt(0), player);
            played = true;
            return Integer.toString(3).charAt(0);
        }
        if (grid[0][2] == enemy && grid[2][0] == enemy && grid[1][1] != player && played == false) {
            set_value(Integer.toString(5).charAt(0), player);
            played = true;
            return Integer.toString(5).charAt(0);
        }
        if (grid[1][1] == enemy && grid[2][0] == enemy && grid[1][1] != player && played == false) {
            set_value(Integer.toString(7).charAt(0), player);
            played = true;
            return Integer.toString(7).charAt(0);
        }
        // return types for hint to say hint did not play and player should go
        if (played == false)
        {
        	if (player == 'X')
        	{
        		return 'X';
        	}
        	else
        	{
        		return 'O';
        	}
        }
        return ' ';//cannot be reached
    }
    
    public char AI_hard(char player) 
    {
        char enemy = 'X';
        boolean played = false;
        if (player == 'X')
            enemy = 'O';
        for (int i = 0; i < 3; i++) 
        {
            if (grid[i][0] == player && grid[i][1] == player && grid[i][2] != enemy && played == false) {
                set_value(Integer.toString(7 + i).charAt(0), player);
                played = true;
                return Integer.toString(7 + i).charAt(0);
            }
            if (grid[i][0] == player && grid[i][2] == player && grid[i][1] != enemy && played == false) {
                set_value(Integer.toString(4 + i).charAt(0), player);
                played = true;
                return Integer.toString(4 + i).charAt(0);
            }
            if (grid[i][1] == player && grid[i][2] == player && grid[i][0] != enemy && played == false) {
                set_value(Integer.toString(1 + i).charAt(0), player);
                played = true;
                return Integer.toString(1 + i).charAt(0);
            }
            if (grid[0][i] == player && grid[1][i] == player && grid[2][i] != enemy && played == false) {
                set_value(Integer.toString(3*i + 3).charAt(0), player);
                played = true;
                return Integer.toString(3*i + 3).charAt(0);
            }
            if (grid[0][i] == player && grid[2][i] == player && grid[1][i] != enemy && played == false) {
                set_value(Integer.toString(3*i + 2).charAt(0), player);
                played = true;
                return Integer.toString(3*i + 2).charAt(0);
            }
            if (grid[1][i] == player && grid[2][i] == player && grid[0][i] != enemy && played == false) {
                set_value(Integer.toString(3*i + 1).charAt(0), player);
                played = true;
                return Integer.toString(3*i + 1).charAt(0);
            }   
        }
        if (grid[0][0] == player && grid[1][1] == player && grid[2][2] != enemy && played == false) {
            set_value(Integer.toString(9).charAt(0), player);
            played = true;
            return Integer.toString(9).charAt(0);
        }
        if (grid[0][0] == player && grid[2][2] == player && grid[1][1] != enemy && played == false) {
            set_value(Integer.toString(5).charAt(0), player);
            played = true;
            return Integer.toString(5).charAt(0);
        }
        if (grid[1][1] == player && grid[2][2] == player && grid[0][0] != enemy && played == false) {
            set_value(Integer.toString(1).charAt(0), player);
            played = true;
            return Integer.toString(1).charAt(0);
        }
        if (grid[0][2] == player && grid[1][1] == player && grid[2][0] != enemy && played == false) {
            set_value(Integer.toString(3).charAt(0), player);
            played = true;
            return Integer.toString(3).charAt(0);
        }
        if (grid[0][2] == player && grid[2][0] == player && grid[1][1] != enemy && played == false) {
            set_value(Integer.toString(5).charAt(0), player);
            played = true;
            return Integer.toString(5).charAt(0);
        }
        if (grid[1][1] == player && grid[2][0] == player && grid[1][1] != enemy && played == false) {
            set_value(Integer.toString(7).charAt(0), player);
            played = true;
            return Integer.toString(7).charAt(0);
        }
        if (played == false)
           return AI_medium(player);
        return ' ';//should not be reached
    }
    
    public char AI_impossible(char player) 
    {
        boolean played = false;
        for (int i = 1; i < 10; i++) {
            if (get_value(Integer.toString(i).charAt(0)) != 'X' && get_value(Integer.toString(i).charAt(0)) != 'O' && played == false)
                if (AI_recursive(i, player, player) == 'y') 
                {
                    set_value(Integer.toString(i).charAt(0), player);
                    played = true;
                    return Integer.toString(i).charAt(0);
                }
        }
        return ' ';//should not run
    }
   
    public char AI_recursive(int play_at, char player, char current_move) 
    {
        char temp, enemy = 'X';
        if (player == 'X')
            enemy = 'O';
        set_value(Integer.toString(play_at).charAt(0), current_move);
        if (check_victory() == player || check_victory() == 't') {
            set_value(Integer.toString(play_at).charAt(0), Integer.toString(play_at).charAt(0));
            return 'y';
        }
        else if (check_victory() == enemy) {
            set_value(Integer.toString(play_at).charAt(0), Integer.toString(play_at).charAt(0));
            return 'n';
        }
        if (current_move == player) {
            temp = 'y';
            for (int i = 1; i < 10; i++)
                if (get_value(Integer.toString(i).charAt(0)) != 'X' && get_value(Integer.toString(i).charAt(0)) != 'O')
                    if (AI_recursive(i, player, enemy) == 'n')
                        temp = 'n';
        }
        else {
            temp = 'n';
            for (int i = 1; i < 10; i++)
                if (get_value(Integer.toString(i).charAt(0)) != 'X' && get_value(Integer.toString(i).charAt(0)) != 'O')
                    if (AI_recursive(i, player, player) == 'y')
                        temp = 'y';
        }
        set_value(Integer.toString(play_at).charAt(0), Integer.toString(play_at).charAt(0));
        return temp;
    }
    
    public char check_victory() 
    {
        for (int i = 0; i < 3; i++) 
        {
            if (grid[0][i] == 'X' && grid[1][i] == 'X' && grid[2][i] == 'X')
                return 'X';
            if (grid[i][0] == 'X' && grid[i][1] == 'X' && grid[i][2] == 'X')
                return 'X';
            if (grid[0][i] == 'O' && grid[1][i] == 'O' && grid[2][i] == 'O')
                return 'O';
            if (grid[i][0] == 'O' && grid[i][1] == 'O' && grid[i][2] == 'O')
                return 'O';
        }
        if (grid[0][0] == 'X' && grid[1][1] == 'X' && grid[2][2] == 'X')
            return 'X';
        if (grid[0][2] == 'X' && grid[1][1] == 'X' && grid[2][0] == 'X')
            return 'X';
        if (grid[0][0] == 'O' && grid[1][1] == 'O' && grid[2][2] == 'O')
            return 'O';
        if (grid[0][2] == 'O' && grid[1][1] == 'O' && grid[2][0] == 'O')
            return 'O';
        boolean tie = true;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (grid[i][j] != 'X' && grid[i][j] != 'O')
                    tie = false;
        if (tie == true)
            return 't';
        return ' ';
    }    
}
*/