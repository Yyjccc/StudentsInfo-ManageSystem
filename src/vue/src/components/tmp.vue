<template>
    <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="id" label="ID" width="60"></el-table-column>
        <el-table-column prop="studentId" label="学号" width="140">
            <template v-if="editMode" #default="{ row }">
                <!-- 编辑模式，显示输入框 -->
                <el-input v-model="row.studentId" />
            </template>
            <template v-else #default="{ row }">
                <!-- 非编辑模式，显示文本内容 -->
                {{ row.studentId }}
            </template>
        </el-table-column>
        <el-table-column prop="name" label="姓名" width="100">
            <template v-if="editMode" #default="{ row }">
                <!-- 编辑模式，显示输入框 -->
                <el-input v-model="row.name" />
            </template>
            <template v-else #default="{ row }">
                <!-- 非编辑模式，显示文本内容 -->
                {{ row.name }}
            </template>
        </el-table-column>
        <el-table-column prop="sex" label="性别" width="50">
            <template v-if="editMode" #default="{ row }">
                <!-- 编辑模式，显示输入框 -->
                <el-input v-model="row.sex" />
            </template>
            <template v-else #default="{ row }">
                <!-- 非编辑模式，显示文本内容 -->
                {{ row.sex }}
            </template>
        </el-table-column>
        <el-table-column prop="classId" label="班级" width="180">
            <template v-if="editMode" #default="{ row }">
                <!-- 编辑模式，显示输入框 -->
                <el-input v-model="row.classId" />
            </template>
            <template v-else #default="{ row }">
                <!-- 非编辑模式，显示文本内容 -->
                {{ row.classId }}
            </template>
        </el-table-column>
        <el-table-column prop="department" label="学院" width="200">
            <template v-if="editMode" #default="{ row }">
                <!-- 编辑模式，显示输入框 -->
                <el-input v-model="row.department" />
            </template>
            <template v-else #default="{ row }">
                <!-- 非编辑模式，显示文本内容 -->
                {{ row.department }}
            </template>
        </el-table-column>
        <el-table-column prop="birthday" label="出生日期" width="200">
            <template v-if="editMode" #default="{ row }">
                <!-- 编辑模式，显示输入框 -->
                <el-input v-model="row.birthday" />
            </template>
            <template v-else #default="{ row }">
                <!-- 非编辑模式，显示文本内容 -->
                {{ row.birthday }}
            </template>
        </el-table-column>
        <el-table-column prop="nativePlace" label="籍贯" width="150">
            <template v-if="editMode" #default="{ row }">
                <!-- 编辑模式，显示输入框 -->
                <el-input v-model="row.nativePlace" />
            </template>
            <template v-else #default="{ row }">
                <!-- 非编辑模式，显示文本内容 -->
                {{ row.nativePlace }}
            </template>
        </el-table-column>
        <el-table-column fix="select" label="选项">
            <template #default="{ row }">
                <el-button v-if="!editMode" type="primary" size="small" @click="toggleEditMode(row)">编辑</el-button>
                <el-button v-else type="success" size="small" @click="saveChanges(row)">保存</el-button>
                <el-button v-else type="danger" size="small" @click="cancelEdit(row)">取消</el-button>
                <el-button v-if="!editMode" type="primary" size="small">删除</el-button>

            </template>
        </el-table-column>
    </el-table>
    <el-dialog :visible.sync="editDialogVisible">
        <!-- 编辑表单内容 -->
    </el-dialog>
</template>
  
<script >
import { ref, onMounted } from 'vue'
import apiService from "../api"



export default {
    setup() {
        const editDialogVisible = ref(false);
        const editMode = ref(false);
        const tableData = ref([]);
        const getall = async () => {

            const response = await apiService.getAllStudents();
            // 获取学生信息的 API 方法
            if (response.data.ok) {
                tableData.value = response.data.info.data.map((item, index) => ({ ...item, id: index, editMode: false }));
            } else {
                //TODO异常处理...
            }
        };
        const toggleEditMode = (row) => {
            // 只有所在行变为编辑模式
            tableData.value.forEach(item => {
                item.editMode = item === row;
            });
        };

        const saveChanges = async (row) => {
            // 在这里保存编辑后的数据
            console.log('Save data:', row);
            // 退出编辑模式
            // 保存修改后的数据到后端
            row.editMode = false;
            const response = await apiService.updateStudent('2205030316', 'StudentId', row.studentId);
            if (response.data.ok) {
                // 退出编辑模式
                row.editMode = false;
            } else {
                // TODO 异常处理...
            }
        };

        const cancelEdit = (row) => {
            // 取消编辑，回滚数据
            if (row.originalData) {
                Object.assign(row, row.originalData);
            }
            // 退出编辑模式
            row.editMode = false;
        };

        //hook函数，获取初始数据 
        onMounted(getall);

        return {
            tableData,
            editDialogVisible,
            editMode,
            toggleEditMode,
            saveChanges,
            cancelEdit
        }
    },

}
</script>
  