
import java.util.Scanner;

public class CRC {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("Data input : ");
        String data = sc.next();
        int ld = data.length();
        System.out.println("Polynomial bits : ");
        String poly = sc.next();
        int lp = poly.length();
        for(int i=0;i<lp-1;i++){
            data += "0";
        }
        System.out.println("Rough CRC code : ");
        System.out.println(data);
        String remainder = calcCRC(data,poly);
        String crc = add(data,remainder);
        
        //CRC sent to the receiver
        char [] rec = data.toCharArray();
        if(rec[4] == 0)
            rec[4] = 1;
        else
            rec[4] = 0;
        String r = new String();
        r = rec.toString();
        System.out.println("CRC received at the receiver's end");
        String rem2 = calcCRC(r,poly);
        int c=0;
        for(int i=0;i<rem2.length();i++){
            if(rem2.charAt(i)==0)
                c++;
        }
        if(c == rem2.length())
           System.out.println("Data received correctly");
        else
           System.out.println("Data received is not correct");
    }
    static String calcCRC(String data,String poly){
        int current; int j=0;
        String r = new String();
        char [] rem = data.toCharArray();
        char [] div = poly.toCharArray();
        current=0;
        while(true){
            for(int i=0;i<div.length;i++){
                if(rem[current+i] == div[i])
                    rem[current+i]='0';
                else
                    rem[current+i]='1';
            }
            for(j=0;j<rem.length;j++){
                if(rem[j]=='0'){
                    current++;
                }
                if(rem[j]=='1'){
                    current = j;
                    break;
                }
            }    
            if(rem.length - current <= div.length -1 )
                break;
        }
        for(int i=0;i<rem.length;i++){
                r += rem[i]; 
            }
        System.out.println("\n"+"Remainder:"+r);
        return r;
    }
    static String add(String data, String remainder){
        String crc = new String();
        int l=3;
        for(int i=0;i<data.length()-l;i++){
            crc += data.charAt(i);
        }
        //System.out.println(crc);
        for(int i=data.length()-l;i<data.length();i++){
            crc += remainder.charAt(i);
        }
        //System.out.println(crc);
        return crc;
    }
}
