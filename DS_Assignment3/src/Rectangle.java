public class Rectangle {
    private int xmin,xmax,ymin,ymax;

    //constructor
    public Rectangle(int xmin, int xmax, int ymin, int ymax){
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
    }

    public Rectangle(){
    }

    public int xmin() // minimum x-coordinate of rectangle
    {
        return xmin;
    }
    public int ymin() // minimum y-coordinate of rectangle
    {
        return ymin;
    }
    public int xmax() // maximum x-coordinate of rectangle
    {
        return xmax;
    }

    public int ymax() // maximum y-coordinate of rectangle
    {

        return ymax;
    }

    public boolean contains(Point p) //does p belong to the rectangle?
    {
        if ((p.x() <=xmax && p.x() >=xmin) && (p.y()>=ymin && p.y() <=ymax)){
            return true;
        }
        return false;
    }

    public boolean intersects(Rectangle that) // do the two rectangles intersect?
    {
        //tha vrw pote den enwnontai to ena me to allo

        //1. Den enwnontai otan to ena einai aristera apo to allo
        if (xmax()<that.xmin() || xmin>that.xmax()) return false;
        
        //2. Den enwnontai otan to ena einai panw apo to allo
        if (ymax() <that.ymin() || ymin() > that.ymax()) return false;
        
        return true;
    }
    
    public double distanceTo(Point p) // Euclidean distance from p to closest point in rectangle
    {
        if (p.x()==0 && p.y()==0) return 0;
        Point r = VresSimeio(p); //to r einai to shmeio toy kyklou poy einai pio kontino
        return r.distanceTo(p);
    }
    
    private Point VresSimeio(Point p){ //vriskei to shmeio tou rectangle pou einai pio konta sto p
        int xclosest=0, yclosest=0;

        if (!this.contains(p)){ 
            if (p.x()>=xmin() && p.x() <=xmax()) {
                xclosest = p.x();
                int min1 = Math.abs(p.y()-ymin());
                yclosest = ymin();
                if (Math.abs(p.y()-ymax())<min1) yclosest = ymax();
            } else if (p.y() >= ymin() && p.y() <=ymax()){
                yclosest = p.y();
                int min2 = Math.abs(p.x() - xmin());
                xclosest = xmin();
                if (Math.abs(p.x() - xmax()) < min2) xclosest = xmax();  
            } else {
                if (p.y()>ymax()) yclosest = ymax();
                else if (p.y() <ymin()) yclosest = ymin();
    
                if (p.x() >xmax()) xclosest=xmax();
                else if (p.x() <xmin()) xclosest = xmin();
            }
        } 
        Point shmeio = new Point(xclosest, yclosest);
        System.out.println(shmeio.toString());
        return shmeio;
    }


    public int squareDistanceTo(Point p) // square of Euclidean distance from p to closest point in rectangle
    {
        if (p.x()==0 && p.y()==0) return 0;
        Point r = VresSimeio(p); //to r einai to shmeio toy kyklou poy einai pio kontino
        return r.squareDistanceTo(p);
    }
    
    public String toString() // string representation: [xmin, xmax] × [ymin, ymax]
    {
        return "[" + xmin() + "," + xmax() + "]" + " x " + "[" + ymin() + "," + ymax() + "]"; 
    }

}