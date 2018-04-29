package com.route.finder.vo;

public enum YesOrNo {
	
	YES("yes"),
	
	NO("no");
	
	private String yesOrNo;
	
	private YesOrNo(String yesOrNo) {
		this.yesOrNo = yesOrNo;
	}
	
	public String getValue() {
		return this.yesOrNo;
	}
	
}