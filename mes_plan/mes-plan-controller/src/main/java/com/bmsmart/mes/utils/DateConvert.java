package com.bmsmart.mes.utils;

import org.springframework.core.convert.converter.Converter;

import com.bmsmart.mes.base.util.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by susq on 2017-7-10.
 */
public class DateConvert implements Converter<String, Date>  {

    @Override
    public Date convert(String stringDate) {
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        if (StringUtil.isBlank(stringDate)) {
        	return null;
        }
        try {
        	if (stringDate.indexOf(':')>0){
        		d = simpleDateFormat1.parse(stringDate);
        	}else{
        		d = simpleDateFormat2.parse(stringDate);
        	}
        } catch (Exception e) {
            e.printStackTrace();
            try {
				throw e;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        return d;
    }
}