package com.tmall.service.impl;

import com.tmall.mapper.AdminProductCategoryMapper;
import com.tmall.pojo.ProductCategory;
import com.tmall.service.AdminProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminProductCategoryServiceImpl implements AdminProductCategoryService {

    @Autowired
    private AdminProductCategoryMapper adminProductCategoryMapper;

    private Map<Integer,List<ProductCategory>> getMap(){
        //新建一个map集合
        Map<Integer,List<ProductCategory>> map = new HashMap<>();
        //查询所有的数据
        List<ProductCategory> list = adminProductCategoryMapper.findCategoryList();
        //遍历所有的数据
        for (ProductCategory productCategory : list) {
            int key = productCategory.getParentId();
            if (map.containsKey(key)){
                map.get(key).add(productCategory);
            }else { //key不存在,第一个数据
                List<ProductCategory> tempList = new ArrayList<>();
                tempList.add(productCategory);
                map.put(key, tempList);
            }
        }
        return map;
    }

    private List<ProductCategory> getTwoList(Map<Integer,List<ProductCategory>> map){
        //查询一级数据
        List<ProductCategory> oneList = map.get(0);
        for (ProductCategory oneCategory : oneList) {
            //获取1级id
            int id = oneCategory.getCategoryId();
            //根据1级id,查询查询二级数据
            List<ProductCategory> twoList = map.get(id);
            //封装二级数据到一级对象中
            oneCategory.setChildren(twoList);
        }
        return oneList;
    }

    private List<ProductCategory> getThreeList(Map<Integer,List<ProductCategory>> map){
        //先查询一级和二级数据
        List<ProductCategory> oneList = getTwoList(map);
        //遍历一级集合,获取二级数据
        for (ProductCategory oneCategory : oneList) {
            //获取二级数据信息,二级数据可能为null
            List<ProductCategory> twoList = oneCategory.getChildren();
            if (twoList == null || twoList.size() == 0){
                continue;
            }
            for (ProductCategory twoCategory : twoList) {
                int twoId = twoCategory.getCategoryId();
                //获取三级集合数据
                List<ProductCategory> threeList = map.get(twoId);
                //将三级数据封装到二级对象中
                twoCategory.setChildren(threeList);
            }
        }
        return oneList;
    }

    //动态获取商品分类信息
    @Override
    public List<ProductCategory> findCategoryList(Integer level) {
        //创建一个map集合
        Map<Integer,List<ProductCategory>> map = getMap();
        //level=1 只查询一级数据
        if (level == 1){
            return map.get(0);
        }
        //level=2 查询一级和二级数据
        if (level == 2){
            return getTwoList(map);
        }
        //level=3 查询一级,二级,三级数据
        List<ProductCategory> threeList = getThreeList(map);
        return threeList;
    }

    //修改商品状态
    @Override
    @Transactional
    public void updateStatus(ProductCategory productCategory) {
        adminProductCategoryMapper.updateStatus(productCategory);
    }

    //新增商品分类
    @Override
    @Transactional
    public void addCategoryList(ProductCategory productCategory) {
        adminProductCategoryMapper.addCategoryList(productCategory);
    }

    //修改商品分类信息
    @Override
    @Transactional
    public void updateCategoryList(ProductCategory productCategory) {
        adminProductCategoryMapper.updateCategoryList(productCategory);
    }

    @Override
    @Transactional
    public void deleteCategoryList(ProductCategory productCategory) {
        if (productCategory.getLevel() == 3){
            adminProductCategoryMapper.deleteCategory(productCategory.getCategoryId());
        }else if(productCategory.getLevel() == 2){
            adminProductCategoryMapper.deleteCategory2(productCategory.getCategoryId());
        } else if (productCategory.getLevel() == 1){
            adminProductCategoryMapper.deleteCategory3(productCategory.getCategoryId());
        }
    }


}
