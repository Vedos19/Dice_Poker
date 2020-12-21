import java.util.Random;

public class Dice implements Comparable<Dice>{
    int points;

    Dice(){
        Random random = new Random();
        points = random.nextInt(6)+1;
    }
    public int getPoints() {
        return points;
    }

    @Override
    public int compareTo(Dice o) {
        return 0;
    }
    @Override
    public String toString() {
        return points + "";
    }
}
