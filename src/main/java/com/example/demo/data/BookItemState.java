/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.data;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * [OVERVIEW] State.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/21      LinhDT             Create new
*/
public enum BookItemState {

	UNDEFINED(-1),
    NOT_AVAILABLE(0),
    AVAILABLE(1),
    LOST(2);
	
	private final Integer value;

	BookItemState(Integer i) {
		value = i;
	}
	
	public Integer value() {
		return value;
	}
	
	public static BookItemState parse(Integer i) {
		BookItemState state = BookItemState.UNDEFINED;
		for (BookItemState item : BookItemState.values()) {
			if (item.value().equals(i)) {
				state = item;
				break;
			}
		}
		
		return state;
	}
}
