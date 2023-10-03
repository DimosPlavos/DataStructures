//import java.io.BufferedWriter;
import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.util.Random;

public class Comparisons {
    public static void main(String[] args) throws Exception
    {

        //     -------------------------------------CREATE FILES-------------------------------------//
        //     for (int x=1;x<=30;x++){
        //     try {           
        //         String filename = "data\\"+"data"+x+".txt"; 
        //         File myObj = new File(filename);    
        //         if (myObj.createNewFile()) {
        //           System.out.println("File created: " + myObj.getName());
        //         } else {
        //           System.out.println("File already exists.");
        //         }
        //       } catch (IOException e) {
        //         System.out.println("An error occurred.");
        //         e.printStackTrace();
        //       }
        //     }


        //     -------------------------------------CREATE VALUES-------------------------------------//
        //    int N=0;
        //     for(int j=0;j<3;j++){
        //         if (j==0) N=100;
        //         else if (j==1) N=500;
        //         else if(j==2) N=1000;
            
        //         for (int x=1;x<=10;x++){
        //             try {
        //                 int y = j*10 + x;
        //                 String filename = "data\\"+"data"+y+".txt"; 
        //                 File fout = new File(filename);
        //                 FileOutputStream fos = new FileOutputStream(fout);
                    
        //                 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        //                 for (int i=0;i<N;i++){
        //                     // create instance of Random class
        //                     Random rand = new Random();

        //                     // Generate random integers in range 0 to 999
        //                     int rand_int1 = rand.nextInt(1000000);
        //                     System.out.println(rand_int1);
        //                     bw.write(Integer.toString(rand_int1));

        //                     if(i<N-1) bw.newLine(); //gia na einai akrivws N grammes to arxeio
        //                 }
        //                 bw.close();
        //                 System.out.println("Successfully wrote to the file: "+y);
        //             } catch (IOException e) {
        //                 System.out.println("An error occurred.");
        //                 e.printStackTrace();
        //             }
        //         }
        //     } 

        Greedy algorithm = new Greedy();
        int totaldisksNoSort=0;
        int totalDisksSort=0;
        
        try{

        
            for (int i=1;i<=30;i++){
                File f = new File("data/data"+i+".txt");

                List<Integer> folders = new List<>();
                folders = algorithm.ReadFile(f);
                System.out.println("-----------------------------------------------");
                System.out.println("----------------GIA TO ARXEIO "+i+"----------------");
                System.out.println("-----------------------------------------------");
                System.out.println();
                System.out.println("-------------------XWRIS SORT------------------");
                int diskNoSort = algorithm.toDO(folders);
                System.out.println("-----------------------------------------------");
                System.out.println();
                System.out.println("--------------------ME SORT--------------------");
                totaldisksNoSort+=diskNoSort;

                //Using heapSort
                Sort sorted = new Sort();
                List<Integer> Sortedfolders = new List<>();
                Sortedfolders = sorted.heapSort(folders);
                int disksSort = algorithm.toDO(Sortedfolders);  
                System.out.println("-----------------------------------------------");
                System.out.println();
                totalDisksSort += disksSort;
            }  
            
            System.out.println("MESOS OROS XWRIS SORT: "+totaldisksNoSort/30);
            System.out.println("MESOS OROS ME SORT: "+totalDisksSort/30);
        }catch(Exception e){
            System.err.println("LATHOS STO DIAVASMA ARXEIOU!"); 
        }
    }   
}