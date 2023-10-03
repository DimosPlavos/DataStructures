public class Point {
    private int x, y;

    //consturctor
    Point(int x, int y) {
        this.x = x;this.y = y;
    }


    public int x(){ // return the x-coordinate
        return x;
    } 

    public int y(){ // return the y-coordinate
        return y;
    } 

    public double distanceTo(Point z){ // Euclidean distance between two points d = √[ (x2– x1)^2 + (y2– y1)^2]
        double dx = x - z.x(), dy = y - z.y();
        return Math.sqrt(dx*dx + dy*dy);
    } 

    public int squareDistanceTo(Point z){  // square of the Euclidean distance between two points
        int dx = x - z.x(), dy = y - z.y();
        return dx*dx + dy*dy;
    } 

    public String toString(){ // string representation: (x, y)
        return "(" + x() + "," + y() + ")"; 
    } 
}
