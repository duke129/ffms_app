package com.happiest.minds.ffms.sales.pojo;

import java.math.BigInteger;

public class ProductDTO {

		private String idProduct;
		private BigInteger assetId;
		private String description;
		private String imgPath;
		private String name;
		private String videoPath;
		private String price;
		private String image;
		private String assetTypeId;
		
		public String getAssetTypeId() {
			return assetTypeId;
		}
		public void setAssetTypeId(String assetTypeId) {
			this.assetTypeId = assetTypeId;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public String getIdProduct() {
			return idProduct;
		}
		public void setIdProduct(String idProduct) {
			this.idProduct = idProduct;
		}
		public BigInteger getAssetId() {
			return assetId;
		}
		public void setAssetId(BigInteger assetId) {
			this.assetId = assetId;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getImgPath() {
			return imgPath;
		}
		public void setImgPath(String imgPath) {
			this.imgPath = imgPath;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getVideoPath() {
			return videoPath;
		}
		public void setVideoPath(String videoPath) {
			this.videoPath = videoPath;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
		@Override
		public String toString() {
			return "ProductDTO [idProduct=" + idProduct + ", imgPath=" + imgPath + ", name=" + name + ", price=" + price
					+ ", image=" + image + ", assetTypeId=" + assetTypeId + "]";
		}
		
		
		
		
		
		
			
	
}
