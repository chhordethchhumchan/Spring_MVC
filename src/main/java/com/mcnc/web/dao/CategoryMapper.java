package com.mcnc.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.mcnc.web.model.Category;

@Repository
public interface CategoryMapper {

	//@Select("select * from bbs_category")
   public List<Category> listCatergory();
}
