package org.edupoll.app.repository;

import org.apache.ibatis.session.SqlSession;
import org.edupoll.app.model.Product;
import org.edupoll.app.model.ProductImage;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductsRepository {
	private final SqlSession sqlSession;
	
	public int saveProdcut(Product one) {
		return sqlSession.insert("products.saveProduct", one);
	}
	
	public int saveProdcutImage(ProductImage one) {
		return sqlSession.insert("products.saveProductImage", one);
	}
}
