<template>
    <el-dialog
      :title="'培养方案填写'"
      :close-on-click-modal="false"
      :visible.sync="visible">
      <div class="mod-config">
        <!--<el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">-->
          <!--<el-form-item>-->
            <!--<el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>-->
          <!--</el-form-item>-->

          <!--<el-form-item>-->
            <!--<el-button @click="getDataList()" icon="el-icon-zoom-in" type="primary" plain >查询</el-button>-->
            <!--<el-button @click="getDataAllList()" icon="el-icon-zoom-out" type="primary" plain >取消</el-button>-->
          <!--</el-form-item>-->
        <!--</el-form>-->

        <el-form >
          <el-form-item style="margin-bottom: 5px">
            <el-button  size="small" type="primary" @click="addOrUpdateHandle()">新增</el-button>
            <el-button  size="small" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
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
            <!--prop="projectId"-->
            <!--header-align="center"-->
            <!--align="center"-->
            <!--label="项目编号">-->
          <!--</el-table-column>-->
          <el-table-column
            prop="teacherId"
            header-align="center"
            align="center"
            label="教师工号">
          </el-table-column>
          <el-table-column
            prop="teacherName"
            header-align="center"
            align="center"
            label="教师姓名">
          </el-table-column>
          <el-table-column
            prop="classHour"
            header-align="center"
            align="center"
            label="课时">
          </el-table-column>
          <el-table-column
            prop="classDate"
            header-align="center"
            align="center"
            :formatter="dateFormat"
            label="上课时间">
          </el-table-column>
          <el-table-column
            prop="classAddress"
            header-align="center"
            align="center"
            label="上课地点">
          </el-table-column>
          <el-table-column
            fixed="right"
            header-align="center"
            align="center"
            width="150"
            label="操作">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
              <el-button type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
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
        <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
      </div>
    </el-dialog>
</template>

<script>
  import AddOrUpdate from './projectTrainProgram-add-or-update'
  let moment = require('moment');

  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          key: ''
        },
        projectId: '',
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
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
      init (id, projectId) {
        this.visible = true;
        this.projectId = projectId;
        this.getDataList();
      },
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/train/projectTrainProgram/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'projectId': this.projectId
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
          this.$emit('refreshDataList')
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
          this.$refs.addOrUpdate.init(id, this.projectId)
        })
      },
      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`确定${id ? '删除' : '批量删除'}?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/train/projectTrainProgram/delete'),
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
