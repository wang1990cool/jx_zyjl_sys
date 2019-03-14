webpackJsonp([48],{XRDI:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r={data:function(){return{visible:!1,dataForm:{id:0,nf:"",xsxh:"",xsxm:"",sfzh:"",kmmc:"",score:"",status:"",createUserId:"",createTime:""},dataRule:{nf:[{required:!0,message:"成绩年份不能为空",trigger:"blur"}],xsxh:[{required:!0,message:"学生学号不能为空",trigger:"blur"}],xsxm:[{required:!0,message:"学生姓名不能为空",trigger:"blur"}],sfzh:[{required:!0,message:"身份证号不能为空",trigger:"blur"}],kmmc:[{required:!0,message:"科目名称不能为空",trigger:"blur"}],score:[{required:!0,message:"分数不能为空",trigger:"blur"}],status:[{required:!0,message:"状态(0:禁用,1:有效)不能为空",trigger:"blur"}],createUserId:[{required:!0,message:"不能为空",trigger:"blur"}],createTime:[{required:!0,message:"不能为空",trigger:"blur"}]}}},methods:{init:function(e){var t=this;this.dataForm.id=e||0,this.visible=!0,this.$nextTick(function(){t.$refs.dataForm.resetFields(),t.dataForm.id&&t.$http({url:t.$http.adornUrl("/cjgl/xscjxxb/info/"+t.dataForm.id),method:"get",params:t.$http.adornParams()}).then(function(e){var a=e.data;a&&0===a.code&&(t.dataForm.nf=a.xscjxxb.nf,t.dataForm.xsxh=a.xscjxxb.xsxh,t.dataForm.xsxm=a.xscjxxb.xsxm,t.dataForm.sfzh=a.xscjxxb.sfzh,t.dataForm.kmmc=a.xscjxxb.kmmc,t.dataForm.score=a.xscjxxb.score,t.dataForm.status=a.xscjxxb.status,t.dataForm.createUserId=a.xscjxxb.createUserId,t.dataForm.createTime=a.xscjxxb.createTime)})})},dataFormSubmit:function(){var e=this;this.$refs.dataForm.validate(function(t){t&&e.$http({url:e.$http.adornUrl("/cjgl/xscjxxb/"+(e.dataForm.id?"update":"save")),method:"post",data:e.$http.adornData({id:e.dataForm.id||void 0,nf:e.dataForm.nf,xsxh:e.dataForm.xsxh,xsxm:e.dataForm.xsxm,sfzh:e.dataForm.sfzh,kmmc:e.dataForm.kmmc,score:e.dataForm.score,status:e.dataForm.status,createUserId:e.dataForm.createUserId,createTime:e.dataForm.createTime})}).then(function(t){var a=t.data;a&&0===a.code?e.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){e.visible=!1,e.$emit("refreshDataList")}}):e.$message.error(a.msg)})})}}},s={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-dialog",{attrs:{title:e.dataForm.id?"修改":"新增","close-on-click-modal":!1,visible:e.visible},on:{"update:visible":function(t){e.visible=t}}},[a("el-form",{ref:"dataForm",attrs:{model:e.dataForm,rules:e.dataRule,"label-width":"80px"},nativeOn:{keyup:function(t){if(!("button"in t)&&e._k(t.keyCode,"enter",13,t.key))return null;e.dataFormSubmit()}}},[a("el-form-item",{attrs:{label:"成绩年份",prop:"nf"}},[a("el-input",{attrs:{placeholder:"成绩年份"},model:{value:e.dataForm.nf,callback:function(t){e.$set(e.dataForm,"nf",t)},expression:"dataForm.nf"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"学生学号",prop:"xsxh"}},[a("el-input",{attrs:{placeholder:"学生学号"},model:{value:e.dataForm.xsxh,callback:function(t){e.$set(e.dataForm,"xsxh",t)},expression:"dataForm.xsxh"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"学生姓名",prop:"xsxm"}},[a("el-input",{attrs:{placeholder:"学生姓名"},model:{value:e.dataForm.xsxm,callback:function(t){e.$set(e.dataForm,"xsxm",t)},expression:"dataForm.xsxm"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"身份证号",prop:"sfzh"}},[a("el-input",{attrs:{placeholder:"身份证号"},model:{value:e.dataForm.sfzh,callback:function(t){e.$set(e.dataForm,"sfzh",t)},expression:"dataForm.sfzh"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"科目名称",prop:"kmmc"}},[a("el-input",{attrs:{placeholder:"科目名称"},model:{value:e.dataForm.kmmc,callback:function(t){e.$set(e.dataForm,"kmmc",t)},expression:"dataForm.kmmc"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"分数",prop:"score"}},[a("el-input",{attrs:{placeholder:"分数"},model:{value:e.dataForm.score,callback:function(t){e.$set(e.dataForm,"score",t)},expression:"dataForm.score"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"状态(0:禁用,1:有效)",prop:"status"}},[a("el-input",{attrs:{placeholder:"状态(0:禁用,1:有效)"},model:{value:e.dataForm.status,callback:function(t){e.$set(e.dataForm,"status",t)},expression:"dataForm.status"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"",prop:"createUserId"}},[a("el-input",{attrs:{placeholder:""},model:{value:e.dataForm.createUserId,callback:function(t){e.$set(e.dataForm,"createUserId",t)},expression:"dataForm.createUserId"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"",prop:"createTime"}},[a("el-input",{attrs:{placeholder:""},model:{value:e.dataForm.createTime,callback:function(t){e.$set(e.dataForm,"createTime",t)},expression:"dataForm.createTime"}})],1)],1),e._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.visible=!1}}},[e._v("取消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.dataFormSubmit()}}},[e._v("确定")])],1)],1)},staticRenderFns:[]},o=a("46Yf")(r,s,!1,null,null,null);t.default=o.exports}});