package com.celizion.app.controller.rest.board;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.celizion.app.model.board.Board;
import com.celizion.app.service.board.BoardService;
import com.celizion.app.util.PageNavigation;

/**
 * <pre>
 * com.celizion.app.controller.rest.board
 * BoardRestController.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2017. 2. 21.
 */
@RestController
@RequestMapping(value = "/board")
public class BoardRestController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	ApplicationContext context;
	
	@RequestMapping(value = {"/{currPageNo}/{srchFromYMDHM}/{srchToYMDHM}"
							 , "/{currPageNo}/{srchFromYMDHM}/{srchToYMDHM}/{searchType}/{searchStr}"
							}, method = RequestMethod.GET)
	public Map<String, Object> getBoardList(@PathVariable("currPageNo") int currPageNo
											, @PathVariable Optional<String> srchFromYMDHM
											, @PathVariable Optional<String> srchToYMDHM
											, @PathVariable Optional<String> searchType
											, @PathVariable Optional<String> searchStr
											, Principal principal) {
		
		Board board = new Board();
		board.setSrchFromYMDHM(srchFromYMDHM.get());
		board.setSrchToYMDHM(srchToYMDHM.get());
		board.setSearchType((searchType.isPresent()) ? searchType.get() : "");
		board.setSearchStr((searchStr.isPresent()) ? searchStr.get() : "");
		
		int totalCnt = boardService.countBoardList(board);
		
		PageNavigation pageNav = (PageNavigation) context.getBean("pageNavigation");
		pageNav.calculateForPageNav(totalCnt, currPageNo);
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", boardService.getBoardList(board, pageNav));
		map.put("pageBar", pageNav.makePageNavBar(totalCnt));
		
		return map;
	
	}

}
