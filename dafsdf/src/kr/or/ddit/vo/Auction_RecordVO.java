package kr.or.ddit.vo;

import java.io.Serializable;

public class Auction_RecordVO implements Serializable {

	private String a_art_no;
	private String a_art_name;
	private String a_result;

	public String getA_art_no() {
		return a_art_no;
	}

	public void setA_art_no(String a_art_no) {
		this.a_art_no = a_art_no;
	}

	public String getA_art_name() {
		return a_art_name;
	}

	public void setA_art_name(String a_art_name) {
		this.a_art_name = a_art_name;
	}

	public String getA_result() {
		return a_result;
	}

	public void setA_result(String a_result) {
		this.a_result = a_result;
	}

}