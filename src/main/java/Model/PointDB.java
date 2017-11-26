package Model;

import com.sun.rowset.JdbcRowSetImpl;
import com.sun.xml.internal.ws.policy.spi.PrefixMapper;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.rowset.JdbcRowSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.primefaces.component.remotecommand.RemoteCommand.PropertyKeys.name;

public class PointDB  {
    private Connection dbConnection;

    public PointDB(){
        connectDB();
    }

    private void connectDB()
    {
//        PGSimpleDataSource dataSource = new PGSimpleDataSource();
//        dataSource.setServerName("localhost");
//        dataSource.setDatabaseName("postgres");
//        dataSource.setCurrentSchema("public");
//        dataSource.setUser("postgres");
//        dataSource.setPassword("123");

        try {
            dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.10.10:1521:orbis", "s225092", "jccRQac2");
            String sql = "CREATE TABLE IF NOT EXISTS point (\n" +
                    "  id     NUMBER(10) PRIMARY KEY,\n" +
                    "  x      FLOAT(49) NOT NULL,\n"     +
                    "  y      FLOAT(49) NOT NULL,\n" +
                    "  r      DOUBLE PRECISION,\n" +
                    "  inArea NUMBER(1) NOT NULL CHECK (inarea IN (0,1))\n" +
                    ");\n";

            String sql2 = "CREATE SEQUENCE records_id_seq START WITH 1;\n" +
            "CREATE OR REPLACE TRIGGER point_id_trigger\n" +
            "BEFORE INSERT ON records\n" +
            "FOR EACH ROW\n" +
            "        BEGIN\n" +
            "SELECT records_id_seq.NEXTVAL\n" +
            "INTO   :NEW.id\n" +
            "FROM   dual;\n" +
            "END;\n";
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.execute();
            statement = dbConnection.prepareStatement(sql2);
            statement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPoint(PointEntity p){
        try{
            String sql = "insert into point (x, y, r, inArea) values (?, ?, ?, ?);";
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setFloat(1, p.getX());
            statement.setFloat(2, p.getY());
            statement.setFloat(3, p.getR());
            statement.setByte(4, p.isInArea());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<PointEntity> getAll() throws SQLException {
        String sql = "select * from point order by id ASC;";
        PreparedStatement statement = dbConnection.prepareStatement(sql);
        statement.execute();

        ResultSet rs = statement.getResultSet();
        JdbcRowSet jdbcRowSet = new JdbcRowSetImpl(rs);
        ArrayList<PointEntity> list = new ArrayList<PointEntity>();
        while(jdbcRowSet.next()) {
            int Id = Integer.parseInt(jdbcRowSet.getString("id"));
            float X = Float.parseFloat(jdbcRowSet.getString("x"));
            float Y = Float.parseFloat(jdbcRowSet.getString("y"));
            float R = Float.parseFloat(jdbcRowSet.getString("r"));
            list.add(new PointEntity(Id, X, Y, R));
        }
        return list;
    }


}
