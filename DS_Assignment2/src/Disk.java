public class Disk implements Comparable<Disk> 
{
    private int size;
    private int id; 
    private List<String> folders = new List<>();

    public Disk(int id, List<String> folders){
        this.size = 1000000; //default size
        this.id = id;
        this.folders = folders;
    }
    
    public int getId(){
        return id;
    }

    public int getFreeSpace(){
        return size;
    }

    public void setSize(int size){
        this.size = size;
    }

    public void AddFolderToList(String folder){
        (this.folders).add(folder);
    }

    public List<String> getFolders(){
        return folders;
    }

    public int compareTo(Disk o) {
        if (this.size > o.size){
            return 1;
        }else if(this.size < o.size) {
            return -1;
        }else {return 0;}
    }
}
