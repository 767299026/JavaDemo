<template>
  <el-dialog
      :title="!dataForm.id ? '新增' : '修改'"
      :close-on-click-modal="false"
      :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="80px">
      <el-form-item label="员工部门" prop="departmentId">
        <el-input v-model="dataForm.departmentId" placeholder="员工部门"></el-input>
      </el-form-item>
      <el-form-item label="员工角色" prop="roleId">
        <el-input v-model="dataForm.roleId" placeholder="员工角色"></el-input>
      </el-form-item>
      <el-form-item label="员工姓名" prop="name">
        <el-input v-model="dataForm.name" placeholder="员工姓名"></el-input>
      </el-form-item>
      <el-form-item label="员工职称" prop="positionCode">
        <el-input v-model="dataForm.positionCode" placeholder="员工职称"></el-input>
      </el-form-item>
      <el-form-item label="员工入职时间" prop="entryTime">
        <el-input v-model="dataForm.entryTime" placeholder="员工入职时间"></el-input>
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
  data() {
    return {
      visible: false,
      dataForm: {
        id: 0,
        departmentId: '',
        roleId: '',
        name: '',
        positionCode: '',
        entryTime: ''
      },
      dataRule: {
        departmentId: [
          {required: true, message: '员工部门不能为空', trigger: 'blur'}
        ],
        roleId: [
          {required: true, message: '员工角色不能为空', trigger: 'blur'}
        ],
        name: [
          {required: true, message: '员工姓名不能为空', trigger: 'blur'}
        ],
        positionCode: [
          {required: true, message: '员工职称不能为空', trigger: 'blur'}
        ],
        entryTime: [
          {required: true, message: '员工入职时间不能为空', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    init(id) {
      this.dataForm.id = id || 0
      this.visible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        if (this.dataForm.id) {
          this.$http({
            url: this.$http.adornUrl(`/product/employee/info/${this.dataForm.id}`),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.dataForm.departmentId = data.employee.departmentId
              this.dataForm.roleId = data.employee.roleId
              this.dataForm.name = data.employee.name
              this.dataForm.positionCode = data.employee.positionCode
              this.dataForm.entryTime = data.employee.entryTime
            }
          })
        }
      })
    },
    // 表单提交
    dataFormSubmit() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(`/product/employee/${!this.dataForm.id ? 'save' : 'update'}`),
            method: 'post',
            data: this.$http.adornData({
              'id': this.dataForm.id || undefined,
              'departmentId': this.dataForm.departmentId,
              'roleId': this.dataForm.roleId,
              'name': this.dataForm.name,
              'positionCode': this.dataForm.positionCode,
              'entryTime': this.dataForm.entryTime
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
