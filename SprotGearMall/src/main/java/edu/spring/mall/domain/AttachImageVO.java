package edu.spring.mall.domain;

public class AttachImageVO {
	/* 경로 */
	private String uploadPath;
	
	/* uuid */
	private String uuid;
	
	/* 파일 이름 */
	private String fileName;
	
	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "AttachImageVO [uploadPath=" + uploadPath + ", uuid=" + uuid + ", fileName=" + fileName + ", productId="
				+ productId + "]";
	}

	public AttachImageVO(String uploadPath, String uuid, String fileName, int productId) {
		super();
		this.uploadPath = uploadPath;
		this.uuid = uuid;
		this.fileName = fileName;
		this.productId = productId;
	}

	public AttachImageVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* 상품 id */
	private int productId;
}
