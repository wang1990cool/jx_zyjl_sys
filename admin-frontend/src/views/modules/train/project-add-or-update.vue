<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="项目编号" prop="projectId">
      <el-input v-model="dataForm.projectId" placeholder="项目编号"></el-input>
    </el-form-item>
    <el-form-item label="项目名称" prop="projectName">
      <el-input v-model="dataForm.projectName" placeholder="项目名称"></el-input>
    </el-form-item>
    <el-form-item label="项目预算" prop="projectBudget">
      <el-input v-model="dataForm.projectBudget" placeholder="项目预算"></el-input>
    </el-form-item>
    <el-form-item label="培养方案" prop="trainingPlan">
      <el-input v-model="dataForm.trainingPlan" placeholder="培养方案"></el-input>
    </el-form-item>
    <el-form-item label="审核意见" prop="auditOption">
      <el-input v-model="dataForm.auditOption" placeholder="审核意见"></el-input>
    </el-form-item>
    <el-form-item label="申请人工号" prop="applicantId" >
      <el-input v-model="dataForm.applicantId" placeholder="申请人工号"></el-input>
    </el-form-item>
    <el-form-item label="申请人姓名" prop="applicantName">
      <el-input v-model="dataForm.applicantName" placeholder="申请人姓名"></el-input>
    </el-form-item>
    <el-form-item label="申请人所在部门" prop="applicantDept">
      <el-input v-model="dataForm.applicantDept" placeholder="申请人所在部门"></el-input>
    </el-form-item>
    <el-form-item label="审核人id" prop="auditorId">
      <el-input v-model="dataForm.auditorId" placeholder="审核人id"></el-input>
    </el-form-item>
    <el-form-item label="审核人姓名" prop="auditorName">
      <el-input v-model="dataForm.auditorName" placeholder="审核人姓名"></el-input>
    </el-form-item>
    <el-form-item label="审核时间" prop="auditTime">
      <el-input v-model="dataForm.auditTime" placeholder="审核时间"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="status">
      <el-input v-model="dataForm.status" placeholder="状态"></el-input>
    </el-form-item>
    <el-form-item label="状态码" prop="statusCode">
      <el-input v-model="dataForm.statusCode" placeholder="状态码"></el-input>
    </el-form-item>
    <el-form-item label="创建用户" prop="createUserId">
      <el-input v-model="dataForm.createUserId" placeholder="创建用户"></el-input>
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
          projectId: '',
          projectName: '',
          projectBudget: '',
          trainingPlan: '',
          auditOption: '',
          applicantId: '',
          applicantName: '',
          applicantDept: '',
          auditorId: '',
          auditorName: '',
          auditTime: '',
          status: '',
          statusCode: '',
          createUserId: '',
          createTime: ''
        },
        dataRule: {
          projectId: [
            { required: true, message: '项目编号不能为空', trigger: 'blur' }
          ],
          projectName: [
            { required: true, message: '项目名称不能为空', trigger: 'blur' }
          ],
          projectBudget: [
            { required: true, message: '项目预算不能为空', trigger: 'blur' }
          ],
          trainingPlan: [
            { required: true, message: '培养方案不能为空', trigger: 'blur' }
          ],
          auditOption: [
            { required: true, message: '审核意见不能为空', trigger: 'blur' }
          ],
          applicantId: [
            { required: true, message: '申请人工号不能为空', trigger: 'blur' }
          ],
          applicantName: [
            { required: true, message: '申请人姓名不能为空', trigger: 'blur' }
          ],
          applicantDept: [
            { required: true, message: '申请人所在部门不能为空', trigger: 'blur' }
          ],
          auditorId: [
            { required: true, message: '审核人id不能为空', trigger: 'blur' }
          ],
          auditorName: [
            { required: true, message: '审核人姓名不能为空', trigger: 'blur' }
          ],
          auditTime: [
            { required: true, message: '审核时间不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          statusCode: [
            { required: true, message: '状态码不能为空', trigger: 'blur' }
          ],
          createUserId: [
            { required: true, message: '创建用户不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/train/project/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.projectId = data.trainproject.projectId
                this.dataForm.projectName = data.trainproject.projectName
                this.dataForm.projectBudget = data.trainproject.projectBudget
                this.dataForm.trainingPlan = data.trainproject.trainingPlan
                this.dataForm.auditOption = data.trainproject.auditOption
                this.dataForm.applicantId = data.trainproject.applicantId
                this.dataForm.applicantName = data.trainproject.applicantName
                this.dataForm.applicantDept = data.trainproject.applicantDept
                this.dataForm.auditorId = data.trainproject.auditorId
                this.dataForm.auditorName = data.trainproject.auditorName
                this.dataForm.auditTime = data.trainproject.auditTime
                this.dataForm.status = data.trainproject.status
                this.dataForm.statusCode = data.trainproject.statusCode
                this.dataForm.createUserId = data.trainproject.createUserId
                this.dataForm.createTime = data.trainproject.createTime
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
              url: this.$http.adornUrl(`/train/project/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'projectId': this.dataForm.projectId,
                'projectName': this.dataForm.projectName,
                'projectBudget': this.dataForm.projectBudget,
                'trainingPlan': this.dataForm.trainingPlan,
                'auditOption': this.dataForm.auditOption,
                'applicantId': this.dataForm.applicantId,
                'applicantName': this.dataForm.applicantName,
                'applicantDept': this.dataForm.applicantDept,
                'auditorId': this.dataForm.auditorId,
                'auditorName': this.dataForm.auditorName,
                'auditTime': this.dataForm.auditTime,
                'status': this.dataForm.status,
                'statusCode': this.dataForm.statusCode,
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
