package edu.spring.mall.domain;

public class ProductQnaJoinReplyVO {
	private ProductQnaVO qna;
	private ProductQnaReplyVO reply;

	public ProductQnaJoinReplyVO(ProductQnaVO qna, ProductQnaReplyVO reply) {
		this.qna = qna;
		this.reply = reply;
	}
	@Override
	public String toString() {
		return "ProductQnaJoinReplyVO [qna=" + qna + ", reply=" + reply + "]";
	}
	public ProductQnaVO getQna() {
		return qna;
	}
	public void setQna(ProductQnaVO qna) {
		this.qna = qna;
	}
	public ProductQnaReplyVO getReply() {
		return reply;
	}
	public void setReply(ProductQnaReplyVO reply) {
		this.reply = reply;
	}
	public ProductQnaJoinReplyVO() {
	}


}
