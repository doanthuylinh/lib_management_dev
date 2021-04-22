/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * [OVERVIEW] Transaction Entity.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/21      LinhDT       Create new
*/
@Entity
@Table(name = "Transaction")
public class TransactionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer transactionId;

    @Column(name = "transaction_type_id", insertable = false, updatable = false)
    private Integer transactionTypeId;

    @Column(name = "reservation_id", insertable = false, updatable = false)
    private Integer reservationId;

    @Column(name = "money")
    private Double money;

    @Column(name = "created_time")
    private LocalDateTime createdTime;
    
    @ManyToOne
    @JoinColumn(name = "transaction_type_id")
    private TransactionTypeEntity transactionTypeEntity;
    
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private ReservationEntity reservationEntity;

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(Integer transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public LocalDateTime getCreatedTime() {
        return LocalDateTime.now();
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public TransactionEntity(Integer transactionId, Integer transactionTypeId, Integer reservationId, Double money, LocalDateTime createdTime) {
        super();
        this.transactionId = transactionId;
        this.transactionTypeId = transactionTypeId;
        this.reservationId = reservationId;
        this.money = money;
        this.createdTime = createdTime;
    }

    public TransactionEntity() {
        super();
    }

}
