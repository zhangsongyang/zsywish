/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.example.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Country;
import org.example.model.CountryConver;
import org.example.service.CountryService;
import org.example.vo.CountryQuery;
import org.example.vo.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuzh
 * @since 2015-12-19 11:10
 */
@Slf4j
@RestController
@RequestMapping("/test/")
public class CountryController {

    @Autowired
    private CountryService countryService;


    @PostMapping("/xxx")
    public String testJuc(@RequestBody CountryQuery query) {
        System.out.println("123456789");
        List<Country> countryList = countryService.listCountry(query);
        if (CollectionUtils.isEmpty(countryList)) {
            return null;
        }
        PageInfo<Country> pageInfo = new PageInfo<Country>(countryList);
        log.info("pageInfo----->:" + JSONObject.toJSONString(pageInfo));
        List<CountryConver> countryConvers = pageInfo.getList().stream()
                .map(country -> {
                    CountryConver countryConver = new CountryConver();
                    countryConver.setId(country.getId());
                    countryConver.setCountrycodeName(country.getCountrycode() + "---" + country.getCountryname());
                    countryConver.setCountrycode(country.getCountrycode());
                    countryConver.setCountryname(country.getCountryname());
                    return countryConver;
                })
                .collect(Collectors.toList());
        PageData<CountryConver> pageData = new PageData<CountryConver>(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getSize(), pageInfo.getTotal(), countryConvers);
        log.info("pageData----->:" + JSONObject.toJSONString(pageData));
        return JSONObject.toJSONString(pageData);
    }


    @PostMapping("/insert")
    public String testInsert(@RequestBody CountryQuery query) {
        System.out.println("123456789");
        int countryList = countryService.batchInsert(query);

        return JSONObject.toJSONString(countryList);
    }


    public static void main(String[] args) {
        System.out.println(DateUtil.formatDateTime(new Date()));
        System.out.println(DateUtil.parse("2024-09-20 13:13:13"));


    }

}
