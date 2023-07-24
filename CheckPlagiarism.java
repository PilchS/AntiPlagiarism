import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;


public class CheckPlagiarism extends Hamming{
    
     public static String[] ReadFile1(String string) throws IOException{
         FileReader fileRead1 = new FileReader(string);
         BufferedReader buffRead1 = new BufferedReader(fileRead1);
         List<String> text1 = new ArrayList<String>();
         String line1 = null;
         while ((line1 = buffRead1.readLine()) != null){
             text1.add(line1);
            }
         buffRead1.close();

         return text1.toArray(new String[text1.size()]);
        
    }




    public static void CompareFiles(File ReadFile1, File ReadFile2) throws Exception{
        String[] file1 = ReadFile1("test1.txt");
        String[] file2 = ReadFile1("test2.txt");
        HammingCleared hc = new HammingCleared();
        double [] mini = new double[0];
        int same = 0;
        for(int i = 0; i < file1.length; i++){
            double minhd = 1000;
            for(int j = 0; j < file2.length; j++){
                if((hc.compare(file1[i], file2[j])) < minhd){
                    minhd = hc.compare(file1[i], file2[j]);
                    if(minhd == 0){
                        same++;
                        break;
                    }
                }
            }
            mini = Arrays.copyOf(mini, mini.length +1);
            mini[mini.length-1] = minhd;
        }
        int avg = 0;
        for(int i = 0; i < mini.length; i++){
            avg +=mini[i];
        }
        double average = avg/mini.length;
        System.out.println("Average Hamming Distance in those files: " + ReadFile1.getName() + ", " + ReadFile2.getName() + " = " + average);
        if(same == 0 || same == 1){
            System.out.println("There is "+ same +" identical line");
        }else{
            System.out.println("There are " + same + " identical lines");
        }
    }
        

    public static void main(String[] args){
        File file1 =new File("test1.txt");
        File file2 = new File("test2.txt");
        try {
            CompareFiles(file1, file2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}