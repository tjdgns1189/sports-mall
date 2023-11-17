package edu.spring.mall.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.mall.domain.CartVO;

@Repository
public class CartDAOImple implements CartDAO {
	
	private static final Logger logger =
			LoggerFactory.getLogger(CartDAOImple.class);
	
	private static final String NAMESPACE =
			"edu.spring.mall.CartMapper";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(CartVO vo) {
		logger.info("cart insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<CartVO> select(String memberId) {
		logger.info("cart select() 호출");
		return sqlSession.selectList(NAMESPACE + ".select_by_member_id", memberId);
	}

	@Override
	public CartVO select(int cartId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int cartId) {
		logger.info("cart delete() 호출");
		return sqlSession.delete(NAMESPACE + ".delete_by_cart_id", cartId);
	}

	@Override
	public int update(int cartId, CartVO vo) {
		logger.info("update() 호출 : vo " + vo.toString());
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("cartId", cartId);
		args.put("vo", vo);
		return sqlSession.update(NAMESPACE + ".update", args);
	}

	@Override
	public List<CartVO> selectByProductId(int productId) {
		logger.info("selectByProductId() 호출 : productId " + productId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_product_id", productId);
	}


}
