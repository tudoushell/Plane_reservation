package util;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapperObject {
	Object rowMapperObject(ResultSet rs) throws SQLException; 
}
