package kr.or.ddit.vo;

import java.io.Serializable;

public class Category_AVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3315902952669247954L;
	private String cat_a_no;
	private String cat_a_name;
	public String getCat_a_no() {
		return cat_a_no;
	}
	public void setCat_a_no(String cat_a_no) {
		this.cat_a_no = cat_a_no;
	}
	public String getCat_a_name() {
		return cat_a_name;
	}
	public void setCat_a_name(String cat_a_name) {
		this.cat_a_name = cat_a_name;
	}
}
