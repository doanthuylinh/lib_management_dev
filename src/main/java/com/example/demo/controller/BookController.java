/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.ResultBean;
import com.example.demo.exception.ApiValidateException;
import com.example.demo.service.BookService;
import com.example.demo.utils.MessageUtils;
import com.example.demo.utils.ResponseUtils;

/**
 * [OVERVIEW] Book Controller.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/09      LinhDT             Create new
 * 002       1.0       2021/05/06      LinhDT             Add getBooksByDepartment
*/
@RestController
@RequestMapping(value = "/api")
public class BookController {

    @Autowired
    private BookService bookService;

    private static final Logger LOGGER = LogManager.getLogger(BookController.class);

    /**
     * getBookById
     * @author: LinhDT
     * @param bookId
     * @return
     */
    @RequestMapping(value = "/book/{bookId}", method = RequestMethod.GET)
    public ResponseEntity<ResultBean> getBookById(@PathVariable Integer bookId) {
        LOGGER.info("----------getBookById START----------");
        ResultBean entity = null;
        try {
            entity = bookService.getBookById(bookId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(entity, HttpStatus.OK);
        }
        LOGGER.info("----------getBookById END----------");
        return new ResponseEntity<ResultBean>(entity, HttpStatus.OK);
    }

    /**
     * getBookByName
     * @author: LinhDT
     * @param query
     * @return
     */
    @RequestMapping(value = "/getbookbyname", method = RequestMethod.GET)
    public ResponseEntity<ResultBean> getBookByName(@RequestParam("q") String query) {
        LOGGER.info("----------getBookByName START----------");
        ResultBean resultBean = null;
        try {
            resultBean = bookService.getBookByName(query);
        } catch (ApiValidateException e) {
            resultBean = new ResultBean(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", "Internal server error");
        }
        LOGGER.info("----------getBookByName END----------");
        return new ResponseEntity<ResultBean>(resultBean, ResponseUtils.getResponseStatus(resultBean));
    }

    /**
     * getBooksByAuthor
     * @author: LinhDT
     * @param query
     * @return
     */
    @RequestMapping(value = "/getbooksbyauthor", method = RequestMethod.GET)
    public ResponseEntity<ResultBean> getBooksByAuthor(@RequestParam("q") String query) {
        LOGGER.info("----------getBooksByAuthor START with query: " + query);
        ResultBean resultBean = null;
        try {
            resultBean = bookService.getBooksByAuthor(query);
        } catch (ApiValidateException e) {
            resultBean = new ResultBean(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", "Internal server error");
        }
        LOGGER.info("----------getBooksByAuthor END----------");
        return new ResponseEntity<ResultBean>(resultBean, ResponseUtils.getResponseStatus(resultBean));
    }

    /**
     * getBookByCategory
     * @author: LinhDT
     * @param query
     * @return
     */
    @RequestMapping(value = "/getbooksbycategory", method = RequestMethod.GET)
    public ResponseEntity<ResultBean> getBooksByCategory(@RequestParam("q") Integer query) {
        LOGGER.info("----------Get Book By Category START with query: " + query);
        ResultBean resultBean = null;
        try {
            resultBean = bookService.getBooksByCategory(query);
        } catch (ApiValidateException e) {
            resultBean = new ResultBean(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", MessageUtils.getMessage("ERR17"));
        }
        LOGGER.info("----------Get Book By Category END----------");
        return new ResponseEntity<ResultBean>(resultBean, ResponseUtils.getResponseStatus(resultBean));
    }

    /**
     * getBooksByDepartment
     * @author: LinhDT
     * @param query
     * @return
     */
    @RequestMapping(value = "/getbooksbydepartment", method = RequestMethod.GET)
    public ResponseEntity<ResultBean> getBooksByDepartment(@RequestParam("q") Integer query) {
        LOGGER.info("----------Get Book By Department START with query: " + query);
        ResultBean resultBean = null;
        try {
            resultBean = bookService.getBooksByDepartment(query);
        } catch (ApiValidateException e) {
            resultBean = new ResultBean(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", MessageUtils.getMessage("ERR17"));
        }
        LOGGER.info("----------Get Book By Department END----------");
        return new ResponseEntity<ResultBean>(resultBean, ResponseUtils.getResponseStatus(resultBean));
    }

    /**
     * getBookByPublicationDate
     * @author: LinhDT
     * @param query
     * @return
     */
    @RequestMapping(value = "/getbookbypublicationdate", method = RequestMethod.GET)
    public ResponseEntity<ResultBean> getBookByPublicationDate(@RequestParam("q") String query) {
        LOGGER.info("----------get Book By Publication Date START with query: " + query);
        ResultBean resultBean = null;
        try {
            resultBean = bookService.getBookByPublicationDate(query);
        } catch (ApiValidateException e) {
            resultBean = new ResultBean(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", "Internal server error");
        }
        LOGGER.info("----------get Book By Publication Date END----------");
        return new ResponseEntity<ResultBean>(resultBean, ResponseUtils.getResponseStatus(resultBean));
    }

    /**
     * searchBook
     * @author: LinhDT
     * @param query
     * @return
     */
    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public ResponseEntity<ResultBean> searchBook(@RequestParam(name = "q", required = false) String query,
            @RequestParam(name = "from", required = false) Integer from, @RequestParam(name = "limit", required = false) Integer limit) {
        LOGGER.info("--- Search Book START with query: " + query);

        ResultBean resultBean = null;
        try {
            resultBean = bookService.searchBook(query, from, limit);
        } catch (ApiValidateException e) {
            resultBean = new ResultBean(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", "Internal server error");
        }

        LOGGER.info("--- Search book END ---");
        return new ResponseEntity<ResultBean>(resultBean, ResponseUtils.getResponseStatus(resultBean));
    }

    /**
     * updateBook
     * @author: LinhDT
     * @param data
     * @return
     */
    @RequestMapping(value = "/book", method = RequestMethod.PUT)
    public ResponseEntity<ResultBean> updateBook(@RequestBody String data) {
        LOGGER.info("--- Update book START ---");

        ResultBean resultBean = null;
        try {
            resultBean = bookService.updateBook(data);
        } catch (ApiValidateException e) {
            resultBean = new ResultBean(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", "Internal server error");
        }

        LOGGER.info("--- Update book END ---");
        return new ResponseEntity<ResultBean>(resultBean, ResponseUtils.getResponseStatus(resultBean));
    }

    /**
     * addBook
     * @author: LinhDT
     * @param data
     * @return
     */
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity<ResultBean> addBook(@RequestBody String data) {
        LOGGER.info("--- Add book START ---");

        ResultBean resultBean = null;
        try {
            resultBean = bookService.addBook(data);
        } catch (ApiValidateException e) {
            resultBean = new ResultBean(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", "Internal server error");
        }

        LOGGER.info("--- Add book END ---");
        return new ResponseEntity<ResultBean>(resultBean, ResponseUtils.getResponseStatus(resultBean));
    }
    
    @RequestMapping(value = "/book", method = RequestMethod.DELETE)
    public ResponseEntity<ResultBean> removeBook(@RequestBody String data) {
        LOGGER.info("--- remove book START ---");

        ResultBean resultBean = null;
        try {
            resultBean = bookService.removeBook(data);
        } catch (ApiValidateException e) {
            resultBean = new ResultBean(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", "Internal server error");
        }

        LOGGER.info("--- remove book END ---");
        return new ResponseEntity<ResultBean>(resultBean, ResponseUtils.getResponseStatus(resultBean));
    }

}
