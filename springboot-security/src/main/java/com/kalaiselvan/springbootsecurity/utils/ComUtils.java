package com.kalaiselvan.springbootsecurity.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ComUtils {
	
	
	
	
//	public static String generateDeptCode(Long deptID) {
//		StringBuilder deptCode = new StringBuilder("DPT");
//		int length = Long.toString(deptID).length();
//		if(length == 1) {
//			deptCode.append("00").append(deptID);
//		}else if(length == 2) {
//			deptCode.append("0").append(deptID);
//		}else if(length > 2) {
//			deptCode.append(deptID);
//		}
//		return deptCode.toString();
//	}
	
	public static String generateCode(JdbcTemplate jdbcTemplate, String prefix) {
        return jdbcTemplate.execute((Connection con) -> {
            try (CallableStatement cs = con.prepareCall("CALL generate_code(?, ?)")) {
                cs.setString(1, prefix);
                cs.registerOutParameter(2, Types.VARCHAR);
                cs.execute();
                return cs.getString(2);
            }
        });
    }
	
	public static Sort parseSortParams(String[] sort) {
	    if (sort.length % 2 != 0)
	        throw new IllegalArgumentException("Invalid sort parameters");

	    Sort.Order[] orders = new Sort.Order[sort.length / 2];

	    for (int i = 0; i < sort.length; i += 2) {
	        String field = sort[i];
	        String direction = sort[i + 1];
	        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
	        orders[i / 2] = new Sort.Order(sortDirection, field);
	    }

	    return Sort.by(orders);
	}


	
//	public static<T> setdefaultSaveDetail()

}
