public class Node
{
    Movie data;
    Node left, right;
    int height;

    Node(Movie s)
    {
        data = s;
    }

    boolean isEqual(String s)
    {
        return data.isEqual(s);
    }

    boolean isLess(String s)
    {
        return data.isLess(s);
    }

    boolean isGreater(String s)
    {
        return data.isGreater(s);
    }
}