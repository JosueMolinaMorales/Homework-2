import java.util.*;
import java.io.*;

public class GenerateNumbers{

    /**
     * Generates {size} psuedo random numbers and creates a file with these numbers
     * the numbers are some rand int with a rand double 0-1 added together
     * @param size amount of numbers to generate
     * @param filename the name of the file to write the numbers to
     */
    public static void genNumbers(int size, String filename){
        try{
            Random rand = new Random();
            FileWriter fWriter = new FileWriter(filename);
            for(int i = 0; i < size; i++){
                fWriter.write(rand.nextDouble() + "\n");
            }
            fWriter.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

    }
}