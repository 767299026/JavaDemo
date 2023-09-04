<template>
  <el-dialog
      :title="!dataForm.id ? '新增' : '修改'"
      :close-on-click-modal="false"
      :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="80px">
      <el-form-item label="职级名称" prop="positionName">
        <el-input v-model="dataForm.positionName" placeholder="职级名称"></el-input>
      </el-form-item>
      <el-form-item label="职级编号" prop="positionCode">
        <el-input v-model="dataForm.positionCode" placeholder="职级编号"></el-input>
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
        positionName: '',
        positionCode: ''
      },
      dataRule: {
        positionName: [
          {required: true, message: '职级名称不能为空', trigger: 'blur'}
        ],
        positionCode: [
          {required: true, message: '职级编号不能为空', trigger: 'blur'}
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
            url: this.$http.adornUrl(`/product/position/info/${this.dataForm.id}`),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.dataForm.positionName = data.position.positionName
              this.dataForm.positionCode = data.position.positionCode
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
            url: this.$http.adornUrl(`/product/position/${!this.dataForm.id ? 'save' : 'update'}`),
            method: 'post',
            data: this.$http.adornData({
              'id': this.dataForm.id || undefined,
              'positionName': this.dataForm.positionName,
              'positionCode': this.dataForm.positionCode
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
