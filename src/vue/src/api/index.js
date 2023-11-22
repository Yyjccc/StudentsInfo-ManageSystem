import axios from 'axios';

const apiBaseURL = 'http://127.0.0.1:13355/api/';

export default {
    login(username, password) {
        let formDatta = new FormData();
        formDatta.append('username', username);
        formDatta.append('password', password);
        return axios.post(`${apiBaseURL}login`, formDatta);
    },
    checkLogin() {
        axios.get(`${apiBaseURL}test`).then((response) => {
            try {
                if (response.data == 'test') {
                    console.log("验证登录成功")
                    return true;
                } else {
                    console.log("验证登录失败")
                    return false;
                }
            } catch (e) {
                console.log("验证登录失败")
                return false;
            }
        });
    },
    getAllStudents() {
        return axios.get(`${apiBaseURL}students/getAll`);
    },
    searchStudent(type, value) {
        let formDatta = new FormData();
        formDatta.append("type", type);
        formDatta.append("value", value);
        return axios.post(`${apiBaseURL}students/query`, formDatta);
    },
    addStudent(studentData) {
        let formDatta = new FormData();
        formDatta.append("StudentId", studentData.StudentId);
        formDatta.append("name", studentData.name);
        if (studentData.sex != '') {
            formDatta.append("sex", studentData.sex);
        }
        if (studentData.classId != '') {
            formDatta.append("classId", studentData.classId);
        }
        if (studentData.department != '') {
            formDatta.append("department", studentData.department);
        }
        if (studentData.birthday != '') {
            formDatta.append("birthday", studentData.birthday);
        }
        if (studentData.nativePlace != '') {
            formDatta.append("nativePlace", studentData.nativePlace);
        }
        return axios.post(`${apiBaseURL}students/insert`, formDatta);
    },
    updateStudent(id, type, value) {
        let formDatta = new FormData();
        formDatta.append("StudentId", id);
        formDatta.append("type", type);
        formDatta.append("value", value);
        return axios.post(`${apiBaseURL}students/update`, formDatta);

    },
    deleteStudent(id) {
        let formDatta = new FormData();
        formDatta.append("StudentId", id);
        return axios.post(`${apiBaseURL}students/delete`, formDatta);
    }
};
