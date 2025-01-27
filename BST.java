import java.util.ArrayList;

class BST {

    private Node root;
    
    public BST()
    {
         root = null;
    }

    void insert(int key){
        //If the tree is empty, make the key the root
        if(root == null){
            root = new Node(key);
            return;
        }
        Node temp = root;
        //find where to put the key
        while(true){
            if(temp.key > key){
                if(temp.left == null){
                    //places the key
                    temp.left = new Node(key);
                    return;
                }
                temp = temp.left;
            }
            else if(temp.key < key){
                if(temp.right == null){
                    //places the key
                    temp.right = new Node(key);
                    return;
                }
                temp = temp.right;
            }

        }


    }
    //Return false if the tree is empty
    boolean search(int key){
        if(root == null){
            return false;
        }
        else{
            Node temp = root;
            while(true){
                //check left if the number is smaller
                if(temp.key > key){
                    if(temp.left != null){
                        temp = temp.left;
                    }
                    else{
                        return false;
                    }
                }
                //check right if the number is higher
                else if(temp.key < key){
                    if(temp.right != null){
                        temp = temp.right;
                    }
                    else{
                        return false;
                    }
                }
                else{
                    return true;
                }
            }
    }
    }


    boolean remove(int key){
        //Makes sure the item needed to be removed is in the tree
        if(!search(key)){
            return false;
        }
        //Makes sure there are items in the tree
        if(root == null){
          return false;
       }
       else{
            //Reshapes the tree if the root needs to be removed
            if(root.key == key){
                // 3 possibilitiets
                if(root.left == null && root.right == null){
                    root = null;
                    return true;
                }
                else if(root.right != null && root.left == null){
                   root = root.right;
                   return true;
                }
                else if(root.right == null && root.left != null){
                    root = root.left;
                    return true;
                 }
                else{
                    Node temp = root.right;
                    while(temp.left != null){
                        temp = temp.left;
                    }
                    remove(temp.key);
                    root.key = temp.key;
                    return true;
                }

            }
            else{
                Node parent = root;
                Node del = root;
                while(del.key != key){
                    if(del.key < key){
                        parent = del;
                        del = del.right;
                    }
                    else{
                        parent = del;
                        del = del.left;
                    }
                }
                
                
                //no children
                if(del.right == null && del.left == null){
                    if(parent.left.key == key){
                        parent.left = null;
                    }
                    else{
                        parent.right = null;
                    }
                    return true;
                }
                //one child
                else if(del.right != null && del.left == null){
                    if(parent.left.key == key){
                        parent.left  = del.right;
                    }
                    else{
                        parent.right = del.right;
                    }
                    return true;
                }
                else if(del.right == null && del.left != null){
                    if(parent.left.key == key){
                        parent.left = del.left;
                    }
                    else{
                        parent.right = del.left;
                    }
                    return true;
                }
                //two children
                else{
                    Node temp = del.right;
                    while(temp.left != null){
                        temp = temp.left;
                    }
                    remove(temp.key);
                    del.key = temp.key;
                    return true;
                }

            }
       }
    }


    //Utilizes an array list of array lists and stringhelper to print out the tree
    public String toString(){
        ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>(); 
        aList = stringHelper(root, aList, 0);
       
        return aList.toString();
    }

    //Does the work of toString()
    private ArrayList<ArrayList<String>> stringHelper(Node n, ArrayList<ArrayList<String>> list, int depth){
        //Adds an array list to store a new layer if layer does not have one yet
        if(list.size()<=depth){
            list.add(new ArrayList<String>());
        }
        //Adds to the array list corresponding with its depth in the order the tree is
        list.get(depth).add(n.key+"");
        if(n.left != null){
        stringHelper(n.left, list, depth+1);
        }
        if(n.right != null){
        stringHelper(n.right, list, depth+1);
        }
        return list;
    }


public static void showTrunks(Trunk p)
{
    if (p == null) {
        return;
    }

    showTrunks(p.prev);
    System.out.print(p.str);
}

public void printTree(){
    printTree(root, null, false);
}

private void printTree(Node root, Trunk prev, boolean isLeft)
{
    if (root == null) {
        return;
    }

    String prev_str = "    ";
    Trunk trunk = new Trunk(prev, prev_str);

    printTree(root.right, trunk, true);

    if (prev == null) {
        trunk.str = "———";
    }
    else if (isLeft) {
        trunk.str = ".———";
        prev_str = "   |";
    }
    else {
        trunk.str = "`———";
        prev.str = prev_str;
    }

    showTrunks(trunk);
    System.out.println(" " + root.key);

    if (prev != null) {
        prev.str = prev_str;
    }
    trunk.str = "   |";

    printTree(root.left, trunk, false);
}
//Running the code to make sure it works
public static void main(String[] args) {
    BST jimbo = new BST();
    jimbo.insert(40);
    jimbo.insert(30);
    jimbo.insert(50);
    jimbo.insert(55);
    jimbo.insert(45);
    jimbo.insert(35);
    jimbo.search(10);
    jimbo.remove(30);
    jimbo.remove(40);
    jimbo.printTree();
    System.out.println(jimbo);
    System.out.println(jimbo.search(9));
    System.out.println(jimbo.search(13));

}
}
//Put in separate file
class Trunk
{
Trunk prev;
String str;

Trunk(Trunk prev, String str)
{
this.prev = prev;
this.str = str;
}
};

 