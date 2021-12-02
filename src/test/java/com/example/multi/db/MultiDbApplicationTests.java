package com.example.multi.db;

import com.example.multi.db.coupoun.entities.Coupon;
import com.example.multi.db.coupoun.entities.CouponRepo;
import com.example.multi.db.product.entities.Product;
import com.example.multi.db.product.entities.ProductRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class MultiDbApplicationTests {

	private final CouponRepo couponRepo;
	private final ProductRepo productRepo;

   @Autowired
	public MultiDbApplicationTests(CouponRepo couponRepo, ProductRepo productRepo) {
		this.couponRepo = couponRepo;
	   this.productRepo = productRepo;
   }

	@Test
	void contextLoads() {
	}

	@Test
	public void testSaveCoupon()
	{
		Coupon code = new Coupon("CODE", new BigDecimal(23), "12/12/12");
		Coupon save = couponRepo.save(code);
		Assertions.assertNotNull(save);

	}
	@Test
	public void testSaveProduct()
	{
		Product product = new Product("iphone", "desc", new BigDecimal(123), "CODE");
		Product save = productRepo.save(product);
		Assertions.assertNotNull(save);

	}


}
