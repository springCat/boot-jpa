package org.springcat.controller;


import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by springcat on 16/10/20.
 */
@RestController
@RequestMapping(value = "/cache")
public class CacheController {

    @Cacheable(value = "hello_cache")
    @RequestMapping(method = RequestMethod.GET)
    public String get(){
        return "cached";
    }

    @CacheEvict(value = "hello_cache",allEntries=true)
    @RequestMapping(method = RequestMethod.POST)
    public Boolean post(){
        return true;
    }

}
