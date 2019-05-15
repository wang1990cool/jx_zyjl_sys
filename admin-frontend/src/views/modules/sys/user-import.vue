<template>
  <el-dialog :title="title" :visible.sync="dialogVisible" width="30%">

    <el-form size="mini" label-width="90px">


    <el-form-item label="用户角色" prop="roleName">
      <el-select v-model="roleName" label="角色" placeholder="请选择" @change="selectGet" >
        <el-option v-for="item in roleList" :key="item.roleId" :label="item.roleName" :value="item.roleName" >
        </el-option>
      </el-select>
    </el-form-item>

      <el-form-item label="选择文件" prop="roleName">
      <el-upload
        style="margin-left:50px"
        class="upload-demo"
        ref="upload"
        :action="importUrl"
        :before-upload="beforeUpload"
        :auto-upload="false"
        :file-list="fileList"
        limit=1
        :data="myData"
      >
        <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
        <!--<div class="el-upload__tip" slot="tip">请先下载模板，按照模板字段进行填写！</div>-->
      </el-upload>
      </el-form-item>

    </el-form>

    <!--<div class="download-template"  style="margin-left:50px">-->
      <!--<a class="btn-download" @click="downloadHandle">-->
        <!--<i class="icon-download"></i>下载模板</a>-->
    <!--</div>-->


    <div slot="footer" class="dialog-footer">
      <el-button v-on:click="dialogVisible = false">取 消</el-button>
      <el-button v-if="isAuth('train:score:import')" type="primary" @click="submitUpload">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        // importUrl: 'www.baidu.com',//后台接口config.admin_url+'rest/schedule/import/'
        // importHeaders: {
        //   enctype: 'multipart/form-data',
        //   cityCode: ''
        // },
        // name: 'import',
        // fileList: [],
        // withCredentials: true,
        // processing: false,
        // uploadTip: '点击上传',
        // imporFlag: 1,
        roleName: '',
        roleId: '',
        title: '用户导入',
        dialogVisible: false,
        importUrl: '',
        fileList: [],
        roleList: []
        // errorResults: []
      }
    },
    computed: {
      myData () {
        return {
          'roleId': this.roleId,
          'roleName': this.roleName
        }
      }
    },

    methods: {
      init () {
        this.importUrl = this.$http.adornUrl(`/sys/user/import?token=${this.$cookie.get('token')}`)
        this.fileList = []
        this.dialogVisible = true
        this.getRoleList()
        // this.importData()
      },
      // 上传成功后的回调
      uploadSuccess (response) {
        let code = response.returncode
        let msg = response.msg
        this.open(msg, code)
      },
      // 上传错误
      uploadError (response) {
        this.open('500', '文件导入异常！')
      },

      // 提示信息
      open: function (msg, code) {
        if (code == '000') {
          this.$message.info(msg)
          this.$emit('refreshDataList')
        } else {
          this.$message.error(msg)
        }
      },

      //获取班级信息
      getRoleList () {
        this.$http({
          url: this.$http.adornUrl('/sys/role/select'),
          method: 'get'
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.roleList = data.list
          } else {
            this.roleList = []
          }

        })
      },

      //下拉框选中事件
      selectGet (vId) {
        let obj = {};
        obj = this.roleList.find((item)=>{
          return item.roleName === vId;
        });
        this.roleId = obj.roleId
      },

      beforeUpload (file) {
        // this.importHeaders.cityCode='上海'//可以配置请求头
        let excelFileExtend = '.xls,.xlsx' // 设置文件格式
        let fileExtend = file.name.substring(file.name.lastIndexOf('.')).toLowerCase()
        if (excelFileExtend.indexOf(fileExtend) <= -1) {
          this.$message.error('文件格式错误')
          return false
        }
        // this.uploadTip = '正在处理中...'
        this.processing = true
      },
      getList () {
        this.$emit('refreshDataList')
      },
      submitUpload () {
        var me = this;
        me.dialogVisible = false
        me.roleName = ''
        me.$refs.upload.submit()
        var t = setTimeout(function () {
          me.$emit('refreshDataList')
        }, 1000)
      }

      // 下载模板
      // download () {
      //   this.$http({
      //     url: this.$http.adornUrl(`/train/score/download?token=${this.$cookie.get('token')}`),
      //     method: 'get'
      //   })
      // }
    }
  }
</script>

