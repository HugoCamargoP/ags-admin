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
			config.setDollarCost(rs.getDouble(7));
			config.setBoxSmallHeight(rs.getDouble(8));
			config.setBoxMediumHeight(rs.getDouble(9));
			config.setBoxLargeHeight(rs.getDouble(10));
			config.setBoxSmallDepth(rs.getDouble(11));
			config.setBoxMediumDepth(rs.getDouble(12));
			config.setBoxLargeDepth(rs.getDouble(13));
			config.setBoxSmallWidth(rs.getDouble(14));
			config.setBoxMediumWidth(rs.getDouble(15));
			config.setBoxLargeWidth(rs.getDouble(16));
			config.setBoxSmallWeight(rs.getDouble(17));
			config.setBoxMediumWeight(rs.getDouble(18));
			config.setBoxLargeWeight(rs.getDouble(19));
			config.setShirtWeight(rs.getDouble(20));
			config.setEnvelopeWeight(rs.getDouble(21));
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
		String sql = "UPDATE basic_config SET iva = :iva, caja_pequena = :cp, caja_mediana = :cm, caja_grande = :cg, costo_envio = :ce, costo_dolar = :usd, "
				+ "caja_altura_s = :chs, caja_altura_m = :chm, caja_altura_l = :chl, caja_largo_s = :cls, caja_largo_m = :clm, caja_largo_l = :cll, "
				+ "caja_ancho_s = :cas, caja_ancho_m = :cam, caja_ancho_l = :cal, caja_peso_s = :cps, caja_peso_m = :cpm, caja_peso_l = :cpl,  "
				+ " guayabera_peso = :gp, sobre_peso = :sp WHERE id = :id";
		
//		String sql = "UPDATE basic_config SET iva = :iva, caja_pequena = :cp, caja_mediana = :cm, caja_grande = :cg, costo_envio = :ce, costo_dolar = :usd WHERE id = :id";
		
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("iva", config.getIva());
		paramMap.put("cp", config.getLittleBox());
		paramMap.put("cm", config.getMediumBox());
		paramMap.put("cg", config.getBigBox());
		paramMap.put("ce", config.getShipmentCost());
		paramMap.put("id", config.getId());
		paramMap.put("usd", config.getDollarCost());
		paramMap.put("chs", config.getBoxSmallHeight());
		paramMap.put("chm", config.getBoxMediumHeight());
		paramMap.put("chl", config.getBoxLargeHeight());
		paramMap.put("cls", config.getBoxSmallDepth());
		paramMap.put("clm", config.getBoxMediumDepth());
		paramMap.put("cll", config.getBoxLargeDepth());
		paramMap.put("cas", config.getBoxSmallWidth());
		paramMap.put("cam", config.getBoxMediumWidth());
		paramMap.put("cal", config.getBoxLargeWidth());
		paramMap.put("cps", config.getBoxSmallWeight());
		paramMap.put("cpm", config.getBoxMediumWeight());
		paramMap.put("cpl", config.getBoxLargeWeight());
		paramMap.put("gp", config.getShirtWeight());
		paramMap.put("sp", config.getEnvelopeWeight());
		
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
		args.put("costo_dolar", config.getDollarCost());
		args.put("caja_altura_s", config.getBoxSmallHeight() );
		args.put("caja_altura_m", config.getBoxMediumHeight() );
		args.put("caja_altura_l", config.getBoxLargeHeight() );
		args.put("caja_largo_s", config.getBoxSmallDepth() );
		args.put("caja_largo_m", config.getBoxMediumDepth() );
		args.put("caja_largo_l", config.getBoxLargeDepth() );
		args.put("caja_ancho_s", config.getBoxSmallWidth() );
		args.put("caja_ancho_m", config.getBoxMediumWidth() );
		args.put("caja_ancho_l", config.getBoxLargeWidth() );
		args.put("caja_peso_s", config.getBoxSmallWeight() );
		args.put("caja_peso_m", config.getBoxMediumWeight() );
		args.put("caja_peso_l", config.getBoxLargeWeight() );
		args.put("guayabera_peso", config.getShirtWeight() );
		args.put("sobre_peso", config.getEnvelopeWeight() );
		
		Number id = configInsertActor.executeAndReturnKey(args);
		
		config.setId(id.intValue());
		
	}
	
	
}
