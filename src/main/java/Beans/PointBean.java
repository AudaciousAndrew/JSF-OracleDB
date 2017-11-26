package Beans;

import Model.*;
import com.sun.istack.internal.NotNull;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.SQLException;
import java.util.ArrayList;

@ManagedBean(name = "point")
@SessionScoped

public class PointBean {

   private float x;
   private float y;
   private float r;
   private ArrayList<PointEntity> points;
   private final PointDB db = new PointDB();


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

   public ArrayList<PointEntity> getPoints() throws SQLException {
       points = db.getAll();
       return points;
   }

    public void addNewPoint(){
        PointEntity point = new PointEntity(this.x, this.y,this.r);
        db.addPoint(point);
    }

}
