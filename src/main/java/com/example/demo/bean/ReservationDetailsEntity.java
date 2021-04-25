/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * [OVERVIEW] Reservation Details.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/21      LinhDT       Create new
*/
@Entity
@Table(name = "ReservationDetails")
public class ReservationDetailsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "reservation_id")
    private Integer reservationId;

    @Id
    @Column(name = "book_item_id")
    private Integer bookItemId;

//    @ManyToOne
//    @JoinColumn(name = "reservation_id")
//    private ReservationEntity reservationEntity;
//
////    @OneToOne(cascade = CascadeType.ALL)
////    @JoinColumn(name = "book_item_id", referencedColumnName = "book_item_id")
//    private BookItemEntity bookItemEntity;

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getBookItemId() {
        return bookItemId;
    }

    public void setBookItemId(Integer bookItemId) {
        this.bookItemId = bookItemId;
    }

    public ReservationDetailsEntity(Integer reservationId, Integer bookItemId) {
        super();
        this.reservationId = reservationId;
        this.bookItemId = bookItemId;
    }

    public ReservationDetailsEntity() {
        super();
    }

}
