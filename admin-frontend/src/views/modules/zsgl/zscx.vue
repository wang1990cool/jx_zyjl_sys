<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.sznd" placeholder="年度" clearable></el-input>
      </el-form-item>
      <el-form-item  label="" prop="szxb">
        <el-select v-model="dataForm.szxb" label="栏目" placeholder="系部"  >
          <el-option v-for="item in szxbList" :key="item.label" :label="item.label" :value="item.label" >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item  label="" prop="szzy">
        <el-select v-model="dataForm.szzy" label="栏目" placeholder="专业"  >
          <el-option v-for="item in szzyList" :key="item.label" :label="item.label" :value="item.label" >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item  label="" prop="szbj">
        <el-select v-model="dataForm.szbj" label="栏目" placeholder="班级"  >
          <el-option v-for="item in szbjList" :key="item.label" :label="item.label" :value="item.label" >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item  label="" prop="ztm">
        <el-select v-model="dataForm.ztm" label="栏目" placeholder="状态"  >
          <el-option v-for="item in ztList" :key="item.value" :label="item.label" :value="item.value" >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button @click="exportDataList()">导出</el-button>
        <el-button v-if="isAuth('zsgl:zscx:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button v-if="isAuth('zsgl:zscx:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
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
      <el-table-column
        type="index"
        width="50"
        label="序号">
      </el-table-column>
<!--      <el-table-column
        prop="id"
        header-align="center"
        align="center"
        label="ID">
      </el-table-column>-->
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
      <el-table-column v-if="false"
        prop="sfzh"
        header-align="center"
        align="center"
        label="身份证号">
      </el-table-column>
      <el-table-column v-if="false"
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
<!--      <el-table-column
              prop="hzrq"
              header-align="center"
              align="center"
              label="获证日期">
            </el-table-column>

            <el-table-column
              prop="zszp"
              header-align="center"
              align="center"
              label="证书照片">
            </el-table-column>

            <el-table-column
              prop="bjshyj"
              header-align="center"
              align="center"
              label="班级审核意见">
            </el-table-column>
            <el-table-column
              prop="ztm"
              header-align="center"
              align="center"
              label="状态码">
            </el-table-column>
         -->   <el-table-column
        prop="zt"
        header-align="center"
        align="center"
        label="状态">
      </el-table-column>
      <el-table-column
        prop="hzrq"
        header-align="center"
        align="center"
        label="获证日期">
      </el-table-column>
<!--      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>-->
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
  </div>
</template>

<script>
  import AddOrUpdate from './zscx-add-or-update'
  export default {
    data () {
      return {
          ztList : [{
            value: '1',
            label: '待提交'
          }, {
            value: '2',
            label: '待班级审核'
          }, {
            value: '3',
            label: '班级审核不通过'
          }, {
            value: '4',
            label: '待中心审核'
          }, {
            value: '5',
            label: '中心审核不通过'
          }, {
            value: '6',
            label: '中心审核通过'
          }, {
            value: '0',
            label: '所有'
          }],
        dataForm: {
          sznd: '',
          szbj: '',
          ztm: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        order:'id desc',
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false
      }
    },
    components: {
      AddOrUpdate
    },
    activated () {
      this.getDataList()
    },
    methods: {
        //导出数据
      exportDataList () {
//        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/zsgl/zscx/export'),
          method: 'post',
          responseType:'arraybuffer',
          params: this.$http.adornParams({
            'sznd': this.dataForm.sznd,
            'szbj': this.dataForm.szbj,
            'ztm' : this.dataForm.ztm
          })
        }).then(({data}) => {
          if (data) {
            const blob = new Blob([data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8' })
            const downloadElement = document.createElement('a')
            const href = window.URL.createObjectURL(blob)
            downloadElement.href = href
            let fname = '学生证书信息表'
            downloadElement.download =fname+'.xlsx'
            document.body.appendChild(downloadElement)
            downloadElement.click()
            document.body.removeChild(downloadElement) // 下载完成移除元素
            window.URL.revokeObjectURL(href) // 释放掉blob对象
            this.getDataList()
          }
        })
      },

      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/zsgl/zscx/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'order': this.order,
            'sznd': this.dataForm.sznd,
            'szbj': this.dataForm.szbj,
            'ztm' : this.dataForm.ztm

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
            url: this.$http.adornUrl('/zsgl/zscx/delete'),
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
