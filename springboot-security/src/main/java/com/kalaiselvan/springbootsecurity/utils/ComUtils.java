package com.kalaiselvan.springbootsecurity.utils;

public class ComUtils {
	
	public static String generateDeptCode(Long deptID) {
		StringBuilder deptCode = new StringBuilder("DPT");
		int length = Long.toString(deptID).length();
		if(length == 1) {
			deptCode.append("00").append(deptID);
		}else if(length == 2) {
			deptCode.append("0").append(deptID);
		}else if(length > 2) {
			deptCode.append(deptID);
		}
		return deptCode.toString();
	}

}
