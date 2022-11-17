import java.util.Scanner;
import java.util.Random;

public class game {
    public static void main(String[] args){
        int[][] board = new int[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                board[i][j] = 0;
            }
        }

        Scanner scanner = new Scanner(System.in);

        //Choose Game Mode
        System.out.println("PvE Mode(1) or PvP Mode(2)?");
        int in=0;
        in = scanner.nextInt();

        printBoard(board);
        //Player VS Player Mode
        if(in==2) {
            int x = -1, y = -1;
            int player = 1;
            //Game Loop
            while (gameWin(board) == 0) {

                //receive coordinate
                String currentPlayer;
                if (player == 1) {
                    currentPlayer = "Player 1 ";
                } else {
                    currentPlayer = "Player 2 ";
                }
                while (validCoordinate(x, y, board) == false) {
                    System.out.println(currentPlayer + "Input y-axis coordinate: ");
                    x = scanner.nextInt();
                    System.out.println(currentPlayer + "Input x-axis coordinate: ");
                    y = scanner.nextInt();
                }
                //update coordinate
                if (player == 1) {
                    board[x][y] = 1;
                } else {
                    board[x][y] = 2;
                }
                //change player
                if (player == 1) {
                    player = 2;
                } else {
                    player = 1;
                }
                printBoard(board);
                System.out.println("Win: " + gameWin(board));
            }

            if (gameWin(board) == 1) {
                System.out.println("Player 1 win!");
            } else {
                System.out.println("Player 2 win!");
            }
        }//AI Mode
        else{
            int cur = 1,x = -1, y = -1;
            //Game Loop
            while(gameWin(board) == 0){
                //Player Turn
                if(cur==1){
                    while (validCoordinate(x, y, board) == false) {
                        System.out.println("Input y-axis coordinate: ");
                        x = scanner.nextInt();
                        System.out.println("Input x-axis coordinate: ");
                        y = scanner.nextInt();
                    }
                    board[x][y] = 1;
                    cur = 2;
                }else{
                    int[] decision = aiDecision(board);
                    int aiX=decision[0], aiY=decision[1];
                    board[aiX][aiY] = 2;
                    cur = 1;
                }
                printBoard(board);
                System.out.println("Win: " + gameWin(board));
            }
            if (gameWin(board) == 1) {
                System.out.println("Player 1 win!");
            } else {
                System.out.println("Player 2 win!");
            }
        }
    }

    public static  int[] aiDecision(int[][] b){
        int[] res = new int[2];
        int x=-1,y=-1;
        //Generate random number until we got two valid numbers
        while(validCoordinate(x,y,b)==false){
            Random random = new Random();
            x = random.nextInt(2 + 1);
            y = random.nextInt(2 + 1);
        }
        res[0]=x;
        res[1]=y;
        return res;
    }

    public static boolean validCoordinate(int x, int y,int[][] b){
        if(x>2||x<0){
            return false;
        }
        if(y>2||y<0){
            return false;
        }
        if(b[x][y]==1 || b[x][y]==2){
            return false;
        }
        return true;
    }

    public static void printBoard(int[][] board){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int gameWin(int[][] board){
        if(board[0][0]==board[1][1] && board[1][1]==board[2][2]){
            return whoWin(board[0][0]);
        }
        if(board[0][2]==board[1][1] && board[1][1]==board[2][0]){
            return whoWin(board[0][2]);
        }
        for(int i=0;i<3;i++){
            if(board[1][i]==board[0][i] && board[0][i]==board[2][i]){
                return whoWin(board[1][i]);
            }
        }
        for(int j=0;j<3;j++){
            if(board[j][1]==board[j][0] && board[j][0]==board[j][2]){
                return whoWin(board[j][1]);
            }
        }
        return 0;
    }

    public static int whoWin(int r){
        if(r==1){
            return 1;
        }else if(r==2){
            return 2;
        }else{
            return 0;
        }
    }
}
