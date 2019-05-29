<template>
  <el-dialog :title="title" :visible.sync="dialogVisible" width="30%">
    <el-upload
      style="margin-left:50px"
      class="upload-demo"
      ref="upload"
      :action="importUrl"
      :before-upload="beforeUpload"
      :auto-upload="false"
      :file-list="fileList"
      :on-success="handleSuccess"
      limit=1
    >
      <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
      <div class="el-upload__tip" slot="tip">请先下载模板，按照模板字段进行填写！</div>
    </el-upload>

    <div class="download-template"  style="margin-left:50px">
      <a class="btn-download" @click="downloadHandle">
        <i class="icon-download"></i>下载模板</a>
    </div>


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
        // importFlag: 1,
        title: '成绩导入',
        dialogVisible: false,
        importUrl: '',
        fileList: []
        // errorResults: []
      }
    },
    methods: {
      init () {
        this.importUrl = this.$http.adornUrl(`/train/score/import?token=${this.$cookie.get('token')}`)
        this.fileList = []
        this.dialogVisible = true
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
        me.$refs.upload.submit()
        var t = setTimeout(function () {
          me.$emit('refreshDataList')
        }, 1000)
      },

      handleSuccess(response, file, fileList){
        console.log('上传成功');
        if(response.code == 0){
          setTimeout(() =>{
            this.$message.success('成绩导入成功');
            this.getVipList();
          },2000);
          fileList.splice(0);//上传成功后将fileList清空，不影响下一次上传
        }else{
          this.$message.error(response.msg)
        }
      },

      // 下载模板
      // download () {
      //   this.$http({
      //     url: this.$http.adornUrl(`/train/score/download?token=${this.$cookie.get('token')}`),
      //     method: 'get'
      //   })
      // }

      downloadHandle () {
         // this.url2 = this.$http.adornUrl(`/gc/gcscgyxxb/downloads/${this.dataForm.id}?token=${this.$cookie.get('token')}`)
        this.$http({
          url: this.$http.adornUrl(`/train/score/download?token=${this.$cookie.get('token')}`),
          method: 'post',
          responseType: 'blob'
        }).then(({data}) => {
          if (data) {
            console.log('文件下载成功')
            let blob = new Blob([data], {
              type: 'application/vnd.ms-excel;charset-UTF-8'
            })
            let objectUrl = URL.createObjectURL(blob)
            console.log(objectUrl)
            let downEle = document.createElement('a')
            let fname = 'template.xls' // 下载文件的名字
            downEle.href = objectUrl
            downEle.setAttribute('download', fname)
            document.body.appendChild(downEle)
            downEle.click()
          }
        })
      }
    }
  }
</script>

