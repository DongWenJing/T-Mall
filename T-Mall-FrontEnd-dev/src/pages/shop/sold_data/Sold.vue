<template>
  <Container>
    <template v-slot>
      <SubHeader>
        <template v-slot>商品销售总体情况</template>
      </SubHeader>
      <div class="head">
        <el-button class="btn" type="primary" size="mini"  @click="refreshTable">刷新</el-button>
      </div>
      <el-descriptions
        class="margin-top"
        :column="1"
        style="width: 200px"
        border
      >
        <el-descriptions-item>
          <template slot="label"> 销售总额（元） </template>
          {{sale}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label"> 已售商品（件） </template>
          {{volume}}
        </el-descriptions-item>
      </el-descriptions>
    </template>
  </Container>
</template>

<script>
import request from '../../../utils/request'
const SubHeader = () => import('../../components/SubHeader.vue')
const Container = () => import('../../components/Container.vue')

export default {
  components: {
    SubHeader,
    Container
  }, data(){
    return{
      sale:0, //销售额
      volume:0 
      }//销量
    },
    methods: {
      async getshopId(){
        let shopId=sessionStorage.getItem("shopId")
        request.get("/shop/getsale/"+shopId)
              .then((res) => {
                this.sale=res.data
              })
        // this.sale=res.data
        // console.log("当前数据"+data)
        // let {date:res1}=await request.get("/shop/getVolume/"+shopId)
        request.get("/shop/getVolume/"+shopId)
               .then((res) => {
                 this.volume=res.data
               })
        // this.volume=res1.data
        // return null
      },
      //刷新表格
      refreshTable(){
        this.getshopId(),
        this.$message({
          message: '刷新成功!',
          type: 'success'
        })
      },  
    },
    created: function(){
      this.getshopId()
    }
  }

</script>

<style scoped>
.container {
  padding: 20px;
  box-shadow: rgba(0, 0, 0, 0.16) 0px 1px 4px;
  border-radius: 5px;
  /* background-color: #fff; */
}
.head {
  display: flex;
  margin-bottom: 10px;
}
.head h3 {
  font-weight: 400;
  color: #333;
}
</style>