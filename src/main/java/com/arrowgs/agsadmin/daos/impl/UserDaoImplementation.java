package com.arrowgs.agsadmin.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.arrowgs.agsadmin.daos.UserDao;
import com.arrowgs.agsadmin.entities.User;


@Repository
public class UserDaoImplementation implements UserDao{
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	

	
	//Jdbc classes
	class UserRowMapper implements RowMapper<User>{

		@Override
		public User mapRow(ResultSet rs, int row) throws SQLException {
			User myUser = new User();
			myUser.setEmail(rs.getString(1));
			myUser.setType(rs.getString(2));			
			myUser.setName(rs.getString(3));
			myUser.setPassword(rs.getString(4));
			myUser.setId(rs.getInt(5));
			return myUser;
		}
		
	}
	
	class UserRowExtractor implements ResultSetExtractor<User>{

		@Override
		public User extractData(ResultSet rs) throws SQLException, DataAccessException {
			
			return rs.next() ? (new UserRowMapper()).mapRow(rs,0) : null;
		}
		
	}
	
	
    //Connection 
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);				
		
	}
	
	//All kind of getters from data base
	
	@Override
	public List<User> getUsers() {
		String query= "SELECT * from ususarios";
		
		return jdbcTemplate.query(query, new UserRowMapper());
	}	

	@Override
	public List<User> getUserByFilter(String email, Integer way) {
		String sql = "SELECT * FROM usuarios WHERE email";
		if(way.intValue()==1){
			sql = sql + " = :email";
		}else{
			sql = sql + " LIKE :email";
			switch(way){
			case 3:
				email = email + "%";				
				break;
				
			case 4:
				email = "%" + email;
				break;
				
			default:
				email = "%" + email + "%";
			}
		}
		SqlParameterSource paramMap = new MapSqlParameterSource("email",email);
		return jdbcTemplate.query(sql, paramMap, new UserRowMapper());
	}

	
	@Override
	public User getUserByEmail(String email) {
	
		String query = "SELECT * FROM usuarios WHERE email = :email";
		SqlParameterSource userMap = new MapSqlParameterSource("email",email);
		return jdbcTemplate.query(query, userMap, new UserRowExtractor());
	}

	
	
	//Update user
	@Override
	public void modifyUser(User user) {
		String sql = "UPDATE usuarios SET rol = :rol, nombre = :nombre";
		Map<String,Object> paramMap = new HashMap<>();
		if(user.getPassword()!=null){
			sql = sql + ", password = :pass";
			paramMap.put("pass", user.getPassword());
		}
		sql = sql + " WHERE id = :id";		
		paramMap.put("nombre", user.getName());
		paramMap.put("rol", user.getType());
		paramMap.put("id", user.getId());
		
		jdbcTemplate.update(sql, paramMap);
		
	}

	//Delete user
	@Override
	public void removeUserByEmail(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyUserRol(User user) {
		String sql = "UPDATE usuarios SET rol = :rol WHERE id = :id";
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("rol", user.getType());
		paramMap.put("id", user.getId());
		
		jdbcTemplate.update(sql, paramMap);
		
	}


}
