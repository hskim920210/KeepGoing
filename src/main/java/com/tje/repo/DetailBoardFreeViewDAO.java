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
public class DetailBoardFreeViewDAO {
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public DetailBoardFreeViewDAO(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	class DetailBoardFreeViewRowMapper implements RowMapper<DetailBoardFreeView>{
		@Override
		public DetailBoardFreeView mapRow(ResultSet rs, int rowNum) throws SQLException {
			DetailBoardFreeView detailBoardFreeView=new DetailBoardFreeView(
					rs.getInt(1),
					rs.getInt(2),
					rs.getString(3),
					rs.getString(4),
					rs.getInt(5),
					rs.getString(6),
					rs.getString(7),
					rs.getInt(8),
					rs.getInt(9),
					rs.getInt(10),
					rs.getDate(11));
			return detailBoardFreeView;
		}
	}
	
	public DetailBoardFreeView selectOne(DetailBoardFreeView model) {
		String sql = "select * from DetailBoardFreeView where board_id=?";
		return this.jdbcTemplate.queryForObject(sql, 
				new DetailBoardFreeViewRowMapper(), 
				model.getBoard_id());
	}
	
	
	
	
	//좋아요 카운트
	public List<DetailBoardFreeView> selectAllOrdByLikeCntAsc() {
		String sql = "select * from DetailBoardFreeView order by like_cnt asc";
		List<DetailBoardFreeView> results=this.jdbcTemplate.query(sql,
				new DetailBoardFreeViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	//좋아요 카운트
	public List<DetailBoardFreeView> selectAllOrdByDisLikeCntAsc() {
		String sql = "select * from DetailBoardFreeView order by dislike_cnt asc";
		List<DetailBoardFreeView> results=this.jdbcTemplate.query(sql,
				new DetailBoardFreeViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	

	
	// 게시판 삭제
	public int delete(DetailBoardFreeView model) {
				return this.jdbcTemplate.update("delete from board_free where board_id = ?",
	
				model.getBoard_id()
					);
	}
	
	
	// 게시판 수정
	public int update(DetailBoardFreeView model) {
		
				return this.jdbcTemplate.update("update board_free set Content = ? , Title = ? where board_id = ?",
				model.getContent(),
				model.getTitle(),
				model.getBoard_id()
				);
				
				
	}


	
}



















