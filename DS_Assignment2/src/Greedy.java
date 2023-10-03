import java.io.*;
public class Greedy {

    private static int i=101; 

    //examines if a string value is numeric
    public static boolean isNumeric(String str) { 
        try {  
          Double.parseDouble(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }   
    }

    public List<Integer> ReadFile(File fl) throws Exception{
        List<Integer> arr = new List<>();
        try(BufferedReader br = new BufferedReader (new FileReader(fl)))
        { 
            String str = null;
            
            while((str=br.readLine())!=null )/*diavazw to arxeio grammh-grammh kai oso yparxei keimeno sunexizw*/ 
            {
                if (!isNumeric(str)){
                    throw new Exception("LATHOS DEDOMENA EISODOU");
                }else if (Integer.parseInt(str)<0 || Integer.parseInt(str)>1000000) {
                    throw new Exception("LATHOS DEDOMENA EISODOU");
                }else{
                    arr.add(Integer.parseInt(str));
                }
            } 
        }catch(IOException e) {
            System.err.println(e);
        }
        return arr;   
    }


    public int toDO(List<Integer> arr){

            int returnedvalue;
            MaxPQInterface<Disk> pq = new MaxPQ<>(); //make instant priority queue
            pq.insert(new Disk(i++, new List<String>())); //insert instant Disk in the priority queue

            int FoldersMemory=0;    //synoliki mnimi pou tha pianoyn oi fakeloi kathe arxeiou
            int numberOfFolders=0;  //plithos fakelwn kathe arxeiou
            int DisksUsed=0;        //plithos diskwn pou tha xrhsimopoihthoun gia tous fakeloys kathe arxeiou

            for(int k=0;k<arr.size();k++){
                numberOfFolders++;
                int folderMB = arr.get(k); //o xwros pou katalamvanei kathe fakelos, dhl. h timi pou periexei kathe grammi
                FoldersMemory+= folderMB;

                if (pq.peek().getFreeSpace()<folderMB){ //an o xwros tis rizas einai < tou megethous tou fakelou
                    pq.insert(new Disk(i++, new List<String>())); //tote ftiaxe neo instant Disk
                    DisksUsed++;                                   //+1 diskos
                }
                pq.peek().AddFolderToList(Integer.toString(folderMB)); //vale ton fakelo ston disko pou vrisketai sti riza ths priority queue
                pq.peek().setSize(pq.peek().getFreeSpace() - folderMB); //allaxe thn timi tou freeSpace thws priority queue
                pq.sink(1);                                           //kane sink ton disko tis rizas gia na diathreitai h proteraiothta stin priority queue
                
            }   

            //EKTYPWSE STIN OTHONI
            System.out.println("Sum of all folders = "+FoldersMemory*0.000001+" " + "TB"); 
            System.out.print("Total number of disks used = ");
            if (numberOfFolders==0) {System.out.println(0); returnedvalue=0 ;}
            else {System.out.println(DisksUsed+1); returnedvalue=DisksUsed+1;}

            //an o arithmos twn fakelwn se ena arxeio data.txt einai [0,100] ektypwse ayta:
            if (numberOfFolders>0 && numberOfFolders<=100){
                while(pq.peek()!=null){
                    // ektypwnontai me seira fthinousa afoy ksekiname apo th riza h opoia exei ton megalutero xwro kai pame me vash thn proteraiothta
                    System.out.print("id "+pq.peek().getId()+" "+ pq.peek().getFreeSpace()+" : ");
                    for(int i=0;i<pq.peek().getFolders().size();i++){
                        System.out.print(pq.peek().getFolders().get(i)+" ");
                    }
                    pq.getMax();
                    System.out.println();
                }
            }

            return returnedvalue;
    }

    public static void main(String[] args) throws Exception
    {
        Greedy algorithm = new Greedy();
        try{

        
                File f = new File(args[0]);

                List<Integer> folders = new List<>();
                folders = algorithm.ReadFile(f);
                System.out.println("-----------------------------------------------");
                System.out.println("----------------GIA TO ARXEIO "+i+"----------------");
                System.out.println("-----------------------------------------------");
                System.out.println();
                algorithm.toDO(folders);
                System.out.println("-----------------------------------------------");
        }catch(Exception e){
            System.err.println("LATHOS STO DIAVASMA ARXEIOU!"); 
        }
    }
}

