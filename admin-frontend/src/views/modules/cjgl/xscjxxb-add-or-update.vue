<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="成绩年份" prop="nf">
      <el-input v-model="dataForm.nf" placeholder="成绩年份"></el-input>
    </el-form-item>
    <el-form-item label="学生学号" prop="xsxh">
      <el-input v-model="dataForm.xsxh" placeholder="学生学号"></el-input>
    </el-form-item>
    <el-form-item label="学生姓名" prop="xsxm">
      <el-input v-model="dataForm.xsxm" placeholder="学生姓名"></el-input>
    </el-form-item>
    <el-form-item label="身份证号" prop="sfzh">
      <el-input v-model="dataForm.sfzh" placeholder="身份证号"></el-input>
    </el-form-item>
    <el-form-item label="科目名称" prop="kmmc">
      <el-input v-model="dataForm.kmmc" placeholder="科目名称"></el-input>
    </el-form-item>
    <el-form-item label="分数" prop="score">
      <el-input v-model="dataForm.score" placeholder="分数"></el-input>
    </el-form-item>
    <el-form-item label="状态(0:禁用,1:有效)" prop="status">
      <el-input v-model="dataForm.status" placeholder="状态(0:禁用,1:有效)"></el-input>
    </el-form-item>
    <el-form-item label="" prop="createUserId">
      <el-input v-model="dataForm.createUserId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder=""></el-input>
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
          nf: '',
          xsxh: '',
          xsxm: '',
          sfzh: '',
          kmmc: '',
          score: '',
          status: '',
          createUserId: '',
          createTime: ''
        },
        dataRule: {
          nf: [
            { required: true, message: '成绩年份不能为空', trigger: 'blur' }
          ],
          xsxh: [
            { required: true, message: '学生学号不能为空', trigger: 'blur' }
          ],
          xsxm: [
            { required: true, message: '学生姓名不能为空', trigger: 'blur' }
          ],
          sfzh: [
            { required: true, message: '身份证号不能为空', trigger: 'blur' }
          ],
          kmmc: [
            { required: true, message: '科目名称不能为空', trigger: 'blur' }
          ],
          score: [
            { required: true, message: '分数不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '状态(0:禁用,1:有效)不能为空', trigger: 'blur' }
          ],
          createUserId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/cjgl/xscjxxb/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.nf = data.xscjxxb.nf
                this.dataForm.xsxh = data.xscjxxb.xsxh
                this.dataForm.xsxm = data.xscjxxb.xsxm
                this.dataForm.sfzh = data.xscjxxb.sfzh
                this.dataForm.kmmc = data.xscjxxb.kmmc
                this.dataForm.score = data.xscjxxb.score
                this.dataForm.status = data.xscjxxb.status
                this.dataForm.createUserId = data.xscjxxb.createUserId
                this.dataForm.createTime = data.xscjxxb.createTime
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
              url: this.$http.adornUrl(`/cjgl/xscjxxb/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'nf': this.dataForm.nf,
                'xsxh': this.dataForm.xsxh,
                'xsxm': this.dataForm.xsxm,
                'sfzh': this.dataForm.sfzh,
                'kmmc': this.dataForm.kmmc,
                'score': this.dataForm.score,
                'status': this.dataForm.status,
                'createUserId': this.dataForm.createUserId,
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
