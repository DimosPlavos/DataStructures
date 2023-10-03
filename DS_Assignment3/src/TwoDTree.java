import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TwoDTree {
    private Treenode head; 
    private int size;

    private class Treenode {
        Point item; // an object of the class Point
        Treenode l; // pointer to left subtree
        Treenode r; // pointer to right subtree
        String pedio; //pairnei times "x" / "y". Dhlwnei thn metavlhth me vash thn opoia tha kanw th sygkrish gia tis insert. rangeSearch etc. phgainontas sto epomeno epipedo
        Rectangle rect = new Rectangle(); //to tetragwno pou dhmiourgei o komvos

        Treenode(Point item,Treenode l, Treenode r, String pedio, Rectangle rect){
            this.item = item;
            this.l = l;
            this.r =r;
            this.pedio = pedio; 
            this.rect = rect;
        }
    };

    public TwoDTree(){} // construct an empty tree

    public boolean isEmpty() //is the tree empty?
    {
        if (head==null) return true;
        return false;
    }

    public int size() { return size; } //number of points in the tree

    public void insert(Point p) // inserts the point p to the tree
    {
        boolean flag = true;
        Rectangle rectPateras = new Rectangle(0, 100, 0, 100);
        if (isEmpty()) {
            head = new Treenode(p, null, null,"x",rectPateras);
            size++;}
        else{
            int i=0; //xrhsimopoiw to i gia na perasw sth metavlhth pedio tis times "x" kai "y" enallaks
            Treenode newNode = new Treenode(p, null, null,"",null); //neos komvos
            Treenode previous = null; //prohgoumenos komvos
            Treenode current = head; //to current einai pointer sto head
            while(current != null){ //oso to current deixnei se komvo
                previous = current; //se kathe epanalipsi prepei to previous na deixnei ston prohgoumeno komvo pou episkeftikame

                if (i % 2==0){
                    //an to x tou p einai mikrotero apo to x tou current
                    if (current.item.x()> newNode.item.x()){ //tote synexizw thn anazhthsh sto aristero ypodentro
                        current = current.l;
                    }
                    //an to x tou p einai iso me to x tou current
                    else if (current.item.x() == newNode.item.x()){ //vgazw antistoixo mhnuma an yparxei o idios komvos/komvos me idio x(diplotupo)
                        if (current.item.y() == newNode.item.y()){ 
                            System.out.println("YPARXEI HDH"); 
                        }else{
                            System.out.println("DEN EPITREPONTAI DIPLOTYPA"); 
                        }
                        return; //telos epanalipsis
                    }
                    //an to x tou p einai megalytero apo to x tou current
                    else{ //tote synexizw thn anazhthsh sto dexio ypodentro
                        current = current.r;
                        flag=false;
                    }
                    newNode.pedio = "y";
                    i++;
                }else{
                    //an to y tou p einai mikrotero apo to x tou current
                    if (current.item.y()> newNode.item.y()){
                    //tote synexizw thn anazhthsh sto aristero ypodentro
                    current = current.l;
                    }else if (current.item.y() == newNode.item.y()){
                        if (current.item.x() == newNode.item.x()){ 
                            System.out.println("YPARXEI HDH"); 
                        }else{
                            System.out.println("DEN EPITREPONTAI DIPLOTYPA"); 
                        }
                        return;
                    }else{
                        current = current.r;
                        flag = false;
                    }
                    newNode.pedio = "x";
                    i++;
                }
            }

            //==================================DHMIOURGIA rect======================================//

            //an to flag einai false -> shmainei oti o eisagomenos komvos einai dexiou ypodentrou
            //an true -> aristerou 
            int x = previous.item.x(); //to previous deixnei ston patera tou neou eisagomenou komvou
            int y= previous.item.y();

            //analogws me thn timh tou 'pediou' tha perastoun diaforetika oi syntetagmenes sto rectangle toy kathe node
            if (flag==false) { //Right Subtree
                previous.r = newNode; 
                current= newNode;
                if (previous.pedio.equals("x")){  
                    current.rect = new Rectangle(x, previous.rect.xmax(),  previous.rect.ymin(), previous.rect.ymax());
                }else{
                    current.rect = new Rectangle(previous.rect.xmin(), previous.rect.xmax(), y, previous.rect.ymax());
                }
            } //an to current deixnei sto previous.r 
            else if (flag==true){ //Left Subtree
                previous.l = newNode; 
                current= newNode;
                if (previous.pedio.equals("x")){  
                    current.rect = new Rectangle(previous.rect.xmin(), x, previous.rect.ymin(), previous.rect.ymax());
                }else{
                    current.rect = new Rectangle(previous.rect.xmin(), previous.rect.xmax(), previous.rect.ymin(),y);
                }
            } 
            System.out.println("Point inserted to tree!");
        }
    }

    public boolean search(Point p) // does the tree contain p?
    {
        //to shmeio pou psaxnoume einai to p
        if (isEmpty()) return false;
        else{
            int i=0;
            Treenode newNode = new Treenode(p, null, null,"",null);
            Treenode current = head;

            //omoiws me thn insert
            while(current != null){
                if (i % 2==0){
                    if (current.item.x()> newNode.item.x()){
                        current = current.l;
                    }else if (current.item.x() == newNode.item.x()){
                        if (current.item.y() == newNode.item.y()) return true;
                    }else{
                        current = current.r;
                    }
                    i++;
                }else{
                    if (current.item.y()> newNode.item.y()){
                    current = current.l;
                    }else if (current.item.y() == newNode.item.y()){
                        if (current.item.x() == newNode.item.x()) return true;
                    }else{
                        current = current.r;
                    }
                    i++;
                }
            }
            return false;
        }
    }


    public Point nearestNeighbor(Point p) // point in the tree that is closest to p (null if tree is empty)
    {
        if (isEmpty()) return null;
        else{
            Point r =null;
            int min = p.squareDistanceTo(head.item); //ksekinaw me max = apostash tou p apo to head
            r = traverse(min, head.item , head,p); //kalw tin traverse
            return r;
        }
    }

    public static Point traverse(int min,  Point closest, Treenode node, Point p)
    {
        //se kathe anadromh tha eksetazw thn apostash tou p apo ton kathe komvo (min = miktoterh apostash, closest = o komvos) 

        if (node == null)
            return null;

        // Traverse left subtree
        if (node.l != null && p.squareDistanceTo(closest)<node.l.rect.squareDistanceTo(p)) {
            if(p.squareDistanceTo(node.l.item)<min) {
                min= p.squareDistanceTo(node.l.item);
                closest=node.l.item;
            }
            return traverse(min, closest,node.l,p); //anadromh
        }

        // Traverse right subtree        
        if (node.r != null && p.squareDistanceTo(closest)<node.r.rect.squareDistanceTo(p)) {
            if(p.squareDistanceTo(node.r.item)<min) {
                min= p.squareDistanceTo(node.r.item);
                closest=node.r.item;
            }
            return traverse(min,closest,node.r,p); //anadromh
        }
        return closest;
    }

    public List<Point> rangeSearch(Rectangle rect) // Returns a list with the Points that are contained in the rectangle
    {
        List <Point> r = new List <Point>(); 
        r = traverse1(r,head,rect);  
        return r;
    }

    public static List<Point> traverse1(List<Point> r, Treenode node,Rectangle rect)
    {
        
        if (node == null)
            return null;

        //synexizw stin eksetasi tou ypodentrou mono an to rect tou komvou exei koino shmeio me to rectangle pou dinei o xrhsths 
        if (node.l != null && node.l.rect.intersects(rect)){ traverse1(r,node.l,rect); }  // Traverse left subtree

        if (node.r != null && node.r.rect.intersects(rect)){ traverse1(r,node.r,rect); } // Traverse right subtree

        if (rect.contains(node.item)){ 
            r.add(node.item);
        }

        return r;
    }

    public static boolean isNumeric(String str) { 
        try {  
          Double.parseDouble(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
    }

    public static void main(String[] args) throws Exception
    {
        try{

        
        String filename = args[0];
		
		File f = new File(filename);
		try(BufferedReader br = new BufferedReader(new FileReader(f)))
                { /*diavazw to arxeio*/
			        String str = null;

                    //------------------------------------DIAXEIRISI PRWTHS GRAMMHS------------------------------------//
                    str = br.readLine();
                    String[] line = str.split(" ");
                    if (line.length!=1) throw new Exception(); //exception an oi times pou dinei sthn 1h grammh den einai mono 2 se plithos
                    int N = Integer.parseInt(line[0]); 

                    //dhmioyrgia 2D Tree
                    TwoDTree tree = new TwoDTree();

                    //----------------------------------SYNTETAGMENES TWN SHMEIWN-------------------------------------//
                    for (int i=0;i<N;i++){
                        str = br.readLine();
                        line = str.split(" ");
                        if (line.length!=2) throw new Exception(); //exception an oi times pou dinei sthn 1h grammh den einai mono 2 se plithos

                        int coordinateX = Integer.parseInt(line[0]);
                        int coordinateY = Integer.parseInt(line[1]);

                        if (coordinateX<0 || coordinateX>100 || coordinateY<0 || coordinateY>100 ) {throw new Exception();}
                        //dhmiourgia shmeiou typou Point
                        Point p = new Point(coordinateX, coordinateY);
                        tree.insert(p);
                    }
                    if (br.readLine() !=null){throw new Exception();}
                    
                    Scanner s = new Scanner(System.in);
                    System.out.println("\nFile is read...");

                    boolean isOver = false;
                    while(!isOver) {
                        System.out.println("================================ M E N U ================================");
                        System.out.println("=========================================================================");
                        System.out.println("1. Compute the size of the tree.");
                        System.out.println("2. Insert a new point.");
                        System.out.println("3. Search if a given point exists in the tree.");
                        System.out.println("4. Provide a query rectangle.");
                        System.out.println("5. Provide a query point.");
                        System.out.println("0. EXIT.");
                        System.out.println("=========================================================================\n");
                        System.out.print("Choose an option: ");

                        int answer = Integer.parseInt(s.nextLine());
                        switch(answer){
                            case 1:
                                System.out.println("The size of the tree is: "+tree.size());
                                break;
                            case 2:
                                System.out.print("Give coordinate x: ");
                                int cx = Integer.parseInt(s.nextLine());
                                System.out.print("Give coordinate y: ");
                                int cy = Integer.parseInt(s.nextLine());
                                tree.insert(new Point(cx, cy));
                                break;
                            case 3:
                                System.out.print("Give coordinate x: ");
                                cx = Integer.parseInt(s.nextLine());
                                System.out.print("Give coordinate y: ");
                                cy = Integer.parseInt(s.nextLine());
                                boolean a =tree.search(new Point(cx, cy));
                                if (a==true) System.out.println("Point found!");
                                else System.out.println("Point not found!");
                                break;
                            case 4:
                                System.out.print("Give xmin, xmax: ");
                                str = s.nextLine();
                                line = str.split(" ");
                                int xmin = Integer.parseInt(line[0]);
                                int xmax = Integer.parseInt(line[1]);
                                System.out.print("Give ymin, ymax: ");
                                str = s.nextLine();
                                line = str.split(" ");
                                int ymin = Integer.parseInt(line[0]);
                                int ymax = Integer.parseInt(line[1]);
                                Rectangle rect = new Rectangle(xmin, xmax, ymin, ymax);
                                List<Point> arr = new List<>();
                                arr = tree.rangeSearch(rect);
                                if (arr.size()==0){
                                    System.out.println("Kanena shmeio den periexetai.");
                                }
                                else{
                                    System.out.println("Ta shmeia pou periexontai einai ta: ");
                                    for(int i=0;i<arr.size();i++){
                                        System.out.println(arr.get(i));
                                    }
                                }
                                break;
                            case 5:
                                System.out.print("Give coordinate x: ");
                                cx = Integer.parseInt(s.nextLine());
                                System.out.print("Give coordinate y: ");
                                cy = Integer.parseInt(s.nextLine());
                                System.out.println(tree.nearestNeighbor(new Point(cx, cy)));
                                break;
                            case 0:
                                isOver = true;
                                break;
                        }
                    }

                }catch(IOException e) {
                    System.err.println(e);
                }
            }catch(Exception e){
                System.err.println("Lathos stin anagnosi!"); 
            }
                
    } //end of main
}