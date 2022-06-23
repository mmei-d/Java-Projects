public class BSTIndex{

  private Node root;

  //node class
  private class Node{
    private String key;
    private MovieInfo data;
    private Node left, right;

    public Node(MovieInfo data){
      this.data = data;
      key = data.toString();
    }
  }

  //constructor to initialize BST; data element is object of type MovieInfo
  public BSTIndex(){
    root = null;
  }

  /*returns data element MovieInfo where the short-name
  matches the key exactly (case insensitive)*/
  public MovieInfo findExact(String Key){
    Node current = root;

    while(current != null){
      //lexicographically compares the key with the current node's short name
      int compare = Key.compareToIgnoreCase(current.key);

      //if the key is less than the current node's short name, move to the left child Node
      if(compare < 0)
        current = current.left;
      //if the key is greater than the current node's short name, move to the right child node
      else if(compare > 0)
        current = current.right;
      //the current root/subroot node's shortname matches the key
      else
        return current.data;
    }//while

    //if there is no match
    return null;
  }

  /*returns data element MovieInfo where the short-name
  starts with the prefix (case insensitive)*/
  public MovieInfo findPrefix(String prefix){
    Node current = root;
    //gets rid of the * from the input prefix
    String pref = prefix.substring(0, prefix.length() - 1);

    //if the prefix is longer than any existing key
    if(current.key.length() < pref.length())
      return null;

    while(current != null){
      //the beginning of the current node's short name
      String currentBeginning = current.key.substring(0, pref.length());
      //lexicographically compares the prefix with the the beginning of the current node's short name
      int compare = pref.compareToIgnoreCase(currentBeginning);

      //same logic as findExact(String key)
      if(compare < 0)
        current = current.left;
      else if(compare > 0)
        current = current.right;
      else
        return current.data;
    }//while

    //if there is no match
    return null;
  }

  //inserts given data element into the proper place in BST structure
  public void insert(MovieInfo data){
    //if the BST is empty, add the new node as the root
    if(root == null){
      root = new Node(data);
      return;
    }

    Node current = root;

    while(current != null){
      //lexicographically compares the argument's short name with the current node's short name
      int compare = data.toString().compareTo(current.key);

      //add node to the left
      if(compare < 0){
        if(current.left == null){
          current.left = new Node(data);
          //moves left to the node that was just added
          current = current.left;
        }
        current = current.left;
      //add node to the right
      }else if(compare > 0){
        if(current.right == null){
          current.right = new Node(data);
          current = current.right;
        }
        current = current.right;
      //if the key already exists, update the data
      }else{
        current.data = data;
        return;
      }
    }//while
  }

}
