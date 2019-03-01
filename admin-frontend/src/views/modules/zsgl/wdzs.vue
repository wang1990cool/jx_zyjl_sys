<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-button v-if="isAuth('zsgl:wdzs:save')" type="primary" @click="addOrUpdateHandle(0,0)">新增</el-button>
        <el-button v-if="isAuth('zsgl:wdzs:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
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
      <el-table-column v-if="false"
        prop="id"
        header-align="center"
        align="center"
        width="80"
        label="ID">
      </el-table-column>
      <el-table-column
        type="index"
        width="50"
        label="序号">
      </el-table-column>
      <el-table-column
        prop="xsxh"
        header-align="center"
        align="center"
        label="学号">
      </el-table-column>
      <el-table-column
        prop="xsxm"
        header-align="center"
        align="center"
        label="姓名">
      </el-table-column>
      <el-table-column
        prop="szbj"
        header-align="center"
        align="center"
        label="所在班级">
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
        label="电话">
      </el-table-column>
      <el-table-column
        prop="zsmc"
        header-align="center"
        align="center"
        label="证书名称">
      </el-table-column>
      <el-table-column
                       header-align="center"
                       align="center"
                       width="80"
                       label="证书照片">
        <template slot-scope="scope">
          <el-button  v-if="isAuth('zsgl:wdzs:info')" type="text" size="small" @click="zszpHandle(scope.row.id)">查看证书 </el-button>
        </template>
      </el-table-column>
      <el-table-column
        v-if="false"
        prop="ztm"
        header-align="center"
        align="center"
        label="状态码">
      </el-table-column>
      <el-table-column
        prop="zt"
        header-align="center"
        align="center"
        label="状态">
      </el-table-column>
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        label="创建时间">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button  v-if="isAuth('zsgl:wdzs:info')" type="text" size="small" @click="detailHandle(scope.row.id)">查看 </el-button>
          <el-button v-if="isAuth('zsgl:wdzs:update')" type="text" size="small" @click="addOrUpdateHandle(scope.row.id,scope,row,ztm)">修改</el-button>
          <el-button v-if="isAuth('zsgl:wdzs:delete')" type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
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
    <detail v-if="detailVisible" ref="detail" @refreshDataList="getDataList"></detail>
    <zszp v-if="zszpVisible" ref="zszp" @refreshDataList="getDataList"></zszp>
  </div>
</template>
<script>
  import AddOrUpdate from './wdzs-add-or-update'
  import Detail from './wdzs-detail.vue'
  import Zszp from './wdzs-zszp.vue'
  export default {
    data () {
      return {
        dataForm: {
//          key: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false,
        detailVisible: false,
        zszpVisible: false
      }
    },
    components: {
      AddOrUpdate,
      Detail,
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
          url: this.$http.adornUrl('/zsgl/wdzs/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize
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
      //查看照片
      zszpHandle (id) {
        this.zszpVisible = true
        this.$nextTick(() => {
          this.$refs.zszp.init(id)
        })
      },
      //查看
      detailHandle (id) {
          this.detailVisible = true
          this.$nextTick(() => {
            this.$refs.detail.init(id)
          })
      },
      // 新增 / 修改
      addOrUpdateHandle (id, ztm) {
        if(ztm<=1) {
          this.addOrUpdateVisible = true
          this.$nextTick(() => {
            this.$refs.addOrUpdate.init(id)
          })
        }else{
          this.$message({
            message: '该状态不可修改'
/*
            type: 'success',
            duration: 1500
*/
          })
        }
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
            url: this.$http.adornUrl('/zsgl/wdzs/delete'),
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
