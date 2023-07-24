import java.lang.String;

public class Hamming{
    public static int HammingDistance(String x, String y) throws Exception{
        int hd = 0;
        int r = 0;
        int lx = x.length();
        int ly = y.length();
        if(lx <= ly){
           r = lx;
        }else if(lx >= ly){
            r = ly;
        }
        for(int i = 0; i < r; i++){
                if(x.split("")[i].charAt(0) != y.split("")[i].charAt(0)){                    
                    hd++;
                }
        }
        hd += Math.abs(lx-ly);
        return hd;
    }

    // public static void main(String[] args){
    //     String a = new String ("speed");
    //     String b = new String ("spreading");
    //     try{
    //         System.out.println(HammingDistance(a, b));
    //     } catch (Exception ex){
    //          System.out.println("Missing String");
    //     }
        
    // }
}

