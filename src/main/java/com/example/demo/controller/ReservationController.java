/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.ReservationEntity;
import com.example.demo.bean.ResultBean;
import com.example.demo.data.ReservationStatus;
import com.example.demo.exception.LibException;
import com.example.demo.service.ReservationService;
import com.example.demo.utils.DataUtils;
import com.example.demo.utils.ResponseUtils;
import com.google.gson.JsonObject;

/**
 * [OVERVIEW] Reservation Controller.
 *
 * @author: LinhDT
 * @version: 1.1
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/25      LinhDT             Create new
*/
@RestController
@RequestMapping(value = "api/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    private static final Logger LOGGER = LogManager.getLogger(ReservationController.class);

    /**
     * addReservation
     * @author: LinhDT
     * @param data
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('MEMBER') or hasAuthority('ADMIN')")
    public ResponseEntity<ResultBean> addReservation(@RequestBody String data) {
        LOGGER.info("--- addReservation START ---");
        ResultBean resultBean = null;
        try {
            ReservationEntity entity = DataUtils.getEntityByJsonString(data, ReservationEntity.class);
            resultBean = reservationService.addReservation(entity);
        } catch (AccessDeniedException e) {
            resultBean = new ResultBean("401", e.getMessage());
        } catch (LibException e) {
            resultBean = new ResultBean(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", e.getMessage());
        }

        LOGGER.info("--- addReservation END ---");
        return new ResponseEntity<ResultBean>(resultBean, ResponseUtils.getResponseStatus(resultBean));
    }

    /**
     * getReservation
     * @author: LinhDT
     * @param userId
     * @param status
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('MEMBER') or hasAuthority('ADMIN')")
    public ResponseEntity<ResultBean> getReservation(@RequestParam(name = "userId", required = false) Integer userId,
            @RequestParam(name = "status", required = false, defaultValue = "-1") Integer status) {
        return this.getReservation(userId, ReservationStatus.parse(status));
    }

    /**
     * getIssueReservation
     * @author: LinhDT
     * @return
     */
    @RequestMapping(value = "/issue", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResultBean> getIssueReservation() {
        return this.getReservation(null, ReservationStatus.RESERVED);
    }

    /**
     * getReturnReservation
     * @author: LinhDT
     * @return
     */
    @RequestMapping(value = "/return", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResultBean> getReturnReservation() {
        return this.getReservation(null, ReservationStatus.BORROWING);
    }

    /**
     * getReservation
     * @author: LinhDT
     * @param userId
     * @param status
     * @return
     */
    public ResponseEntity<ResultBean> getReservation(Integer userId, ReservationStatus status) {
        LOGGER.info("--- getReservation START ---");
        ResultBean resultBean = null;
        try {
            resultBean = userId == null ? reservationService.getReservationWithStatus(status)
                    : reservationService.getReservationWithStatusByUserId(userId, status);
        } catch (AccessDeniedException e) {
            resultBean = new ResultBean("401", e.getMessage());
        } catch (LibException e) {
            resultBean = new ResultBean(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", "Internal server error");
        }

        LOGGER.info("--- getReservation END ---");
        return new ResponseEntity<ResultBean>(resultBean, ResponseUtils.getResponseStatus(resultBean));
    }

    /**
     * borrowReservation
     * @author: LinhDT
     * @param data
     * @return
     */
    @RequestMapping(value = "/borrow", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('MEMBER')")
    public ResponseEntity<ResultBean> borrowReservation(@RequestBody String data) {
        LOGGER.info("--- borrowReservation START ---");
        ResultBean resultBean = null;
        try {
        	ReservationEntity reservation = DataUtils.getEntityByJsonString(data, ReservationEntity.class);
            resultBean = reservationService.borrowReservation(reservation);
        } catch (AccessDeniedException e) {
            resultBean = new ResultBean("401", e.getMessage());
        } catch (LibException e) {
            resultBean = new ResultBean(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", "Internal server error");
        }

        LOGGER.info("--- borrowReservation END ---");
        return new ResponseEntity<ResultBean>(resultBean, ResponseUtils.getResponseStatus(resultBean));
    }

    /**
     * issueReservation
     * @author: LinhDT
     * @param data
     * @return
     */
    @RequestMapping(value = "/issue", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResultBean> issueReservation(@RequestBody String data) {
        LOGGER.info("--- issueReservation START ---");
        ResultBean resultBean = null;
        try {
            JsonObject obj = DataUtils.getEntityByJsonString(data, JsonObject.class);

            // TO DO: validate this
            Integer reservationId = DataUtils.getAsIntegerByJson(obj, "reservation_id");

            resultBean = reservationService.issueReservation(reservationId);
        } catch (AccessDeniedException e) {
            resultBean = new ResultBean("401", e.getMessage());
        } catch (LibException e) {
            resultBean = new ResultBean(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", "Internal server error");
        }

        LOGGER.info("--- issueReservation END ---");
        return new ResponseEntity<ResultBean>(resultBean, ResponseUtils.getResponseStatus(resultBean));
    }

    /**
     * returnReservation
     * @author: LinhDT
     * @param data
     * @return
     */
    @RequestMapping(value = "/return", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResultBean> returnReservation(@RequestBody String data) {
        LOGGER.info("--- returnReservation START ---");
        ResultBean resultBean = null;
        try {
            ReservationEntity reservation = DataUtils.getEntityByJsonString(data, ReservationEntity.class);
            resultBean = reservationService.returnReservation(reservation);
        } catch (AccessDeniedException e) {
            resultBean = new ResultBean("401", e.getMessage());
        } catch (LibException e) {
            resultBean = new ResultBean(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", "Internal server error");
        }

        LOGGER.info("--- returnReservation END ---");
        return new ResponseEntity<ResultBean>(resultBean, ResponseUtils.getResponseStatus(resultBean));
    }

    /**
     * cancelBorrowingReservation
     * @author: LinhDT
     * @param data
     * @return
     */
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MEMBER')")
    public ResponseEntity<ResultBean> cancelBorrowingReservation(@RequestBody String data) {
        LOGGER.info("--- returnReservation START ---");
        ResultBean resultBean = null;
        try {
            JsonObject obj = DataUtils.getEntityByJsonString(data, JsonObject.class);

            // TO DO: validate this
            Integer reservationId = DataUtils.getAsIntegerByJson(obj, "reservation_id");

            resultBean = reservationService.cancelBorrowingReservation(reservationId);
        } catch (AccessDeniedException e) {
            resultBean = new ResultBean("401", e.getMessage());
        } catch (LibException e) {
            resultBean = new ResultBean(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", "Internal server error");
        }

        LOGGER.info("--- returnReservation END ---");
        return new ResponseEntity<ResultBean>(resultBean, ResponseUtils.getResponseStatus(resultBean));
    }

    /**
     * addReservationItem
     * @author: LinhDT
     * @param data
     * @return
     */
    @RequestMapping(value = "/item", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('MEMBER') or hasAuthority('ADMIN')")
    public ResponseEntity<ResultBean> addReservationItem(@RequestBody String data) {
        LOGGER.info("--- addReservationItem START ---");
        ResultBean resultBean = null;
        try {
            JsonObject obj = DataUtils.getEntityByJsonString(data, JsonObject.class);

            // TO DO: validate this
            Integer userId = DataUtils.getAsIntegerByJson(obj, "user_id");
            Integer bookId = DataUtils.getAsIntegerByJson(obj, "book_id");

            resultBean = reservationService.addItemReservation(userId, bookId);
        } catch (AccessDeniedException e) {
            resultBean = new ResultBean("401", e.getMessage());
        } catch (LibException e) {
            resultBean = new ResultBean(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", "Internal server error");
        }

        LOGGER.info("--- addReservationItem END ---");
        return new ResponseEntity<ResultBean>(resultBean, ResponseUtils.getResponseStatus(resultBean));
    }

    /**
     * removeReservationItem
     * @author: LinhDT
     * @param data
     * @return
     */
    @RequestMapping(value = "/item", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('MEMBER') or hasAuthority('ADMIN')")
    public ResponseEntity<ResultBean> removeReservationItem(@RequestBody String data) {
        LOGGER.info("--- removeReservationItem START ---");
        ResultBean resultBean = null;
        try {
            JsonObject obj = DataUtils.getEntityByJsonString(data, JsonObject.class);

            // TO DO: validate this
            Integer userId = DataUtils.getAsIntegerByJson(obj, "user_id");
            Integer bookId = DataUtils.getAsIntegerByJson(obj, "book_id");
            Integer amount = DataUtils.getAsIntegerByJson(obj, "amount");

            resultBean = reservationService.removeItemReservation(userId, bookId, amount);
        } catch (AccessDeniedException e) {
            resultBean = new ResultBean("401", e.getMessage());
        } catch (LibException e) {
            resultBean = new ResultBean(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", "Internal server error");
        }

        LOGGER.info("--- removeReservationItem END ---");
        return new ResponseEntity<ResultBean>(resultBean, ResponseUtils.getResponseStatus(resultBean));
    }
}
