public abstract class GeometricObject {
    private String color = "white";
    private double x = 0;
    private double y = 0;
    private double theta = 0;
    private boolean filled;
    private java.util.Date dateCreated;

    /**Default construct*/
    public GeometricObject() {
        dateCreated = new java.util.Date();
    }

    /**Construct a geometric object*/
    public GeometricObject(String color, boolean filled) {
        this();    
        this.color = color;
        this.filled = filled;
    }

    /**Getter method for color*/
    public String getColor() {
        return color;
    }

    /**Setter method for color*/
    public void setColor(String color) {
        this.color = color;
    }

    /**Getter method for filled. Since filled is boolean,
         so, the get method name is isFilled*/
    public boolean isFilled() {
        return filled;
    }

    /**Setter method for filled*/
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    public void setDateCreated(java.util.Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

    public double getTheta() {
        return theta;
    }
    public void setTheta(double theta) {
        this.theta = theta;
    }

    /**Abstract method findArea*/
    public abstract double getArea();

    /**Abstract method getPerimeter*/
    public abstract double getPerimeter();

    public java.util.Date getDateCreated() {
        return dateCreated;
    }

    public String toString() {
        return "created on " + dateCreated + "\ncolor: " + color + " and filled: " + filled;
    }

    public void rotate(double angle){
        this.theta += angle;
    }
}
