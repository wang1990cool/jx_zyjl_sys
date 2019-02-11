<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
<!--
    <el-form-item label="身份证号" prop="sfzh">
      <el-input v-model="dataForm.sfzh" placeholder="身份证号"></el-input>
    </el-form-item>
    <el-form-item label="电话" prop="telephone">
      <el-input v-model="dataForm.telephone" placeholder="电话"></el-input>
    </el-form-item>
-->
    <el-form-item label="证书名称" prop="zsmc">
      <el-input v-model="dataForm.zsmc" placeholder="证书名称" disabled="true"></el-input>
    </el-form-item>
    <el-form-item label="获证日期" prop="hzrq">
      <el-input v-model="dataForm.hzrq" placeholder="证书名称" disabled="true"></el-input>
<!--
      <el-date-picker
        v-model="dataForm.hzrq"
        type="date"
        placeholder="选择日期"
        value-format="yyyyMMdd">
      </el-date-picker>
-->
    </el-form-item>
    <el-form-item label="证书照片" prop="zszp">
      <el-table-column v-if="false"
                       prop="zppath"
                       header-align="center"
                       align="center"
                       width="80"
                       label="证书照片名称">
      </el-table-column>
<!--
      <el-upload
        class="avatar-uploader"
        action="#"
        accept="image/png,image/gif,image/jpg,image/jpeg"
        list-type="picture-card"
        :limit=1
        :auto-upload="false"
        :on-exceed="handleExceed"
        :before-upload="handleBeforeUpload"
        :on-preview="handlePictureCardPreview"
        :on-remove="handleRemove"
      >
      <i class="el-icon-plus"></i>
      </el-upload>
      <el-dialog :visible.sync="dialogVisible">
        <img width="100%" :src="imageUrl" alt="">
      </el-dialog>
-->
      <el-upload
        class="avatar-uploader"
        :disabled="true"
        :action= "uploadUrl"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload">
        <img v-if="imageUrl" :src="imageUrl" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </el-form-item>
      <el-form-item label="审核意见" prop="shbm">
        <el-radio v-model="dataForm.shbm" label="2">通过</el-radio>
        <el-radio v-model="dataForm.shbm" label="3">驳回</el-radio>
      </el-form-item>
      <el-form-item label="反馈意见" prop="bjshyj">
        <el-input v-model="dataForm.bjshyj" placeholder="反馈意见"></el-input>
      </el-form-item>
    </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>
<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>

<script>
  export default {
    data () {
      return {
//        uploadUrl: this.$http.adornUrl(`/zsgl/wdzs/upload?token=${this.$cookie.get('token')}`),
        uploadUrl: this.$http.adornUrl(`/upload?token=${this.$cookie.get('token')}`),
        visible: false,
        dataForm: {
          id: 0,
          sfzh: '',
          telephone: '',
          zsmc: '',
          shbm:'2',
          bjshyj:'',
          hzrq: '',
          zszp: '',
          zppath:''
        },
        imageUrl: '',
        dataRule: {
          sfzh: [
            { required: true, message: '身份证号不能为空', trigger: 'blur' }
          ],
          telephone: [
            { required: true, message: '电话不能为空', trigger: 'blur' }
          ],
          zsmc: [
            { required: true, message: '证书名称不能为空', trigger: 'blur' }
          ],
          hzrq: [
            { required: true, message: '获证日期不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.imageUrl = ''
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/zsgl/bjsh/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.sfzh = data.xsZsxxb.sfzh
                this.dataForm.telephone = data.xsZsxxb.telephone
                this.dataForm.zsmc = data.xsZsxxb.zsmc
                this.dataForm.hzrq = data.xsZsxxb.hzrq
                this.imageUrl = 'data:image/png;base64,' + data.xsZsxxb.zszp
                this.dataForm.zppath = data.xsZsxxb.zppath
              }
            })
          }
        })
      },

      handleBeforeUpload(file){
        if(!(file.type === 'image/png' || file.type === 'image/gif' || file.type === 'image/jpg' || file.type === 'image/jpeg')) {
          this.$notify.warning({
            title: '警告',
            message: '请上传格式为image/png, image/gif, image/jpg, image/jpeg的图片'
          })
        }
        let size = file.size / 1024 / 1024 / 2
        if (size > 2) {
          this.$notify.warning({
            title: '警告',
            message: '图片大小必须小于2M'
          })
        }
      },
      // 文件超出个数限制时的钩子
      handleExceed (files, fileList) {

      },
      // 文件列表移除文件时的钩子
      handleRemove (file, fileList) {
      },
      // 点击文件列表中已上传的文件时的钩子
      handlePictureCardPreview (file) {
        this.imageUrl = file.url
      },
      uploadFile ( ) {
        this.$refs.upload.submit()
      },

      handleAvatarSuccess (res, file) {
        this.imageUrl = URL.createObjectURL(file.raw)
        console.log(file.raw)
        this.dataForm.zppath = res.filename
        console.log(res.filename)
      },

      beforeAvatarUpload (file) {
        const isJPG = file.type === 'image/png' || file.type === 'image/gif' || file.type === 'image/jpg' || file.type === 'image/jpeg'
        const isLt2M = file.size / 1024 / 1024 < 2
        if (!isJPG) {
          this.$message.error('上传图片只能是 JPG 格式!')
        }
        if (!isLt2M) {
          this.$message.error('上传图片大小不能超过 2MB!')
        }
        return isJPG && isLt2M
      },

      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/zsgl/bjsh/audit`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'ztm': this.dataForm.shbm,
                'bjshyj': this.dataForm.bjshyj

              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
