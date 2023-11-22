<template>
    <searchComponent :options="ChangeOptions" :mytypes="typemy" @search-action="ChangeSearch" @return-all="ReturnAll"
        @to-addform="toaddform" />
    <el-table :data="tableData" style="width: 100%;margin-top: -70px;" :row-class-name="tableRowClassName">
        <el-table-column prop="id" label="id" width="150" />
        <el-table-column prop="studentId" label="学号" width="150" />
        <el-table-column prop="levels" label="级别" width="100" />
        <el-table-column prop="enable" label="是否生效" width="150" />
        <el-table-column prop="recTime" label="记录时间" width="160" />
        <el-table-column prop="description" label="描述" width="250" />
        <el-table-column fix="select" label="选项">
            <template #default="{ row }">
                <el-button v-if="!editMode[row.index - 1]" type="primary" size="small"
                    @click="toggleEditMode(row)">编辑</el-button>
                <el-button v-if="!editMode[row.index - 1]" type="danger" size="small"
                    @click="deleteEntry(row)">删除</el-button>
                <el-button v-if="editMode[row.index - 1]" type="success" size="small"
                    @click="saveChanges(row)">保存</el-button>
                <el-button v-if="editMode[row.index - 1]" type="danger" size="small" @click="cancelEdit(row)">取消</el-button>
            </template>
        </el-table-column>

    </el-table>
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentpage"
        :page-sizes="[10]" :page-size="10" layout="total, prev, pager, next, jumper"
        :total="alldata.length"></el-pagination>


    <div>
        <el-dialog v-model="dialogFormVisible" title="添加学籍变更信息" :append-to-body="true">
            <el-form :model="form">
                <el-form-item label="学号" :label-width="formLabelWidth">
                    <el-input v-model="form.StudentId" autocomplete="off" />
                </el-form-item>
                <el-form-item label="级别" :label-width="formLabelWidth">
                    <el-input v-model="form.levels" autocomplete="off" />
                </el-form-item>
                <el-form-item label="记录日期" :label-width="formLabelWidth">
                    <el-date-picker v-model="form.recTime" type="date" placeholder="请选择日期" size="large" />
                </el-form-item>
                <el-form-item label="描述" :label-width="formLabelWidth">
                    <el-input v-model="form.description" autocomplete="off" />
                </el-form-item>
            </el-form>

            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取消</el-button>
                    <el-button type="primary" @click="addAction(form)">
                        确定
                    </el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>
  
<script >
import searchComponent from "./others/search.vue"
import { ref, reactive, onMounted } from "vue"
import apiService from "../api/action"
import mycommon from "../common/check"
export default {
    components: {
        'searchComponent': searchComponent,
    },
    setup() {
        const maxpage = ref(0);
        const alldata = ref([])
        const currentpage = ref(1);
        const tmpsave = ref([])
        const tableData = ref([])


        const typemy = ref("学生处分记录表");
        //添加
        const dialogFormVisible = ref(false);
        const form = reactive({
            StudentId: '',
            levels: '',
            recTime: '',
            description: '',

        })

        //添加
        const toaddform = () => {
            dialogFormVisible.value = true;
        }

        const addAction = (form) => {
            let errorvalue = mycommon.methods.checkStudentId(form.StudentId);
            //校验数据
            if (form.StudentId == '' || form.levels == '' || !errorvalue) {
                if (!errorvalue) { alert("学号值非法"); } else {
                    alert("学号或者变更不能为空");
                }
            } else {
                //转化日期格式
                if (form.recTime != '') {
                    let date = form.recTime;
                    const year = date.getFullYear();
                    const month = (date.getMonth() + 1).toString().padStart(2, '0');
                    const day = date.getDate().toString().padStart(2, '0');
                    form.recTime = `${year}-${month}-${day}`;
                }
                apiService.insertAction("punishment", form).then(
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



        //编辑
        const editMode = ref([]);
        const column = ['index', 'id', 'StudentId', 'levels', 'recTime', 'description']


        //搜索
        const ChangeOptions = ref([
            {
                "label": "id",
                value: "id"
            },
            {
                label: "学号",
                value: "StudentId"
            },
            {
                label: "级别",
                value: "levels"
            },
            {
                label: "记录时间",
                value: "recTime"
            },
            {
                label: "描述",
                value: "description"
            }
        ])

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
        //获取所有数据
        const getall = async () => {
            alldata.value = [];
            await apiService.getall("punishment").then(
                (response) => {
                    // 获取学生信息的 API 方法
                    if (response.data.ok) {
                        let res = response.data.info.data
                        alldata.value = res.map((item, index) => ({
                            index: index + 1, // You can adjust the index starting point if needed
                            ...item,
                        }));
                        maxpage.value = Math.ceil(alldata.value.length / 10);
                    } else {
                        //TODO异常处理...
                    }
                }
            ).catch(
                (err) => {
                    alert("服务器错误")
                    console.log(err);
                }
            );
            fetchData(currentpage.value);
            if (alldata.value && alldata.value.length) {
                for (let i = 0; i < alldata.value.length; i++) {
                    editMode.value.push(false);
                }
            }
        };


        //搜索
        const ChangeSearch = async (searchCriteria) => {
            await apiService.searchAction("punishment", searchCriteria.type, searchCriteria.value).then(
                (response) => {
                    if (response.data.ok) {
                        let res = response.data.info.data
                        alldata.value = res.map((item, index) => ({
                            index: index + 1, // You can adjust the index starting point if needed
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
                        alert("数据错误");
                    }
                }
            ).catch(
                (error) => {
                    alert("服务器错误");
                    console.log(error.message);
                }
            )

        }
        const ReturnAll = async () => {
            await getall();
        }

        //编辑和删除
        const toggleEditMode = (row) => {
            editMode.value[row.index - 1] = true;
            const my = document.getElementsByClassName("el-table__row")[row.index - 1];
            const ceil = my.querySelectorAll('.cell');
            for (let i = 0; i < ceil.length - 1; i++) {
                let value = ceil[i].textContent
                tmpsave.value.push(value);
                if (i > 0) {
                    let index = column[i];
                    ceil[i].innerHTML = '<input name="' + index + '" value="' + value + '" />'
                }
            }
        }

        const deleteEntry = (row) => {
            apiService.deleteAction("punishment", row.id).then(
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
        const saveChanges = async (row) => {
            const my = document.getElementsByClassName("el-table__row")[row.index - 1];
            const ceil = my.querySelectorAll('.cell');
            const inputs = my.querySelectorAll('input');
            let data = tmpsave.value;
            let refeshdata = [];
            for (let i = 0; i < inputs.length; i++) {
                refeshdata.push(inputs[i].value);
                let res = inputs[i].value;
                if (res != data[i + 1]) {
                    await (apiService.updateAction('punishment', data[0], column[i + 1], res).
                        then((response) => {
                            if (response.data.ok) {
                            } else {
                                // TODO 异常处理...
                                alert("输入异常");
                            }
                        }).catch((error) => {
                            console.log(error.message);
                            alert("后端或数据库错误")
                        }));

                }
            }
            for (let i = 1; i < refeshdata.length + 1; i++) {
                ceil[i].innerHTML = refeshdata[i - 1];
            }
            tmpsave.value = []
            editMode.value[row.index - 1] = false;

        }

        const cancelEdit = (row) => {
            let data = tmpsave.value;
            tmpsave.value = [];
            const my = document.getElementsByClassName("el-table__row")[row.index - 1];
            const ceil = my.querySelectorAll('.cell');
            console.log(ceil);
            for (let i = 0; i < data.length; i++) {
                ceil[i].innerHTML = data[i];
            }
            editMode.value[row.index - 1] = false;
        }

        const handleSizeChange = (pageSize) => {

            currentpage.value = 1;
            fetchData(currentpage.value);
        };

        const handleCurrentChange = (page) => {
            currentpage.value = page;
            fetchData(page);
        };

        onMounted(() => {
            // 在生命周期钩子中使用组件实例
            getall();


        });

        return {
            ChangeOptions, tableData, alldata, currentpage, editMode,
            fetchData, getall, handleSizeChange, handleCurrentChange,
            ChangeSearch, ReturnAll, typemy,
            dialogFormVisible, form, addAction, toaddform,
            toggleEditMode, deleteEntry, saveChanges, cancelEdit, tmpsave,

        }
    }
};
</script>
  