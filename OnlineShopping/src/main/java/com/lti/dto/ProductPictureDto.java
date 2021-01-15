package com.lti.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProductPictureDto {
	
	private	int productId;
	private MultipartFile productImg;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public MultipartFile getProductImg() {
		return productImg;
	}
	public void setProductImg(MultipartFile productImg) {
		this.productImg = productImg;
	}

	
}
