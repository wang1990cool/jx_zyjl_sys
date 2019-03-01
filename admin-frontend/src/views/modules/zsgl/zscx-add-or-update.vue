<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="学号" prop="xsxh" v-if="false">
      <el-input v-model="dataForm.xsxh" placeholder="学号"></el-input>
    </el-form-item>
    <el-form-item label="姓名" prop="xsxm">
      <el-input v-model="dataForm.xsxm" placeholder="姓名"></el-input>
    </el-form-item>
    <el-form-item label="所在班级" prop="szbj">
      <el-input v-model="dataForm.szbj" placeholder="所在班级"></el-input>
    </el-form-item>
    <el-form-item label="身份证号" prop="sfzh">
      <el-input v-model="dataForm.sfzh" placeholder="身份证号"></el-input>
    </el-form-item>
    <el-form-item label="电话" prop="telephone">
      <el-input v-model="dataForm.telephone" placeholder="电话"></el-input>
    </el-form-item>
    <el-form-item label="证书名称" prop="zsmc">
      <el-input v-model="dataForm.zsmc" placeholder="证书名称"></el-input>
    </el-form-item>
    <el-form-item label="获证日期" prop="hzrq">
      <el-input v-model="dataForm.hzrq" placeholder="获证日期"></el-input>
    </el-form-item>
    <el-form-item label="证书照片" prop="zszp">
      <el-input v-model="dataForm.zszp" placeholder="证书照片"></el-input>
    </el-form-item>
    <el-form-item label="班级审核意见" prop="bjshyj">
      <el-input v-model="dataForm.bjshyj" placeholder="班级审核意见"></el-input>
    </el-form-item>
    <el-form-item label="状态码" prop="ztm">
      <el-input v-model="dataForm.ztm" placeholder="状态码"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="zt">
      <el-input v-model="dataForm.zt" placeholder="状态"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          xsxh: '',
          xsxm: '',
          szbj: '',
          sfzh: '',
          telephone: '',
          zsmc: '',
          hzrq: '',
          zszp: '',
          bjshyj: '',
          ztm: '',
          zt: '',
          createTime: ''
        },
        dataRule: {
          xsxh: [
            { required: true, message: '学号不能为空', trigger: 'blur' }
          ],
          xsxm: [
            { required: true, message: '姓名不能为空', trigger: 'blur' }
          ],
          szbj: [
            { required: true, message: '所在班级不能为空', trigger: 'blur' }
          ],
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
          ],
          zszp: [
            { required: true, message: '证书照片不能为空', trigger: 'blur' }
          ],
          bjshyj: [
            { required: true, message: '班级审核意见不能为空', trigger: 'blur' }
          ],
          ztm: [
            { required: true, message: '状态码不能为空', trigger: 'blur' }
          ],
          zt: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/zsgl/zscx/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.xsxh = data.zscx.xsxh
                this.dataForm.xsxm = data.zscx.xsxm
                this.dataForm.szbj = data.zscx.szbj
                this.dataForm.sfzh = data.zscx.sfzh
                this.dataForm.telephone = data.zscx.telephone
                this.dataForm.zsmc = data.zscx.zsmc
                this.dataForm.hzrq = data.zscx.hzrq
                this.dataForm.zszp = data.zscx.zszp
                this.dataForm.bjshyj = data.zscx.bjshyj
                this.dataForm.ztm = data.zscx.ztm
                this.dataForm.zt = data.zscx.zt
                this.dataForm.createTime = data.zscx.createTime
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/zsgl/zscx/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'xsxh': this.dataForm.xsxh,
                'xsxm': this.dataForm.xsxm,
                'szbj': this.dataForm.szbj,
                'sfzh': this.dataForm.sfzh,
                'telephone': this.dataForm.telephone,
                'zsmc': this.dataForm.zsmc,
                'hzrq': this.dataForm.hzrq,
                'zszp': this.dataForm.zszp,
                'bjshyj': this.dataForm.bjshyj,
                'ztm': this.dataForm.ztm,
                'zt': this.dataForm.zt,
                'createTime': this.dataForm.createTime
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
