<template>
  <div class="list">
    <el-card class="operate-container" shadow="never">
        <el-form :inline="true">
          <el-form-item label="调查船">
            <el-input style="width:  203px" v-model="searchObj.shipName" size="mini"  placeholder="请输入调查船"></el-input>
          </el-form-item>
          <el-form-item label="航次">
            <el-input style="width:  203px" v-model="searchObj.voyageNo" size="mini"  placeholder="请输入航次"></el-input>
          </el-form-item>
          <el-form-item label="海域">
            <el-input style="width:  203px" v-model="searchObj.seaArea" size="mini"  placeholder="请输入海域"></el-input>
          </el-form-item>
          <el-button style="margin-top: 6px;" type="primary" size="mini" icon="el-icon-search" @click="fetchData()">查询</el-button>
          <el-button type="default" size="mini"  @click="resetData()">清空</el-button>
        </el-form>
      </el-card>
      <div style="margin-top: 10px;"/>

      <!-- 工具条 -->
      <el-card class="operate-container" shadow="never">
        <i class="el-icon-tickets" style="margin-top: 5px"></i>
        <span style="margin-top: 5px">数据列表</span>
        <span style="margin-left: 5px;"></span>
        <!-- <el-button class="btn-add" size="mini" @click="addRecord()">添加</el-button> -->
        <el-button class="btn-add" size="mini" @click="removeRows()" style="margin: 0 10px;">批量删除</el-button>
          <el-button type="success" icon="el-icon-upload2" size="mini"  @click="handleImport()">导入excel</el-button>
          <el-button type="success" icon="el-icon-download" size="mini"  @click="handleExport()" :loading="exportLoading">导出excel</el-button>
      </el-card>
    
       <!-- banner列表 -->
       <el-table
        v-loading="listLoading"
        :data="list"
        stripe
        border
        style="width: 100%;margin-top: 10px;"
        @selection-change="handleSelectionChange">
  
        <el-table-column
          type="selection"
          width="55" />
        <el-table-column label="序号" width="70" prop="recordId" align="center"/>
        <el-table-column prop="sampleNo" label="样品编号" align="center"/>
        <el-table-column prop="shipName" label="调查船" align="center"/>
        <el-table-column prop="voyageNo" label="航次" align="center"/>
        <el-table-column  label="详细位置" align="center">
            <el-table-column prop="seaArea" label="海域" align="center" />
            <el-table-column prop="station" label="站位" align="center"/>
            <el-table-column prop="xcoordinate" label="X坐标" align="center" />
            <el-table-column prop="ycoordinate" label="Y坐标" align="center" />
            <el-table-column prop="detailAddress" label="详细地址" align="center" />
        </el-table-column>
        <el-table-column prop="endDepth" label="结束深度（m）" align="center" />
        <el-table-column prop="heartLength" label="心长（m）" align="center" />
        <el-table-column prop="storageLocation" label="存放位置" align="center" />
        <el-table-column prop="preservationCondition" label="保存状况" align="center" />
        <el-table-column prop="remark" label="备注" align="center" />
       
        <el-table-column label="操作" width="200" align="center">
          <template slot-scope="scope">
            <el-button type="text" size="mini" @click="updateRecord(scope.row.recordId)">修改</el-button>
            <el-button type="text" size="mini" @click="removeDataById(scope.row.recordId)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>


      <!-- 添加/修改记录对话框 -->
      <el-dialog :title="title" :visible.sync="dialogVisible" width="40%" :before-close="resetrecordInfoData">
      <el-form ref="recordInfo" :model="recordInfo" :rules="rules" label-width="80px">
       
        <el-form-item label="样品编号"  prop="sampleNo">
          <el-input v-model="recordInfo.sampleNo" placeholder="请输入样品编号"></el-input>
        </el-form-item>
       
        <el-form-item label="调查船"  prop="shipName">
          <el-input v-model="recordInfo.shipName"  type="textarea" autosize placeholder="请输入调查船"></el-input>
        </el-form-item>

        <el-form-item label="航次" prop="voyageNo">
            <el-input v-model="recordInfo.voyageNo" placeholder="请输入航次"></el-input>
        </el-form-item>

        <el-form-item label="海域"  prop="seaArea">
          <el-input v-model="recordInfo.seaArea" placeholder="请输入海域"></el-input>
        </el-form-item>

        <el-form-item label="站位"  prop="station" >
          <el-input v-model="recordInfo.station"   style="width: 120px;" placeholder="请输入站位"></el-input>
        </el-form-item>

          <el-form-item label="X坐标" prop="xcoordinate">
            <el-input v-model="recordInfo.xcoordinate" @input="handleInput('xcoordinate')"   type="textarea" autosize placeholder="请输入X坐标，且只能是整数或小数"></el-input>
          </el-form-item>
        <el-form-item label="Y坐标" prop="ycoordinate">
            <el-input v-model="recordInfo.ycoordinate" @input="handleInput('ycoordinate')" type="textarea" autosize placeholder="请输入Y坐标，且只能是整数或小数"></el-input>
        </el-form-item>

        <el-form-item label="详细地址" prop="detailAddress">
            <el-input v-model="recordInfo.detailAddress" type="textarea" autosize placeholder="请输入详细地址"></el-input>
        </el-form-item>

        <el-form-item label="结束深度" prop="endDepth">
            <el-input v-model="recordInfo.endDepth" type="textarea" @input="handleInput('endDepth')" autosize placeholder="请输入结束深度，且只能是整数或小数"></el-input>
        </el-form-item>

        <el-form-item label="心长" prop="heartLength">
            <el-input v-model="recordInfo.heartLength" type="textarea" @input="handleInput('heartLength')" autosize placeholder="请输入心长，且只能是整数或小数"></el-input>
        </el-form-item>
        
        <el-form-item label="存放位置" prop="storageLocation">
            <el-input v-model="recordInfo.storageLocation" type="textarea" autosize placeholder="请输入存放位置"></el-input>
        </el-form-item>
        
        <el-form-item label="保存状况" prop="preservationCondition">
            <el-input v-model="recordInfo.preservationCondition" type="textarea" @input="handleInput('preservationCondition')"  autosize placeholder="请输入保存状况，且只能是整数或小数"></el-input>
        </el-form-item>
        
        <el-form-item label="备注" prop="remark">
            <el-input v-model="recordInfo.remark" type="textarea" autosize placeholder="请输入备注"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="saveOrUpdate()">{{buttonVal}}</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>


       <!-- 导入操作对话框 -->
    <el-dialog title="导入样本数据" :visible.sync="uploadDialogFormVisible"  width="400px"
      append-to-body>
      <el-upload
        ref="uploadRecord"
        :limit="1"
        accept=".xlsx, .xls"
        :action="url"
        :disabled="uploadRecord.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :on-error="handleFileError"
        :before-remove="handleFileBeforeRemove"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip text-center" slot="tip">
          <span>仅允许导入xls、xlsx格式文件。</span>
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="close()">取 消</el-button>
      </div>
    </el-dialog>


      <!-- 分页组件 -->
      <el-pagination
        :current-page="page"
        :total="total"
        :page-size="limitNumber"
        :page-sizes="[20, 30, 40, 50, 100]"
        style="padding: 30px 0; text-align: center;"
        layout="sizes, prev, pager, next, jumper, ->, total, slot"
        @current-change="fetchData"
        @size-change="changeSize"
      />
   </div>
 
</template>

<script>
import api from '@/api/record'
export default {
    data() {
      return {
        num: 0,
        listLoading: true, // 数据是否正在加载
        list: [], // banner列表
        total: 0, // 数据库中的总记录数
        page: 1, // 默认页码
        limitNumber: 20, // 每页记录数
        searchObj: {
            shipName: "",
            voyageNo: "",
            seaArea:""
        }, // 查询表单对象
        exportLoading:false, //导出遮罩层
        dateVal:[],
        recordInfo: {},
        uploadDialogFormVisible: false,
        multipleSelection: [], // 批量选择中选择的记录列表
        dialogVisible: false, // 是否显示弹窗
        title: '',
        buttonVal: '',
       
        // 用户导入参数
        uploadRecord: {
          
          open: false,
          // 弹出层标题（用户导入）
          title: "",
          // 是否禁用上传
          isUploading: false,
          // 是否更新已经存在的用户数据
          updateSupport: 0,
        },

        rules:{
            sampleNo:[
                { required: true, message: '请输入样品编号', trigger: 'blur' }
            ],
            shipName:[
                { required: true, message: '请输入调查船', trigger: 'blur' }
            ],
            voyageNo:[
                { required: true, message: '请输入航次', trigger: 'blur' }
            ],
            seaArea:[
                { required: true, message: '请输入海域', trigger: 'blur' }
            ],
            station:[
                { required: true, message: '请输入站位', trigger: 'blur' }
            ],
            xcoordinate:[
                { required: true, message: '请输入X坐标', trigger: 'blur' }
            ],
            ycoordinate:[
                { required: true, message: '请输入Y坐标', trigger: 'blur' }
            ],
            endDepth:[
                { required: true, message: '请输入结束深度', trigger: 'blur' }
            ],
            heartLength:[
                { required: true, message: '请输入心长', trigger: 'blur' }
            ],
            storageLocation:[
                { required: true, message: '请输入存放位置', trigger: 'blur' }
            ],
            preservationCondition:[
                { required: true, message: '请输入保存状况', trigger: 'blur' }
            ],
        },

        url: process.env.BASE_API + "/record/import"

      }
    },
    // 生命周期函数：内存准备完毕，页面尚未渲染
    created() {
      console.log('list created......')
      
      this.fetchData()
    },


    methods: {
        // 加载banner列表数据
        fetchData(page = 1) {
            console.log('翻页。。。' + page)
            // 异步获取远程数据（ajax）
            this.page = page
    
            api.getPageList(this.page, this.limitNumber, this.searchObj).then(
            response => {
                debugger
                this.list = response.data.records
                this.total = response.data.total
    
                // 数据加载并绑定成功
                this.listLoading = false
            }
            )
        },
    
        // 重置查询表单
        resetData() {
            console.log('重置查询表单')
            this.searchObj = {}
            this.fetchData()
        },

        // 当页码发生改变的时候
        changeSize(size) {
            console.log(size)
            this.limitNumber = size
            this.fetchData(1)
        },

        
      // 根据id删除数据
      removeDataById(id) {
        // debugger
        this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => { // promise
          // 点击确定，远程调用ajax
          return api.removeById(id)
        }).then((response) => {
          this.fetchData(this.list.length===1 ? this.page-1 : this.page)
          if (response.code) {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
          }
        })
      },
  
      // 当表格复选框选项发生变化的时候触发
      handleSelectionChange(selection) {
        console.log('handleSelectionChange......')
        console.log(selection)
        this.multipleSelection = selection
      },
  
      // 批量删除
      removeRows() {
        console.log('removeRows......')
  
        if (this.multipleSelection.length === 0) {
          this.$message({
            type: 'warning',
            message: '请选择要删除的记录!'
          })
          return
        }
  
        this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => { // promise
          // 点击确定，远程调用ajax
          // 遍历selection，将id取出放入id列表
          var idList = []
          this.multipleSelection.forEach(item => {
            idList.push(item.recordId)
          })
          // 调用api
          return api.removeRows(idList)
        }).then((response) => {
          this.fetchData(this.page)
          if (response.code) {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
          }
        })
      },

      /** 导入按钮操作 */
      handleImport(){
        this.uploadDialogFormVisible = true; 
      },

      //导出
      handleExport(){
        api.exportRecord().then(response => {             
              this.$download.excel(response.data, '样本数据.xlsx')
              this.$message.success('导出成功')
              }).catch((e) => {
                this.$message.error(e);
                // this.$message.error('样本数据下载失败，请重试');
              })
      },
    
      // 文件上传中处理
        handleFileUploadProgress(event, file, fileList) {
        // console.log(event, file, fileList);
        this.uploadRecord.isUploading = true;
        },

        //上传文件前处理
        handleFileBeforeRemove(file, fileList){
        return this.$confirm(`确定移除 ${ file.name }？`);
        },


        //上传文件失败处理
        handleFileError(err, file, fileList){

        console.log(err, file, fileList);
        // 处理文件上传失败，清空文件列表
        this.$refs.uploadRecord.clearFiles();
        this.uploadDialogFormVisible = false
        this.fetchData()
        this.$forceUpdate();
        },

        // 文件上传成功处理
        handleFileSuccess(response, file, fileList) {
        if (response.code !== 200) {
            this.$message.err(response.message);
            return;
        }
        this.$message.success("导入成功");
        this.$refs.uploadRecord.clearFiles();
        this.uploadDialogFormVisible = false
        this.fetchData()
        this.$forceUpdate(); 
        },
        // 提交上传文件
        submitFileForm() {
        console.log(this.$refs.uploadRecord);

        //获取上传的路径
        const url = this.$refs.uploadRecord.action
        
        this.$refs.uploadRecord.submit();
        
        this.uploadDialogFormVisible = false;
        },

        //添加题目信息
        addRecord() {
        // this.getQuestionGroups()

        this.title = '添加样品数据信息';
        this.dialogVisible = true;
        this.buttonVal = '立即添加'
        },


        handleInput(field) {
            const value = this.recordInfo[field];
            const regex = /^[0-9]*\.?[0-9]*$/;

            if (regex.test(value)) {
                this.recordInfo[field] = value;
            } else {
                this.recordInfo[field] = '';
            }
        },
        
        //更新题目信息
        updateRecord(id){
       
            this.title = '修改样品数据信息';
            this.buttonVal = '立即修改'
            this.dialogVisible = true;
            this.recordInfo = {}
            
            api.getById(id).then(response => {
                const list = response.data
                this.recordInfo = list
            })
        },

        resetrecordInfoData(){
            this.recordInfo = {}
            this.dialogVisible = false;
            this.title = '';
            this.buttonVal = ''
            this.questionInfo = {}
        },

        saveOrUpdate(){


            this.$refs.recordInfo.validate((valid) => {

                if (valid) {
                    if (this.title === '添加样品数据信息') {
                        console.log("添加的内容为",this.recordInfo);
                        
                        api.save(this.recordInfo).then((res) => {
                        if (res.code === 200) {
                            this.$message.success(res.message)
                            this.dialogVisible = false;
                            this.title = '';
                            this.buttonVal = ''
                            this.recordInfo = {}
                            this.fetchData();
                        }
                        });
                    }

                if (this.title === "修改样品数据信息") {

                    console.log("修改的信息",this.recordInfo)
                    
                    api.updateById(this.recordInfo).then((res) => {

                    if (res.code === 200) {
                        this.$message.success(res.message)
                        this.dialogVisible = false;
                        this.title = '';
                        this.buttonVal = ''
                        this.recordInfo = {}
                        this.fetchData();
                    }
                    })      
                }
            }
     
            });      

        },
    
    }
  
}
</script>

<style>

</style>