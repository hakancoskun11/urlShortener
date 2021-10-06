package com.tapu.urlshortener.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tapu.urlshortener.entities.Url;
import com.tapu.urlshortener.entities.UserUrl;
import com.tapu.urlshortener.service.UrlService;
import com.tapu.urlshortener.service.UserUrlService;
import com.tapu.urlshortener.util.Constants;
import com.tapu.urlshortener.util.UrlShortener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("api/v1/short")
public class ShortController {

    public static final Logger logger = LoggerFactory.getLogger(ShortController.class);

    @Autowired
    private UrlService urlService;
    @Autowired
    private UserUrlService userUrlService;


    @RequestMapping(value = "/add-url", method = RequestMethod.POST)
    public ResponseEntity<?> addUrl(@RequestBody Url url) throws Exception {

        logger.info("Url to create" );
        boolean saveUserUrl = true;
        String longUrl = url.getLongUrl();
        Url temp = null;
        Url result = null;
        if(longUrl.indexOf(Constants.DOMAIN) > -1) {
            temp = urlService.findUrlByShortUrl(longUrl);
            if(temp != null && url.getUserId() == null){
                return new ResponseEntity<Url>(temp, HttpStatus.CREATED);
            }
        }else {
            temp = urlService.findUrlByLongUrl(longUrl);
            if(temp != null && url.getUserId() == null){
                return new ResponseEntity<Url>(temp, HttpStatus.CREATED);
            }
        }
        UserUrl userUrl = null;
        if(temp != null) {
            result = temp;
            url.setId(result.getId());
            userUrl = new UserUrl(url.getUserId(), url.getId());
            List<UserUrl> userUrlList = userUrlService.findUserUrlByUserIdAndUrlId(url.getUserId(), url.getId());
            if(userUrlList != null && !userUrlList.isEmpty()) {
                saveUserUrl = false;
            }
        }else {
            UrlShortener shortener = new UrlShortener();
            String shortUrl = "";
            boolean flag = true;
            while (flag) {
                shortUrl = shortener.generateShortURL();
                Url urlTemp = urlService.findUrlByShortUrl(shortUrl);
                if(urlTemp == null || urlTemp.getId() < 0) {
                    flag = false;
                }
            }
            url.setShortUrl(shortUrl);
            url.setCreatedDate(new Date());
            result = urlService.saveUrl(url);
            userUrl = new UserUrl(url.getUserId(), result.getId());
        }
        if(saveUserUrl && url.getUserId() != null) {
            userUrlService.saveUserUrl(userUrl);
        }

        return new ResponseEntity<Url>(result, HttpStatus.CREATED);

    }


    @RequestMapping(value = "/get-urls-by-user-id/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<Url>> getUrlListByUserId(@PathVariable("userId") Long userId) throws Exception {

        logger.info("List of url to return userId " + userId);
        List<UserUrl> userUrlList = userUrlService.findUserUrlByUserId(userId);
        List<Long> urlIdList = new ArrayList<>();
        for (UserUrl each : userUrlList) {
            urlIdList.add(each.getUrlId());
        }
        List<Url> urlList = urlService.listUrlByIdIn(urlIdList);
        return new ResponseEntity<List<Url>>(urlList, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-url-by-short-url/{shortUrl}", method = RequestMethod.GET)
    public ResponseEntity<Url> getUrlByShortUrl(@PathVariable("shortUrl") String shortUrl) throws Exception {

        logger.info("Url to return shortUrl " + shortUrl);
        Url urlt = urlService.findUrlByShortUrl(Constants.DOMAIN + shortUrl);
        return new ResponseEntity<Url>(urlt, HttpStatus.OK);

    }


}