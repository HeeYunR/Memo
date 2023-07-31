package com.test.neulbom.client.repository;

import lombok.Data;

@Data
public class Notice_DetailDTO {
	private String notice_seq;
	private String title;
	private String content;
	private String notice_date;
	private String read;
}
