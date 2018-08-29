import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        AvlTree avlTree = new AvlTree();
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        while (s.compareTo("exit") != 0)
        {
            if (s.compareTo("addmovie") == 0)
            {
                String name = scanner.nextLine();
                int y = scanner.nextInt();
                int d = scanner.nextInt();
                int n = scanner.nextInt();
                scanner.nextLine();
                ArrayList<String> acts = new ArrayList<String>();
                for (int i = 0; i < n; i++)
                    acts.add(scanner.nextLine());
                avlTree.insertAVL(new Movie(name, y, d, acts));
            }
            else if(s.compareTo("deletemovie") == 0)
            {
                String name = scanner.nextLine();
                avlTree.deleteAVL(name);
            }
            else if(s.compareTo("addactor") == 0)
            {
                String movie = scanner.nextLine();
                String act = scanner.nextLine();
                Movie m = avlTree.searchAVL(movie).data;
                m.actors.add(act);
            }
            else if(s.compareTo("deleteactor") == 0)
            {
                String movie = scanner.nextLine();
                String act = scanner.nextLine();
                Movie m = avlTree.searchAVL(movie).data;
                m.actors.remove(act);
            }
            else if(s.compareTo("findmovie") == 0)
            {
                String movie = scanner.nextLine();
                System.out.println(movie);
                Movie m = avlTree.searchAVL(movie).data;
                if (m == null)
                    System.out.println("not found");
                else
                    m.print();
            }
            else if(s.compareTo("findactor") == 0)
            {
                String act = scanner.nextLine();
                boolean flag = true;
                ArrayList<Movie> a = avlTree.getMovies();
                for (int i = 0; i < a.size(); i++)
                {
                    Movie m = a.get(i);
                    for (int j = 0; j < m.actors.size(); j++)
                        if (m.actors.get(j).compareTo(act) == 0)
                        {
                            System.out.println(m.name);
                            flag = false;
                        }
                }
                if (flag)
                    System.out.println("not found");
            }
            else if(s.compareTo("findyear") == 0)
            {
                int y = scanner.nextInt();
                scanner.nextLine();
                boolean flag = true;
                ArrayList<Movie> a = avlTree.getMovies();
                for (int i = 0; i < a.size(); i++)
                {
                    Movie m = a.get(i);
                    if (m.year == y)
                    {
                        System.out.println(m.name);
                        flag = false;
                    }
                }
                if (flag)
                    System.out.println("not found");
            }
            else if(s.compareTo("findyears") == 0)
            {
                int y1 = scanner.nextInt(), y2 = scanner.nextInt();
                scanner.nextLine();
                boolean flag = true;
                ArrayList<Movie> a = avlTree.getMovies();
                for (int i = 0; i < a.size(); i++)
                {
                    Movie m = a.get(i);
                    if (m.year >= y1 && m.year <= y2)
                    {
                        System.out.println(m.name);
                        flag = false;
                    }
                }
                if (flag)
                    System.out.println("not found");
            }
            else if(s.compareTo("findsameyear") == 0)
            {
                String movie = scanner.nextLine();
                Movie m = avlTree.searchAVL(movie).data;
                if (m == null)
                    System.out.println("not found");
                else
                {
                    int y = m.year;
                    ArrayList<Movie> a = avlTree.getMovies();
                    for (int i = 0; i < a.size(); i++)
                    {
                        Movie t = a.get(i);
                        if (t.year == y)
                            System.out.println(t.name);
                    }
                }
            }
            else if(s.compareTo("listmovies") == 0)
            {
                avlTree.inOrder();
            }
            s = scanner.nextLine();
        }
    }
}