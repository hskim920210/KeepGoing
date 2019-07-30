package com.tje.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tje.model.Comment;

@Repository
public class CommentDAO {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public CommentDAO(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	class CommentRowMapper implements RowMapper<Comment>{

		@Override
		public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
			Comment Comment=new Comment(
					rs.getInt(1),
					rs.getInt(2),
					rs.getInt(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getTimestamp(7));
			return Comment;
		}
	}
	
	public Comment selectOne(Comment model) {
		return this.jdbcTemplate.queryForObject("select * from Comment where Comment_id=?", 
				new CommentRowMapper(), 
				model.getComment_id());
	}
	
	public List<Comment> selectAll() {
		List<Comment> results=this.jdbcTemplate.query("select * from Comment",
				new CommentRowMapper());
		
		return results.isEmpty() ? null : results;
	}
	
	public List<Comment> select(Comment model) {
		List<Comment> results=this.jdbcTemplate.query("select * from Comment where board_id=? and topic=?",
				new CommentRowMapper(),
				model.getBoard_id(),
				model.getTopic());
		
		return results.isEmpty() ? null : results;
	}
	
	public int insert(Comment model) {
		
		return this.jdbcTemplate.update("insert into Comment values(0,?,?,?,?,?,now())",
				model.getBoard_id(),
				model.getTopic(),
				model.getMember_id(),
				model.getNickname(),
				model.getContent());
	}
}
