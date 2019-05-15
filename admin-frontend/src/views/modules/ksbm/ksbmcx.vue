<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.ksnd" placeholder="年度" clearable></el-input>
      </el-form-item>
      <el-form-item  label="" prop="szxb">
        <el-select v-model="dataForm.szxb" label="栏目" placeholder="系部"  @change="selectXymc">
          <el-option v-for="item in szxbList" :key="item.value" :label="item.label" :value="item.value" >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item  label="" prop="szzy">
        <el-select v-model="dataForm.szzy" label="栏目" placeholder="专业"  @change="selectZymc">
          <el-option v-for="item in szzyList" :key="item.value" :label="item.label" :value="item.value" >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item  label="" prop="sznj">
        <el-select v-model="dataForm.sznj" label="栏目" placeholder="年级" @change="selectNj">
          <el-option v-for="item in sznjList" :key="item.label" :label="item.label" :value="item.label" >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item  label="" prop="szbj">
        <el-select v-model="dataForm.szbj" label="栏目" placeholder="班级"  >
          <el-option v-for="item in szbjList" :key="item.value" :label="item.label" :value="item.value" >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>

        <el-button @click="exportDataList()">导出</el-button>

      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
<!--
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
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


      <el-table-column
        prop="xsxm"
        header-align="center"
        align="center"
        label="姓名">
      </el-table-column>


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
          <el-button  v-if="isAuth('ksbm:ksbmxxb:info')" type="text" size="small" @click="zszpHandle(scope.row.id)">查看照片 </el-button>
        </template>
      </el-table-column>


      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        label="报名时间">
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
    <!--    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
-->
        <zszp v-if="zszpVisible" ref="zszp" @refreshDataList="getDataList"></zszp>

  </div>
</template>

<script>
//  import AddOrUpdate from './ksbmxxb-add-or-update'
  import Zszp from './ksbmxxb-zszp.vue'
  export default {
    data () {
      return {
        szxbList : [],
        szzyList : [],
        szbjList : [],
        sznjList : [{
          label: '2017'
        }, {
          label: '2018'
        }, {
          label: '2019'
        }],
        dataForm: {
          ksnd: '',
          szxb: '',
          szzy: '',
          sznj: '',
          szbj: '',
          ztm: ''
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
//      AddOrUpdate,
      Zszp
    },
    activated () {
      this.getDataList()
      this.getXyxxList()
    },
    methods: {
      //导出数据
      exportDataList () {
//        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/ksbm/ksbmcx/export'),
          method: 'post',
          responseType:'arraybuffer',
          params: this.$http.adornParams({
            'ksnd': this.dataForm.ksnd,
            'szbj': this.dataForm.szbj
          })
        }).then(({data}) => {
          if (data) {
            const blob = new Blob([data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8' })
            const downloadElement = document.createElement('a')
            const href = window.URL.createObjectURL(blob)
            downloadElement.href = href
            let fname = '学生考试报名信息表'
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
          url: this.$http.adornUrl('/ksbm/ksbmcx/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'ksnd': this.dataForm.ksnd,
            'szxb': this.dataForm.szxb,
            'szzy': this.dataForm.szzy,
            'sznj': this.dataForm.sznj,
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
      //获取系院信息
      getXyxxList(){
        this.$http({
          url: this.$http.adornUrl('/xyxx/zdxyxxb/selectAll'),
          method: 'get'
        }).then(({data}) => {
          this.szxbList = data.xymclist
        })

      },
//选择系院
      selectXymc( vId ){//这个vId也就是value值
        let obj = {};
        obj = this.szxbList.find((item)=> {
          return item.value == vId;
        } );
        var xybm = obj.value
        this.szzyList = []
        this.szbjList = []
        this.dataForm.szzy=""
        this.dataForm.szbj=""
        this.getZymc( xybm )
      },

      //获取专业信息
      getZymc( xybm ) {
        this.$http({
          url: this.$http.adornUrl('/zyxx/zdzyxxb/select'),
          method: 'get',
          params: this.$http.adornParams({
            'xybm': xybm
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.szzyList = data.zymclist
          } else {
            this.szzyList = []
          }

        })
      },

      //选择专业
      selectZymc( vId ){//这个vId也就是value值
        let obj = {};
        obj = this.szzyList.find((item)=> {
          return item.value == vId;
        } );
        var zybm = obj.value
        this.szbjList = []
        this.dataForm.szbj=""
        var sznj=this.dataForm.sznj
        if( sznj ){
          this.getBjmc( zybm, sznj)
        }
      },

      //选择年级
      selectNj( vId ){//这个vId也就是value值
        var szzy = this.dataForm.szzy
        var sznj = this.dataForm.sznj
        if (szzy){
          this.dataForm.szbj=""
          this.getBjmc(szzy, sznj )
        }
      },
      //获取班级信息
      getBjmc( szzy, sznj  ) {
        this.$http({
          url: this.$http.adornUrl('/bjxx/zdbjxxb/select'),
          method: 'get',
          params: this.$http.adornParams({
            'zybm': szzy,
            'sznj': sznj
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.szbjList = data.bjmclist
          } else {
            this.szbjList = []
          }

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
      }
    }
  }
</script>
