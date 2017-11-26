package Model;

public class PointEntity {

    private int id;
    private float x;
    private float y;
    private float r;
    private byte inArea;

    public PointEntity(){}

    public PointEntity(float x, float y, float r){
        this.x = x;
        this.y = y;
        this.r = r;
        inArea = checkArea();
    }

    public PointEntity(int id, float x, float y, float r){
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

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public byte isInArea() {
        return inArea;
    }

    public byte getInArea() {
        return inArea;
    }

    public byte checkArea(){
        if((x <= 0 && x >=-r) &&
                ((y >= 0 && y <= r/2) || (y <= 0 && y >= -r && (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2))))) return 1;
        if(x >= 0 && x <= r/2 && y <= 0 && y >= -r/2 && y>=(x-r/2)) return 1;
        return 0;
    }

}
