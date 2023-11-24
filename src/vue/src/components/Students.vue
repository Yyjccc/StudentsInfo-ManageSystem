<template>
    <div id="search">
        <el-form :model="search">
            <el-form-item :label-width="formLabelWidth" style="margin-top: 8px;">
                <template #label>
                    <span style="font-size: 22px;margin-top: 8px;">搜索</span>
                </template>
                <el-select v-model="search.type" class="m-2" placeholder="请选择搜索的字段" size="large">
                    <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
                </el-select>
            </el-form-item>
            <div id="searchinput">
                <el-input v-model="search.value" style=" width: 260px;
    height: 40px;" autocomplete="off" placeholder="输入搜索值" />
                <el-button v-if="visualReturn" @click="ReturnAll" type="success" style="
    position: relative;left: 150px; height: 40px;width: 75px;">返回</el-button>
            </div>
        </el-form>
        <el-button type="primary" id="serbutton" @click="searchStudent(search)">搜索</el-button>
    </div>
    <el-button type="info" id="add" @click="toaddform">添加</el-button>
    <div id="tabletitle" style="display: flex;float: left;">
        <span>{{ searchMode }}</span>
    </div>
    <div class="custom-table-container">
        <el-table :data="tableData" class="custom-table">

            <el-table-column prop="id" label="ID" width="60" ref=""></el-table-column>
            <el-table-column prop="studentId" label="学号" width="140" />
            <el-table-column prop="name" label="姓名" width="100" />
            <el-table-column prop="sex" label="性别" width="50" />
            <el-table-column prop="classId" label="班级" width="180" />
            <el-table-column prop="department" label="学院" width="200" />
            <el-table-column prop="birthday" label="出生日期" width="180" />
            <el-table-column prop="nativePlace" label="籍贯" width="130" />
            <el-table-column fix="right" label="选项">
                <template #default="{ row }">
                    <el-button v-if="!editMode[row.id - 1]" :ref="getButtonRef(row.id)" type="primary" size="small"
                        @click="toggleEditMode(row)">编辑</el-button>
                    <el-button v-if="!editMode[row.id - 1]" type="danger" size="small"
                        @click="deleteEntry(row)">删除</el-button>
                    <el-button v-if="editMode[row.id - 1]" type="success" size="small"
                        @click="saveChanges(row)">保存</el-button>
                    <el-button v-if="editMode[row.id - 1]" type="danger" size="small"
                        @click="cancelEdit(row)">取消</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentpage"
        :page-sizes="[10]" :page-size="10" layout="total, prev, pager, next, jumper"
        :total="alldata.length"></el-pagination>
    <div>
        <el-dialog v-model="dialogFormVisible" title="添加学生信息" :append-to-body="true">
            <el-form :model="form">
                <el-form-item label="学号" :label-width="formLabelWidth">
                    <el-input v-model="form.StudentId" autocomplete="off" />
                </el-form-item>
                <el-form-item label="姓名" :label-width="formLabelWidth">
                    <el-input v-model="form.name" autocomplete="off" />
                </el-form-item>
                <el-form-item label="性别" :label-width="formLabelWidth">
                    <el-select v-model="form.sex" placeholder="请选择">
                        <el-option label="男" value="男" />
                        <el-option label="女" value="女" />
                    </el-select>
                </el-form-item>
                <el-form-item label="班级" :label-width="formLabelWidth">
                    <el-input v-model="form.classId" autocomplete="on" />
                </el-form-item>
                <el-form-item label="学院" :label-width="formLabelWidth">
                    <el-input v-model="form.department" autocomplete="off" />
                </el-form-item>
                <el-form-item label="出生日期" :label-width="formLabelWidth">
                    <el-date-picker v-model="form.birthday" type="date" placeholder="请选择日期" size="large" />
                </el-form-item>
                <el-form-item label="籍贯" :label-width="formLabelWidth">
                    <el-input v-model="form.nativePlace" autocomplete="off" />
                </el-form-item>
            </el-form>

            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取消</el-button>
                    <el-button type="primary" @click="addStudent(form)">
                        确定
                    </el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>

<script  >

import { ref, reactive, onMounted, computed, getCurrentInstance } from 'vue'
import apiService from "../api"
import mycommon from "../common/check"
import myformat from "../common/format"


export default {
    setup() {
        const editDialogVisible = ref(false);
        const maxpage = ref(0);
        const editMode = ref([]);
        const alldata = ref([]);
        const currentpage = ref(1);
        const tableData = ref([]);
        const tmpsave = ref([]);
        const myRef = ref(null);
        const componentInstance = getCurrentInstance();
        // 将 myRef 分配给组件实例
        componentInstance.proxy.$myRef = myRef;
        const getButtonRef = (id) => `editButton_${id}`;
        const column = ['id', 'StudentId', 'name', 'sex', 'classId', 'department', 'birthday', 'nativePlace']


        //搜索
        const visualReturn = ref(false);
        const searchMode = ref("学生基本信息全部数据");
        const options = [
            {
                value: 'StudentId',
                label: "学号"
            },
            {

                value: 'name',
                label: "姓名"
            },
            {
                value: 'sex',
                label: "性别",
            },
            {
                value: 'classId',
                label: "班级"
            },
            {
                value: 'department',
                label: "学院"
            },
            {
                value: 'birthday',
                label: "出生日期"
            },
            {
                value: "nativePlace",
                label: "籍贯"
            }
        ]

        const search = reactive({
            type: "",
            value: ""
        })

        ///添加
        const dialogFormVisible = ref(false)
        const form = reactive({
            StudentId: '',
            name: '',
            sex: '',
            classId: '',
            department: '',
            birthday: '',
            nativePlace: '',
        });
        const formLabelWidth = '140px';


        //获取所有数据
        const getall = async () => {
            alldata.value = []
            const response = await apiService.getAllStudents().catch(
                (err) => {
                    alert("服务器错误")
                    console.log(err);
                }
            );
            // 获取学生信息的 API 方法
            if (response.data.ok) {
                const res = response.data.info.data
                alldata.value = res.map((item, index) => ({
                    id: index + 1, // You can adjust the index starting point if needed
                    ...item,
                }));
                for (let i = 0; i < alldata.value.length; i++) {
                    let date = myformat.DateFormat(alldata.value[i].birthday);
                    alldata.value[i].birthday = date;
                }
                maxpage.value = Math.ceil(alldata.value.length / 10);
            } else {
                //TODO异常处理...
            }

            fetchData(currentpage.value);
            if (alldata.value && alldata.value.length) {
                for (let i = 0; i < alldata.value.length; i++) {
                    editMode.value.push(false);
                }
            }
        };
        //将数据获取到当前页面中
        const fetchData = (page) => {
            if (page > maxpage.value) {

            }
            let p = page - 1;
            let start = p * 10
            let end = 0
            if ((p + 1) * 10 > alldata.value.length) {
                end = alldata.value.length;
            } else {
                end = start + 10;
            }
            tableData.value = alldata.value.slice(start, end);
        };



        const handleSizeChange = (pageSize) => {

            currentpage.value = 1;
            fetchData(currentpage.value);
        };

        const handleCurrentChange = (page) => {
            currentpage.value = page;
            fetchData(page);
        };

        function toggleEditMode(row) {
            editMode.value[row.id - 1] = true;
            const my = document.getElementsByClassName("el-table__row")[row.id - 1];
            const ceil = my.querySelectorAll('.cell');
            for (let i = 0; i < ceil.length - 1; i++) {
                let value = ceil[i].textContent
                tmpsave.value.push(value);
                if (i > 0) {
                    let index = column[i];
                    if (index == 'birthday') {
                        ceil[i].innerHTML = '<input  type="date" name="' + index + '" value="' + value + '" />'
                    } else {
                        ceil[i].innerHTML = '<input name="' + index + '" value="' + value + '" />'
                    }
                }
            }
        };

        // 保存修改后的数据到后端
        const saveChanges = async (row) => {
            const my = document.getElementsByClassName("el-table__row")[row.id - 1];
            const ceil = my.querySelectorAll('.cell');
            const inputs = my.querySelectorAll('input');
            let data = tmpsave.value;
            let refeshdata = [];
            for (let i = 0; i < inputs.length; i++) {
                refeshdata.push(inputs[i].value);
                let res = inputs[i].value;
                if (res != data[i + 1]) {
                    await (apiService.updateStudent(data[1], column[i + 1], res).
                        then((response) => {
                            if (response.data.ok) {
                            } else {
                                // TODO 异常处理...
                                alert("后端或者数据库异常");
                            }
                        }));
                }
            }
            for (let i = 1; i < refeshdata.length + 1; i++) {
                ceil[i].innerHTML = refeshdata[i - 1];
            }
            tmpsave.value = []
            editMode.value[row.id - 1] = false;

        };
        //取消修改数据
        const cancelEdit = (row) => {
            let data = tmpsave.value;
            tmpsave.value = [];
            const my = document.getElementsByClassName("el-table__row")[row.id - 1];
            const ceil = my.querySelectorAll('.cell');
            console.log(ceil);
            for (let i = 0; i < data.length; i++) {
                ceil[i].innerHTML = data[i];
            }
            editMode.value[row.id - 1] = false;
        };

        //转入添加元素的表单
        const toaddform = () => {
            dialogFormVisible.value = true;
        }


        //提交表单，添加元素
        const addStudent = (form) => {
            let errorvalue = mycommon.methods.checkStudentId(form.StudentId);
            //校验数据
            if (form.StudentId == '' || form.name == '' || !errorvalue) {
                if (!errorvalue) { alert("学号值非法"); } else {
                    alert("学号或者姓名不能为空");
                }
            } else {
                //转化日期格式
                if (form.birthday != '') {
                    let date = form.birthday;
                    const year = date.getFullYear();
                    const month = (date.getMonth() + 1).toString().padStart(2, '0');
                    const day = date.getDate().toString().padStart(2, '0');
                    form.birthday = `${year}-${month}-${day}`;
                }
                apiService.addStudent(form).then(
                    (response) => {
                        if (!response.data.ok) {
                            alert("输入错误或服务端错误")
                        } else {
                            console.log("插入成功");
                            getall();

                        }
                    }
                ).catch((error) => {
                    alert("数据输入错误");
                    console.log(error.message);
                });
            }

            dialogFormVisible.value = false;
        }
        //删除元素
        const deleteEntry = async (row) => {

            apiService.deleteStudent(row.studentId).then(
                (response) => {
                    if (response.data.ok) {
                        getall();
                    }
                }
            ).catch((error) => {
                console.log(error.message);
                alert("服务器错误");
            })
        }
        //搜索功能
        const searchStudent = async (search) => {
            if (search.type != '') {
                await apiService.searchStudent(search.type, search.value).then(
                    (response) => {
                        if (response.data.ok) {
                            visualReturn.value = true;
                            alldata.value = []
                            searchMode.value = "搜索结果";
                            const res = response.data.info.data
                            alldata.value = res.map((item, index) => ({
                                id: index + 1, // You can adjust the index starting point if needed
                                ...item,
                            }));
                            maxpage.value = Math.ceil(alldata.value.length / 10);
                            fetchData(1);
                            if (alldata.value && alldata.value.length) {
                                for (let i = 0; i < alldata.value.length; i++) {
                                    editMode.value.push(false);
                                }
                            }
                        } else {
                            alert(response.data.msg)
                        }

                    }
                ).catch(
                    (err) => {
                        console.log(err);
                        alert("服务器错误")
                    }
                )
            } else {
                alert("请选择类型")
            }
        }

        //返回总表
        const ReturnAll = async () => {
            searchMode.value = "学生基本信息全部数据";
            visualReturn.value = false;
            await getall();
        }



        const overlayClick = (event) => {
            // 阻止点击事件传播，不关闭弹框
            event.stopPropagation();
        };
        const selectedColumnData = computed(() => {
            // 获取某一列的对象数据，这里以 'name' 列为例
            return tableData.value.map((item) => ({
                id: item.id,
                name: item.name,

            }));
        });
        //hook函数，获取初始数据 
        //onMounted(getall);
        onMounted(() => {
            // 在生命周期钩子中使用组件实例
            getall();


        });
        return {
            tmpsave,
            myRef,
            tableData,
            alldata,
            currentpage,
            editDialogVisible,
            editMode,
            getButtonRef,
            toggleEditMode,
            saveChanges,
            cancelEdit,
            handleSizeChange,
            handleCurrentChange,
            selectedColumnData,
            toaddform,
            dialogFormVisible, formLabelWidth, form, addStudent,
            overlayClick,
            deleteEntry,
            options, search, searchStudent, searchMode, visualReturn, ReturnAll,

        }
    },

}
</script>
<style scoped>
input {
    width: 100%;
    /* 使输入框宽度等于父元素的宽度 */
    box-sizing: border-box;
    /* 让宽度包括 border 和 padding */
    padding: 5px;
    /* 适当的 padding，使输入框不紧贴边框 */
}

.el-button--text {
    margin-right: 15px;
}

.el-select {
    width: 300px;
}

.el-input {
    width: 300px;
}

.dialog-footer button:first-child {
    margin-right: 10px;
}

.custom-table-container {
    margin-top: -85px;



}

.custom-table {
    width: 100%;

}

#add {
    position: relative;
    left: 1000px;
    top: -139px;
    width: 65px;
    height: 40px;
}

#serbutton {
    position: relative;
    left: 720px;
    top: -98px;
    height: 40px;
    width: 75px;
}




/* .el-table__fixed .el-table__fixed-body-wrapper {

} */
</style>
<style>
.el-pagination {
    position: absolute;
    left: 450px;
    bottom: -50px;
}

.el-header {
    overflow: hidden;
}

.el-scrollbar__view {
    position: relative;
}

#tabletitle {
    font-size: 24px;
    position: absolute;
    float: left;
    left: 460px;
    top: 65px;
}

#searchinput {
    position: relative;
    left: 450px;
    top: -58px;

}
</style>
