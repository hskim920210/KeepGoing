package com.tje.repo;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tje.model.Board_Free;



@Repository
public class Board_freeDAO {
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public Board_freeDAO(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	class Board_FreeRowMapper implements RowMapper<Board_Free>{

		@Override
		public Board_Free mapRow(ResultSet rs, int rowNum) throws SQLException {
			Board_Free board_free=new Board_Free(
					rs.getInt(1),
					rs.getInt(2),
					rs.getInt(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getInt(7),
					rs.getTimestamp(8));
			
			return board_free;
		}
		
	}
	
	public int insert(Board_Free model) {
		
		return this.jdbcTemplate.update("insert into board_free values(0,6,?,?,?,?,0,now())",
				model.getCategory(),
				model.getTitle(),
				model.getContent(),
				model.getMember_id()
				);
				
				
	}
	
	public int update(Board_Free model) {
		
		return this.jdbcTemplate.update("update board_free set content = ? where board_id = ?)",
				model.getContent(),
				model.getBoard_id()
				);
				
				
	}
	
public int delete(Board_Free model) {
		
		return this.jdbcTemplate.delete("delete board_free set content = ? where board_id = ?)",
				model.getContent(),
				model.getBoard_id()
				);
				
				
	}
	
	
}
