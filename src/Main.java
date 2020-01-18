import java.util.HashMap;
import java.util.Scanner;

//000010010101000101110
public class Main {
    public static HashMap<String, String> ShortCode = new HashMap();
    public static HashMap<String, String> DeShortCode = new HashMap(); //invert
    public static void main(String[] args) {
        int x = 65;
        String u;

        /*for (int i = 0; i < 26; i++) {
            String tmp = Integer.toBinaryString(i);
            String tmp2 = "";
            if (tmp.length() < 5) {
                for (int j = 0; j < 5 - tmp.length(); j++) {
                    tmp2 += "0";
                }
                tmp2 += tmp;
                u="" + (char)(x + i);
                ShortCode.put(u, tmp2);
            }
        }*/
    ShortCode.put("A","00");
    ShortCode.put("B","01");
    ShortCode.put("C","10");
        DeShortCode.put("00","A");
        DeShortCode.put("01","B");
        DeShortCode.put("10","C");
        Tree T=new Tree(100);
        DeCompress(T);
    }
    public static void Compress ( Tree T)
    {
        System.out.println("Enter Characters:");
        Scanner x = new Scanner(System.in);
        String input=x.next();
        for (int i = 0 ; i <input.length();i++)
            T.Add(""+ input.charAt(i));
        System.out.println(T.ComOutput);
    }
    public static void DeCompress (Tree T)
    {
        System.out.println("Enter Seq:");
        Scanner x = new Scanner(System.in);
        String input=x.next();
        int Start=0;
        int CounterforNYT=1;
        int length=ShortCode.get("A").length();
        String Search=input.substring(Start,Start+length);
        String Sym=DeShortCode.get(Search);
        T.Add(Sym);
        Start+=length;

        for (int i =Start;i<=input.length();i++)
        {
            length=CounterforNYT;
            if ((Start+length)<=input.length())
            {Search=input.substring(Start,Start+length);}
            else
            {length=input.length()-Start;}
            if (Search.equals(T.NYTnode.Code))
            {
                Start+=length;
                length=ShortCode.get("A").length();
                if ((Start+length)<=input.length())
                {Search=input.substring(Start,Start+length);}
                else
                {length=input.length()-Start;}
                Sym=DeShortCode.get(Search);
                System.out.println(Sym);
                T.Add(Sym);
                CounterforNYT++;
                Start +=length;
            }
            else
            {
                while (length>0)
                {
                    if ((Start+length)<=input.length())
                    {Search=input.substring(Start,Start+length);}
                    else
                    {length=input.length()-Start;}
                    if(T.CodeArr.containsKey(Search))
                {
                    Sym=T.CodeArr.get(Search).Sympol;
                    T.Add(Sym);
                    Start+=length;
                    break;
                }
                else
                    {
                        length--;
                    }
                }
            }
        }
        System.out.println("S: "+ Start +" l: "+length);
    System.out.println("Your Txt: "+ T.DeOutput);
    }
}