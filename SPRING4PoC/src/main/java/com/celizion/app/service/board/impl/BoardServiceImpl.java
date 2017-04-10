package com.celizion.app.service.board.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.celizion.app.mapper.board.BoardMapper;
import com.celizion.app.model.board.Board;
import com.celizion.app.service.board.BoardService;
import com.celizion.app.util.PageNavigation;

/**
 * <pre>
 * com.celizion.app.service.impl
 * BoardServiceImpl.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2017. 2. 21.
 */
@Service
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public List<Board> getBoardList(Board board, PageNavigation pageNavigation) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("srchFromYMDHM", board.getSrchFromYMDHM());
		map.put("srchToYMDHM", board.getSrchToYMDHM());
		map.put("searchType", board.getSearchType());
		map.put("searchStr", board.getSearchStr());
		map.put("startOffset", pageNavigation.getStartOffset());
		map.put("pageListRowLimit", pageNavigation.getPageListRowLimit());
		
		return boardMapper.selectBoardList(map);
	
	}
	
	@Override
	public int countBoardList(Board board) {
		
		return boardMapper.selectCountBoardList(board);
	
	}

}
