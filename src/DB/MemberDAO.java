package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberDAO {

	private Connect c = Connect.getInstance();
	private Connection conn = c.connect();
	private Statement stmt;
	private ResultSet rs;
	private MemberDTO d = null;
	private static MemberDAO DAOobj;

	private MemberDAO() {
	}

	public static MemberDAO getInstance() {
		if (DAOobj == null) {
			DAOobj = new MemberDAO();
		}
		return DAOobj;
	}

	public boolean InsertMember(MemberDTO m) {
		boolean result = false;
		c.orclelode();
		try {
			String sql = "insert into member values(?,?,?,?,?,1)";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, m.getId());
			psmt.setString(2, m.getName());
			psmt.setString(3, m.getPwd());
			psmt.setString(4, m.getAdr());
			psmt.setString(5, m.getCell());
			int r = psmt.executeUpdate();

			if (r > 0) {
				result = true;
			}
			psmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public Boolean idchk(MemberDTO member) throws Exception {
		boolean result = false;
		c.orclelode();
		try {
			String sql = "SELECT * FROM member where id=?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, member.getId());
			int r = psmt.executeUpdate();

			if (r > 0) {
				result = true;
			}
			psmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public MemberDTO loginchk(String id) {
		c.orclelode();
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * from member where id='" + id + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				MemberDTO member = new MemberDTO();
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setPwd(rs.getString("pwd"));
				member.setAdr(rs.getString("adr"));
				member.setCell(rs.getString("cell"));
				member.setLv(rs.getInt("lv"));
				return member;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return d;

	}

}
