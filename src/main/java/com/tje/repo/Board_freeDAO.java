package com.tje.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.tje.model.Board_Free;
import com.tje.model.DetailBoardFreeView;



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
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		this.jdbcTemplate.update(new PreparedStatementCreator() {			
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		
				PreparedStatement pstmt = 
					con.prepareStatement(
							"insert into board_free values(0,6,?,?,?,?,0,now())", 
						 new String[]{"board_id"});
				pstmt.setInt(1, model.getCategory());
				pstmt.setString(2, model.getTitle());
				pstmt.setString(3, model.getContent());
				pstmt.setString(4, model.getMember_id());
				return pstmt;
			}
		}, keyHolder);
		
		return keyHolder.getKey().intValue();
		
		
		
//		return this.jdbcTemplate.update("insert into board_free values(0,6,?,?,?,?,0,now())",
//				model.getCategory(),
//				model.getTitle(),
//				model.getContent(),
//				model.getMember_id()
//				);
				
				
	}
	
//	public int update(Board_Free model) {
//		
//		return this.jdbcTemplate.update("update board_free set content = ? where board_id = ?)",
//				model.getContent(),
//				model.getBoard_id()
//				);
//				
//				
//	}
//	
//	// 게시판 삭제
//		public int delete(DetailBoardFreeView model) {
//					return this.jdbcTemplate.update("delete from board_free where board_id = ?",
//					model.getContent(),
//					model.getBoard_id()
//						);
//		}
//	

	
	
}
