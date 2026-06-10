
class BTreeNode {
    int t;
    int n;
    int keys[];
    BTreeNode children[];
    boolean leaf;

    BTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        this.keys = new int[2 * t - 1];
        this.children = new BTreeNode[2 * t];
        this.n = 0;
    }

    void traverse() {
        int i;
        for (i = 0; i < n; i++) {
            if (!leaf)
                children[i].traverse();
            System.out.print(keys[i] + " ");
        }

        if (!leaf)
            children[i].traverse();
    }

    BTreeNode search(int k) {
        int i = 0;

        while (i < n && k > keys[i])
            i++;

        if (i < n && keys[i] == k)
            return this;

        if (leaf)
            return null;

        return children[i].search(k);
    }

    void insertNonFull(int k) {
        int i = n - 1;

        if (leaf) {
            while (i >= 0 && keys[i] > k) {
                keys[i + 1] = keys[i];
                i--;
            }

            keys[i + 1] = k;
            n++;
        } else {
            while (i >= 0 && keys[i] > k)
                i--;

            if (children[i + 1].n == 2 * t - 1) {
                splitChild(i + 1, children[i + 1]);

                if (keys[i + 1] < k)
                    i++;
            }

            children[i + 1].insertNonFull(k);
        }
    }

    void splitChild(int i, BTreeNode y) {
        BTreeNode z = new BTreeNode(y.t, y.leaf);
        z.n = t - 1;

        for (int j = 0; j < t - 1; j++)
            z.keys[j] = y.keys[j + t];

        if (!y.leaf) {
            for (int j = 0; j < t; j++)
                z.children[j] = y.children[j + t];
        }

        y.n = t - 1;

        for (int j = n; j >= i + 1; j--)
            children[j + 1] = children[j];

        children[i + 1] = z;

        for (int j = n - 1; j >= i; j--)
            keys[j + 1] = keys[j];

        keys[i] = y.keys[t - 1];
        n++;
    }
}

class BTree {
    BTreeNode root;
    int t;

    BTree(int t) {
        this.root = null;
        this.t = t;
    }

    void traverse() {
        if (root != null)
            root.traverse();
    }

    BTreeNode search(int k) {
        return (root == null) ? null : root.search(k);
    }

    void insert(int k) {

        if (root == null) {
            root = new BTreeNode(t, true);
            root.keys[0] = k;
            root.n = 1;
        } else {

            if (root.n == 2 * t - 1) {

                BTreeNode s = new BTreeNode(t, false);
                s.children[0] = root;

                s.splitChild(0, root);

                int i = 0;
                if (s.keys[0] < k)
                    i++;

                s.children[i].insertNonFull(k);

                root = s;
            } else {
                root.insertNonFull(k);
            }
        }
    }
}

public class ProductInventoryBTree {

    public static void main(String[] args) {

        BTree tree = new BTree(3);

        tree.insert(101);
        tree.insert(105);
        tree.insert(103);
        tree.insert(107);
        tree.insert(102);
        tree.insert(104);
        tree.insert(106);

        System.out.println("Product IDs in Sorted Order:");
        tree.traverse();

        int searchKey = 104;

        if (tree.search(searchKey) != null)
            System.out.println("\nProduct ID " + searchKey + " Found");
        else
            System.out.println("\nProduct ID " + searchKey + " Not Found");
    }
}