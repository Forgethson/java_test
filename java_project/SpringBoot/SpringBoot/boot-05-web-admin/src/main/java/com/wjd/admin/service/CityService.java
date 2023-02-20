package com.wjd.admin.service;

import com.wjd.admin.bean.City;


public interface CityService {

     City getById(Long id);

     void saveCity(City city);

}
