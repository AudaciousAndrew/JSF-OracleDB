package Model;

public class PointEntity {

    private int id;
    private double x;
    private double y;
    private double r;
    private boolean inArea;

    public PointEntity(){}

    public PointEntity(double x, double y, double r){
        this.x = x;
        this.y = y;
        this.r = r;
        inArea = checkArea();
    }

    public PointEntity(int id, double x, double y, double r){
        this.id = id;
        this.x = x;
        this.y = y;
        this.r = r;
        inArea = checkArea();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean isInArea() {
        return inArea;
    }

    public boolean checkArea(){
        if((x < 0 && x >=-r) &&
                ((y > 0 && y <= r/2) || (y < 0 && y >= -r && (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2))))) return true;
        if(x > 0 && x < r/2 && y < 0 && y >= -r/2 && y>=(x-r/2)) return true;
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PointEntity that = (PointEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.x, x) != 0) return false;
        if (Double.compare(that.y, y) != 0) return false;
        if (Double.compare(that.r, r) != 0) return false;
        return inArea == that.inArea;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(x);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(r);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (inArea ? 1 : 0);
        return result;
    }
}
