/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.data;

/**
 * [OVERVIEW] Book Item Status.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/21      LinhDT             Create new
*/
public enum BookItemStatus {

    UNDEFINED(-1), NOT_AVAILABLE(0), AVAILABLE(1), LOST(2);

    private final Integer value;

    BookItemStatus(Integer i) {
        value = i;
    }

    public Integer value() {
        return value;
    }

    public static BookItemStatus parse(Integer i) {
        BookItemStatus state = BookItemStatus.UNDEFINED;
        for (BookItemStatus item : BookItemStatus.values()) {
            if (item.value().equals(i)) {
                state = item;
                break;
            }
        }

        return state;
    }
}
