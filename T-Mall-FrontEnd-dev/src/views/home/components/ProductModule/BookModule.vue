<template>
  <div>
    <h3 class="header">畅销书籍</h3>
    <el-row type="flex" class="bd">
      <el-col :span="8" class="left-bd">
        <a href="javascript:;" style="display: block">
          <img
            class="left-img"
            src="../../../../assets/upload/leftP3.webp"
            alt=""
          />
        </a>
      </el-col>
      <el-col :span="1"></el-col>
      <el-col :span="6" class="right-bd">
        <div
          style="cursor: pointer"
          v-for="(item, index) in showBooks"
          :key="index"
          @click="toDetail(item.productId)"
        >
          <img class="item" :src="item.imgSrc" alt="" />
          <p class="name">{{ item.productName }}</p>
          <div class="price">￥{{ item.productPrice }}</div>
        </div>
      </el-col>
      <el-col :span="1"></el-col>
      <el-col :span="8" class="rank-bd">
        <header>
          <img src="../../../../assets/icon/rank.png" alt="" />
          排行榜
        </header>
        <div
          v-for="(item, index) in books"
          :key="item.productId"
          class="num-bd"
        >
          <div class="num">
            <div class="rank-num">{{ index + 1 }}</div>
            <img
              style="width: 20px"
              src="../../../../assets/icon/num.png"
              alt=""
            />
          </div>
          <div class="rank-item" @click="toDetail(item.productId)">
            <img :src="item.imgSrc" class="rank-img" alt="" />
            <div>
              <div style="font-size: 13px">{{ item.productName }}</div>
              <div class="rank-price">￥ {{ item.productPrice }}</div>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  methods: {
    toDetail(pId) {
      console.log(pId)
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
      books: [
        {
          imgSrc:
            'http://127.0.0.1:8090/files/202204089345f198-f41c-4d5a-ae7c-58fd4303503a三国.jpg',
          productName: '三国演义',
          productPrice: 60,
          productId: 57
        },
        {
          imgSrc:
            'http://127.0.0.1:8090/files/20220408baf093ca-51d5-41c8-8800-90d9cbb7839f红楼梦.jpg',
          productName: '红楼梦',
          productPrice: 80,
          productId: 51
        },
        {
          imgSrc:
            'http://127.0.0.1:8090/files/2022040891a61691-0158-4b70-8566-ce1ee3df6409水浒传.jpg',
          productName: '水浒传',
          productPrice: 50,
          productId: 58
        },
        {
          imgSrc:
            'http://127.0.0.1:8090/files/202204088a64f051-2d7f-4e30-88f1-73f18b75f28f西游记.jpg',
          productName: '西游记',
          productPrice: 49,
          productId: 2
        }
      ],
      showBooks: [
        {
          imgSrc:
            'https://img14.360buyimg.com/n1/jfs/t1705/189/702227414/177982/cc8c12f0/55dab54dN5271c377.jpg',
          productName: '三体全集',
          productPrice: 50,
          productId: 1
        },
        {
          imgSrc:
            'http://127.0.0.1:8090/files/2022040836ebc349-d81b-4b25-9690-175c6ccad51e活着.jpg',
          productName: '活着',
          productPrice: 75,
          productId: 59
        },
        {
          imgSrc:
            'http://127.0.0.1:8090/files/202204083f9066ef-898a-4983-8123-e98668f563d9机器人短篇.jpg',
          productName: '机器人短篇全集',
          productPrice: 45,
          productId: 62
        },
        {
          imgSrc:
            'http://127.0.0.1:8090/files/202204081f5d0689-4fa3-41e4-bfb0-f582072392ad鬼谷子.jpg',
          productName: '鬼谷子',
          productPrice: 88,
          productId: 60
        },
        {
          imgSrc:
            'http://127.0.0.1:8090/files/20220408661550ac-8611-4d6c-b01c-4c127ce26102http.jpg',
          productName: '图解HTTP',
          productPrice: 160,
          productId: 61
        },
        // 2
        {
          imgSrc:
            'http://127.0.0.1:8090/files/202204088a64f051-2d7f-4e30-88f1-73f18b75f28f西游记.jpg',
          productName: '西游记',
          productPrice: 49,
          productId: 2
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
.left-bd,
.rank-bd {
  height: 450px;
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
.bd {
  margin-bottom: 50px;
}
.left-img {
  /* width: 100%; */
  height: 450px;
  width: 100%;
}
.right-bd {
  height: 450px;
  width: 100%;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
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
.right-bd > div:hover img {
  opacity: 0.8;
}
.item {
  width: 80%;
  height: 140px;
  position: absolute;
  top: 10px;
}
.name,
.desc,
.price {
  position: absolute;
}
.price {
  bottom: 10px;
  color: #f12b2b;
  font-weight: 700;
}
.desc {
  bottom: 40px;
  color: #b0b0b0;
  font-size: 14px;
}
.name {
  font-weight: 100;
  bottom: 30px;
}
.rank-bd {
  padding: 10px;
}
header {
  border-bottom: 1px dotted #ccc;
  height: 30px;
  line-height: 20px;
}
header > img {
  height: 20px;
  vertical-align: middle;
}
.rank-img {
  width: 70px;
}
.rank-item {
  display: flex;
  margin-top: 1px;
}
.rank-price {
  color: #f12b2b;
  font-size: 13px;
  margin-top: 10px;
}
.num {
  position: relative;
  left: 0;
  top: 40px;
}
.num-bd {
  cursor: pointer;
}

.rank-num {
  color: #fff;
  position: absolute;
  left: 5px;
  bottom: 4px;
  font-size: 12px;
}
</style>