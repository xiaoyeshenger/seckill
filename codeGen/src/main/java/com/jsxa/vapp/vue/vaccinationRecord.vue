<template>
  <div>
    <!--Layout布局-->
    <el-row>
      <el-col :span="24">
        <el-row :gutter="20">
          <el-col :span="6">
            <!--搜索区域-->
            <el-input
              placeholder="请输入内容"
              v-model="queryInfo.searchKey"
              clearable
              @clear="getListPage"
            >
              <el-button
                slot="append"
                icon="el-icon-search"
                @click="getListPage"
              ></el-button>
            </el-input>
          </el-col>
          <el-col :span="2.5">
            <el-button type="primary" @click="addDialogVisible = true">添加</el-button>
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="24">
        <!--表格-->
        <el-table
          :data="pageList"
          border
          stripe
        >
          <el-table-column type="index" label="序号" width="55" align="center"></el-table-column>
                  <el-table-column prop="recordType" label="记录产生来源(系统产生,电脑补录,小程序端自行补录)" align="center"></el-table-column>
          <el-table-column prop="vaild" label="是否有效(如果是接种者通过小程序端自行补录的接种记录，需要上传接种截图(天府健康码)并由工作人员审核)" align="center"></el-table-column>
          <el-table-column prop="personId" label="接种人员id" align="center"></el-table-column>
          <el-table-column prop="personName" label="接种人员姓名" align="center"></el-table-column>
          <el-table-column prop="sex" label="性别" align="center"></el-table-column>
          <el-table-column prop="age" label="年龄" align="center"></el-table-column>
          <el-table-column prop="mobile" label="联系电话" align="center"></el-table-column>
          <el-table-column prop="idNumber" label="身份证号码" align="center"></el-table-column>
          <el-table-column prop="openId" label="微信小程序openId" align="center"></el-table-column>
          <el-table-column prop="siteId" label="接种地点id" align="center"></el-table-column>
          <el-table-column prop="siteName" label="接种地点名称" align="center"></el-table-column>
          <el-table-column prop="recordStatus" label="接种记录状态(待接种,已入场,已接种,已作废,已取消)" align="center">
            <template slot-scope="scope">
              <span class="tag-js" v-if="scope.row.recordStatus">
                  {{scope.row.recordStatus?'启用':'停用'}}
              </span>
              <span class="tag-js" v-if="!scope.row.recordStatus">
                  {{scope.row.recordStatus?'启用':'停用'}}
              </span>
            </template>
          </el-table-column>
          <el-table-column prop="vaccineId" label="疫苗id" align="center"></el-table-column>
          <el-table-column prop="vaccineName" label="疫苗名称" align="center"></el-table-column>
          <el-table-column prop="vaccineBatch" label="疫苗批次" align="center"></el-table-column>
          <el-table-column prop="manufacturer" label="疫苗生产厂家" align="center"></el-table-column>
          <el-table-column prop="dose" label="接种剂次" align="center"></el-table-column>
          <el-table-column prop="doseUnit" label="接种单位" align="center"></el-table-column>
          <el-table-column prop="appointmentTime" label="预约成功时间" align="center">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.appointmentTime) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="timePeriod" label="接种时间段" align="center"></el-table-column>
          <el-table-column prop="timePeriodName" label="接种时间段名称" align="center"></el-table-column>
          <el-table-column prop="doseTime" label="实际接种时间" align="center">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.doseTime) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="imageUrl" label="接种报告图片" align="center"></el-table-column>
          <el-table-column prop="city" label="市州" align="center"></el-table-column>
          <el-table-column prop="county" label="区县" align="center"></el-table-column>
          <el-table-column prop="town" label="乡镇" align="center"></el-table-column>
          <el-table-column prop="msg" label="未接种原因" align="center"></el-table-column>
          <el-table-column label="操作">
            <!-- 作用域插槽 -->
            <template slot-scope="scope">
              <!--修改按钮-->
              <el-button
                type="primary"
                size="mini"
                icon="el-icon-edit"
                @click="showEditDialog(scope.row)"
              ></el-button>
              <!--删除按钮-->
              <el-button
                type="danger"
                size="mini"
                icon="el-icon-delete"
                @click="deleteById(scope.row.id)"
              ></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <el-row>
      <!--分页区域-->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pageNum"
        :page-sizes="[1, 2, 5, 10]"
        :page-size="queryInfo.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </el-row>
    <!--添加对象的对话框-->
    <el-dialog title="添加" :visible.sync="addDialogVisible" width="30%" @close="addDialogClosed">
      <!--内容主体区域-->
      <el-form :model="addForm" label-width="110px">
        <el-form-item label="记录产生来源(系统产生,电脑补录,小程序端自行补录)" prop="recordType">
          <el-input v-model="addForm.recordType"></el-input>
        </el-form-item>
        <el-form-item label="是否有效(如果是接种者通过小程序端自行补录的接种记录，需要上传接种截图(天府健康码)并由工作人员审核)" prop="vaild">
          <el-input v-model="addForm.vaild"></el-input>
        </el-form-item>
        <el-form-item label="接种人员id" prop="personId">
          <el-input v-model="addForm.personId"></el-input>
        </el-form-item>
        <el-form-item label="接种人员姓名" prop="personName">
          <el-input v-model="addForm.personName"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-input v-model="addForm.sex"></el-input>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="addForm.age"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" prop="mobile">
          <el-input v-model="addForm.mobile"></el-input>
        </el-form-item>
        <el-form-item label="身份证号码" prop="idNumber">
          <el-input v-model="addForm.idNumber"></el-input>
        </el-form-item>
        <el-form-item label="微信小程序openId" prop="openId">
          <el-input v-model="addForm.openId"></el-input>
        </el-form-item>
        <el-form-item label="接种地点id" prop="siteId">
          <el-input v-model="addForm.siteId"></el-input>
        </el-form-item>
        <el-form-item label="接种地点名称" prop="siteName">
          <el-input v-model="addForm.siteName"></el-input>
        </el-form-item>
        <el-form-item label="接种记录状态(待接种,已入场,已接种,已作废,已取消)" prop="recordStatus">
          <el-radio-group v-model="addForm.recordStatus">
            <el-radio :label="1">启用</el-radio>
            <el-radio :label="0">停用</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="疫苗id" prop="vaccineId">
          <el-input v-model="addForm.vaccineId"></el-input>
        </el-form-item>
        <el-form-item label="疫苗名称" prop="vaccineName">
          <el-input v-model="addForm.vaccineName"></el-input>
        </el-form-item>
        <el-form-item label="疫苗批次" prop="vaccineBatch">
          <el-input v-model="addForm.vaccineBatch"></el-input>
        </el-form-item>
        <el-form-item label="疫苗生产厂家" prop="manufacturer">
          <el-input v-model="addForm.manufacturer"></el-input>
        </el-form-item>
        <el-form-item label="接种剂次" prop="dose">
          <el-input v-model="addForm.dose"></el-input>
        </el-form-item>
        <el-form-item label="接种单位" prop="doseUnit">
          <el-input v-model="addForm.doseUnit"></el-input>
        </el-form-item>
        <el-form-item label="预约成功时间" prop="appointmentTime">
          <el-date-picker v-model="addForm.预约成功时间" type="date"
            value-format="timestamp" placeholder="选择日期" style="width: 420px">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="接种时间段" prop="timePeriod">
          <el-input v-model="addForm.timePeriod"></el-input>
        </el-form-item>
        <el-form-item label="接种时间段名称" prop="timePeriodName">
          <el-input v-model="addForm.timePeriodName"></el-input>
        </el-form-item>
        <el-form-item label="实际接种时间" prop="doseTime">
          <el-date-picker v-model="addForm.实际接种时间" type="date"
            value-format="timestamp" placeholder="选择日期" style="width: 420px">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="接种报告图片" prop="imageUrl">
          <el-input v-model="addForm.imageUrl"></el-input>
        </el-form-item>
        <el-form-item label="市州" prop="city">
          <el-input v-model="addForm.city"></el-input>
        </el-form-item>
        <el-form-item label="区县" prop="county">
          <el-input v-model="addForm.county"></el-input>
        </el-form-item>
        <el-form-item label="乡镇" prop="town">
          <el-input v-model="addForm.town"></el-input>
        </el-form-item>
        <el-form-item label="未接种原因" prop="msg">
          <el-input v-model="addForm.msg"></el-input>
        </el-form-item>
      </el-form>
      <!--底部按钮区域-->
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addObj">确 定</el-button>
      </span>
    </el-dialog>
    <!--修改用户的对话框-->
    <el-dialog title="修改" :visible.sync="editDialogVisible" width="30%">
      <!--内容主体区域-->
      <el-form :model="editForm" label-width="110px">
        <el-form-item label="记录产生来源(系统产生,电脑补录,小程序端自行补录)" prop="recordType">
          <el-input v-model="addForm.recordType"></el-input>
        </el-form-item>
        <el-form-item label="是否有效(如果是接种者通过小程序端自行补录的接种记录，需要上传接种截图(天府健康码)并由工作人员审核)" prop="vaild">
          <el-input v-model="addForm.vaild"></el-input>
        </el-form-item>
        <el-form-item label="接种人员id" prop="personId">
          <el-input v-model="addForm.personId"></el-input>
        </el-form-item>
        <el-form-item label="接种人员姓名" prop="personName">
          <el-input v-model="addForm.personName"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-input v-model="addForm.sex"></el-input>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="addForm.age"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" prop="mobile">
          <el-input v-model="addForm.mobile"></el-input>
        </el-form-item>
        <el-form-item label="身份证号码" prop="idNumber">
          <el-input v-model="addForm.idNumber"></el-input>
        </el-form-item>
        <el-form-item label="微信小程序openId" prop="openId">
          <el-input v-model="addForm.openId"></el-input>
        </el-form-item>
        <el-form-item label="接种地点id" prop="siteId">
          <el-input v-model="addForm.siteId"></el-input>
        </el-form-item>
        <el-form-item label="接种地点名称" prop="siteName">
          <el-input v-model="addForm.siteName"></el-input>
        </el-form-item>
        <el-form-item label="接种记录状态(待接种,已入场,已接种,已作废,已取消)" prop="recordStatus">
          <el-radio-group v-model="addForm.recordStatus">
            <el-radio :label="1">启用</el-radio>
            <el-radio :label="0">停用</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="疫苗id" prop="vaccineId">
          <el-input v-model="addForm.vaccineId"></el-input>
        </el-form-item>
        <el-form-item label="疫苗名称" prop="vaccineName">
          <el-input v-model="addForm.vaccineName"></el-input>
        </el-form-item>
        <el-form-item label="疫苗批次" prop="vaccineBatch">
          <el-input v-model="addForm.vaccineBatch"></el-input>
        </el-form-item>
        <el-form-item label="疫苗生产厂家" prop="manufacturer">
          <el-input v-model="addForm.manufacturer"></el-input>
        </el-form-item>
        <el-form-item label="接种剂次" prop="dose">
          <el-input v-model="addForm.dose"></el-input>
        </el-form-item>
        <el-form-item label="接种单位" prop="doseUnit">
          <el-input v-model="addForm.doseUnit"></el-input>
        </el-form-item>
        <el-form-item label="预约成功时间" prop="appointmentTime">
          <el-date-picker v-model="addForm.预约成功时间" type="date"
                          value-format="timestamp" placeholder="选择日期" style="width: 420px">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="接种时间段" prop="timePeriod">
          <el-input v-model="addForm.timePeriod"></el-input>
        </el-form-item>
        <el-form-item label="接种时间段名称" prop="timePeriodName">
          <el-input v-model="addForm.timePeriodName"></el-input>
        </el-form-item>
        <el-form-item label="实际接种时间" prop="doseTime">
          <el-date-picker v-model="addForm.实际接种时间" type="date"
                          value-format="timestamp" placeholder="选择日期" style="width: 420px">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="接种报告图片" prop="imageUrl">
          <el-input v-model="addForm.imageUrl"></el-input>
        </el-form-item>
        <el-form-item label="市州" prop="city">
          <el-input v-model="addForm.city"></el-input>
        </el-form-item>
        <el-form-item label="区县" prop="county">
          <el-input v-model="addForm.county"></el-input>
        </el-form-item>
        <el-form-item label="乡镇" prop="town">
          <el-input v-model="addForm.town"></el-input>
        </el-form-item>
        <el-form-item label="未接种原因" prop="msg">
          <el-input v-model="addForm.msg"></el-input>
        </el-form-item>
        <el-form-item label="创建时间" prop="createTime">
          <el-date-picker v-model="addForm.创建时间" type="date"
                          value-format="timestamp" placeholder="选择日期" style="width: 420px">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <!--底部按钮区域-->
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateObj">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { parseTime } from '@/utils/ruoyi'
import { add, deleteById, update, getById,listPage} from "@/api/vaccinationRecord";
export default {
  data() {
    return {
      pageList: [], // 列表
      total: 0, // 总数
      // 获取列表的参数对象
      queryInfo: {
        searchKey: "", // 查询参数
        pageNum: 1, // 当前页码
        pageSize: 10, //页面大小
      },
      addDialogVisible: false, //控制-添加对象对话框-是否一进页面就显示
      addForm: {
        recordType: "",
        vaild: "",
        personId: "",
        personName: "",
        sex: "",
        age: "",
        mobile: "",
        idNumber: "",
        openId: "",
        siteId: "",
        siteName: "",
        recordStatus: "",
        vaccineId: "",
        vaccineName: "",
        vaccineBatch: "",
        manufacturer: "",
        dose: "",
        doseUnit: "",
        appointmentTime: "",
        timePeriod: "",
        timePeriodName: "",
        doseTime: "",
        imageUrl: "",
        city: "",
        county: "",
        town: "",
        msg: "",
      },
      editDialogVisible: false, // 控制-修改对象对话框-是否一进页面显示
      editForm: {
        id: "",
        recordType: "",
        vaild: "",
        personId: "",
        personName: "",
        sex: "",
        age: "",
        mobile: "",
        idNumber: "",
        openId: "",
        siteId: "",
        siteName: "",
        recordStatus: "",
        vaccineId: "",
        vaccineName: "",
        vaccineBatch: "",
        manufacturer: "",
        dose: "",
        doseUnit: "",
        appointmentTime: "",
        timePeriod: "",
        timePeriodName: "",
        doseTime: "",
        imageUrl: "",
        city: "",
        county: "",
        town: "",
        msg: "",
      },
      multipleSelection: [],
      ids: [],
      fileList: [],
      typelist:[{id:1,value:"吸烟"},{id:2,value:"安全帽"},{id:3,value:"人脸"}]
    };
  },
  created() {
    // 生命周期函数
    this.getListPage();
  },
  methods: {
    //默认显示时分秒，此处传入pattern {y}-{m}-{d}即只显示年月日
    parseTime(timestamp) {
      return parseTime(timestamp,"{y}-{m}-{d}");
    },
    getListPage() {
      listPage(this.queryInfo)
        .then((res) => {
          if (res.data.code === 200) {
            this.pageList = res.data.data.list;
            this.total = res.data.data.total;
          } else {
            this.$message.error(res.data.msg);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 监听 pageSize 改变的事件
    handleSizeChange(newSize) {
      // console.log(newSize)
      this.queryInfo.pageSize = newSize;
      // 重新发起请求列表
      this.getListPage();
    },
    // 监听 当前页码值 改变的事件
    handleCurrentChange(newPage) {
      // console.log(newPage)
      this.queryInfo.pageNum = newPage;
      // 重新发起请求列表
      this.getListPage();
    },
    //添加对象
    addObj() {
      add(this.addForm)
        .then((res) => {
          if (res.data.code === 200) {
            this.addDialogVisible = false;
            this.getListPage();
            this.$message({
              message: "添加成功",
              type: "success",
            });
          } else {
            this.$message.error("添加失败");
          }
        })
        .catch((err) => {
          this.$message.error("添加异常");
          console.log(err);
        });
    },

    // 监听添加对话框的关闭事件
    addDialogClosed() {
      // 表单内容重置为空
      this.$refs.addFormRef.resetFields();
    },

    // 监听修改状态
    showEditDialog(obj) {
      this.editDialogVisible = true;
      //console.log("请求后接收到的响应结果:"+obj);
      this.editForm = obj;
    },
    //修改
    updateObj() {
      update(this.editForm)
        .then((res) => {
          if (res.data.code === 200) {
            this.editDialogVisible = false;
            this.getListPage();
            this.$message({
              message: "修改成功",
              type: "success",
            });
          } else {
            this.$message.error("修改失败");
          }
        })
        .catch((err) => {
          this.$message.error("修改异常");
          console.loge(err);
        });
    },
    // 根据ID删除对应的信息
    async deleteById(id) {
      // 弹框 询问用户是否删除
      const confirmResult = await this.$confirm(
        "此操作将永久删除该数据, 是否继续?",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      ).catch((err) => err);
      // 如果用户确认删除，则返回值为字符串 confirm
      // 如果用户取消删除，则返回值为字符串 cancel
      // console.log(confirmResult)
      if (confirmResult == "confirm") {
        //删除
        deleteById(id)
          .then((res) => {
            if (res.data.code === 200) {
              this.getListPage();
              this.$message({
                message: "删除成功",
                type: "success",
              });
            } else {
              this.$message.error("删除失败");
            }
          })
          .catch((err) => {
            this.$message.error("删除异常");
            console.log(err);
          });
      }
    },
  },
};
</script>

<style>
.el-row {
  margin-bottom: 20px;
}
.el-col {
  border-radius: 4px;
}
.el-card {
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1) !important;
  height: 60pt;
}
</style>
