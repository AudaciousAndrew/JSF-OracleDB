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

   private double x;
   private double y;
   private double r;
   private ArrayList<PointEntity> points;
   private final PointDB db = new PointDB();


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

   public ArrayList<PointEntity> getPoints() throws SQLException {
       points = db.getAll();
       return points;
   }

    public void addNewPoint(){
        PointEntity point = new PointEntity(this.x, this.y,this.r);
        db.addPoint(point);
    }

}
