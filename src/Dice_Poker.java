import java.util.Scanner;

public class Dice_Poker extends Dice_Pack{
    public static void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    public static void Play_Dice_Poker(){
        int player1_points=0, player2_points=0, player1_score=0, player2_score=0, bet;
        Dice_Pack pack1 = new Dice_Pack();
        Dice_Pack pack2 = new Dice_Pack();
        pack1.Dice_instruction_ENG();
        promptEnterKey();

        do{
            pack1.dice_reset(pack1);
            pack2.dice_reset(pack2);
            pack1.sort(pack1);
            pack2.sort(pack2);
            System.out.println("Player's dice roll:");
            System.out.println(pack1);
            System.out.println("Opponent's dice roll:");
            System.out.println(pack2);
            //second round
            pack1.reroll(pack1);
            System.out.println(pack1);
            pack2.reroll_bot(pack2);
            System.out.println(pack2);
            System.out.println("Player's points: ");
            player1_points = pack1.check(pack1);
            System.out.println("\nOpponent's points: ");
            player2_points = pack2.check(pack2);
            //round win
            if(player1_points > player2_points){
                player1_score+=1;
                System.out.println(ANSI_GREEN + "\nPlayer wins a round!" + ANSI_RESET);
            }
            else if(player2_points > player1_points){
                player2_score+=1;
                System.out.println(ANSI_RED + "\nOpponent wins a round!" + ANSI_RESET);
            }
            else
                System.out.println("Draw!");
            System.out.println("Player (" + player1_score + ") vs Opponent (" + player2_score + ")\n");
            promptEnterKey();
        }
        while(player1_score<2 && player2_score<2);
    }
}
