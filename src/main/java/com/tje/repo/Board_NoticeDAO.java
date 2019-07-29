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
public class Board_NoticeDAO {
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public Board_NoticeDAO(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	class Board_noticeRowMapper implements RowMapper<Board_Notice>{
		@Override
		public Board_Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
			Board_Notice board_notice=new Board_Notice(
					rs.getInt(1),
					rs.getInt(2),
					rs.getInt(3),
					rs.getString(4),
					rs.getString(5),
					rs.getDate(6));
			return board_notice;
		}
	}
	
	public Board_Notice selectOne(Board_Notice model) {
		String sql = "select * from board_notice where board_id=?";
		return this.jdbcTemplate.queryForObject(sql, 
				new Board_noticeRowMapper(), 
				model.getBoard_id());
	}
	
	public List<Board_Notice> selectAllOrdByDateDesc() {
		String sql = "select * from board_notice order by write_date desc";
		List<Board_Notice> results=this.jdbcTemplate.query(sql,
				new Board_noticeRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<Board_Notice> selectAllOrdByDateAsc() {
		String sql = "select * from board_notice order by write_date asc";
		List<Board_Notice> results=this.jdbcTemplate.query(sql,
				new Board_noticeRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<Board_Notice> selectAllOrdByBoard_IdDesc() {
		String sql = "select * from board_notice order by board_id desc";
		List<Board_Notice> results=this.jdbcTemplate.query(sql,
				new Board_noticeRowMapper());
		return results.isEmpty() ? null : results;
	}
}
