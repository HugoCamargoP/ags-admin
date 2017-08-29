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
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.arrowgs.agsadmin.daos.AddressDao;
import com.arrowgs.agsadmin.entities.Address;
import com.arrowgs.agsadmin.entities.IdNameTable;

@Repository
public class AddressDaoImplementation implements AddressDao{
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert addressInsertActor;
	
	class AddressRowMapper implements RowMapper<Address>{

		@Override
		public Address mapRow(ResultSet rs, int col) throws SQLException {
			Address address = new Address();
			address.setId(rs.getInt(1));
			address.setUser(rs.getInt(2));
			address.setCountry(rs.getInt(3));
			address.setDetail1(rs.getString(4));
			address.setDetail2(rs.getString(5));
			address.setCity(rs.getString(6));
			address.setState(rs.getString(7));
			address.setZip(rs.getString(8));
			address.setPhone(rs.getString(9));
			return address;
		}
		
	}
	
	class AddressRowExtractor implements ResultSetExtractor<Address>{

		@Override
		public Address extractData(ResultSet rs) throws SQLException, DataAccessException {
			return rs.next() ? (new AddressRowMapper()).mapRow(rs, 0) : null;
		}
		
	}
	
	class IdNameTableRowMapper implements RowMapper<IdNameTable>{

		@Override
		public IdNameTable mapRow(ResultSet rs, int col) throws SQLException {
			IdNameTable country = new IdNameTable();
			country.setId(rs.getInt(1));
			country.setName(rs.getString(2));
			return country;
		}		
	}
	
	class IdNameTableRowExtractor implements ResultSetExtractor<IdNameTable>{

		@Override
		public IdNameTable extractData(ResultSet rs) throws SQLException, DataAccessException {
			return rs.next() ? (new IdNameTableRowMapper()).mapRow(rs, 0) : null;
		}
		
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		
		addressInsertActor = new SimpleJdbcInsert(dataSource)
			.withTableName(addressTable)
			.usingGeneratedKeyColumns("id");
	}

	
	@Override
	public List<Address> getAllAddress() {
		String sql = "SELECT * FROM domicilios";		
		return jdbcTemplate.query(sql, new AddressRowMapper());
	}

	@Override
	public List<Address> getAddressByUser(Integer idUser) {
		String sql = "SELECT * FROM domicilios WHERE usuario = :id";
		SqlParameterSource paramMap = new MapSqlParameterSource("id",idUser);
		return jdbcTemplate.query(sql, paramMap, new AddressRowMapper());
	}

	@Override
	public Address getAddressById(Integer idAddress) {
		String sql = "SELECT * FROM domicilios WHERE id = :id";
		SqlParameterSource paramMap = new MapSqlParameterSource("id",idAddress);
		return jdbcTemplate.query(sql, paramMap, new AddressRowExtractor());
	}

	@Override
	public void createAddress(Address address) {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("usuario", address.getUser());
		paramMap.put("pais", address.getCountry());
		paramMap.put("detalle1", address.getDetail1());
		paramMap.put("detalle2", address.getDetail2());
		paramMap.put("ciudad", address.getCity());
		paramMap.put("estado", address.getState());
		paramMap.put("zip", address.getZip());
		paramMap.put("telefono", address.getPhone());
		Number id = addressInsertActor.executeAndReturnKey(paramMap);
		address.setId(id.intValue());
		
	}

	@Override
	public void removeAddress(Integer idAddress) {
		String sql = "DELETE FROM domicilios WHERE id = :id";
		SqlParameterSource paramMap = new MapSqlParameterSource("id",idAddress);		
		jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public void updateAddress(Address address) {
		String sql = "UPDATE domicilios SET usuario = :usuario, pais = :pais, detalle1 = :detalle1, detalle2 = :detalle2, ciudad = :ciudad, estado = :estado, zip = :zip, telefono = :telefono WHERE id = :id";
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("usuario", address.getUser());
		paramMap.put("pais", address.getCountry());
		paramMap.put("detalle1", address.getDetail1());
		paramMap.put("detalle2", address.getDetail2());
		paramMap.put("ciudad", address.getCity());
		paramMap.put("estado", address.getState());
		paramMap.put("zip", address.getZip());
		paramMap.put("telefono", address.getPhone());		
		paramMap.put("id", address.getId());
		jdbcTemplate.update(sql, paramMap);
	}


	/* Paises */
	@Override
	public List<IdNameTable> getCountries() {
		String sql = "SELECT * FROM paises";
		return jdbcTemplate.query(sql, new IdNameTableRowMapper());
	}


}
