package Model;

import com.sun.rowset.JdbcRowSetImpl;
import com.sun.xml.internal.ws.policy.spi.PrefixMapper;
import oracle.jdbc.*;
import oracle.jdbc.pool.OracleDataSource;
import org.postgresql.ds.PGSimpleDataSource;
import javax.sql.rowset.JdbcRowSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.primefaces.component.remotecommand.RemoteCommand.PropertyKeys.name;

public class PointDB  {
    private Connection dbConnection;

    public PointDB(){
        connectDBPostgres();
    }

   private void connectDBPostgres()
   {
       try {
           PGSimpleDataSource dataSource = new PGSimpleDataSource();
           dataSource.setServerName("pg");
           dataSource.setDatabaseName("studs");
           //dataSource.setCurrentSchema("public");
           dataSource.setUser("s225092");
           dataSource.setPassword("123");
           String sql = "CREATE TABLE point (\n" +
                   "  id     SERIAL PRIMARY KEY,\n" +
                   "  x      FLOAT(49) NOT NULL,\n" +
                   "  y      FLOAT(49) NOT NULL,\n" +
                   "  r      FLOAT(49) NOT NULL,\n" +
                   "  inArea SMALLINT NOT NULL CHECK (inarea IN (0,1))\n" +
                   ");\n";

           dbConnection = dataSource.getConnection();
           PreparedStatement statement = dbConnection.prepareStatement(sql);
           statement.execute();
       }
       catch (SQLException e) {
           e.printStackTrace();
       }
   }

    public void addPoint(PointEntity p){
        try{
            float x= p.getX();
            float y = p.getY();
            float r = p.getR();
            byte i = p.isInArea();
            String sql = "insert into point (x, y, r, inArea) values (" + x + ", " + y+", "+r+", "+i+")";

            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<PointEntity> getAll() throws SQLException {
        String sql = "select * from point";
        Statement stmt = dbConnection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ArrayList<PointEntity> list = new ArrayList<PointEntity>();
        while(rs.next()) {
            int Id = Integer.parseInt(rs.getString("ID"));
            float X = Float.parseFloat(rs.getString("X"));
            float Y = Float.parseFloat(rs.getString("Y"));
            float R = Float.parseFloat(rs.getString("R"));
            list.add(new PointEntity(Id, X, Y, R));
        }
        return list;
    }


}
