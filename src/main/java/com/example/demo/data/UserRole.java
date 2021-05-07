/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.data;

/**
 * [OVERVIEW] User Role.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/21      LinhDT             Create new
*/
public enum UserRole {

    UNDEFINED(-1), MEMBER(0), ADMIN(1);

    private final Integer value;

    UserRole(Integer i) {
        value = i;
    }

    public Integer value() {
        return value;
    }

    public static UserRole parse(Integer i) {
        UserRole state = UserRole.UNDEFINED;
        for (UserRole item : UserRole.values()) {
            if (item.value().equals(i)) {
                state = item;
                break;
            }
        }

        return state;
    }
}