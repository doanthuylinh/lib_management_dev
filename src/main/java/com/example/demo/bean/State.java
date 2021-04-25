/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.bean;

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
public enum State {

	UNDEFINED(-1),
    NOT_AVAILABLE(0),
    AVAILABLE(1),
    LOST(2);
	
	private final Integer value;

	State(Integer i) {
		value = i;
	}
	
	public Integer value() {
		return value;
	}
	
	public static State parse(Integer i) {
		State state = State.UNDEFINED;
		for (State item : State.values()) {
			if (item.value().equals(i)) {
				state = item;
				break;
			}
		}
		
		return state;
	}
}
