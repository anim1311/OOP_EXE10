public class RegularPolygon extends GeometricObject {
    
    private int n = 3;
    private double length = 1;
    private double x = 0;
    private double y = 0;   

    RegularPolygon() {
    
    }

    RegularPolygon(int n, double length) {
        this();
        if(n > 2 ) {
            this.n = n;
            
        }
        if(length >0){
            this.length = length;
        }
    }

    RegularPolygon(int n, double length, double x, double y) {
        this(n, length);
        this.x = x;
        this.y = y;
        if(n > 2 ) {
            this.n = n; 
        }
        if(length >0){
            this.length = length;
        }

    }
    RegularPolygon(String color, boolean filled,int n, double length, double x, double y, double theta ){
        this(n, length, x, y);
        this.setColor(color);
        this.setFilled(filled);
        this.setTheta(theta);
    }

    public int getN() {
        return n;
    }

    public double getSide() {
        return length;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setN(int n){
        this.n = n;
    }

    public void setSide(double length){
        this.length = length;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public double getPerimeter() {
        return n * length;
    }

    public double getArea() {
        return (n * length * length) / (4 * Math.tan(Math.PI / n));
    }

    public String toString(){
        
        String result = "";
        double angle = this.getTheta();
        double tempX = this.x;
        double tempY = this.y;

        for(int i = 0; i< n;i++){

            result += String.format("(%.2f,%.2f),", tempX, tempY);
            //System.out.println("X: " + tempX + " Y: " + tempY);
            tempX += length * Math.cos(angle);
            tempY += length * Math.sin(angle);
            angle += 2 * Math.PI / n;
        }

        result = result.substring(0, result.length() - 1);

        return result;
    }


}