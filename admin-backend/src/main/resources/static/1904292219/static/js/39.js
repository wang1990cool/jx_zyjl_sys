webpackJsonp([39],{r2p1:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=a("efs3"),n=a("JuyP"),r=a("njPW"),o=a("ryJa"),l={data:function(){return{dataForm:{key:""},dataList:[],pageIndex:1,pageSize:10,totalPage:0,dataListLoading:!1,dataListSelections:[],projectAuditVisible:!1,order:"statusCode desc",detailVisible:!1,addOrUpdateVisible:!1}},components:{projectAudit:i.default,trainProgramDetailList:n.default,AddOrUpdate:r.default},activated:function(){this.getDataList()},methods:{getDataList:function(){var t=this;this.dataListLoading=!0,this.$http({url:this.$http.adornUrl("/train/projectAudit/projectList"),method:"get",params:this.$http.adornParams({page:this.pageIndex,limit:this.pageSize,order:this.order,projectName:this.dataForm.projectName})}).then(function(e){var a=e.data;a&&0===a.code?(t.dataList=a.page.list,t.totalPage=a.page.totalCount):(t.dataList=[],t.totalPage=0),t.dataListLoading=!1})},getDataAllList:function(){this.dataForm.projectName="",this.getDataList()},sizeChangeHandle:function(t){this.pageSize=t,this.pageIndex=1,this.getDataList()},currentChangeHandle:function(t){this.pageIndex=t,this.getDataList()},selectionChangeHandle:function(t){this.dataListSelections=t},auditHandle:function(t){var e=this;this.projectAuditVisible=!0,this.$nextTick(function(){e.$refs.projectAudit.init(t)})},printHandle:function(t){this.$http({url:this.$http.adornUrl("/train/projectAudit/print/"+t+"?token="+this.$cookie.get("token")),method:"post",responseType:"arraybuffer"}).then(function(t){var e=t.data;if(e){var a=new Blob([e],{type:"application/pdf;charset-UTF-8"}),i=URL.createObjectURL(a),n=document.createElement("a");n.href=i,n.setAttribute("download","审批表.pdf"),document.body.appendChild(n),n.click()}})},trainProgramDetailHandle:function(t,e){var a=this;this.detailVisible=!0,this.$nextTick(function(){a.$refs.trainProgramDetailList.init(t,e)})},detailHandle:function(t){var e=this;this.addOrUpdateVisible=!0,this.$nextTick(function(){e.$refs.addOrUpdate.init(t,!0)})},dateFormat:function(t,e){var a=t[e.property];return void 0==a?"":o(a).format("YYYY-MM-DD")}}},s={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"mod-config"},[a("el-form",{attrs:{inline:!0,model:t.dataForm},nativeOn:{keyup:function(e){if(!("button"in e)&&t._k(e.keyCode,"enter",13,e.key))return null;t.getDataList()}}},[a("el-form-item",[a("el-input",{attrs:{placeholder:"项目名称",clearable:""},model:{value:t.dataForm.projectName,callback:function(e){t.$set(t.dataForm,"projectName",e)},expression:"dataForm.projectName"}})],1),t._v(" "),a("el-form-item",[a("el-button",{attrs:{icon:"el-icon-zoom-in",type:"primary",plain:""},on:{click:function(e){t.getDataList()}}},[t._v("查询")]),t._v(" "),a("el-button",{attrs:{icon:"el-icon-zoom-out",type:"primary",plain:""},on:{click:function(e){t.getDataAllList()}}},[t._v("取消")])],1)],1),t._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.dataListLoading,expression:"dataListLoading"}],staticStyle:{width:"100%"},attrs:{data:t.dataList,border:""},on:{"selection-change":t.selectionChangeHandle}},[a("el-table-column",{attrs:{type:"selection","header-align":"center",align:"center",width:"50"}}),t._v(" "),a("el-table-column",{attrs:{prop:"projectName","header-align":"center",align:"center",label:"项目名称"}}),t._v(" "),a("el-table-column",{attrs:{prop:"projectBudget","header-align":"center",align:"center",label:"项目预算"}}),t._v(" "),a("el-table-column",{attrs:{prop:"applicantName","header-align":"center",align:"center",width:"100",label:"申请人姓名"}}),t._v(" "),a("el-table-column",{attrs:{prop:"applicantDept","header-align":"center",align:"center",width:"140",label:"申请人部门"}}),t._v(" "),a("el-table-column",{attrs:{prop:"statusCode","header-align":"center",align:"center",width:"100",label:"状态"},scopedSlots:t._u([{key:"default",fn:function(e){return["2"===e.row.statusCode?a("el-tag",{attrs:{size:"small",type:"success"}},[t._v("待中心审核")]):t._e(),t._v(" "),"3"===e.row.statusCode?a("el-tag",{attrs:{size:"small",type:"primary"}},[t._v("审核通过")]):t._e(),t._v(" "),"4"===e.row.statusCode?a("el-tag",{attrs:{size:"small",type:"danger"}},[t._v("课程已填写")]):t._e(),t._v(" "),"5"===e.row.statusCode?a("el-tag",{attrs:{size:"small",type:"info"}},[t._v("项目结束")]):t._e()]}}])}),t._v(" "),a("el-table-column",{attrs:{fixed:"right","header-align":"center",align:"center",width:"160",label:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){return["2"===e.row.statusCode?a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){t.auditHandle(e.row.id)}}},[t._v("审核")]):t._e(),t._v(" "),"2"!==e.row.statusCode?a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){t.printHandle(e.row.id)}}},[t._v("打印")]):t._e(),t._v(" "),a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){t.detailHandle(e.row.id)}}},[t._v("详情")]),t._v(" "),"4"===e.row.statusCode||"5"===e.row.statusCode?a("el-button",{attrs:{type:"text",size:"small"},on:{click:function(a){t.trainProgramDetailHandle(e.row.id,e.row.projectId)}}},[t._v("课程详情")]):t._e()]}}])})],1),t._v(" "),a("el-pagination",{attrs:{"current-page":t.pageIndex,"page-sizes":[10,20,50,100],"page-size":t.pageSize,udit:"",total:t.totalPage,layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":t.sizeChangeHandle,"current-change":t.currentChangeHandle}}),t._v(" "),t.addOrUpdateVisible?a("add-or-update",{ref:"addOrUpdate",on:{refreshDataList:t.getDataList}}):t._e(),t._v(" "),t.detailVisible?a("train-program-detail-list",{ref:"trainProgramDetailList"}):t._e(),t._v(" "),t.projectAuditVisible?a("project-audit",{ref:"projectAudit",on:{refreshDataList:t.getDataList}}):t._e()],1)},staticRenderFns:[]},d=a("46Yf")(l,s,!1,null,null,null);e.default=d.exports}});