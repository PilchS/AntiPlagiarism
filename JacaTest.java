import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.File;

public class JacaTest
{
    public static void main(String[] args) throws Exception
    {
        // For checking all fiels in the working directory:
        // File wd = new File("./"); File[] files = wd.listFiles();
        // for (int m = 0; m < files.length; m++)
        // {
        //     for (int n = m + 1; n < files.length; n++)
        //     {
        //         CompareFiles(files[m], files[n]);
        //     }
        // }
        // For checking 2 selected files:
        File f1 = new File("./plik1.txt"); File f2 = new File("./plik2.txt");
        CompareFiles(f1, f2);
    }

    public static void CompareFiles(File f1, File f2) throws Exception
    {
        try
        { 
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f1))); br.close();
            br = new BufferedReader(new InputStreamReader(new FileInputStream(f2))); br.close();
        }
        catch (Exception e)
        {
            throw new Exception("Cannot open at least one of the files (missing or corrupted)");
        }
        int f1lines = getLines(f1); int f2lines = getLines(f2);
        if (f2lines > f1lines)
        {
            File ftemp = f1; f1 = f2; f2 = ftemp;
            int itemp = f1lines; f1lines = f2lines; f2lines = itemp;
            if (f1lines == 0 || f2lines == 0)
            {
                throw new Exception("At least one file is empty!");
            }
        }
        HammingCleared hc = new HammingCleared(); String line1; String line2; double[] minimals = new double[0];
        BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(f1)));
        int identical = 0;
        while ((line1 = br1.readLine()) != null)
        {
            BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(f2)));
            int min = 1000;
            while ((line2 = br2.readLine()) != null)
            {
                if (hc.compare(line1, line2) < min)
                {
                    min = hc.compare(line1, line2);
                    if (min == 0)
                    {
                        identical++;
                        break;
                    }
                }
            }
            br2.close();
            minimals = Arrays.copyOf(minimals, minimals.length + 1);
            minimals[minimals.length - 1] = min;
        }
        br1.close();
        double average = 0;
        for (int m = 0; m < minimals.length; m++)
        {
            average += minimals[m];
        }
        average = average / minimals.length;
        System.out.println("(" + f1.getName() + ", " + f2.getName() + ")\nAverage letter difference for each line: " + average + "\nIdentical lines: " + identical);
    }

    public static int getLines(File f) throws Exception
    {
        int counter = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        while (br.readLine() != null)
        {
            counter++;
        }
        br.close();
        return counter;
    }
}