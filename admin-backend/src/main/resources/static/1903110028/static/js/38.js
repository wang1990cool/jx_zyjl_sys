webpackJsonp([38],{INS3:function(a,e,t){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var r=t("E4LH"),o={data:function(){var a=this;return{visible:!1,roleList:[],dataForm:{id:0,userName:"",userCName:"",unit:"",password:"",confirmPassword:"",salt:"",email:"",mobile:"",roleIdList:[],status:1},dataRule:{userName:[{required:!0,message:"用户名不能为空",trigger:"blur"}],userCName:[{required:!0,message:"姓名不能为空",trigger:"blur"}],unit:[{required:!0,message:"单位不能为空",trigger:"blur"}],password:[{validator:function(e,t,r){a.dataForm.id||/\S/.test(t)?r():r(new Error("密码不能为空"))},trigger:"blur"}],confirmPassword:[{validator:function(e,t,r){a.dataForm.id||/\S/.test(t)?a.dataForm.password!==t?r(new Error("确认密码与密码输入不一致")):r():r(new Error("确认密码不能为空"))},trigger:"blur"}],email:[{required:!0,message:"邮箱不能为空",trigger:"blur"},{validator:function(a,e,t){Object(r.a)(e)?t():t(new Error("邮箱格式错误"))},trigger:"blur"}],mobile:[{required:!0,message:"手机号不能为空",trigger:"blur"},{validator:function(a,e,t){Object(r.b)(e)?t():t(new Error("手机号格式错误"))},trigger:"blur"}]}}},methods:{init:function(a){var e=this;this.dataForm.id=a||0,this.$http({url:this.$http.adornUrl("/sys/role/select"),method:"get",params:this.$http.adornParams()}).then(function(a){var t=a.data;e.roleList=t&&0===t.code?t.list:[]}).then(function(){e.visible=!0,e.$nextTick(function(){e.$refs.dataForm.resetFields()})}).then(function(){e.dataForm.id&&e.$http({url:e.$http.adornUrl("/sys/user/info/"+e.dataForm.id),method:"get",params:e.$http.adornParams()}).then(function(a){var t=a.data;t&&0===t.code&&(e.dataForm.userName=t.user.username,e.dataForm.userCName=t.user.usercname,e.dataForm.unit=t.user.unit,e.dataForm.salt=t.user.salt,e.dataForm.email=t.user.email,e.dataForm.mobile=t.user.mobile,e.dataForm.roleIdList=t.user.roleIdList,e.dataForm.status=t.user.status)})})},dataFormSubmit:function(){var a=this;this.$refs.dataForm.validate(function(e){e&&a.$http({url:a.$http.adornUrl("/sys/user/"+(a.dataForm.id?"update":"save")),method:"post",data:a.$http.adornData({userId:a.dataForm.id||void 0,username:a.dataForm.userName,usercname:a.dataForm.userCName,unit:a.dataForm.unit,password:a.dataForm.password,salt:a.dataForm.salt,email:a.dataForm.email,mobile:a.dataForm.mobile,status:a.dataForm.status,roleIdList:a.dataForm.roleIdList})}).then(function(e){var t=e.data;t&&0===t.code?a.$message({message:"操作成功",type:"success",duration:1500,onClose:function(){a.visible=!1,a.$emit("refreshDataList")}}):a.$message.error(t.msg)})})}}},s={render:function(){var a=this,e=a.$createElement,t=a._self._c||e;return t("el-dialog",{attrs:{title:a.dataForm.id?"修改":"新增","close-on-click-modal":!1,visible:a.visible},on:{"update:visible":function(e){a.visible=e}}},[t("el-form",{ref:"dataForm",attrs:{model:a.dataForm,rules:a.dataRule,"label-width":"80px"},nativeOn:{keyup:function(e){if(!("button"in e)&&a._k(e.keyCode,"enter",13,e.key))return null;a.dataFormSubmit()}}},[t("el-form-item",{attrs:{label:"用户名",prop:"userName"}},[t("el-input",{attrs:{placeholder:"登录帐号"},model:{value:a.dataForm.userName,callback:function(e){a.$set(a.dataForm,"userName",e)},expression:"dataForm.userName"}})],1),a._v(" "),t("el-form-item",{attrs:{label:"姓名",prop:"userCName"}},[t("el-input",{attrs:{placeholder:"姓名"},model:{value:a.dataForm.userCName,callback:function(e){a.$set(a.dataForm,"userCName",e)},expression:"dataForm.userCName"}})],1),a._v(" "),t("el-form-item",{attrs:{label:"单位",prop:"unit"}},[t("el-input",{attrs:{placeholder:"单位"},model:{value:a.dataForm.unit,callback:function(e){a.$set(a.dataForm,"unit",e)},expression:"dataForm.unit"}})],1),a._v(" "),t("el-form-item",{class:{"is-required":!a.dataForm.id},attrs:{label:"密码",prop:"password"}},[t("el-input",{attrs:{type:"password",placeholder:"密码"},model:{value:a.dataForm.password,callback:function(e){a.$set(a.dataForm,"password",e)},expression:"dataForm.password"}})],1),a._v(" "),t("el-form-item",{class:{"is-required":!a.dataForm.id},attrs:{label:"确认密码",prop:"comfirmPassword"}},[t("el-input",{attrs:{type:"password",placeholder:"确认密码"},model:{value:a.dataForm.confirmPassword,callback:function(e){a.$set(a.dataForm,"confirmPassword",e)},expression:"dataForm.confirmPassword"}})],1),a._v(" "),t("el-form-item",{attrs:{label:"邮箱",prop:"email"}},[t("el-input",{attrs:{placeholder:"邮箱"},model:{value:a.dataForm.email,callback:function(e){a.$set(a.dataForm,"email",e)},expression:"dataForm.email"}})],1),a._v(" "),t("el-form-item",{attrs:{label:"手机号",prop:"mobile"}},[t("el-input",{attrs:{placeholder:"手机号"},model:{value:a.dataForm.mobile,callback:function(e){a.$set(a.dataForm,"mobile",e)},expression:"dataForm.mobile"}})],1),a._v(" "),t("el-form-item",{attrs:{label:"角色",size:"mini",prop:"roleIdList"}},[t("el-checkbox-group",{model:{value:a.dataForm.roleIdList,callback:function(e){a.$set(a.dataForm,"roleIdList",e)},expression:"dataForm.roleIdList"}},a._l(a.roleList,function(e){return t("el-checkbox",{key:e.roleId,attrs:{label:e.roleId}},[a._v(a._s(e.roleName))])}))],1),a._v(" "),t("el-form-item",{attrs:{label:"状态",size:"mini",prop:"status"}},[t("el-radio-group",{model:{value:a.dataForm.status,callback:function(e){a.$set(a.dataForm,"status",e)},expression:"dataForm.status"}},[t("el-radio",{attrs:{label:0}},[a._v("禁用")]),a._v(" "),t("el-radio",{attrs:{label:1}},[a._v("正常")])],1)],1)],1),a._v(" "),t("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(e){a.visible=!1}}},[a._v("取消")]),a._v(" "),t("el-button",{attrs:{type:"primary"},on:{click:function(e){a.dataFormSubmit()}}},[a._v("确定")])],1)],1)},staticRenderFns:[]},l=t("46Yf")(o,s,!1,null,null,null);e.default=l.exports}});