/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * [OVERVIEW] Transaction Type Response.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/24      LinhDT             Create new
*/
public class TransactionTypeResponse {

    @JsonProperty("transaction_type_id")
    private Integer transactionTypeId;
    @JsonProperty("transaction_type_name")
    private String transactionTypeName;

    public Integer getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(Integer transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public String getTransactionTypeName() {
        return transactionTypeName;
    }

    public void setTransactionTypeName(String transactionTypeName) {
        this.transactionTypeName = transactionTypeName;
    }

    public TransactionTypeResponse(Integer transactionTypeId, String transactionTypeName) {
        super();
        this.transactionTypeId = transactionTypeId;
        this.transactionTypeName = transactionTypeName;
    }

    public TransactionTypeResponse() {
        super();
    }

}
