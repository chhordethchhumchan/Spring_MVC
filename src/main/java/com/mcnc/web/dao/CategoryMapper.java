package com.mcnc.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcnc.web.dto.Category;

@Repository
public interface CategoryMapper {

	//@Select("select * from bbs_category")
   public List<Category> listCatergory();
 
   //Edit
 	//@Select("select * from bbs_category where(category_code = #{id})")
 	public Category getCatergoryById(String id);
 	
 	public int updateById(Category category);
 	
 	public int insertCategory(Category category);

 	public int deleteById(String id);
}
