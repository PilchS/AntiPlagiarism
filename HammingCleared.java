public class HammingCleared extends Hamming{
    
        
    private static String clear(String a){
        String x, y, f;
        x = a.replaceAll("\\s", "");
        y = x.replaceAll("\t", "");
        f = y.replaceAll("_", "");
        return f;    
        
    }

    int compare(String x, String y) throws Exception{
        int c;
        x = clear(x);
        y = clear(y);
        c = super.HammingDistance(x, y);
        return c;
    }
}