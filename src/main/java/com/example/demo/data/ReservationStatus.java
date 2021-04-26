/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.data;


public enum ReservationStatus {

	UNDEFINED(-1),
    TEMP(0),
    BORROWING(1),
    RESERVED(2),
    CLOSED(3),
    CANCELED(4);
	
	private final Integer value;

	ReservationStatus(Integer i) {
		value = i;
	}
	
	public Integer value() {
		return value;
	}
	
	public static ReservationStatus parse(Integer i) {
		ReservationStatus state = ReservationStatus.UNDEFINED;
		for (ReservationStatus item : ReservationStatus.values()) {
			if (item.value().equals(i)) {
				state = item;
				break;
			}
		}
		
		return state;
	}
}
