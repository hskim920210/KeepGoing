package com.tje.repo;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tje.model.*;
import com.tje.repo.*;

@Repository
public class SimpleBoardReviewViewDAO {
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public SimpleBoardReviewViewDAO(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	class SimpleBoardReviewViewRowMapper implements RowMapper<SimpleBoardReviewView>{
		@Override
		public SimpleBoardReviewView mapRow(ResultSet rs, int rowNum) throws SQLException {
			SimpleBoardReviewView simpleBoardReviewView=new SimpleBoardReviewView(
					rs.getInt(1),
					rs.getInt(2),
					rs.getString(3),
					rs.getInt(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7),
					rs.getString(8),
					rs.getInt(9),
					rs.getInt(10),
					rs.getInt(11),
					rs.getDate(12));
			return simpleBoardReviewView;
		}
	}
	
	public SimpleBoardReviewView selectOne(SimpleBoardReviewView model) {
		String sql = "select * from SimpleBoardReviewView where board_id=?";
		return this.jdbcTemplate.queryForObject(sql, 
				new SimpleBoardReviewViewRowMapper(), 
				model.getBoard_id());
	}
	
	public List<SimpleBoardReviewView> selectAllOrdByDateDesc() {
		String sql = "select * from SimpleBoardReviewView order by write_date desc";
		List<SimpleBoardReviewView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardReviewViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardReviewView> selectAllOrdByCmtCntDesc() {
		String sql = "select * from SimpleBoardReviewView order by comment_cnt desc";
		List<SimpleBoardReviewView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardReviewViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardReviewView> selectAllOrdByLikeCntDesc() {
		String sql = "select * from SimpleBoardReviewView order by like_cnt desc";
		List<SimpleBoardReviewView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardReviewViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardReviewView> selectAllOrdByDisLikeCntDesc() {
		String sql = "select * from SimpleBoardReviewView order by dislike_cnt desc";
		List<SimpleBoardReviewView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardReviewViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	
	public List<SimpleBoardReviewView> selectAllOrdByDateAsc() {
		String sql = "select * from SimpleBoardReviewView order by write_date asc";
		List<SimpleBoardReviewView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardReviewViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardReviewView> selectAllOrdByCmtCntAsc() {
		String sql = "select * from SimpleBoardReviewView order by comment_cnt asc";
		List<SimpleBoardReviewView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardReviewViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardReviewView> selectAllOrdByLikeCntAsc() {
		String sql = "select * from SimpleBoardReviewView order by like_cnt asc";
		List<SimpleBoardReviewView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardReviewViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardReviewView> selectAllOrdByDisLikeCntAsc() {
		String sql = "select * from SimpleBoardReviewView order by dislike_cnt asc";
		List<SimpleBoardReviewView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardReviewViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	
}
