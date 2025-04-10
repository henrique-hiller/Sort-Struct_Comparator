import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class RDRSort {
    
    private int [] numbers;
    private int [] positions;
    private int size;
    
    public RDRSort (String fileName, int selection){
        this.size = 0;
        reader(fileName);
        long starttime;
        long endtime;
        long difference;
        switch (selection){
            case 1: //arraylist
                starttime = System.nanoTime();
                int arraytime=arrayListCode();
                endtime = System.nanoTime();
                difference = endtime - starttime;
                double differenceseconds = difference / 1_000_000_000.0;
                System.out.println("Times: "+arraytime);
                System.out.printf("Time to process using ArrayList: %.6f s %n",differenceseconds);
                break;
            case 2:
                starttime = System.nanoTime();
                int hashsettime=hashSetCode();
                endtime = System.nanoTime();
                difference = endtime - starttime;
                differenceseconds = difference / 1_000_000_000.0;
                System.out.println("Times: "+hashsettime);
                System.out.printf("Time to process using HashSet: %.6f s %n",differenceseconds);
                break;
            case 3:
                starttime = System.nanoTime();
                int hashmaptime=hashMapCode();
                endtime = System.nanoTime();
                difference = endtime - starttime;
                differenceseconds = difference / 1_000_000_000.0;
                System.out.println("Times: "+hashmaptime);
                System.out.printf("Time to process using HashMap: %.6f s %n",differenceseconds);
                break;
            case 4:
                starttime = System.nanoTime();
                int treesettime=treeSetCode();
                endtime = System.nanoTime();
                difference = endtime - starttime;
                differenceseconds = difference / 1_000_000_000.0;
                System.out.println("Times: "+treesettime);
                System.out.printf("Time to process using TreeSet: %.6f s %n",differenceseconds);
                break;

            default:
                System.out.println("Wrong");
                System.exit(1);

        }    
    }

    private void reader (String filename){
        Path path = Paths.get(filename);
        System.out.println(path.toString());
        try (Scanner sc = new Scanner(Files.newBufferedReader(path, StandardCharsets.UTF_8))){
            this.size = sc.nextInt();
            this.numbers = new int [size];
            this.positions = new int [size];
            for (int i =0; i<size;i++){
                int a = sc.nextInt();
                numbers [i]= a;
                positions [i] = a;
            }
        }
        catch (IOException x){
            System.err.format("I/O Error: %s%n",x);
        }
    }

    private void sorting(){ 
        int pos;
        int [] nbrs = new int[this.size];
        for (int i = 0;i<this.size;i++){
            nbrs[i]=this.numbers[i];
        }
        for (int i =0; i<this.size;i++){
            pos = this.positions[i];
            this.numbers[i]=nbrs[pos];
        }
    }

    private int arrayListCode(){
        ArrayList <int []> arraylist = new ArrayList<>();
        boolean exists = false;
        int sorts = 0;
        while(!exists){
            for (int [] array: arraylist){
                if (Arrays.equals(array,this.numbers)){
                    return sorts;
                }
            }
            arraylist.add(Arrays.copyOf(this.numbers,this.size));
            sorting();
            sorts++;
        }
        return sorts;
    }

    private int hashSetCode(){
        HashSet <String> hashset = new HashSet<String>();
        int sorts = 0;
        String value = Arrays.toString(this.numbers);
        while (!hashset.contains(value)){
            hashset.add(value);
            sorting();
            value = Arrays.toString(this.numbers);
            sorts++;
        }
        return sorts;
    }

    private int hashMapCode(){
        HashMap <String, Integer> hashmap = new HashMap<String, Integer> ();
        int sorts =0;
        String value = Arrays.toString(this.numbers);
        Integer x = hashmap.get(value);
        while(x == null){
            hashmap.put(value,0);
            sorting();
            value = Arrays.toString(this.numbers);
            x =hashmap.get(value);
            sorts++;
        }
        return sorts;
    }

    private int treeSetCode(){
        TreeSet <String> treeset = new TreeSet<String>();
        int sorts =0;
        String value = Arrays.toString(this.numbers);
        while (!treeset.contains(value)){
            treeset.add(value);
            sorting();
            value = Arrays.toString(this.numbers);
            sorts++;
        }
        return sorts;
    }

    private void printArray (int [] arrayy){
        System.out.print("{");
        for (int i = 0;i<arrayy.length;i++){
            System.out.print(arrayy[i]+",");
        }
        System.out.println("}");
    }
}
