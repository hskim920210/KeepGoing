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
public class Board_FaqDAO {
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public Board_FaqDAO(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	class Board_FAQRowMapper implements RowMapper<Board_Faq>{
		@Override
		public Board_Faq mapRow(ResultSet rs, int rowNum) throws SQLException {
			Board_Faq board_qna=new Board_Faq(
					rs.getInt(1),
					rs.getInt(2),
					rs.getInt(3),
					rs.getString(4),
					rs.getString(5));
			return board_qna;
		}
	}
	
	public Board_Faq selectOne(Board_Notice model) {
		String sql = "select * from board_qna where board_id=?";
		return this.jdbcTemplate.queryForObject(sql, 
				new Board_FAQRowMapper(), 
				model.getBoard_id());
	}
	
	public List<Board_Faq> selectAllOrdByDateDesc() {
		String sql = "select * from board_qna order by write_date desc";
		List<Board_Faq> results=this.jdbcTemplate.query(sql,
				new Board_FAQRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<Board_Faq> selectAllOrdByDateAsc() {
		String sql = "select * from board_qna order by write_date asc";
		List<Board_Faq> results=this.jdbcTemplate.query(sql,
				new Board_FAQRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<Board_Faq> selectAllOrdByBoard_IdDesc() {
		String sql = "select * from board_qna order by board_id desc";
		List<Board_Faq> results=this.jdbcTemplate.query(sql,
				new Board_FAQRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	
	public int insert(Board_Faq model) {
		return this.jdbcTemplate.update("insert into board_qna values(0,5,?,?,?)",
				model.getCategory(),
				model.getTitle(),
				model.getContent()
				);
	}

}
