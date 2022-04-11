package com.tmall.service.impl;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.tmall.pojo.PaymentBo;
import com.tmall.pojo.User;
import com.tmall.service.PayService;
import com.tmall.util.OrderUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @Author : DongWJ
 * @Date : 2022/4/2 16:12
 */
@Service
@CrossOrigin
public class PayServiceImpl implements PayService {

    //支付成功后要跳转的页面
    @Value("${alipay.returnUrl}")
    private String returnUrl;

    @Override
    public String pay(User user) throws Exception {
       // PaymentBo paymentBo = new PaymentBo();
        //充值的金额是前端传入的数据
        user.setMoney(user.getMoney());
        
        user.setRecord("用户充值");
        //调用SDK,发起支付
        AlipayTradePagePayResponse response = Factory.Payment
                //选择网页支付平台
                .Page()
                //调用支付方法,设置订单名称,我们系统中的订单号,金额,回调页面
                .pay(user.getRecord(), OrderUtil.getOrderNo(),
                       user.getMoney().toString(), returnUrl);
        //这里的response.body,就是一个可以直接加载的html片段
        return response.body;
    }
}
