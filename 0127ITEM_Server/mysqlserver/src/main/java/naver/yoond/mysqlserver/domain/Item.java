package naver.yoond.mysqlserver.domain;

import lombok.Data;

@Data
public class Item {
	private int itemid;
	private String itemname;
	private int price;
	private String description;
	private String pictureurl;
	private String updatedate;
}
