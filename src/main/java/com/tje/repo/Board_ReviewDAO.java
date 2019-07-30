package com.tje.repo;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tje.model.*;

@Repository
public class Board_ReviewDAO {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public Board_ReviewDAO(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	class Board_ReviewRowMapper implements RowMapper<Board_Review>{

		@Override
		public Board_Review mapRow(ResultSet rs, int rowNum) throws SQLException {
			Board_Review board_Review=new Board_Review(
					rs.getInt(1),
					rs.getInt(2),
					rs.getInt(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7),
					rs.getInt(8),
					rs.getTimestamp(9));
			return board_Review;
		}
		
	}
	
	public int insert(Board_Review model) {
		
		return this.jdbcTemplate.update("insert into Board_Review values(0,?,?,?,?,?,?,?,now())",
				model.getBoard_id(),
				model.getTopic(),
				model.getCategory(),
				model.getTitle(),
				model.getContent(),
				model.getMember_id(),
				model.getImage(),
				model.getView_cnt(),
				model.getWrite_date());
	}
}
