<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <!--<el-form-item>-->
        <!--<el-input v-model="dataForm.projectId" placeholder="项目编号" clearable></el-input>-->
      <!--</el-form-item>-->

      <el-form-item>
        <el-input v-model="dataForm.projectName" placeholder="项目名称" clearable></el-input>
      </el-form-item>

      <el-form-item>
        <el-button @click="getDataList()" icon="el-icon-zoom-in" type="primary" plain >查询</el-button>
        <el-button @click="getDataAllList()" icon="el-icon-zoom-out" type="primary" plain >取消</el-button>
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

      <!--<el-table-column-->
        <!--prop="id"-->
        <!--header-align="center"-->
        <!--align="center"-->
        <!--label="ID">-->
      <!--</el-table-column>-->

      <!--<el-table-column-->
        <!--prop="projectId"-->
        <!--header-align="center"-->
        <!--align="center"-->
        <!--label="项目编号">-->
      <!--</el-table-column>-->
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
        width="100"
        label="审核人工号">
      </el-table-column>
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
        label="审核时间">
      </el-table-column>
      <el-table-column
        prop="statusCode"
        header-align="center"
        align="center"
        width="100"
        label="状态">
        <template slot-scope="scope">
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
          <el-button type="text" size="small" @click="projectDetailHandle(scope.row.id)">详情</el-button>
          <el-button type="text" size="small" @click="detailHandle(scope.row.id, scope.row.projectId)">工作量详情</el-button>
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

    <project-workload-list v-if="detailVisible" ref="projectWorkloadList"></project-workload-list>
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>

  </div>
</template>

<script>
  // import projectAudit from './projectAudit-audit'
  import projectWorkloadList from './projectWorkload-list'
  import AddOrUpdate from './project-add-or-update'

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
        detailVisible: false,
        addOrUpdateVisible: false
      }
    },
    components: {
      projectWorkloadList,
      AddOrUpdate
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/train/projectWorkload/projectList'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'order': this.order,
            // 'projectId': this.dataForm.projectId,
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

      detailHandle (id, projectId) {
        this.detailVisible = true;
        this.$nextTick(() => {
          this.$refs.projectWorkloadList.init(id, projectId)
        })
      },

      projectDetailHandle (id) {
        this.addOrUpdateVisible = true;
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id, true)
        })
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
