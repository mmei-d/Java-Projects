// An AVLTree is a BST (binary search tree) which also maintains the
// "height balance condition": for every node in the tree, its two
// child subtrees have heights within one of each other.
// AVLTree inherits most of its methods from BST.

public class AVLTree<K extends Comparable<K>> extends BST<K>
{

    protected Node fixup(Node t)
    {
        t.update(); // update t.size and t.height

        int bal = height(t.left) - height(t.right);
        if (bal >= -1 && bal <= +1)
            // got lucky: balanced already, nothing to do!
            return t;

        // Unhappy node t is "z"
        // t's taller child is "y", and y's taller child is "x"

        // -----IF NOT BALANCED-----
        // single left rotation where y is z's right child, and x is y's
        // right child
        if(bal < -1 && height(t.right.right) >= height(t.right.left)){
          return rotateLeft(t);
        // single right rotation where y is z's left child, and x is y's
        // left child
        }else if(bal > 1 && height(t.left.left) >= height(t.left.right)){
          return rotateRight(t);
        // double rotation where y is is z's right child, and x is y's
        // left child
        }else if(bal < -1 && height(t.right.left) >= height(t.right.left)){
          t.right = rotateRight(t.right);
          return rotateLeft(t);
        // double rotation where y is is z's left child, and x is y's
        // right child
        }else if(bal > 1 && height(t.left.right) >= height(t.left.left)){
          t.left = rotateLeft(t.left);
          return rotateRight(t);
        }
        return t;

    }

    // rotateRight does a rotation: the left child becomes the new
    // root of this subtree, the old root becomes its right child.
    Node rotateRight(Node k2) {
        Node k1 = k2.left;
        k2.left = k1.right;
        k2.update(); // update size and height
        k1.right = k2;
        k1.update(); // update size and height
        return k1;   // return new root of this subtreee
    }

    // rotateLeft does a rotation: the right child becomes the new
    // root of this subtree, the old root becomes its left child.
    Node rotateLeft(Node k2){
      Node k1 = k2.right;
      k2.right = k1.left;
      k2.update();
      k1.left = k2;
      k1.update();
      return k1;
    }

}
