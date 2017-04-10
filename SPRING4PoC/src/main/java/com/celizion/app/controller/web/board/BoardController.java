package com.celizion.app.controller.web.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.celizion.app.model.board.Board;

/**
 * <pre>
 * com.celizion.app.controller.web.board
 * BoardController.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2017. 2. 21.
 */
@Controller
public class BoardController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/index.app", method = RequestMethod.GET)
	public String getList() {
		
		return "board/list";
	
	}
	
	@RequestMapping(value = "/board/view.app", method = RequestMethod.GET)
	public ModelAndView getContents(Board board) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/view");
		mav.addObject("board", board);
		
		return mav;
	
	}

}
