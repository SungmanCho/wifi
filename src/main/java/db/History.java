package db;

import java.sql.Date;

import lombok.Data;

@Data
public class History {
	private Integer siteId;
	private String xCol;
	private String yCol;
	private Date searchDt;
}
