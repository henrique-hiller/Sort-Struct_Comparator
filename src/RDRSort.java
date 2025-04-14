import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

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
        int times;
        switch (selection){
            case 1: //arraylist
                starttime = System.nanoTime();
                times=arrayListCode();
                endtime = System.nanoTime();
                difference = endtime - starttime;
                double differenceseconds = difference / 1_000_000_000.0;
                System.out.println("Times: "+times);
                System.out.printf("Time to process using ArrayList: %.6f s %n",differenceseconds);
                break;
            case 2: //hashset
                starttime = System.nanoTime();
                times=hashSetCode();
                endtime = System.nanoTime();
                difference = endtime - starttime;
                differenceseconds = difference / 1_000_000_000.0;
                System.out.println("Times: "+times);
                System.out.printf("Time to process using HashSet: %.6f s %n",differenceseconds);
                break;
            case 3: //hashmap
                starttime = System.nanoTime();
                times=hashMapCode();
                endtime = System.nanoTime();
                difference = endtime - starttime;
                differenceseconds = difference / 1_000_000_000.0;
                System.out.println("Times: "+times);
                System.out.printf("Time to process using HashMap: %.6f s %n",differenceseconds);
                break;
            case 4: //treeset
                starttime = System.nanoTime();
                times=treeSetCode();
                endtime = System.nanoTime();
                difference = endtime - starttime;
                differenceseconds = difference / 1_000_000_000.0;
                System.out.println("Times: "+times);
                System.out.printf("Time to process using TreeSet: %.6f s %n",differenceseconds);
                break;
            case 5: //Least Common Multiple
                starttime = System.nanoTime();
                long timeslong=mmcCode(); 
                endtime = System.nanoTime();
                difference = endtime - starttime;
                differenceseconds = difference / 1_000_000_000.0;
                System.out.println("Times: "+timeslong);
                System.out.printf("Time to process using Least Common Multiple: %.6f s %n",differenceseconds);
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
                    exists = true;
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
        HashSet <String> hashset = new HashSet<>();
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
        HashMap <String, Integer> hashmap = new HashMap<> (); 
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
        TreeSet <String> treeset = new TreeSet<>();
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

    private long mmcCode(){
        long ctr = mmcCodeSort(0, this.numbers[0]);
        long rep = 0;
        long ctr2;
        for (int i =0;i<this.size;i++){
            ctr2 =0;
            if(this.numbers[i]!=-1){
                ctr2 = mmcCodeSort(i, this.numbers[i]);
            }
            if(ctr2==0 && i==0){
                rep = ctr;
                continue;}
            if(ctr2==0){continue;}
            rep = rep * ctr2;
        }
        return rep;
    }

    private long mmcCodeSort(int actual, int next){
        long ctr = 0;
        while (this.positions[actual]!=-1){
            next = this.positions[actual];
            this.positions[actual] = -1;
            actual = next;
            ctr++;
        }
        return ctr;
    }


}
