package com.yslm.dao.goods;

import java.util.List;

import com.yslm.dao.BaseDao;
import com.yslm.model.goods.Goods;

public class GoodsDao extends BaseDao {

	@Override
	protected Class<?> entityClass() {
		return Goods.class;
	}
	
	public List<Goods> getAll(){
		return super.list();
	}
	
	

}
 