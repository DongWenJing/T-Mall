package com.tmall.controller;

import com.tmall.common.ResponseData;
import com.tmall.common.ResponseDataUtils;
import com.tmall.pojo.Product;
import com.tmall.service.ProductService;
import com.tmall.vo.Page;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @author R.Yu
 * @date 2022/3/19 17:27
 */
@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 用于所有商品的展示
     * @return
     */
    @GetMapping("/all")
    public List<Product> doSelectProductAll() {
        return productService.selectProductAll();
    }

    /**
     * 商家查询显示本店商品
     * @RequestParam(defaultValue)用于设置界面默认显示的数据
     * @return
     */
    @GetMapping("/shop")
    public ResponseData<?> findByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "5") Integer pageSize,
                                      @RequestParam(defaultValue = "") String key,
                                      @RequestParam Integer ownerId){
        //通过page对象数据获取用户信息
        int offset = (pageNum-1)*pageSize; //offset 从第几条开始显示
        List<Product> userData =  productService.findByPage(offset,pageSize,key,ownerId);
        //数据注入page对象
        Page<Product> page = new Page<>();
        page.setData(userData);
        //通过商家Id获取到商品总数
       Integer total = productService.countShopProduct(ownerId);
       page.setTotal(total);
       page.setPageNum(pageNum);
       page.setPageSize(pageSize);
        return ResponseDataUtils.buildSuccess("0","商品信息获取成功",page);
    }
    /**
     * 修改本店铺商品信息
     */
    @PutMapping
    public ResponseData<?> updateProduct(@RequestBody Product product){
        productService.updateProduct(product);
        return ResponseDataUtils.buildSuccess("0","商品信息修改成功");
    }


    /**
     * 商家管理新增商品
     */
    @PostMapping
    public ResponseData<?> insertProduct(@RequestBody Product product) {
        productService.insertProduct(product);
        return ResponseDataUtils.buildSuccess("0","商品添加成功");
    }

    /**
     *根据id删除商品信息
     */
    @DeleteMapping("/{productId}")
    public ResponseData<?> deleteProduct(@PathVariable BigInteger productId){
        productService.deleteProductById(productId);
        return ResponseDataUtils.buildSuccess("0","删除成功");
    }

    /**
     * 根据shopID获取所有商品
     */
    @GetMapping("/shop_id/{shopId}")
    public ResponseData<?> findByShopId(@PathVariable BigInteger shopId) {
        List<Product> products = productService.findByShopId(shopId);
        return ResponseDataUtils.buildSuccess("0", "该店商品信息获取成功！", products);
    }

}
