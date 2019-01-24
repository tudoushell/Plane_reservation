package util;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionImpl implements Transaction{

	public void start() throws SQLException {
		Connection conn = null;
		try {
			conn = JDBCUtil.getConnection();
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void rollback() throws SQLException {
		Connection conn = null;
		try {
			conn = JDBCUtil.getConnection();
			conn.rollback();//回滚后事务结束
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally{
			JDBCUtil.close(null, null, conn);
		}
	}

	public void commit() throws SQLException {
		Connection conn = null;
		try {
			conn = JDBCUtil.getConnection();
			conn.commit();//提交后事务结束
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally{
			JDBCUtil.close(null, null, conn);
		}
	}	
}
