package pckg;

import java.util.ArrayList;
import java.util.Random;

public class AirportManager {
    private static final ArrayList<Airport> list;
    private static final Random rand = new Random();

    static {
        list = new ArrayList<>();
        list.add(new Airport(40.f, 40.f, "Sz"));
        list.add(new Airport(300.f, 50.f, "Ol"));
        list.add(new Airport(450.f, 60.f, "By"));
        list.add(new Airport(300.f, 200.f, "£ó"));
        list.add(new Airport(100.f, 300.f, "ZG"));
        list.add(new Airport(520.f, 200.f, "Lu"));
        list.add(new Airport(400.f, 350.f, "Kr"));
    }

    private AirportManager() {
    }

    public static Airport getRandomOtherThan(final Airport previous) {
        Airport airport;
        do {
            airport = list.get(rand.nextInt(list.size()));
        } while (airport == previous);
        return airport;
    }

    public static ArrayList<Airport> getList() {
        return list;
    }
}
