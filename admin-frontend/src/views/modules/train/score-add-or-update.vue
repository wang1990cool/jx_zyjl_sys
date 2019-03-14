<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="年份" prop="year">
      <el-input v-model="dataForm.year" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="学号" prop="studentNum">
      <el-input v-model="dataForm.studentNum" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="学生姓名" prop="studentName">
      <el-input v-model="dataForm.studentName" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="课程号" prop="courseNum">
      <el-input v-model="dataForm.courseNum" placeholder="课程号"></el-input>
    </el-form-item>
    <el-form-item label="课程名" prop="courseName">
      <el-input v-model="dataForm.courseName" placeholder="课程名"></el-input>
    </el-form-item>
    <el-form-item label="学生成绩" prop="studentScore">
      <el-input v-model="dataForm.studentScore" placeholder="学生成绩"></el-input>
    </el-form-item>
    <el-form-item label="状态码" prop="statusCode" v-if="false">
      <el-input v-model="dataForm.statusCode" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="stauts" v-if="false">
      <el-input v-model="dataForm.stauts" placeholder=""></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  let scoreRule = (rule, value, callback) => {
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
          year: '',
          studentNum: '',
          studentName: '',
          courseNum: '',
          courseName: '',
          studentScore: '',
          statusCode: '',
          stauts: ''
        },
        dataRule: {
          year: [
            { required: true, message: '年份不能为空', trigger: 'blur' }
          ],
          studentNum: [
            { required: true, message: '学号不能为空', trigger: 'blur' }
          ],
          studentName: [
            { required: true, message: '学生姓名不能为空', trigger: 'blur' }
          ],
          courseNum: [
            { required: true, message: '课程号不能为空', trigger: 'blur' }
          ],
          courseName: [
            { required: true, message: '课程明不能为空', trigger: 'blur' }
          ],
          studentScore: [
            { required: true, validator: scoreRule, trigger: 'blur' }
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
              url: this.$http.adornUrl(`/train/score/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.year = data.trainScore.year
                this.dataForm.studentNum = data.trainScore.studentNum
                this.dataForm.studentName = data.trainScore.studentName
                this.dataForm.courseNum = data.trainScore.courseNum
                this.dataForm.courseName = data.trainScore.courseName
                this.dataForm.studentScore = data.trainScore.studentScore
                this.dataForm.statusCode = data.trainScore.statusCode
                this.dataForm.stauts = data.trainScore.stauts
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
              url: this.$http.adornUrl(`/train/score/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'year': this.dataForm.year,
                'studentNum': this.dataForm.studentNum,
                'studentName': this.dataForm.studentName,
                'courseNum': this.dataForm.courseNum,
                'courseName': this.dataForm.courseName,
                'studentScore': this.dataForm.studentScore,
                'statusCode': this.dataForm.statusCode,
                'status': this.dataForm.stauts
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
