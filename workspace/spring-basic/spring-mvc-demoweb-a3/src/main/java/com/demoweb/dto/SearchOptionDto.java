package com.demoweb.dto;

import lombok.Data;

@Data
public class SearchOptionDto {
	
	private boolean isSearch;
	private String searchType;
	private String searchWord;
	
	private int from;
	private int count;
	private int to;

}
