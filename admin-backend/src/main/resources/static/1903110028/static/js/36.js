webpackJsonp([36],{efs3:function(t,a,e){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var r={data:function(){return{visible:!1,dataForm:{id:0,projectId:"",projectName:"",projectBudget:"",trainingPlan:"",auditOption:"",applicantId:"",applicantName:"",applicantDept:"",auditorId:"",auditorName:"",auditTime:"",status:"3",statusCode:"审核通过"},dataRule:{status:[{required:!0,validator:function(t,a,e){""===a&&e(new Error("请选择证件"))},trigger:"change"}]}}},methods:{init:function(t){var a=this;this.dataForm.id=t||0,this.visible=!0,this.$nextTick(function(){a.$refs.dataForm.resetFields(),a.dataForm.id&&a.$http({url:a.$http.adornUrl("/train/project/info/"+a.dataForm.id),method:"get",params:a.$http.adornParams()}).then(function(t){var e=t.data;e&&0===e.code&&(a.dataForm.projectId=e.trainProject.projectId,a.dataForm.projectName=e.trainProject.projectName,a.dataForm.projectBudget=e.trainProject.projectBudget,a.dataForm.trainingPlan=e.trainProject.trainingPlan,a.dataForm.auditOption=e.trainProject.auditOption,a.dataForm.applicantId=e.trainProject.applicantId,a.dataForm.applicantName=e.trainProject.applicantName,a.dataForm.applicantDept=e.trainProject.applicantDept,a.dataForm.auditorId=e.trainProject.auditorId,a.dataForm.auditorName=e.trainProject.auditorName,a.dataForm.auditTime=e.trainProject.auditTime,a.dataForm.status="审核通过",a.dataForm.statusCode="3")})})},changeStatusHandler:function(t){this.dataForm.status="2"===t?"审核通过":"审核未通过"},dataFormSubmit:function(){var t=this;this.$refs.dataForm.validate(function(a){a&&t.$http({url:t.$http.adornUrl("/train/projectAudit/audit"),method:"post",data:t.$http.adornData({id:t.dataForm.id||void 0,projectId:t.dataForm.projectId,projectName:t.dataForm.projectName,projectBudget:t.dataForm.projectBudget,trainingPlan:t.dataForm.trainingPlan,auditOption:t.dataForm.auditOption,applicantId:t.dataForm.applicantId,applicantName:t.dataForm.applicantName,applicantDept:t.dataForm.applicantDept,auditorId:t.dataForm.auditorId,auditorName:t.dataForm.auditorName,auditTime:t.dataForm.auditTime,status:t.dataForm.status,statusCode:t.dataForm.statusCode})}).then(function(a){var e=a.data;e&&0===e.code?t.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){t.visible=!1,t.$emit("refreshDataList")}}):t.$message.error(e.msg)})})}}},o={render:function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("el-dialog",{attrs:{title:"审核","close-on-click-modal":!1,visible:t.visible},on:{"update:visible":function(a){t.visible=a}}},[e("el-form",{ref:"dataForm",attrs:{model:t.dataForm,rules:t.dataRule,size:"mini","label-width":"130px"},nativeOn:{keyup:function(a){if(!("button"in a)&&t._k(a.keyCode,"enter",13,a.key))return null;t.dataFormSubmit()}}},[t._e(),t._v(" "),t._e(),t._v(" "),t._e(),t._v(" "),t._e(),t._v(" "),e("el-form-item",{attrs:{label:"是否同意"}},[e("el-radio-group",{on:{change:t.changeStatusHandler},model:{value:t.dataForm.statusCode,callback:function(a){t.$set(t.dataForm,"statusCode",a)},expression:"dataForm.statusCode"}},[e("el-radio",{attrs:{label:"3"}},[t._v("同意")]),t._v(" "),e("el-radio",{attrs:{label:"9"}},[t._v("不同意")])],1)],1),t._v(" "),e("el-form-item",{attrs:{label:"审核意见",prop:"auditOption"}},[e("el-input",{attrs:{type:"textarea",placeholder:"审核意见"},model:{value:t.dataForm.auditOption,callback:function(a){t.$set(t.dataForm,"auditOption",a)},expression:"dataForm.auditOption"}})],1),t._v(" "),t._e(),t._v(" "),t._e(),t._v(" "),t._e(),t._v(" "),t._e(),t._v(" "),t._e(),t._v(" "),t._e(),t._v(" "),t._e()],1),t._v(" "),e("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[e("el-button",{on:{click:function(a){t.visible=!1}}},[t._v("取消")]),t._v(" "),e("el-button",{attrs:{type:"primary"},on:{click:function(a){t.dataFormSubmit()}}},[t._v("确定")])],1)],1)},staticRenderFns:[]},i=e("46Yf")(r,o,!1,null,null,null);a.default=i.exports}});