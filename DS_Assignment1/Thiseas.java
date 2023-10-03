import java.io.*;
import java.io.File;

public class Thiseas<T> {
    private int x;
    private int y;
    private T[][] l;

    public Thiseas(T[][] l, int x, int y){
        this.l = l;
        this.x=x;
        this.y = y;
    }

    public void setX(int x1){
        x=x1;
    }

    public void setY(int y1){
        y=y1;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public T getValue(){
        return l[x][y];
    }

    public void setValue(T v){
        l[x][y]=v;
    }

    public static boolean isAdiexodo(){
        return false;
    }

    public T getLeft(){
        return l[x][y-1];
    }

    public T getUp(){
        return l[x-1][y];
    }

    public T getDown(){
        return l[x+1][y];
    }

    public T getRight(){
        return l[x][y+1];
    }

    public static boolean isNumeric(String str) { 
        try {  
          Double.parseDouble(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
    }

    //-----------------------------------------------------------MAIN---------------------------------------------------//

    public static void main(String[] args) throws Exception
    {
        String filename = "word.txt";
		
		File f = new File(filename);
		try(BufferedReader br = new BufferedReader
				(new FileReader(f))){ /*diavazw to arxeio*/
			        String str = null;
			        StringStackImpl <String> stoiva = new StringStackImpl <String>(); /*dhmiourgw stoiva me onoma d*/

                    //------------------------------------DIAXEIRISI 2 PRWTWN GRAMMWN------------------------------------//
                    str = br.readLine();
                    String[] line = str.split(" ");
                    if (line.length!=2) throw new Exception("LATHOS DEDOMENA EISODOU"); //exception an oi times pou dinei sthn 1h grammh den einai mono 2 se plithos
                    if (!isNumeric(line[0]) || !isNumeric(line[1])) throw new Exception("LATHOS DEDOMENA EISODOU"); //exception an oi times den einai arithmitikes
                    int height = Integer.parseInt(line[0]); 
                    int width = Integer.parseInt(line[1]);
                    //System.out.println("Width is: "+ width + " And Height is: " + height);

                    str = br.readLine();
                    line = str.split(" ");
                    if (line.length!=2) throw new Exception("LATHOS DEDOMENA EISODOU"); //exception an oi times pou dinei sthn 2h grammh den einai mono 2
                    if (!isNumeric(line[0]) || !isNumeric(line[1])) throw new Exception("LATHOS DEDOMENA EISODOU"); //exception an oi times den einai arithmitikes
                    int coordinateX = Integer.parseInt(line[0])+1; //vazw +1 giati exw megalwsei ton pinaka
                    int coordinateY = Integer.parseInt(line[1])+1; //omoiws
                    //System.out.println("Coordinate X is: "+ coordinateX + " And Coordinate Y is: " + coordinateY);


                    //-------------------------------DHMIOYRGIA VOH8HTIKOY PINAKA-------------------------------//
                    String [][] labyrinth = new String[height+2][width+2];

                    for (int i=0;i<height+2;i++){ 
                        if(i>0 && i<height+1){
                            str=br.readLine();
                            if (str==null) throw new Exception("LATHOS DEDOMENA"); //exception an height<>zhtoymeno
                            line = str.split(" "); 
                            if (line.length!=width) {
                                throw new Exception("LATHOS DEDOMENA"); //exception an width<>zhtoymeno
                            }
                        }
                        
                        //EISAGWGH TIMWN: gurw gurw oi times einai X, sth meria ths ekkinisis oi times einai A, kai o ypoloipos pinakas opws provlepetai
                        for (int j=0;j<width+2;j++){ 
                                if (i==0 || i==height+1) labyrinth[i][j] = "X";
                                else{
                                    if (j==0 || j==width+1) labyrinth[i][j] = "X";
                                    if (coordinateX==1) labyrinth[0][j] = "A";
                                    else if (coordinateX==height+1) labyrinth[height+1][j] = "A";
                                    else if (coordinateY==1) labyrinth[i][0] = "A";
                                    else if (coordinateY==width+1) labyrinth[i][width+1] = "A";
                                    if (j<width) labyrinth[i][j+1] = line[j];
                                }
                        }
                    }

                    // //---------------------------EKTYPWSH PINAKA---------------------------//
                    // System.out.println("PINAKAS: ");
                    // for (int i=0;i<height+2;i++){
                    //     for (int j=0;j<width+2;j++){
                    //         System.out.print(labyrinth[i][j]);
                    //     }
                    //     System.out.println();
                    // }

                    boolean telos = false;
                    Thiseas<String> shmeio = new Thiseas<>(labyrinth, coordinateX, coordinateY);
                    
                    aa: //to kommati auto tha xrisimopoiithei stin break
                    while (telos==false){

                        if (shmeio.getLeft().equals("0")) //an to aristera einai 0
                        {
                            if (shmeio.getDown().equals("0") /*an to apo katw einai 0*/ || ( shmeio.getRight().equals("0") /*an to deksia einai 0*/ ) || ( shmeio.getUp().equals("0") /*an to panw einai 0*/)) { 
                                shmeio.setValue("K");
                            }else{
                                shmeio.setValue("F");
                            }
                            stoiva.push(Integer.toString(shmeio.getX())); 
                            stoiva.push(Integer.toString(shmeio.getY()));
                            shmeio.setY(shmeio.getY()-1);
                        }
                        else if (shmeio.getDown().equals("0")) //an to apo katw einai 0
                        {
                            if (shmeio.getLeft().equals("0") /*an to aristera einai 0*/ || ( shmeio.getRight().equals("0") /*an to deksia einai 0*/ ) || ( shmeio.getUp().equals("0") /*an to panw einai 0*/)) { 
                            shmeio.setValue("K");
                            }else{
                                shmeio.setValue("F");
                            }
                            stoiva.push(Integer.toString(shmeio.getX()));
                            stoiva.push(Integer.toString(shmeio.getY()));
                            shmeio.setX(shmeio.getX() +1);                                
                        }
                        else if (shmeio.getRight().equals("0")) //an to dexia einai 0
                        {
                            if (shmeio.getLeft().equals("0") /*an to aristera einai 0*/ || ( shmeio.getDown().equals("0") /*an to katw einai 0*/ ) || ( shmeio.getUp().equals("0") /*an to panw einai 0*/)) { 
                                shmeio.setValue("K");
                            }else{
                                shmeio.setValue("F");
                            }
                            stoiva.push(Integer.toString(shmeio.getX()));
                            stoiva.push(Integer.toString(shmeio.getY()));
                            shmeio.setY(shmeio.getY()+1);
                            
                        }
                        else if (shmeio.getUp().equals("0")) //an to panw einai 0
                        {
                            
                            if (shmeio.getDown().equals("0") /*an to katw einai 0*/ || ( shmeio.getLeft().equals("0") /*an to aristera einai 0*/ ) || ( shmeio.getRight().equals("0") /*an to deksia einai 0*/)) { 
                                shmeio.setValue("K");
                            }else{
                                shmeio.setValue("F");
                            }
                            stoiva.push(Integer.toString(shmeio.getX()));
                            stoiva.push(Integer.toString(shmeio.getY()));
                            shmeio.setX(shmeio.getX() -1);                          
                        }else //adiexodo 
                        {
                            shmeio.setValue("F");
                            do {
                                if(stoiva.isEmpty()) {
                                    System.out.println("O lavyrinthos den exei kamia exodo");
                                    break aa;
                                } 
                                shmeio.setY(Integer.parseInt(stoiva.pop()));
                                shmeio.setX(Integer.parseInt(stoiva.pop()));
                            } while (!shmeio.getValue().equals("K"));
                            shmeio.setX(shmeio.getX());
                            shmeio.setY(shmeio.getY());
                        }  
                        if(shmeio.getValue().equals("0") && (shmeio.getLeft().equals("X") || shmeio.getRight().equals("X") || shmeio.getUp().equals("X") || shmeio.getDown().equals("X"))) {
                            telos=true;
                            System.out.print("To shmeio exodou einai: ");
                            System.out.println("("+ shmeio.getX()+ "," + shmeio.getY()+ ")");
                        }                 
                    }
                }catch(IOException e) {
                    System.err.println(e);
                }

                
    } //end of main
}
        