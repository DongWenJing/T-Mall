<template>
  <div class="container">
    <h3 class="header">关于</h3>
    <p>提莫商城项目</p>
    <el-descriptions title="高战码农" style="padding: 10px" :column="1">
      <el-descriptions-item label="小组成员"
        >任禹 董文靖 邱思七 何书武 邓彬 赵贵川</el-descriptions-item
      >
    </el-descriptions>
    <el-descriptions title="项目地址" style="padding: 10px" :column="1">
      <el-descriptions-item label="Gitee">
        <el-link
          target="blank"
          type="primary"
          href="https://gitee.com/xiao-dong-doesnt-understand/t-mall.git"
          >T-Mall</el-link
        >
      </el-descriptions-item>
      <el-descriptions-item label="GitHub">
        <el-link
          target="blank"
          type="primary"
          href="https://github.com/DongWenJing/T-Mall.git"
          >T-Mall</el-link
        >
      </el-descriptions-item>
    </el-descriptions>
  </div>
</template>

<script>
export default {}
</script>

<style scoped>
.container {
  width: 70%;
  margin-left: 50%;
  transform: translate(-50%);
}
.header {
  height: 50px;
  line-height: 60px;
  font-weight: 400;
  border-bottom: 1px dotted rgba(204, 204, 204, 0.5);
}
p {
  font-style: italic;
  padding: 10px;
  color: #666;
}
</style>