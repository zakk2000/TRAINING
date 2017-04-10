package com.celizion.app.dao;

import java.sql.ResultSet;

/**
 * <pre>
 * com.celizion.app.dao
 * SearchDAO.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2017. 3. 31.
 */
public interface SearchDAOStrategy<T> {

	T bindByListResult(ResultSet rs) throws Exception;

}
