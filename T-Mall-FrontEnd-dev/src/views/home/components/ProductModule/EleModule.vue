<template>
  <div>
    <h3 class="header">热销产品</h3>
    <el-row type="flex" class="bd">
      <el-col :span="5" class="left-bd">
        <a href="javascript:;" style="display: block">
          <img
            class="left-img"
            src="../../../../assets/upload/leftP1.webp"
            alt=""
          />
        </a>
      </el-col>
      <el-col :span="1"></el-col>
      <el-col :span="18" class="right-bd">
        <div
          style="cursor: pointer"
          v-for="product in products"
          :key="product.productId"
          @click="toDetail(product.productId)"
        >
          <img class="item" :src="product.imgSrc" alt="" />
          <div class="items">
            <h4 class="name">{{ product.productName }}</h4>
            <p class="desc">{{ product.productDesc }}</p>
            <div class="price">{{ product.productPrice }}元起</div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  methods: {
    a() {},
    toDetail(pId) {
      this.$router.push({
        path: '/details',
        query: {
          id: pId
        }
      })
    }
  },
  data() {
    return {
      products: [
        {
          productId: 65,
          productName: '美的空调',
          productPrice: 2599,
          productDesc: '变频冷暖壁挂式空调',
          imgSrc:
            'http://127.0.0.1:8090/files/202204084dee2f67-69f2-4563-aea5-493f73cd9508空调.jpg'
        },
        {
          productId: 64,
          productName: '小米电视',
          productPrice: 899,
          productDesc: '4K高清HDR蓝牙语音遥控',
          imgSrc:
            'http://127.0.0.1:8090/files/20220408451e6a58-7651-41b9-8b11-4458cf9b6713电视.jpg'
        },
        {
          productId: 66,
          productName: '九阳家用榨汁机',
          productPrice: 299,
          productDesc: '全自动冷压炸果汁果蔬机渣汁分离',
          imgSrc:
            'http://127.0.0.1:8090/files/202204081cf7917f-77d8-44b6-9e7f-8a505a16a06c榨汁机.jpg'
        },
        {
          productId: 89,
          productName: '999感冒灵',
          productPrice: 13.3,
          productDesc: '三九999感冒灵颗粒9袋',
          imgSrc:
            'http://127.0.0.1:8090/files/2022040872b923e3-8ecc-43c7-aa1c-53b7f657696213.3.jpg'
        },
        {
          productId: 80,
          productName: 'HUAWEI P50 pro',
          productPrice: 5988,
          productDesc: '创新双屏操作体验',
          imgSrc:
            'http://127.0.0.1:8090/files/202204082012a5be-e3c5-475e-b041-aba305a6d770p50.jpg'
        },
        {
          productId: 1,
          productName: '三体全集',
          productPrice: 50,
          productDesc: '中国科幻基石丛书',
          imgSrc:
            'https://img14.360buyimg.com/n1/jfs/t1705/189/702227414/177982/cc8c12f0/55dab54dN5271c377.jpg'
        },
        {
          productId: 67,
          productName: '水壶',
          productPrice: 69,
          productDesc: '1800W大功率开水壶双层',
          imgSrc:
            'http://127.0.0.1:8090/files/20220408325907e5-54e2-441d-9369-372215ad5b76小米电水壶.jpg'
        },
        // 23
        {
          productId: 71,
          productName: '洗手液',
          productPrice: 69.9,
          productDesc: '健康抑菌洗手液滋润',
          imgSrc:
            'http://127.0.0.1:8090/files/2022040839c5535a-e227-4b40-a9f8-85500ecc8359蓝月亮洗手液.jpg'
        }
      ]
    }
  }
}
</script>

<style scoped>
.header {
  font-weight: 400;
  height: 60px;
  line-height: 80px;
}
.left-bd {
  height: 500px;
  box-shadow: rgba(67, 71, 85, 0.27) 0px 0px 0.25em,
    rgba(90, 125, 188, 0.05) 0px 0.25em 1em;
  transition: all 0.4s;
  overflow: hidden;
}
.left-bd:hover {
  box-shadow: rgba(50, 50, 93, 0.25) 0px 6px 12px -2px,
    rgba(0, 0, 0, 0.3) 0px 3px 7px -3px;
  transform: translateY(-3px);
}

.left-img {
  width: 100%;
}
/*
.right-bd {
  height: 500px;
  width: 100%;
  display: grid;
  grid-template-columns: repeat(4, 25%);
  grid-template-rows: repeat(2, 25%);
}
*/
.right-bd {
  height: 500px;
  width: 100%;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-template-rows: repeat(2, 1fr);
  grid-column-gap: 10px;
  grid-row-gap: 10px;
}
.right-bd > div {
  box-shadow: rgba(9, 30, 66, 0.25) 0px 1px 1px,
    rgba(9, 30, 66, 0.13) 0px 0px 1px 1px;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 2px;
  transition: all 0.4s;
  position: relative;
}
.right-bd > div:hover {
  box-shadow: rgba(17, 17, 26, 0.1) 0px 4px 16px,
    rgba(17, 17, 26, 0.1) 0px 8px 24px, rgba(17, 17, 26, 0.1) 0px 16px 56px;
  transform: translateY(-3px);
}
.item {
  width: 80%;
  height: 150px;
  position: absolute;
  top: 10px;
}

.price {
  /* bottom: 8px; */
  color: #ff6700;
  font-weight: 100;
}
.desc {
  /* bottom: 30px; */
  color: #b0b0b0;
  font-size: 14px;
}
.name {
  font-weight: 100;
  /* bottom: 50px; */
}
.items {
  position: absolute;
  bottom: 10px;
}
</style>