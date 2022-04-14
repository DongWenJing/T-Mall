<template>
  <div>
    <h3 class="header">限时折扣</h3>
    <el-row type="flex" class="discount-bd">
      <el-col :span="4" class="seckill">
        <h3>限时秒杀</h3>
        <img src="../../../assets/upload/seckill.png" alt="" />
        <p>距离本场结束还剩</p>
        <div class="time">
          <div>{{ hour }}</div>
          <div>{{ minute }}</div>
          <div>{{ second }}</div>
        </div>
      </el-col>
      <el-col :span="4" v-for="product in products" :key="product.productId">
        <DiscountItem :cProduct="product" />
      </el-col>
    </el-row>
  </div>
</template>

<script>
import request from '../../../utils/request'
const DiscountItem = () => import('./DiscountItem.vue')
export default {
  data() {
    return {
      inputTime: +new Date('2022-04-14 23:00:00'),
      hour: '',
      minute: '',
      second: '',
      timer: null,
      products: [
        {
          productId: 51,
          productName: '红楼梦',
          productPrice: 80,
          discount: 0.9,
          imgSrc:
			'http://127.0.0.1:8090/files/20220408baf093ca-51d5-41c8-8800-90d9cbb7839f红楼梦.jpg'
        },

        {
          productId: 52,
          productName: '小米投影仪',
          productPrice: 5000,
          discount: 0.9,
          imgSrc:
            'http://127.0.0.1:8090/files/20220408e7fb565d-2282-4552-bea2-ea544907eb11投影仪.jpg'
        },
        {
          productId: 53,
          productName: '小米空调',
          productPrice: 2700,
          discount: 0.9,
          imgSrc:
            'http://127.0.0.1:8090/files/20220408461a0c49-4ff3-4b45-b7c5-a1bfd835d9db小米空调.jpg'
        },
        {
          productId: 110,
          productName: 'HAZZYS POLO衫',
          productPrice: 800,
          discount: 0.8,
          imgSrc:
            'http://127.0.0.1:8090/files/20220408141cdf5f-0683-4988-8e62-b64c2d28ef53540.jpg'
        },
        {
          productId: 70,
          productName: '舒肤佳洗手液',
          productPrice: 20.0,
          discount: 1,
          imgSrc:
            'http://127.0.0.1:8090/files/2022040824420696-b977-4e4b-93c4-13557770958c舒肤佳.jpg'
        }
      ]
    }
  },
  created() {
    //调用定时器
    request.get('/countdown').then((res) => {
      let s = res.data
      let a = s.slice(0, 11)
      let b = s.slice(13)
      let m = s.slice(11, 13)
      m = (Number(m) + 8) % 24
      m = m < 10 ? '0' + m : m
      let t = a + m + b
      this.inputTime = +new Date(t)
    })
    this.timer = setInterval(() => {
      this.countDown()
    }, 1000)
  },
  methods: {
    countDown() {
      // 返回的是当前时间总的毫秒数
      var nowTime = +new Date()
      // times是剩余时间总的秒数
      var times = (this.inputTime - nowTime) / 1000
      var h = parseInt((times / 60 / 60) % 24)
      h = h < 10 ? '0' + h : h
      // 把剩余的小时给 小时黑色盒子
      this.hour = h
      var m = parseInt((times / 60) % 60)
      m = m < 10 ? '0' + m : m
      this.minute = m
      var s = parseInt(times % 60)
      s = s < 10 ? '0' + s : s
      this.second = s
    }
  },
  components: {
    DiscountItem
  }
}
</script>

<style scoped>
.discount-bd {
  box-shadow: rgba(17, 17, 26, 0.1) 0px 4px 16px,
    rgba(17, 17, 26, 0.05) 0px 8px 32px;
  height: 240px;
}
.header {
  font-weight: 400;
  height: 60px;
  line-height: 80px;
  color: 333;
}
.seckill {
  height: 240px;
  padding-top: 25px;
  text-align: center;
  background-color: #f02b2b;
}
.seckill img {
  margin-top: 20px;
  width: 30px;
}
.seckill h3 {
  font-weight: 400;
  color: #fff;
  font-size: 25px;
  margin-top: 10px;
}
.seckill p {
  font-size: 10px;
  color: #fff;
  margin-top: 10px;
}
.time {
  display: flex;
  margin-left: 50%;
  transform: translate(-50%);
  margin-top: 5px;
  width: 100px;
}
.time div {
  height: 30px;
  width: 30px;
  margin-left: 3px;
  background-color: #000;
  color: #fff;
  font-weight: 700;
  text-align: center;
  line-height: 30px;
}
.time::after {
  display: block;
  content: '';
  width: 100px;
  height: 1px;
  background-color: #f02b2b;
  position: absolute;
  top: 15px;
}
</style>