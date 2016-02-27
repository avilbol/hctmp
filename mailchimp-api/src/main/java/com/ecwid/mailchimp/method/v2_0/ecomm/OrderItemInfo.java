package com.ecwid.mailchimp.method.v2_0.ecomm;

import com.ecwid.mailchimp.MailChimpObject;

public class OrderItemInfo extends MailChimpObject {
	@Field
	public Integer line_num;
	
	@Field
	public Integer product_id;
	
	@Field
	public String sku;
	
	@Field
	public String product_name;
	
	@Field
	public Integer category_id;
	
	@Field
	public String category_name;
	
	@Field
	public Double qty;
	
	@Field
	public Double cost;
}
