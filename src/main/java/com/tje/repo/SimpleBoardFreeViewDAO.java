package com.tje.repo;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tje.model.*;
import java.util.*;

@Repository
public class SimpleBoardFreeViewDAO {
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public SimpleBoardFreeViewDAO(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	class SimpleBoardFreeViewRowMapper implements RowMapper<SimpleBoardFreeView>{
		@Override
		public SimpleBoardFreeView mapRow(ResultSet rs, int rowNum) throws SQLException {
			SimpleBoardFreeView simpleBoardFreeView=new SimpleBoardFreeView(
					rs.getInt(1),
					rs.getInt(2),
					rs.getString(3),
					rs.getInt(4),
					rs.getString(5),
					rs.getString(6),
					rs.getInt(7),
					rs.getInt(8),
					rs.getInt(9),
					rs.getDate(10));
			return simpleBoardFreeView;
		}
	}
	
	public SimpleBoardFreeView selectOne(SimpleBoardFreeView model) {
		String sql = "select * from SimpleBoardFreeView where board_id=?";
		return this.jdbcTemplate.queryForObject(sql, 
				new SimpleBoardFreeViewRowMapper(), 
				model.getBoard_id());
	}
	
	public List<SimpleBoardFreeView> selectAllOrdByDateDesc() {
		String sql = "select * from SimpleBoardFreeView order by write_date desc";
		List<SimpleBoardFreeView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardFreeViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardFreeView> selectAllOrdByCmtCntDesc() {
		String sql = "select * from SimpleBoardFreeView order by comment_cnt desc";
		List<SimpleBoardFreeView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardFreeViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardFreeView> selectAllOrdByLikeCntDesc() {
		String sql = "select * from SimpleBoardFreeView order by like_cnt desc";
		List<SimpleBoardFreeView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardFreeViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardFreeView> selectAllOrdByDisLikeCntDesc() {
		String sql = "select * from SimpleBoardFreeView order by dislike_cnt desc";
		List<SimpleBoardFreeView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardFreeViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	
	public List<SimpleBoardFreeView> selectAllOrdByDateAsc() {
		String sql = "select * from SimpleBoardFreeView order by write_date asc";
		List<SimpleBoardFreeView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardFreeViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardFreeView> selectAllOrdByCmtCntAsc() {
		String sql = "select * from SimpleBoardFreeView order by comment_cnt asc";
		List<SimpleBoardFreeView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardFreeViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardFreeView> selectAllOrdByLikeCntAsc() {
		String sql = "select * from SimpleBoardFreeView order by like_cnt asc";
		List<SimpleBoardFreeView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardFreeViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardFreeView> selectAllOrdByDisLikeCntAsc() {
		String sql = "select * from SimpleBoardFreeView order by dislike_cnt asc";
		List<SimpleBoardFreeView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardFreeViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
}
