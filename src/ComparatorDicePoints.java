import java.util.Comparator;

public class ComparatorDicePoints implements Comparator<Dice> {
    @Override
    public int compare(Dice d1, Dice d2) {
        return Integer.compare(d1.getPoints(), d2.getPoints());
    }
}