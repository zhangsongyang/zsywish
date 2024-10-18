package org.example.service;

import com.github.pagehelper.PageHelper;
import org.example.mapper.CountryMapper;
import org.example.model.Country;
import org.example.vo.CountryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuzh
 * @since 2015-12-19 11:09
 */
@Service
public class CountryService {

    @Autowired
    private CountryMapper countryMapper;

    public List<Country> listCountry(CountryQuery query) {
        PageHelper.startPage(query.getPage(), query.getRows());
        return countryMapper.listCountry(query);
    }

    @Transactional
    public int batchInsert(CountryQuery query) {
        List<Country> countryList = new ArrayList<>();
        for (Long i = 300L; i < 306; i++) {
            Country country = new Country();
            country.setId(i);
            country.setCountryname("yyy");
            country.setCountrycode("zzz");
            countryList.add(country);
        }
        int result = countryMapper.batchInsertCountry(countryList);
        return result;
    }

}
