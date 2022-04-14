<template>
  <Container>
    <template slot>
      <SubHeader>
        <template v-slot>管理所有商品分类</template>
      </SubHeader>
      
       <el-button style="margin-bottom: 10px" type="primary"
       @click="showAddCategoryDialog">添加分类</el-button>
       <el-button
         style="margin-bottom: 10px"
         plain
         type="primary"
         @click="refresh"
       		>刷新</el-button>
		
      <el-table :data="tableData" style="width: 100%;margin-bottom: 20px;" row-key="categoryId" border stripe>
       <el-table-column type="index" label="序号">
       </el-table-column>
       <el-table-column prop="categoryName" label="分类名称">
       </el-table-column>
       <el-table-column prop="status" label="状态">
		   <!-- 定义作用域插槽 展现数据     scope.row展现行级元素 -->
		   <template slot-scope="scope">
		     <el-switch v-model="scope.row.status" active-color="#13ce66" inactive-color="#ff4949"
		       @change="updateStatus(scope.row)"></el-switch>
		   </template>
        </el-table-column>
		<el-table-column prop="level" label="等级">
		  <!-- 定义作用域插槽 定义标签等级-->
		  <template slot-scope="scope">
		    <el-tag effect="dark" v-if="scope.row.level == 1">一级分类</el-tag>
		    <el-tag effect="dark" type="warning" v-if="scope.row.level == 2">二级分类</el-tag>
		    <el-tag effect="dark" type="danger" v-if="scope.row.level == 3">三级分类</el-tag>
		  </template>
		</el-table-column>
        <el-table-column align="center" label="操作" width="200px">
          <template slot-scope="scope">
            <el-button size="mini" type="success" icon="el-icon-edit" @click="updateCategoryBtn(scope.row)">编辑</el-button>
            <el-button size="mini" type="danger" icon="el-icon-delete" @click="deleteCategoryBtn(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 添加分类信息弹出 -->
      <el-dialog title="新增商品分类" :visible.sync="addCategoryDialogVisible" width="50%" @close="closeAddCategoryDialog">
		<!-- 定义分类表单 -->
        <el-form ref="categoryFormRef" :model="categoryForm" :rules="rules" label-width="100px">
          <el-form-item label="分类名称:" prop="categoryName">
            <el-input v-model="categoryForm.categoryName"></el-input>
          </el-form-item>
		  <!-- 定义父级分类选项 -->
		  <el-form-item label="父级分类名称:">
			  <!-- 通过级联选择器定义1/2级商品分类
			     步骤:  1.注意标签导入
			            2.options 表示数据的来源
			            3.指定props属性指定数据配置
			  -->
			  <el-cascader v-model="selectedKeys" :props="props" :options="parentCategoryList" clearable
			  @change="parentCategoryChange"></el-cascader>
		  </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="addCategoryDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="addCategoryForm">确 定</el-button>
        </span>
      </el-dialog> 

      <!-- 编辑分类信息弹出 -->
      <el-dialog title="修改商品分类名称" :visible.sync="updateCategoryDialogFormVisible" width="50%">
        <el-form ref="updateCategoryForm" :model="updateCategoryForm" :rules="rules" label-width="100px">
          <el-form-item label="分类名称:" prop="categoryName">
            <el-input v-model="updateCategoryForm.categoryName"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="updateCategoryDialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="updateCategory">确 定</el-button>
        </span>
      </el-dialog>
    </template>
  </Container>
</template>

<script>
// import axios from 'axios'
import request from '../../../utils/request'
const SubHeader = () => import('../../components/SubHeader.vue')
const Container = () => import('../../components/Container.vue')

export default {
  components: {
    SubHeader,
    Container
  },
  data() {
    return {
	  //定义商品分类数据
      tableData: [],
	  //控制分类对话框的显示
      addCategoryDialogVisible: false,
	  //定义商品分类新增对象
      categoryForm: {
		  categoryName: '', //定义商品分类名称
		  parentId: 0, //默认父级ID=0
		  level: 1 ,//默认是一级菜单
		  status: 1,
	  },
	  //定义商品分类校验规则
	  rules: {
		  categoryName: [{
			  required: true,
			  message: '请输入分类名称',
			  trigger: 'blur'
		  }]
	  },
	  //定义级联选择项
	  props: {
	    //定义子节点菜单展开方式
	    expandTrigger: "hover",
	    value: "categoryId", //选中数据的value值
	    label: "categoryName", //选中数据展现名称
	    children: "children", //自选项数据
	    checkStrictly: true
	  },
	  
	  //定义用户选中父级ID数组
	  selectedKeys: [],
	  //定义父级商品分类信息只查询一级和二级
	  parentCategoryList: [],
	  //定义修改对话框属性
	  updateCategoryDialogFormVisible: false,
	  updateCategoryForm: {}
    }
  },
  //定义初始化函数
  created() {
	  //默认获取商品分类列表数据
    this.load()
  },
  methods: {
	  refresh() {
	    this.load()
	  },
	  load() {
	  	this.getCategory()
	      .then((res) => {
	        this.tableData = res.data
			console.log(res)
		  })
	       .catch((err) => {
	        console.log(err)
	      })
	      .then(() => {
	        // 异步操作，需要放到 then 里面
	      })
	  },
	  getCategory() {
	  	return request.get('/category/findCategoryList/3')
	  	.then((res) => {
	  		this.$message({
	  		  message: '获取商品分类信息成功！',
	  		  type: 'success',
	  		  duration: 1000,
	  		})
	      return res
	    })
	  },
	  //根据ID修改状态信息
	  updateStatus(productCategory){
	  	request.put(`/category/status/${productCategory.categoryId}/${productCategory.status}`)
	  	.then((res) => {
	  		if (res.code === '0000') {
	  			this.$message.success("状态修改成功")
	  		} else {
	  		  this.$message.error('状态修改失败')
	  		}
	  	})
	  },
	  // 当展现新增商品分类时,应该渲染级联框数据
	  showAddCategoryDialog() {
	    this.findParentCategoryList()
	    this.addCategoryDialogVisible = true
	  },
	  findParentCategoryList(){
	  	//动态获取商品分类信息  type=2表示获取2级商品分类信息
		request.get("/category/findCategoryList/2")
		.then((res) => {
			if (res.code !== "0000") {
				this.$message.error("获取商品分类列表失败!!")
			}
			this.parentCategoryList = res.data
		})
	  },
	  //当选择项发生变化时,触发该函数
	  parentCategoryChange(){
	  	console.log(this.selectedKeys)
	  	console.log(this.categoryForm)
	  	//如果选中节点的长度>0 则表示不是一级商品分类
	  	if(this.selectedKeys.length > 0) {
	  		//[1,2] 主要获取最后一位
	  		this.categoryForm.parentId = this.selectedKeys[this.selectedKeys.length - 1]
	  		//数组级别+1
	  		this.categoryForm.level = this.selectedKeys.length + 1
	  	} else {
	  		//如果数组长度不大于0 则表示一级商品分类信息
	  		this.categoryForm.parentId = 0
	  		this.categoryForm.level = 1
	  	}
	  },
	  addCategoryForm() {
	  	//先将整个表单进行校验
	    this.$refs.categoryFormRef.validate(async validate => {
	  	if (!validate) return  
		  await request.post("/category/saveCategory",this.categoryForm)
		  .then((res) => {
			if(res.code !== "0000") {
				this.$message.error("新增商品分类失败")
			} else{
			  this.$message.success("新增商品分类成功")
			  //新增成功,则刷新分类列表信息
			  this.load()
			  this.addCategoryDialogVisible = false	
			}  
		  })
	    })
	  },
	  //当点击关闭按钮时,应该重置整个表单
	  closeAddCategoryDialog(){
	  	this.initCategoryForm()
	  },
	  initCategoryForm(){
	  	this.$refs.categoryFormRef.resetTransform()
	  	//清空form提交其他数据
	  	this.categoryForm.parentId = 0
	  	this.categoryForm.level = 1
	  	//清空级联选择框的数组
	  	this.selectedKeys = []
	  },
	  //由于有层级关系,所以修改名称
	  updateCategoryBtn(productCategory) {
	  	this.updateCategoryForm = productCategory
	  	this.updateCategoryDialogFormVisible = true
	  },
	  updateCategory(){
	  	//修改商品分类信息
	  	request.put('/category/updateCategory',this.updateCategoryForm)
		.then((res) => {
			if(res.code !== "0000") {
				this.$message.error("更新商品分类失败")
			}
			this.$message.success("更新商品分类成功")
			this.tableData = res.data
			this.load();
			this.updateCategoryDialogFormVisible = false
		})
	  },
	  deleteCategoryBtn(productCategory) {
		  //删除商品分类信息,如果为父级节点则需要删除所有的子级信息
		  this.$confirm('此操作将永久删除该数据, 是否继续?', '提示',{
			  confirmButtonText: '确定',
			  cancelButtonText: '取消',
			  type: 'warning'
		  }).then((res) => {
			//传递分类id
			request.delete('/category/delete',{params: {categoryId:productCategory.categoryId,level:productCategory.level}})
			.then((res) => {
				if(res.code !== '0000') {
					this.$message.error("删除商品分类失败")
				}
				this.$message.success('删除数据成功')
				this.load()
			})
		  }).catch(() => {
			this.$message({
				type: 'info',
				message: '已取消删除'
			});
		  });
	  },
  }
}
</script>

<style scoped>
.container {
  padding: 10px;
  box-shadow: rgba(0, 0, 0, 0.16) 0px 1px 4px;
  border-radius: 5px; 
  /* background-color: #fff; */
 }
.el-cascader {
    width: 100%;
  }
</style>