
public class Solution {

    public static main(String[] args){
            new 
        }

    private List<Integer> res = new ArrayList<>();

    void List<Integer>  reverse(Node root){
            if(root == null) {
                return res;
            }
          Queue<Node> queue =  new LinkedList<Node>();
          queue.add(root);
          while(queue.size()>0){
              Node temp = queue.pop();
              int size = queue.size();
              for(int i= 0; i< size; i++){
                  res.add(root.val);
                  if(temp.right!=null){
                    queue.add(temp.right);
                  }
                  if(temp.left != null){
                    queue.add(temp.left);
                  }
              }
          }
          return res;
        }
}

class Node {
    Node left;
    Node right;
    int val;
}
