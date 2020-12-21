import java.util.*;

public class Dice_Pack extends Dice{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    List<Dice> DiceList = new ArrayList<>();
    Random random = new Random();

    Dice_Pack(){
        Dice dice1 = new Dice(); DiceList.add(dice1);
        Dice dice2 = new Dice(); DiceList.add(dice2);
        Dice dice3 = new Dice(); DiceList.add(dice3);
        Dice dice4 = new Dice(); DiceList.add(dice4);
        Dice dice5 = new Dice(); DiceList.add(dice5);
    }
    public void sort(Dice_Pack pack){
        Collections.sort(DiceList, new ComparatorDicePoints());
    }
    public void reroll(Dice_Pack pack){
        pack.sort(pack);
        int  wybór;
        Scanner scanner = new Scanner(System.in);
        for(int i=0;i<5;i++) {
            System.out.print("Do you want to roll dice " + (i+1) + ". |" + DiceList.get(i) + "|?\n1. Yes\n2. No\nYour choice: ");
            wybór = scanner.nextInt();
            if(wybór==1)
                pack.DiceList.get(i).points = random.nextInt(6)+1;
            else if(wybór==2) System.out.print("");
            else{ System.out.println("Wrong choice!"); i--; }
        }
        System.out.println("\nPlayer's reroll");
        pack.sort(pack);
    }
    public void reroll_bot(Dice_Pack pack){
        pack.sort(pack);
        System.out.println("Opponent's reroll");
        for(int i=0;i<5;i++) {
            if(pack.DiceList.get(i).points < 4)
                pack.DiceList.get(i).points = random.nextInt(6)+1;
        }
        pack.sort(pack);
    }
    public void dice_reset(Dice_Pack z1){
        for(int i=0;i<5;i++)
            z1.DiceList.get(i).points = random.nextInt(6)+1;
    }
    public int check(Dice_Pack z1){
        z1.sort(z1);
        int points=0, counter=0, multiplier=0, pair=0, trio=0;

        for(int i=1;i<=6;i++){
            for (int j = 0; j < 5; j++){
                if(z1.DiceList.get(j).getPoints()==i){
                    counter++;
                }
                multiplier = i;
            }

            if(counter==5){
                points+=counter*multiplier+50;
                System.out.println("Five of a kind (" + multiplier + ") | +" + ((counter*multiplier)+50) + " points!");
            }
            else if(counter==4){
                points+=counter*multiplier+20;
                System.out.println("Four of a kind (" + multiplier + ") | +" + ((counter*multiplier)+20) + " points!");
            }
            else if(counter==3){
                points+=counter*multiplier;
                System.out.println("Three of a kind (" + multiplier + ") | +" + counter*multiplier + " points!");
                trio++;
            }
            else if(counter==2){
                points+=counter*multiplier;
                System.out.println("Pair (" + multiplier + ") | +" + counter*multiplier + " points!");
                pair++;
            }
            counter = 0;
        }

        if(pair==1 && trio==1) {
            points += 10;
            System.out.println("Full house | +10 points!");
        }

        counter=0;
        for(int k=0;k<5;k++){
            if(z1.DiceList.get(k).getPoints()!=k+1){
                continue;
            }
            counter++;
        }
        if(counter==5){
            System.out.println("Five High Straight | + 15 points!");
            points+=15;
        }

        counter=0;
        for(int k=0;k<5;k++){
            if(z1.DiceList.get(k).getPoints()!=k+2){
                continue;
            }
            counter++;
        }
        if(counter==5){
            System.out.println("Six High Straight | + 20 points!");
            points+=20;
        }

        counter=0;
        for(int i=0;i<5;i++){
            if(z1.DiceList.get(i).getPoints()%2==1){
                counter++;
            }
        }
        if(counter==5){
            System.out.println("Odds | + 15 points!");
            points+=15;
        }

        counter=0;
        for(int i=0;i<5;i++){
            if(z1.DiceList.get(i).getPoints()%2==0){
                counter++;
            }
        }
        if(counter==5){
            System.out.println("Evens | + 24 points!");
            points+=24;
        }
        if(points==0)
            System.out.println("0 points!");
        return points;
    }
    public void Dice_instruction_ENG(){
        System.out.println(ANSI_GREEN + "Rules of Dice Poker:" + ANSI_RESET + "\nPlayers roll five 6-sided dices, trying to roll the best poker hand they can.\nEach player has the right to " +
                "reroll once in which he may choose dices he want's to change.\nThe player who collects the most points at the end of two out of three rounds wins!\n\nRating:\nOne pair - Two dices " +
                "showing the same value: number of total dice dots\nThree of a kind - Three dices showing the same value: number of total dice dots\nFour of a kind - Four dices showing the same value: " +
                "number of total dice dots + 20 points\nFive of a kind - Five dices showing the same value: number of total dice dots + 50pkt\nFive High Straight - Dices showing values from 1 through 5, inclusive: 15 points\n" +
                "Six High Straight - Dices showing values from 2 through 6, inclusive: 20 points\nFull House - Pair of one value and Three-of-a-Kind of another: " +
                "number of total dice 'spots' + 10 points\nOdds - All dices have an odd number of dots: number of total dice dots + 15pkt\nEvens - all dices have an even number of dots: number of total dice dots + 24 points\n");
    }
    public void Dice_instruction_PL(){
        System.out.println(ANSI_GREEN + "Zasady gry w kości:" + ANSI_RESET + "\nGracze rzucają pięcioma kośćmi o 6 ściankach.\nKażdy gracz ma prawo do przerzutu, w którym może wybrać " +
                "kości do przerzucenia wyniku.\nWygrywa gracz, który zbierze największą ilość punktów pod koniec dwóch z trzech tur!\n\nPunktacja:\nPara - Dwie identyczne kości: liczba oczek w parze\n" +
                "Trójka - Trzy identyczne kości: liczba oczek w trójce\nKareta - Cztery identyczne kości: liczba oczek w karecie + 20pkt\nGenerał - Pięć identycznych kości: liczba oczek w generale + 50pkt" +
                "\nMały strit - Kombinacja 1, 2, 3, 4, 5: + 15pkt\nDuży strit - Kombinacja 2, 3, 4, 5, 6: + 20pkt\nFull - 3 identyczne i 2 identyczne kości: + 10pkt\nNieparzyste - wszystkie kości " +
                "posiadają nieparzystą ilość oczek: + 15pkt\nParzyste - wszystkie kości posiadają parzystą ilość oczek: + 24pkt\n\n");
    }
    @Override
    public String toString() {
        System.out.print("|");
        for(Dice k : DiceList)
            System.out.print(k + "|");
        System.out.print("\n");
        return "";
    }
}
