package edu.spring.mall.domain;

import java.util.Date;

public class ProductVO {
   private int productId;
   private String productName;
   private int productPrice;
   private int productStock;
   private String productMaker;
   private String productImgPath;
   private String productCategory;
   private int productIsDeleted;
   private String productContent;
   private Date productCreatedDate;
   
   
   public ProductVO() {}


   public ProductVO(int productId, String productName, int productPrice, int productStock, String productMaker,
         String productImgPath, String productCategory, int productIsDeleted, String productContent,Date productCreatedDate) {
      super();
      this.productId = productId;
      this.productName = productName;
      this.productPrice = productPrice;
      this.productStock = productStock;
      this.productMaker = productMaker;
      this.productImgPath = productImgPath;
      this.productCategory = productCategory;
      this.productIsDeleted = productIsDeleted;
      this.productContent = productContent;
      this.productCreatedDate = productCreatedDate;
      
   }


   public ProductVO(String productName, int productPrice, int productStock, String productMaker, String productImgPath,
         String productCategory, int productIsDeleted) {
      super();
      this.productName = productName;
      this.productPrice = productPrice;
      this.productStock = productStock;
      this.productMaker = productMaker;
      this.productImgPath = productImgPath;
      this.productCategory = productCategory;
      this.productIsDeleted = productIsDeleted;
   }


   public ProductVO(String productName, int productPrice, int productStock, String productMaker, String productImgPath,
         String productCategory, String productContent) {
      super();
      this.productName = productName;
      this.productPrice = productPrice;
      this.productStock = productStock;
      this.productMaker = productMaker;
      this.productImgPath = productImgPath;
      this.productCategory = productCategory;
      this.productContent = productContent;
   }


   public String getProductContent() {
      return productContent;
   }


   public void setProductContent(String productContent) {
      this.productContent = productContent;
   }


   public int getProductId() {
      return productId;
   }


   public void setProductId(int productId) {
      this.productId = productId;
   }


   public String getProductName() {
      return productName;
   }


   public void setProductName(String productName) {
      this.productName = productName;
   }


   public int getProductPrice() {
      return productPrice;
   }


   public void setProductPrice(int productPrice) {
      this.productPrice = productPrice;
   }


   public int getProductStock() {
      return productStock;
   }


   public void setProductStock(int productStock) {
      this.productStock = productStock;
   }


   public String getProductMaker() {
      return productMaker;
   }


   public void setProductMaker(String productMaker) {
      this.productMaker = productMaker;
   }


   public String getProductImgPath() {
      return productImgPath;
   }


   public void setProductImgPath(String productImgPath) {
      this.productImgPath = productImgPath;
   }


   public String getProductCategory() {
      return productCategory;
   }


   public void setProductCategory(String productCategory) {
      this.productCategory = productCategory;
   }


   public int getProductIsDeleted() {
      return productIsDeleted;
   }


   public void setProductIsDeleted(int productIsDeleted) {
      this.productIsDeleted = productIsDeleted;
   }
   
   public Date getProductCreatedDate() {
	      return productCreatedDate;
	   }


   public void setProductCreatedDate(Date productCreatedDate) {
	      this.productCreatedDate = productCreatedDate;
	   }
   
   
   @Override
   public String toString() {
      return "ProductVO [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
            + ", productStock=" + productStock + ", productMaker=" + productMaker + ", productImgPath="
            + productImgPath + ", productCategory=" + productCategory + ", productIsDeleted=" + productIsDeleted + ", productCreatedDate=" + productCreatedDate
            + "]";
   }
   
   
   
   
   
}