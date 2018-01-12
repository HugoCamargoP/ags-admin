package com.arrowgs.agsadmin.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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

import com.arrowgs.agsadmin.daos.ConfigDao;
import com.arrowgs.agsadmin.entities.ConfigEntity;


@Repository
public class ConfigDaoImpl implements ConfigDao{

	private NamedParameterJdbcTemplate jdbcTemplate;

	private SimpleJdbcInsert configInsertActor;
	
	class ConfigRowMapper implements RowMapper<ConfigEntity>{

		@Override
		public ConfigEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
			ConfigEntity config = new ConfigEntity();
			
			config.setId(rs.getInt(1));
			config.setIva(rs.getInt(2));
			config.setLittleBox(rs.getDouble(3));
			config.setMediumBox(rs.getDouble(4));
			config.setBigBox(rs.getDouble(5));
			config.setShipmentCost(rs.getDouble(6));
			
			return config;
		}
		
	}
	
	
	class ConfigRowExtractor implements ResultSetExtractor<ConfigEntity>{

		@Override
		public ConfigEntity extractData(ResultSet rs) throws SQLException, DataAccessException {
			return rs.next() ? (new ConfigRowMapper()).mapRow(rs, 0) : null;
		}
		
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource){		
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		
		configInsertActor = new SimpleJdbcInsert(dataSource)
			.withTableName(BasicConfigTable)
			.usingGeneratedKeyColumns("id");
	}

	@Override
	public ConfigEntity getConfigEntity() {
		String sql = "SELECT * FROM	basic_config WHERE id = :id";
		SqlParameterSource paramMap = new MapSqlParameterSource("id",1);
		return jdbcTemplate.query(sql, paramMap, new ConfigRowExtractor());
	}

	@Override
	public ConfigEntity updateConfigEntity(ConfigEntity config) {
		String sql = "UPDATE basic_config SET iva = :iva, caja_pequena = :cp, caja_mediana = :cm, caja_grande = :cg, costo_envio = :ce WHERE id = :id";
		
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("iva", config.getIva());
		paramMap.put("cp", config.getLittleBox());
		paramMap.put("cm", config.getMediumBox());
		paramMap.put("cg", config.getBigBox());
		paramMap.put("ce", config.getShipmentCost());
		paramMap.put("id", config.getId());
		
		jdbcTemplate.update(sql, paramMap);
		
		return config;
	}

	@Override
	public void createConfigEntity(ConfigEntity config) {
		
		Map<String,Object> args = new HashMap<>();
		args.put("iva", config.getIva());
		args.put("caja_pequena", config.getLittleBox());
		args.put("caja_mediana", config.getMediumBox());
		args.put("caja_grande", config.getBigBox());
		args.put("costo_envio", config.getShipmentCost());
		
		Number id = configInsertActor.executeAndReturnKey(args);
		
		config.setId(id.intValue());
		
	}
	
	
}
