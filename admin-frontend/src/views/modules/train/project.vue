<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.projectId" placeholder="项目编号" clearable></el-input>
      </el-form-item>

      <el-form-item>
        <el-input v-model="dataForm.projectName" placeholder="项目名称" clearable></el-input>
      </el-form-item>

      <el-form-item>
        <el-button @click="getDataList()" icon="el-icon-zoom-in" type="primary" plain >查询</el-button>
        <el-button @click="getDataAllList()" icon="el-icon-zoom-out" type="primary" plain >取消</el-button>
      </el-form-item>
    </el-form>

    <el-form >
      <el-form-item style="margin-bottom: 5px">
        <el-button v-if="isAuth('train:project:save')" size="small" type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button v-if="isAuth('train:project:delete')" size="small" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
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
      </el-table-column>ß

      <!--<el-table-column-->
        <!--prop="id"-->
        <!--header-align="center"-->
        <!--align="center"-->
        <!--label="ID">-->
      <!--</el-table-column>-->

      <el-table-column
        prop="projectId"
        header-align="center"
        align="center"
        label="培训编号"
      >
      </el-table-column>
      <el-table-column
        prop="projectName"
        header-align="center"
        align="center"
        label="培训名称">
      </el-table-column>
      <el-table-column
        prop="projectBudget"
        header-align="center"
        align="center"
        width="100"
        label="预算金额">
      </el-table-column>
      <el-table-column
        prop="trainClassHour"
        header-align="center"
        align="center"
        width="100"
        label="培训课时">
      </el-table-column>

      <!--<el-table-column-->
        <!--prop="applicantId"-->
        <!--header-align="center"-->
        <!--align="center"-->
        <!--label="申请人工号">-->
      <!--</el-table-column>-->
      <el-table-column
        prop="applicantName"
        header-align="center"
        align="center"
        width="100"
        label="申请人姓名">
      </el-table-column>
      <!--<el-table-column-->
        <!--prop="applicantDept"-->
        <!--header-align="center"-->
        <!--align="center"-->
        <!--label="申请人部门">-->
      <!--</el-table-column>-->
      <!--<el-table-column-->
        <!--prop="auditorId"-->
        <!--header-align="center"-->
        <!--align="center"-->
        <!--label="审核人工号">-->
      <!--</el-table-column>-->
      <el-table-column
        prop="auditorName"
        header-align="center"
        align="center"
        width="100"
        label="审核人姓名">
      </el-table-column>
      <el-table-column
        prop="auditTime"
        header-align="center"
        align="center"
        :formatter="dateFormat"
        width="100"
        label="审核时间">
      </el-table-column>
      <el-table-column
        prop="statusCode"
        header-align="center"
        align="center"
        width="100"
        label="状态">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.statusCode === '1'" size="small" type="warning">草稿状态</el-tag>
          <el-tag v-if="scope.row.statusCode === '2'" size="small" type="success">待中心审核</el-tag>
          <el-tag v-if="scope.row.statusCode === '3'" size="small" type="primary">审核通过</el-tag>
          <el-tag v-if="scope.row.statusCode === '4'" size="small" type="danger">方案已填写</el-tag>
          <el-tag v-if="scope.row.statusCode === '5'" size="small" type="info">项目结束</el-tag>
          <el-tag v-if="scope.row.statusCode === '9'" size="small" type="danger">中心驳回</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="left"
        width="220"
        label="操作">
        <template slot-scope="scope">
          <el-button v-if="scope.row.statusCode === '1' || scope.row.statusCode === '9' " type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
          <el-button v-if="scope.row.statusCode === '1' || scope.row.statusCode === '9'" type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
          <el-button v-if="scope.row.statusCode === '1' || scope.row.statusCode === '9' " type="text" size="small" @click="sumbitHandle(scope.row.id)">提交</el-button>
          <el-button v-if="scope.row.statusCode === '2'" type="text" size="small" @click="withdrawHandle(scope.row.id)">撤回</el-button>
          <el-button v-if="scope.row.statusCode === '4' || scope.row.statusCode === '5'" type="text" size="small" @click="trainProgramDetailHandle(scope.row.id, scope.row.projectId)">课程详情</el-button>
          <el-button type="text" size="small" @click="detailHandle(scope.row.id)">详情</el-button>

          <!--<el-button v-if="scope.row.statusCode === '3'" type="text" size="small" @click="addOrUpdateHandle(scope.row.id, scope.row.projectId)">方案填写</el-button>-->

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
    <train-program-detail-list v-if="detailVisible" ref="trainProgramDetailList"></train-program-detail-list>

  </div>
</template>

<script>
  import AddOrUpdate from './project-add-or-update'
  import trainProgramDetailList from './projectTrainProgram-detail-list'
  let moment = require('moment');
  export default {
    data () {
      return {
        dataForm: {
          projectId: '',
          projectName: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        order:'id desc',
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false,
        detailVisible: false
      }
    },
    components: {
      AddOrUpdate,
      trainProgramDetailList
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/train/project/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'order': this.order,
            'projectId': this.dataForm.projectId,
            'projectName': this.dataForm.projectName

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

      getDataAllList () {
        this.dataForm.projectId = '';
        this.dataForm.projectName = '';
        this.getDataList();
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

      trainProgramDetailHandle (id, projectId) {
        this.detailVisible = true;
        this.$nextTick(() => {
          this.$refs.trainProgramDetailList.init(id, projectId)
        })
      },

      detailHandle (id) {
        this.addOrUpdateVisible = true;
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id, true)
        })
      },

      sumbitHandle (id) {
        this.$confirm('确定提交？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/train/project/submit'),
            method: 'post',
            data: this.$http.adornData(id, false)
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
      },

      withdrawHandle (id) {
        this.$confirm('确定撤回？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/train/project/withdraw'),
            method: 'post',
            data: this.$http.adornData(id, false)
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
      },

      // 删除
      deleteHandle (id) {
        var isAudit = false;

        for (var i = 0; i <  this.dataListSelections.length; i++){
          if (this.dataListSelections[i].statusCode !== '1') {
            isAudit = true;
            break;
          }
        }

       if (!isAudit) {
         var ids = id ? [id] : this.dataListSelections.map(item => {
           return item.id
         })
         this.$confirm(`确定${id ? '删除' : '批量删除'}?`, '提示', {
           confirmButtonText: '确定',
           cancelButtonText: '取消',
           type: 'warning'
         }).then(() => {
           this.$http({
             url: this.$http.adornUrl('/train/project/delete'),
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
       } else {
         this.$message.error('请选择"草稿状态"的记录')
       }
      },
      dateFormat: function(row, column) {
        var date = row[column.property];
        if (date == undefined) {
          return "";
        }
        return moment(date).format('YYYY-MM-DD');
      }
    }
  }
</script>
