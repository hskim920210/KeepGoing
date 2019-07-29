package com.tje.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tje.model.Member;

@Repository
public class MemberDAO {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public MemberDAO(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	class MemberRowMapper implements RowMapper<Member>{

		@Override
		public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
			Member member=new Member(
					rs.getString(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7),
					rs.getString(8),
					rs.getString(9),
					rs.getInt(10),
					rs.getDouble(11),
					rs.getDouble(12),
					rs.getInt(13));
			return member;
		}
	}
	
	public Member selectOne(Member model) {
		return this.jdbcTemplate.queryForObject("select * from member where member_id=?", 
				new MemberRowMapper(), 
				model.getMember_id());
	}
	
	public Member selectOneNickName(Member model) {
		try {
			return this.jdbcTemplate.queryForObject("select * from member where nickname=?", 
					new MemberRowMapper(), 
					model.getNickname());
		}catch (Exception e) {
			return null;
		}
	}
	
	public List<Member> selectAll() {
		List<Member> results=this.jdbcTemplate.query("select * from member",
				new MemberRowMapper());
		
		return results.isEmpty() ? null : results;
	}
	
	public int insert(Member model) {
		
		return this.jdbcTemplate.update("insert into member values(?,?,?,?,?,?,?,?,?,?,?,?,?)",
				model.getMember_id(),
				model.getPassword(),
				model.getName(),
				model.getNickname(),
				model.getTel(),
				model.getAddress_post(),
				model.getAddress_basic(),
				model.getAddress_detail(),
				model.getInterest(),
				model.getAuth(),
				model.getLatitude(),
				model.getLongitude(),
				model.getMember_type());
	}
}
