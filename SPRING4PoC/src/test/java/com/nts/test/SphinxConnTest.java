package com.nts.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class SphinxConnTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	BasicDataSource datasource = null;
	JdbcTemplate jdbcTemplate = null;
	Connection conn = null;
	
	/*@Before
	public void setUp() throws Exception {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://192.168.10.190:9306?characterEncoding=utf8&maxAllowedPacket=512000", "", "");
			conn = DriverManager.getConnection("jdbc:mysql://192.168.10.190:9306?characterEncoding=utf8&maxAllowedPacket=512000", "", "");
//			conn = DriverManager.getConnection("jdbc:mysql://192.168.10.190:3306/sphinx?autoReconnect=true", "srch", "srch*!");
		
		} catch(Exception e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
		
		}
	
	}*/
	
	@Before
	public void setUpDBCP() throws Exception {
		
		try {
			
			datasource = new BasicDataSource();
			datasource.setDriverClassName("com.mysql.jdbc.Driver");
//			datasource.setUsername(searchEngineUser);
//			datasource.setPassword(searchEnginePassword);
			datasource.setUrl("jdbc:mysql://192.168.10.190:9306?characterEncoding=utf8&maxAllowedPacket=512000");
			
			conn = datasource.getConnection();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}
	
	@Test
	public void testSelectByJdbcTemplate() {
		
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM ZIPCODE WHERE MATCH('서산')");
		logger.debug("RESULTSET CHECK >>>>>>>>>>>>>>>>>>>>>>> {}", rows.size());
	
	}
	
	@Test
	public void testSelectFromSphinx() {
		
		Statement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.createStatement();
//			rs = st.executeQuery("SHOW META");
			rs = st.executeQuery("SELECT * FROM ZIPCODE WHERE MATCH('공주')");
//			rs = st.executeQuery("select * from zipcode where city like '%서산%' limit 20");
			
			while(rs.next()) {
				
				logger.debug("ZIPCODE : {}", rs.getString("city"));
			
			}
		
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new RuntimeException();
		
		} finally {
			
			if(rs != null) {
				
				try {
					rs.close();
				} catch (Exception e) {}
			
			}
			
			if(st != null) {
				
				try {
					st.close();
				} catch (Exception e) {}
			
			}
			
			if(conn != null) {
				
				try {
					conn.close();
				} catch (Exception e) {}
			
			}
		
		}
	
	}

}
