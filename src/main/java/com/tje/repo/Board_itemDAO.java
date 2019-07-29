package com.tje.repo;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tje.model.Board_Item;

@Repository
public class Board_itemDAO {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public Board_itemDAO(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	class Board_itemRowMapper implements RowMapper<Board_Item>{

		@Override
		public Board_Item mapRow(ResultSet rs, int rowNum) throws SQLException {
			Board_Item board_Item=new Board_Item(
					rs.getInt(1),
					rs.getInt(2),
					rs.getInt(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getInt(7),
					rs.getString(8),
					rs.getString(9),
					rs.getInt(10),
					rs.getTimestamp(11));
			
			return board_Item;
		}
		
	}
	
	public int insert(Board_Item model) {
		
		return this.jdbcTemplate.update("insert into board_item values(0,?,?,?,?,?,?,?,?,0,now())",
				model.getTopic(),
				model.getCategory(),
				model.getTitle(),
				model.getContent(),
				model.getMember_id(),
				model.getNumber(),
				model.getPrice(),
				model.getImage());
	}
}
