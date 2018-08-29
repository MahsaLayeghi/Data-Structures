import java.util.ArrayList;

public class AvlTree
{
    Node root;

    AvlTree()
    {
        root = null;
    }

    private int height (Node node)
    {
        if (node == null)
            return 0;
        return node.height;
    }

    private Node leftRotate(Node x)
    {
        Node y = x.right;
        Node a = y.left;
        y.left = x;
        x.right = a;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        if (a != null)
            a.height = Math.max(height(a.left), height(a.right)) + 1;
        return y;
    }

    private Node rightRotate(Node y)
    {
        Node x = y.left;
        Node a = x.right;
        x.right = y;
        y.left = a;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        if (a != null)
            a.height = Math.max(height(a.left), height(a.right)) + 1;
        return x;
    }

    private int getBalance(Node node)
    {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    void insertAVL(Movie data)
    {
        Node tmp = searchAVL(data.name);
        if (tmp == null)
            root = insert(root, data);
    }

    private Node insert(Node node, Movie data)
    {
        if (node == null)
            return (new Node(data));

        if (node.isGreater(data.name))
            node.left = insert(node.left, data);
        else
            node.right = insert(node.right, data);

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        int balance = getBalance(node);
        if (balance > 1 && node.left.isGreater(data.name))
        if (balance < -1 && node.right.isLess(data.name))
            return leftRotate(node);
        if (balance > 1 && node.left.isLess(data.name))
        {
            node.left =  leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && node.right.isGreater(data.name))
        {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    private Node lms(Node node)
    {
        while (node.left != null)
            node = node.left;
        return node;
    }

    Node searchAVL(String data)
    {
        return search(root, data);
    }

    private Node search(Node root, String data)
    {
        if (root == null)
            return null;
        if (root.isEqual(data))
            return root;
        if (root.isGreater(data))
            return search(root.left, data);
        if (root.isLess(data))
            return search(root.right, data);
        return null;
    }

    void deleteAVL(String data)
    {
        Node tmp = searchAVL(data);
        if (tmp != null)
            root = deleteNode(root, tmp.data);
    }

    private Node deleteNode(Node root, Movie data)
    {
        if (root == null)
            return root;
        if (root.isGreater(data.name))
            root.left = deleteNode(root.left, data);
        else if(root.isLess(data.name))
            root.right = deleteNode(root.right, data);
        else
        {
            if((root.left == null) || (root.right == null))
            {
                Node tmp = root.left != null ? root.left : root.right;
                if(tmp == null)
                {
                    tmp = root;
                    root = null;
                }
                else
                    root = tmp;
                tmp = null;
                System.gc(); //delete tmp
            }
            else
            {
                Node temp = lms(root.right);
                root.data = temp.data;
                root.right = deleteNode(root.right, temp.data);
            }
        }
        if (root == null)
            return root;
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);
        if (balance > 1 && getBalance(root.left) < 0)
        {
            root.left =  leftRotate(root.left);
            return rightRotate(root);
        }
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);
        if (balance < -1 && getBalance(root.right) > 0)
        {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }

    void inOrder()
    {
        if (root == null)
            return;
        ArrayList<Movie> a = new ArrayList();
        inOrder(root, a);
        System.out.print(a.get(0).name);
        for (int i = 1; i < a.size(); i++)
            System.out.print("/" + a.get(i).name);
        System.out.println();
    }

    private void inOrder(Node node, ArrayList a)
    {
        if (node == null)
            return;
        inOrder(node.left, a);
        a.add(node.data);
        inOrder(node.right, a);
    }

    ArrayList<Movie> getMovies()
    {
        if (root == null)
        {
            return null;
        }
        ArrayList<Movie> a = new ArrayList();
        inOrder(root, a);
        return a;
    }
}