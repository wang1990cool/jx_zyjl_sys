<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
      <el-form-item label="考试名称" prop="ksxmmc" hidden="true">
        <el-input v-model="dataForm.ksxmmc" placeholder="" readonly="true"></el-input>
      </el-form-item>
    <el-form-item label="考试名称" prop="ksxmid">
      <el-select v-model="dataForm.ksxmid" label="考试名称" placeholder="请选择"  @change="selectKsmc">
        <el-option v-for="item in ksxmmcList" :key="item.value" :label="item.label" :value="item.value" >
        </el-option>
      </el-select>
    </el-form-item>
      <el-form-item label="考试年份" prop="ksnd">
        <el-input v-model="dataForm.ksnd" placeholder="" :disabled="true"></el-input>
      </el-form-item>
<!--
    <el-form-item label="学号" prop="xsxh">
      <el-input v-model="dataForm.xsxh" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="姓名" prop="xsxm">
      <el-input v-model="dataForm.xsxm" placeholder=""></el-input>
    </el-form-item>
-->
    <el-form-item label="性别" prop="sex">
      <el-select v-model="dataForm.sex" label="性别" placeholder="请选择" >
        <el-option v-for="item in sexList" :key="item.label" :label="item.label" :value="item.label" >
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="出生日期" prop="birth">
      <el-date-picker
        v-model="dataForm.birth"
        type="date"
        placeholder="选择日期"
        value-format="yyyyMMdd">
      </el-date-picker>
    </el-form-item>
    <el-form-item label="民族" prop="mz">
      <el-input v-model="dataForm.mz" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="身份证号" prop="sfzh">
      <el-input v-model="dataForm.sfzh" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="联系电话" prop="telephone">
      <el-input v-model="dataForm.telephone" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="email" prop="email">
      <el-input v-model="dataForm.email" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="照片" prop="zszp">
      <el-table-column v-if="false"
                       prop="zppath"
                       header-align="center"
                       align="center"
                       width="80"
                       label="证书照片名称">
      </el-table-column>
      <el-upload
        class="avatar-uploader"
        :action= "uploadUrl"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload">
        <img v-if="imageUrl" :src="imageUrl" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
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
        uploadUrl: this.$http.adornUrl(`/upload?token=${this.$cookie.get('token')}`),
        visible: false,
        dataForm: {
          id: 0,
          ksnd: '',
          ksxmid: '',
          ksxmmc: '',
          sex: '',
          birth: '',
          mz: '',
          sfzh: '',
          telephone: '',
          email: '',
          zppath: '',
          zszp: ''
        },
        imageUrl: '',
        ksxmmcList: [],
        sexList: [{
          value: '2',
          label: '女'
        }, {
          value: '1',
          label: '男'
        }],
        dataRule: {
          ksnd: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          ksxmid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          ksxmmc: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],

          sex: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          birth: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          mz: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          sfzh: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          telephone: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          email: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          zppath: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.imageUrl = ''

        this.$http({
          url: this.$http.adornUrl('/callback/getUserBaseInfo'),
          method: 'get'
        }).then(({data}) => {
          this.dataForm.birth = data.ksbminfo.birthday
          this.dataForm.mz = '汉族'
          this.dataForm.sfzh = data.ksbminfo.idCard
          this.dataForm.telephone = data.ksbminfo.mobile
          this.dataForm.email = data.ksbminfo.email

          if ( data.ksbminfo.gender == '2' ) {
            this.dataForm.sex = '女'
          } else {
            this.dataForm.sex = '男'
          }
//          this.$message({
//            message: this.dataForm.sex + data.ksbminfo.gender,
//            type: 'success',
//            duration: 1500
//          })

        })

        this.$http({
          url: this.$http.adornUrl('/ksbm/ksapxxb/select'),
          method: 'get'
        }).then(({data}) => {
          this.ksxmmcList = data.ksxmmclist
        }).then(() => {
          this.$nextTick(() => {
            this.$refs['dataForm'].resetFields()
            if (this.dataForm.id) {
              this.$http({
                url: this.$http.adornUrl(`/ksbm/ksbmxxb/info/${this.dataForm.id}`),
                method: 'get',
                params: this.$http.adornParams()
              }).then(({data}) => {
                if (data && data.code === 0) {
                  this.dataForm.ksnd = data.ksbmxxb.ksnd
                  this.dataForm.ksxmid = data.ksbmxxb.ksxmid
                  this.dataForm.ksxmmc = data.ksbmxxb.ksxmmc
                  this.dataForm.sex = data.ksbmxxb.sex
                  this.dataForm.birth = data.ksbmxxb.birth
                  this.dataForm.mz = data.ksbmxxb.mz
                  this.dataForm.sfzh = data.ksbmxxb.sfzh
                  this.dataForm.telephone = data.ksbmxxb.telephone
                  this.dataForm.email = data.ksbmxxb.email
                  this.imageUrl = 'data:image/png;base64,' + data.ksbmxxb.zszp
                  this.dataForm.zppath = data.ksbmxxb.zppath
                }
              })
            }
          })
        })
      },

      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/ksbm/ksbmxxb/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'ksnd': this.dataForm.ksnd,
                'ksxmid': this.dataForm.ksxmid,
                'ksxmmc': this.dataForm.ksxmmc,
/*
                'xsxh': this.dataForm.xsxh,
                'xsxm': this.dataForm.xsxm,
*/
                'sex': this.dataForm.sex,
                'birth': this.dataForm.birth,
                'mz': this.dataForm.mz,
//                'szbj': this.dataForm.szbj,
                'sfzh': this.dataForm.sfzh,
                'telephone': this.dataForm.telephone,
                'email': this.dataForm.email,
                'zppath': this.dataForm.zppath,
                'zszp': this.dataForm.zszp
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
      },

      selectKsmc( vId ){//这个vId也就是value值
       let obj = {};
        obj = this.ksxmmcList.find((item)=> {
          return item.value == vId;
        } );
        this.dataForm.ksnd = obj.ksnd
        this.dataForm.ksxmmc = obj.label
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
        this.$message({
          showClose: true,
          message: '这是一条消息提示'
        })
      },
      // 文件列表移除文件时的钩子
      handleRemove (file, fileList) {
        //       console.log(file, fileList)
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
//        console.log(file.raw)
        this.dataForm.zppath = res.filename
//        console.log(res.filename)
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
      }

    }
  }
</script>
