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
        <el-button v-if="isAuth('train:project:save')" size="small" type="primary" @click="finishHandle()" :disabled="dataListSelections.length <= 0">项目结束</el-button>
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
        label="项目编号">
      </el-table-column>
      <el-table-column
        prop="projectName"
        header-align="center"
        align="center"
        label="项目名称">
      </el-table-column>
      <el-table-column
        prop="projectBudget"
        header-align="center"
        align="center"
        label="项目预算">
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
        label="申请人姓名">
      </el-table-column>
      <el-table-column
        prop="applicantDept"
        header-align="center"
        align="center"
        label="申请人部门">
      </el-table-column>
      <el-table-column
        prop="auditorId"
        header-align="center"
        align="center"
        label="审核人工号">
      </el-table-column>
      <el-table-column
        prop="auditorName"
        header-align="center"
        align="center"
        label="审核人姓名">
      </el-table-column>
      <el-table-column
        prop="auditTime"
        header-align="center"
        align="center"
        :formatter="dateFormat"
        label="审核时间">
      </el-table-column>
      <el-table-column
        prop="statusCode"
        header-align="center"
        align="center"
        label="状态">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.statusCode === '3'" size="small" type="primary">审核通过</el-tag>
          <el-tag v-if="scope.row.statusCode === '4'" size="small" type="danger">方案已填写</el-tag>
          <el-tag v-if="scope.row.statusCode === '5'" size="small" type="info">项目结束</el-tag>
        </template>
      </el-table-column>

      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button v-if="scope.row.statusCode !== '5'" type="text" size="small" @click="addOrUpdateHandle(scope.row.id, scope.row.projectId)">填写</el-button>
          <el-button type="text" size="small" @click="detailHandle(scope.row.id, scope.row.projectId)">方案详情</el-button>
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
    <!--<project-audit v-if="projectAuditVisible" ref="projectAudit" @refreshDataList="getDataList"></project-audit>-->

    <project-train-program v-if="addOrUpdateVisible" ref="projectTrainProgram" @refreshDataList="getDataList"></project-train-program>
    <train-program-detail-list v-if="detailVisible" ref="trainProgramDetailList"></train-program-detail-list>

  </div>
</template>

<script>
  // import projectAudit from './projectAudit-audit'
  import projectTrainProgram from './projectTrainProgram-list'
  import trainProgramDetailList from './projectTrainProgram-detail-list'
  let moment = require('moment');

  export default {
    data () {
      return {
        dataForm: {
          key: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        order:'id desc',
        addOrUpdateVisible: false,
        detailVisible: false
      }
    },
    components: {
      projectTrainProgram,
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
          url: this.$http.adornUrl('/train/projectTrainProgram/projectList'),
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
      addOrUpdateHandle (id, projectId) {
        this.addOrUpdateVisible = true;
        this.$nextTick(() => {
          this.$refs.projectTrainProgram.init(id, projectId)
        })
      },

      detailHandle (id, projectId) {
        this.detailVisible = true;
        this.$nextTick(() => {
          this.$refs.trainProgramDetailList.init(id, projectId)
        })
      },

      finishHandle: function () {
        var isAudit = false;

        for (var i = 0; i <  this.dataListSelections.length; i++){
          if (this.dataListSelections[i].statusCode !== '4') {
            isAudit = true;
            break;
          }
        }

        if (!isAudit) {
          var projectIds = this.dataListSelections.map(item => {
            return item.projectId
          })
          this.$confirm(`确定结束培训项目?`, '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$http({
              url: this.$http.adornUrl('/train/projectTrainProgram/finish'),
              method: 'post',
              data: this.$http.adornData(projectIds, false)
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
          this.$message.error('请选择"方案已填写"的记录')
        }
      },



      dateFormat: function (row, column) {
        var date = row[column.property];
        if (date == undefined) {
          return '';
        }
        return moment(date).format('YYYY-MM-DD');
      }
    }
  }
</script>
