<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :modal="false"
    :append-to-body="true"
    :modal-append-to-body="true"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="项目编号" prop="projectId" v-if="false">
      <el-input v-model="dataForm.projectId" placeholder="项目编号"></el-input>
    </el-form-item>
    <el-form-item label="教师工号" prop="teacherId">
      <el-input v-model="dataForm.teacherId" placeholder="教师工号"></el-input>
    </el-form-item>
    <el-form-item label="教师姓名" prop="teacherName">
      <el-input v-model="dataForm.teacherName" placeholder="教师姓名"></el-input>
    </el-form-item>
    <el-form-item label="课时" prop="classHour">
      <el-input v-model="dataForm.classHour" placeholder="课时"></el-input>
    </el-form-item>
      <el-form-item label="上课地址" prop="classAddress">
        <el-input v-model="dataForm.classAddress" placeholder="上课地址"></el-input>
      </el-form-item>
    <el-form-item label="上课日期" prop="classDate">
      <el-date-picker
        v-model="dataForm.classDate"
        type="date"
        value-format="yyyyMMdd"
        placeholder="选择日期">
      </el-date-picker>
    </el-form-item>

    <el-form-item label="上课人数" prop="studentNum">
      <el-input v-model.number="dataForm.studentNum" type="number" placeholder="上课人数"></el-input>
    </el-form-item>

      <el-row>
        <el-col :span="12">
          <el-form-item label="开始节次" prop="startSection">
            <el-input v-model.number="dataForm.startSection" type="number" placeholder="开始节次"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="结束节次" prop="endSection">
            <el-input v-model.number="dataForm.endSection" type="number" placeholder="结束节次"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  let moment = require('moment');
  let classHourRule = (rule, value, callback) => {
    let regExp = /^\d+(\.\d+)?$/;
    if (regExp.test(value) === false) {
      callback(new Error('请输入数字'));
    } else {
      callback();
    }
  };

  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          projectId: '',
          teacherId: '',
          teacherName: '',
          classHour: '',
          classDate: '',
          classAddress: '',
          studentNum: '',
          startSection: '',
          endSection: ''
        },
        dataRule: {
          projectId: [
            { required: true, message: '项目编号不能为空', trigger: 'blur' }
          ],
          teacherId: [
            { required: true, message: '教师工号不能为空', trigger: 'blur' }
          ],
          teacherName: [
            { required: true, message: '教师姓名不能为空', trigger: 'blur' }
          ],
          classHour: [
            { required: true, validator: classHourRule, trigger: 'blur' }
          ],
          studentNum: [
            {  type: 'number', min: 1, trigger: 'blur', message: '学生不能小于1',}
          ],
          startNum: [
            {  type: 'number', min: 1, trigger: 'blur', message: '请填写数字',}
          ],
          endNum: [
            {  type: 'number', min: 1, trigger: 'blur', message: '请填写数字',}
          ]
        }
      }
    },
    methods: {
      init (id, projectId) {
        this.dataForm.projectId = projectId;
        this.dataForm.id = id || 0;
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/train/projectTrainProgram/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.projectId = data.projectTrainProgram.projectId
                this.dataForm.teacherId = data.projectTrainProgram.teacherId
                this.dataForm.teacherName = data.projectTrainProgram.teacherName
                this.dataForm.classHour = data.projectTrainProgram.classHour
                this.dataForm.classDate = data.projectTrainProgram.classDate
                this.dataForm.classAddress = data.projectTrainProgram.classAddress
                this.dataForm.studentNum = data.projectTrainProgram.studentNum
                this.dataForm.startSection = data.projectTrainProgram.startSection
                this.dataForm.endSection = data.projectTrainProgram.endSection
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        // alert(moment(this.dataForm.classDate).format('YYYY-MM-DD').toString())
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/train/projectTrainProgram/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'projectId': this.dataForm.projectId,
                'teacherId': this.dataForm.teacherId,
                'teacherName': this.dataForm.teacherName,
                'classHour': this.dataForm.classHour,
                'classAddress': this.dataForm.classAddress,
                'studentNum': this.dataForm.studentNum,
                'startSection': this.dataForm.startSection,
                'endSection': this.dataForm.endSection,
                'classDate': moment(this.dataForm.classDate).format('YYYYMMDD')
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
