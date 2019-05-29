<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.year" placeholder="年份"  clearable></el-input>
      </el-form-item>

      <el-form-item>
        <el-input v-model="dataForm.studentNum" placeholder="学号" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="dataForm.studentName" placeholder="学生姓名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="dataForm.courseName" placeholder="课程名" clearable></el-input>
      </el-form-item>

      <el-form-item>
        <el-button @click="getDataList()" icon="el-icon-zoom-in" type="primary" plain >查询</el-button>
        <el-button @click="getDataAllList()" icon="el-icon-zoom-out" type="primary" plain >取消</el-button>
      </el-form-item>
    </el-form>

    <el-form >
      <el-form-item style="margin-bottom: 5px">
        <el-button v-if="isAuth('train:score:save')" size="small" type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button size="small" type="primary" @click="importHandle()">导入</el-button>
        <el-button v-if="isAuth('train:score:delete')" size="small" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>

    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      @sort-change="sortChangeHandle"
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
      <el-table-column
        prop="year"
        header-align="center"
        align="center"
        width="100"
        sortable
        label="年份">
      </el-table-column>
      <el-table-column
        prop="studentNum"
        header-align="center"
        align="center"
        width="120"
        sortable
        label="学号">
      </el-table-column>
      <el-table-column
        prop="studentName"
        header-align="center"
        align="center"
        width="100"
        label="学生姓名">
      </el-table-column>
      <el-table-column
        prop="identityCard"
        header-align="center"
        align="center"
        sortable
        width="150"
        label="身份证号">
      </el-table-column>
      <el-table-column
        prop="courseName"
        header-align="center"
        align="center"
        sortable
        width="180"
        label="课程名">
      </el-table-column>
      <el-table-column
        prop="studentScore"
        header-align="center"
        align="center"
        label="学生成绩">
      </el-table-column>
      <!--<el-table-column-->
        <!--prop="statusCode"-->
        <!--header-align="center"-->
        <!--align="center"-->
        <!--label="">-->
      <!--</el-table-column>-->
      <!--<el-table-column-->
        <!--prop="status"-->
        <!--header-align="center"-->
        <!--align="center"-->
        <!--label="">-->
      <!--</el-table-column>-->
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
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
    <score-import v-if="scoreImportVisible" ref="scoreImport" @refreshDataList="getDataList"></score-import>
  </div>
</template>

<script>
  import AddOrUpdate from './score-add-or-update'
  import ScoreImport from './score-import'
  export default {
    data () {
      return {
        dataForm: {
          year: '',
          studentNum: '',
          studentName: '',
          courseName: '',
          order: 'year desc, studentNum asc'
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false,
        scoreImportVisible: false
      }
    },
    components: {
      AddOrUpdate,
      ScoreImport
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/train/score/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'order': this.dataForm.order,
            'year': this.dataForm.year,
            'studentNum': this.dataForm.studentNum,
            'studentName': this.dataForm.studentName,
            'courseName': this.dataForm.courseName
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
        this.dataForm.year = '';
        this.dataForm.studentNum = '';
        this.dataForm.studentName = '';
        this.dataForm.courseName = '';
        this.dataForm.order = 'year desc, studentNum asc'
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

      sortChangeHandle (val) {
        this.setOrder(val);
        this.getDataList();
      },
      // 设置排序条件
      setOrder (val) {
        var dir = val.order=='ascending'?'ASC':'DESC';
        var prop = val.prop;
        this.order = prop + ' ' + dir
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
      // 成绩导入
      importHandle () {
        this.scoreImportVisible = true
        this.$nextTick(() => {
          this.$refs.scoreImport.init()
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
            url: this.$http.adornUrl('/train/score/delete'),
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
