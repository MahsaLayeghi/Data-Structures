import java.util.ArrayList;
import java.util.Collections;

public class Movie
{
    String name;
    int year, duration;
    ArrayList<String> actors;

    Movie(String name, int year, int duration, ArrayList<String> actors)
    {
        this.name = name;
        this.year = year;
        this.duration = duration;
        this.actors = actors;
    }

    boolean isEqual(String s)
    {
        return name.compareTo(s) == 0;
    }

    boolean isLess(String s)
    {
        return name.compareTo(s) < 0;
    }

    boolean isGreater(String s)
    {
        return name.compareTo(s) > 0;
    }

    void print()
    {
        System.out.println(name);
        System.out.println(year);
        System.out.println(duration);
        Collections.sort(actors);
        for (int i = 0; i < actors.size(); i++)
            System.out.println(actors.get(i));
    }
}