package com.celizion.app.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PageNavigation {
	
	private @Value("${board.20.rows.limit}") int pageListRowLimit;
	
	private int currPageNo;
	private int lastPageNo;
	private int startPageNoNavBar;
	private int endPageNoNavBar;
	private int prevPageNo;
	private int nextPageNo;
	
	private int startOffset;
	private int endOffset;
	
	public void calculateForPageNav(int totalCnt, int currPageNo) {
		
		this.currPageNo = currPageNo;
		lastPageNo = (totalCnt / pageListRowLimit);
		
		if(totalCnt % pageListRowLimit > 0) {
			
			++lastPageNo;
		
		}
		
		startPageNoNavBar = ((currPageNo - 1) / 10) * 10 + 1;
		endPageNoNavBar = startPageNoNavBar + 9;
		
		if(endPageNoNavBar > lastPageNo) {
			
			endPageNoNavBar = lastPageNo;
		
		}
		
		prevPageNo = startPageNoNavBar - 1;
		
		if(prevPageNo < 1) {
			
			prevPageNo = 1;
		
		}
		
		nextPageNo = endPageNoNavBar + 1;
		
		if(nextPageNo > lastPageNo) {
			
			nextPageNo = lastPageNo;
		
		}
		
		startOffset = (currPageNo - 1) * pageListRowLimit;
		
		// For Oracle...
		endOffset = (currPageNo * pageListRowLimit) + 1;
	
	}
	
	public String makePageNavBar(int totalCnt) {
		
		StringBuilder sb = new StringBuilder();
		
		if(totalCnt > 0) {
			
			sb.append("<div class=\"btn-group pull-right\" role=\"group\">")
			  .append("<button id=\"btnFirst\" type=\"button\" class=\"btn btn-xs btn-primary\" data-page=\"1\">FIRST</button>")
			  .append("<button id=\"btnPrev\" type=\"button\" class=\"btn btn-xs btn-info\" data-page=\"").append(prevPageNo).append("\">PREV</button>");
			
			for(int i = startPageNoNavBar; i <= endPageNoNavBar; i++) {
				
				if(i == currPageNo) {
					
					sb.append("<button type=\"button\" class=\"btn btn-xs btn-default\">").append(i).append("</button>");
				
				} else {
					
					sb.append("<button name=\"btnPage\" type=\"button\" class=\"btn btn-xs btn-link\" data-page=\"").append(i).append("\">").append(i).append("</button>");
				
				}
			
			}
			
			sb.append("<button id=\"btnNext\" type=\"button\" class=\"btn btn-xs btn-info\" data-page=\"").append(nextPageNo).append("\">NEXT</button>")
			  .append("<button id=\"btnLast\" type=\"button\" class=\"btn btn-xs btn-primary\" data-page=\"").append(lastPageNo).append("\">LAST</button>")
			  .append("</div>");
		
		}
		
		return sb.toString();
	
	}
	
	public int getPageListRowLimit() {
		return pageListRowLimit;
	}

	public int getStartOffset() {
		return startOffset;
	}

	public int getEndOffset() {
		return endOffset;
	}

}
