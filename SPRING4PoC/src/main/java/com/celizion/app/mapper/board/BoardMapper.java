package com.celizion.app.mapper.board;

import java.util.List;
import java.util.Map;

import com.celizion.app.model.board.Board;

/**
 * <pre>
 * com.celizion.app.mapper.board
 * BoardMapper.java
 * </pre>
 *
 * @author : 남택승
 * @date   : 2017. 2. 21.
 */
public interface BoardMapper {

	List<Board> selectBoardList(Map<String, Object> map);
	int selectCountBoardList(Board board);

}
