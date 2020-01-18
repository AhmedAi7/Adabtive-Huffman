import java.util.HashMap;

public class Tree {

    int nextID;
    Node root, NYTnode;
    HashMap<String, Node> SymArr = new HashMap();  // to search for compress
    HashMap<String, Node> CodeArr = new HashMap(); // to search for decompress
    // ID rqmha fl tree ,, Code ==> short code
    String ComOutput,DeOutput;
    Tree(int innextID) {
        Node NYT = new Node("", innextID, "");
        NYT.Count = 0;
        nextID=innextID;
        this.root = NYT;
        this.NYTnode = NYT;
        DeOutput="";
        ComOutput="";
        nextID--;
    }

    void Add(String Sym)
    {
        DeOutput+= Sym;
        if (SymArr.containsKey(Sym)) {
            SymArr.get(Sym).Count++;
            ComOutput+=SymArr.get(Sym).Code;
            Node temp = SymArr.get(Sym);
            while (temp!= root)
            {
                temp.Parent.Count++;
                temp=temp.Parent;
            }
            Swap(Sym);


        }
        else {
            ComOutput += NYTnode.Code;
            Node right = new Node(NYTnode.Code + "1", nextID, Sym);
            System.out.println(nextID+"))"+right.ID);
            nextID--;

            Node left = new Node(NYTnode.Code + "0", nextID, "");
            nextID--;
            if (root.right==null)
            {
                root.right = right;
                root.left = left;
                root.left.Count=0;
                right.Parent = root;
                left.Parent = root;
                ComOutput += Main.ShortCode.get(Sym);
            }
            else
            {
                NYTnode.right = right;
                NYTnode.left = left;
                right.Parent = NYTnode;
                left.Parent = NYTnode;
                ComOutput += Main.ShortCode.get(Sym);
            }
            SymArr.put(Sym,right);
            CodeArr.put(right.Code,right);
            NYTnode=NYTnode.left;
            Node temp = SymArr.get(Sym);
            while (temp!= root)
            {
                temp.Parent.Count++;
                temp=temp.Parent;
            }
            Swap(Sym);
        }

    }
    void Swap(String x)
    {
        int count =0;
        Node temp = SymArr.get(x);
        Node curr = SymArr.get(x);
        if(root.right.Count<root.left.Count) {
            count++;
            System.out.println(root.right.Count+root.right.Sympol + " " + root.left.Sympol+" "+root.left.Count);
            System.out.println("swap2");
            int id=root.right.ID;
            String code=root.right.Code ;
            root.right.ID=root.left.ID;
            root.right.Code=root.left.Code;
            root.left.ID=id;
            root.left.Code=code;
            Node temp1=root.right;
            root.right=root.left;
            root.left=temp1;
            if(root.right.Sympol=="")
            {
                temp1=root.right;
                }
            else {
                temp1 = root.left;
            }
            while (temp1.left!= null)
                {
                    temp1.right.Code=temp1.Code+"1";
                    temp1.left.Code=temp1.Code+"0";
                    SymArr.put(temp1.right.Sympol,temp1.right);
                    SymArr.put(temp1.left.Sympol,temp1.left);
                    CodeArr.put(temp1.right.Code,temp1.right);
                    CodeArr.put(temp1.left.Code,temp1.left);
                    System.out.println("$ "+temp1.right.Sympol+" "+temp1.right.Code);
                    temp1=temp1.left;
                }

        }
        else if(root.left.Count<curr.Count && root.left.ID>= curr.ID){
            count++;
            System.out.println(count);
            System.out.println(root.left.Count+" "+root.left.Sympol+" "+curr.Count+ curr.Sympol);
            System.out.println("swap3");
            String tsym;
            int tcount;
            tcount=root.left.Count;
            tsym=root.left.Sympol;
            root.left.Count=curr.Count;
            root.left.Sympol=curr.Sympol;
            curr.Sympol=tsym;
            curr.Count=tcount;
            SymArr.put(root.left.Sympol,root.left);
            SymArr.put(curr.Sympol,curr);
            CodeArr.put(root.left.Code,root.left);
            CodeArr.put(curr.Code,curr);
            while (curr.Parent!= root)
            {
                curr.Parent.Count--;
                curr=curr.Parent;
            }
        }
        else
        {while (temp!= root)
        {
            count++;
            if(temp.Parent.right.Count<curr.Count &&temp.Parent.right.ID >= curr.ID)
            {
                System.out.println(temp.Parent.right.Count + " " + curr.Count + "#" + temp.Parent.right.Sympol+"#"+curr.Sympol);
                System.out.println("swap1");
                String tsym;
                int tcount;
                tcount=temp.Parent.right.Count;
                tsym=temp.Parent.right.Sympol;
                temp.Parent.right.Count=curr.Count;
                temp.Parent.right.Sympol=curr.Sympol;
                curr.Sympol=tsym;
                curr.Count=tcount;
                SymArr.put(temp.Parent.right.Sympol,temp.Parent.right);
                SymArr.put(curr.Sympol,curr);
                CodeArr.put(temp.Parent.right.Code,temp.Parent.right);
                CodeArr.put(curr.Code,curr);
            }
            temp=temp.Parent;
        }}

    }

    }


