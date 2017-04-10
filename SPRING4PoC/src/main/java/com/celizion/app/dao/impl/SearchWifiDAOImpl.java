package com.celizion.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.celizion.app.dao.SearchDAOStrategy;
import com.celizion.app.model.search.Wifi;

/**
 * <pre>
 * com.celizion.app.dao.impl
 * SearchWifiDAOImpl.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2017. 3. 31.
 */
@Repository
public class SearchWifiDAOImpl {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	DataSource searchEngineDataSource;
	
	public List<Wifi> selectWifiList(String searchKeyword) {
		
		StringBuilder query = new StringBuilder("SELECT ID")
									.append(", CITY")
									.append(" FROM ZIPCODE")
									.append(" WHERE MATCH('")
									.append(searchKeyword)
									.append("')");
		
		return (List<Wifi>) queryForList(query.toString(), Wifi.class, new SearchDAOStrategy<Wifi>() {
			
			@Override
			public Wifi bindByListResult(ResultSet rs) throws Exception {
				
				logger.debug("RESULTSET CHECK >>>>>>>>>>>>>>>>>>>>>>> {}", rs.getString("CITY"));
				Wifi wifi = new Wifi();
				wifi.setCity(rs.getString("CITY"));
				
				return wifi;
			
			}
		
		});
	
	}
	
	public <T> List<T> queryForList(String query, Class<T> clazz, SearchDAOStrategy<T> searchDAOStrategy) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			conn = searchEngineDataSource.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			List<T> list = new LinkedList<>();
			
			while(rs.next()) {
				
				list.add(clazz.cast(searchDAOStrategy.bindByListResult(rs)));
			
			}
			
			return list;
		
		} catch(Exception e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
		
		} finally {
			
			if(rs != null) {
				
				try {
					
					rs.close();
					
				} catch (Exception e) {
					
					e.printStackTrace();
					throw new RuntimeException(e);
				
				}
			
			}
			
			if(ps != null) {
				
				try {
					
					ps.close();
					
				} catch (Exception e) {
					
					e.printStackTrace();
					throw new RuntimeException(e);
				
				}
			
			}
			
			if(conn != null) {
				
				try {
					
					conn.close();
					
				} catch (Exception e) {
					
					e.printStackTrace();
					throw new RuntimeException(e);
				
				}
			
			}
		
		}
	
	}

}
