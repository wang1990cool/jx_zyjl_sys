<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.ksnd" placeholder="考试年度" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('ksbm:ksbmxxb:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button v-if="isAuth('ksbm:ksbmxxb:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
<!--
      <el-table-column
        prop="id"
        header-align="center"
        align="center"
        label="id">
      </el-table-column>
-->
      <el-table-column
        prop="ksnd"
        header-align="center"
        align="center"
        label="考试年份">
      </el-table-column>

      <el-table-column
        prop="ksxmmc"
        header-align="center"
        align="center"
        label="考试名称">
      </el-table-column>
<!--

      <el-table-column
        prop="xsxm"
        header-align="center"
        align="center"
        label="姓名">
      </el-table-column>
-->

      <el-table-column
        prop="sex"
        header-align="center"
        align="center"
        label="性别">
      </el-table-column>
      <el-table-column
        prop="birth"
        header-align="center"
        align="center"
        label="出生日期">
      </el-table-column>
      <el-table-column
        prop="mz"
        header-align="center"
        align="center"
        label="民族">
      </el-table-column>

      <el-table-column
        prop="sfzh"
        header-align="center"
        align="center"
        label="身份证号">
      </el-table-column>
      <el-table-column
        prop="telephone"
        header-align="center"
        align="center"
        label="联系电话">
      </el-table-column>
<!--

      <el-table-column
        prop="email"
        header-align="center"
        align="center"
        label="email">
      </el-table-column>
-->

      <el-table-column
        header-align="center"
        align="center"
        width="80"
        label="照片">
        <template slot-scope="scope">
          <el-button  v-if="isAuth('ksbm:ksbmxxb:info')" type="text" size="small" @click="zszpHandle(scope.row.id)">查看证书 </el-button>
        </template>
      </el-table-column>


      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        label="报名时间">
      </el-table-column>

      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button v-if="isAuth('ksbm:ksbmxxb:update')" type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
          <el-button v-if="isAuth('ksbm:ksbmxxb:delete')" type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
    <zszp v-if="zszpVisible" ref="zszp" @refreshDataList="getDataList"></zszp>
  </div>
</template>

<script>
  import AddOrUpdate from './ksbmxxb-add-or-update'
  import Zszp from './ksbmxxb-zszp.vue'
  export default {
    data () {
      return {
        dataForm: {
          ksnd: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false,
        zszpVisible: false
      }
    },

    components: {
      AddOrUpdate,
      Zszp
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/ksbm/ksbmxxb/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },
      //查看照片
      zszpHandle (id) {
        this.zszpVisible = true
        this.$nextTick(() => {
          this.$refs.zszp.init(id)
        })
      },
      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataList()
      },
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/ksbm/ksbmxxb/delete'),
            method: 'post',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      }
    }
  }
</script>
