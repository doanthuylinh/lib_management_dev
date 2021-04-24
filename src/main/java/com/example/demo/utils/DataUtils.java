/////////////////////////////////////////////////////////////////////////////
//
// � 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.utils;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * [OVERVIEW] DataUtils.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/06      LinhDT       	  Create new
*/
public class DataUtils {

    /**
     * getUsernameByToken
     * @author: LinhDT
     * @return
     */
    public static String getUsernameByToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            return currentUsername;
        }
        return null;
    }

    /**
     * isNullWithMemberNameByJson
     * @author: LinhDT
     * @param object
     * @param member
     * @return
     * @throws ApiValidateException
     */
    public static boolean isNullWithMemberNameByJson(JsonObject object, String member) throws ApiValidateException {
        if (!object.has(member) || object.has(member) && (object.get(member).isJsonNull() || object.get(member).getAsString().equals(""))) {
            return true;
        }
        return false;
    }

    /**
     * getAsIntegerByJson
     * @author: LinhDT
     * @param object
     * @param member
     * @return
     * @throws ApiValidateException
     */
    public static Integer getAsIntegerByJson(JsonObject object, String member) throws ApiValidateException {
        try {
            if (object.has(member)) {
                return object.get(member).getAsInt();
            }
        } catch (Exception e) {
            throw new ApiValidateException("ERR", "JSON " + member + " is invalid.");
        }
        return null;
    }

    /**
     * getAsStringByJson
     * @author: LinhDT
     * @param object
     * @param member
     * @return
     * @throws ApiValidateException
     */
    public static String getAsStringByJson(JsonObject object, String member) throws ApiValidateException {
        try {
            if (object.has(member)) {
                return object.get(member).getAsString();
            }
        } catch (Exception e) {
            throw new ApiValidateException("ERR", "JSON " + member + " is invalid.");
        }
        return null;
    }

    /**
     * getAsDoubleByJson
     * @author: LinhDT
     * @param object
     * @param member
     * @return
     * @throws ApiValidateException
     */
    public static Double getAsDoubleByJson(JsonObject object, String member) throws ApiValidateException {
        try {
            if (object.has(member)) {
                return object.get(member).getAsDouble();
            }
        } catch (Exception e) {
            throw new ApiValidateException("ERR", "JSON " + member + " is invalid.");
        }
        return null;
    }
    
    public static boolean isNullOrEmpty(String data) throws ApiValidateException {
        if (data == null || data.trim().isEmpty()) {
        	return true;
        }
        return false;
    }
    
    public static boolean isNullOrEmpty(Integer data) throws ApiValidateException {
        if (data == null || data.equals(0)) {
        	return true;
        }
        return false;
    }
    
    public static <T> T getEntityByJsonString(String data, Class<T> typeoff) throws ApiValidateException {
    	ValidateUtils.validateDataBody(data);
    	
    	T entity = new Gson().fromJson(data, typeoff);
    	
    	return entity;
    }
}
