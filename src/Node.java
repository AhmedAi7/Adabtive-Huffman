public class Node {

    String Code,Sympol ;
    int  ID, Count;
    Node Parent , left , right ;


    public Node(String Code,int ID1, String Sym){
        this.Code = Code;
        this.Sympol = Sym;
        this.Count = 1;
        this.left = null;
        this.right= null;
        this.ID=ID1;
        this.Parent=null;
    }
}
