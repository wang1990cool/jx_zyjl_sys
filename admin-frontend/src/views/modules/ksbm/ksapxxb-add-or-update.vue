<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="考试名称" prop="name">
      <el-input v-model="dataForm.name" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="考试年份" prop="ksnd">
      <el-input v-model="dataForm.ksnd" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="开始时间" prop="qsrq">
      <el-date-picker
        v-model="dataForm.qsrq"
        type="date"
        placeholder="选择日期"
        value-format="yyyyMMdd">
      </el-date-picker>
    </el-form-item>
    <el-form-item label="结束时间" prop="jsrq">
      <el-date-picker
        v-model="dataForm.jsrq"
        type="date"
        placeholder="选择日期"
        value-format="yyyyMMdd">
      </el-date-picker>
    </el-form-item>
<!--
    <el-form-item label="" prop="status">
      <el-input v-model="dataForm.status" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="createUserId">
      <el-input v-model="dataForm.createUserId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder=""></el-input>
    </el-form-item>
-->
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
          name: '',
          ksnd: '',
          qsrq: '',
          jsrq: ''
        },
        dataRule: {
          name: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          ksnd: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          qsrq: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          jsrq: [
            { required: true, message: '不能为空', trigger: 'blur' }
/*
          ],
          status: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createUserId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
*/
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
              url: this.$http.adornUrl(`/ksbm/ksapxxb/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.ksapxxb.name
                this.dataForm.ksnd = data.ksapxxb.ksnd
                this.dataForm.qsrq = data.ksapxxb.qsrq
                this.dataForm.jsrq = data.ksapxxb.jsrq
/*
                this.dataForm.status = data.ksapxxb.status
                this.dataForm.createUserId = data.ksapxxb.createUserId
                this.dataForm.createTime = data.ksapxxb.createTime
*/
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
              url: this.$http.adornUrl(`/ksbm/ksapxxb/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'ksnd': this.dataForm.ksnd,
                'qsrq': this.dataForm.qsrq,
                'jsrq': this.dataForm.jsrq
/*
                'status': this.dataForm.status,
                'createUserId': this.dataForm.createUserId,
                'createTime': this.dataForm.createTime
*/
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
