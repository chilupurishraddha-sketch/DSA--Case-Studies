class AVLNode {
    int score;
    AVLNode left, right;
    int height;

    AVLNode(int score) {
        this.score = score;
        this.height = 1;
    }
}

class AVLTree {

    int height(AVLNode node) {
        if (node == null)
            return 0;
        return node.height;
    }

    int getBalance(AVLNode node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    AVLNode insert(AVLNode node, int score) {

        if (node == null)
            return new AVLNode(score);

        if (score < node.score)
            node.left = insert(node.left, score);
        else if (score > node.score)
            node.right = insert(node.right, score);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && score < node.left.score)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && score > node.right.score)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && score > node.left.score) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && score < node.right.score) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    void inorder(AVLNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.score + " ");
            inorder(root.right);
        }
    }
}

public class LeaderboardAVL {

    public static void main(String[] args) {

        AVLTree tree = new AVLTree();
        AVLNode root = null;

        int[] scores = {450, 300, 600, 200, 400, 500, 700};

        for (int score : scores) {
            root = tree.insert(root, score);
        }

        System.out.println("Online Gaming Leaderboard");
        System.out.println("-------------------------");
        System.out.println("Player Scores in Sorted Order:");

        tree.inorder(root);

        System.out.println("\n\nLeaderboard Generated Successfully!");
    }
}