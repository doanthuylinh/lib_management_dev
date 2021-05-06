/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service;

import org.springframework.security.core.Authentication;

/**
 * [OVERVIEW] AppAuthorizer.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/08      LinhDT             Create new
*/
public interface AppAuthorizer {
    boolean authorize(Authentication authentication, String action, Object callerObj);
}
