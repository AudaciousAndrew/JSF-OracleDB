package Model;

import com.sun.rowset.JdbcRowSetImpl;
import com.sun.xml.internal.ws.policy.spi.PrefixMapper;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.rowset.JdbcRowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("postgres");
        dataSource.setCurrentSchema("public");
        dataSource.setUser("postgres");
        dataSource.setPassword("123");
        try {
            String sql = "CREATE TABLE IF NOT EXISTS point (\n" +
                    "  id     SERIAL PRIMARY KEY,\n" +
                    "  x      DOUBLE PRECISION,\n" +
                    "  y      DOUBLE PRECISION,\n" +
                    "  r      DOUBLE PRECISION,\n" +
                    "  inArea BOOLEAN\n" +
                    ");\n";
            dbConnection = dataSource.getConnection();
            final PreparedStatement statement = dbConnection.prepareStatement(sql);
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
            statement.setDouble(1, p.getX());
            statement.setDouble(2, p.getY());
            statement.setDouble(3, p.getR());
            statement.setBoolean(4, p.isInArea());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<PointEntity> getAll() throws SQLException {
        String sql = "select * from point;";
        PreparedStatement statement = dbConnection.prepareStatement(sql);
        statement.execute();

        ResultSet rs = statement.getResultSet();
        JdbcRowSet jdbcRowSet = new JdbcRowSetImpl(rs);
        ArrayList<PointEntity> list = new ArrayList<PointEntity>();
        while(jdbcRowSet.next()) {
            int Id = Integer.parseInt(jdbcRowSet.getString("id"));
            double X = Double.parseDouble(jdbcRowSet.getString("x"));
            double Y = Double.parseDouble(jdbcRowSet.getString("y"));
            double R = Double.parseDouble(jdbcRowSet.getString("r"));
            list.add(new PointEntity(Id, X, Y, R));
        }
        return list;
    }


}
