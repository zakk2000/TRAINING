package com.celizion.app.model.board;

import org.pojomatic.annotations.AutoProperty;

import com.celizion.app.model.common.CommonModel;

/**
 * <pre>
 * com.celizion.app.model.board
 * Board.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2017. 2. 21.
 */
@AutoProperty
public class Board extends CommonModel {

	private long seq;
	private long rownum;
	private String title;
	private String cont;
	private String createDate;
	private String createdBy;
	private String updateDate;
	private String updatedBy;
	
	private String srchFromYMDHM;
	private String srchToYMDHM;
	private String searchType;
	private String searchStr;
	
	public long getSeq() {
		return seq;
	}
	public void setSeq(long seq) {
		this.seq = seq;
	}
	public long getRownum() {
		return rownum;
	}
	public void setRownum(long rownum) {
		this.rownum = rownum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getSrchFromYMDHM() {
		return srchFromYMDHM;
	}
	public void setSrchFromYMDHM(String srchFromYMDHM) {
		this.srchFromYMDHM = srchFromYMDHM;
	}
	public String getSrchToYMDHM() {
		return srchToYMDHM;
	}
	public void setSrchToYMDHM(String srchToYMDHM) {
		this.srchToYMDHM = srchToYMDHM;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchStr() {
		return searchStr;
	}
	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

}
