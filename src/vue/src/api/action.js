import axios from 'axios';

const apiBaseURL = 'http://127.0.0.1:13355/api/action/';

export default {

    getall(type) {
        let formDatta = new FormData();
        formDatta.append("ActionType", type);
        return axios.post(`${apiBaseURL}getAll`, formDatta);
    },
    searchAction(ActionType, type, value) {
        let formDatta = new FormData();
        formDatta.append("ActionType", ActionType);
        formDatta.append("type", type);
        formDatta.append("value", value);
        return axios.post(`${apiBaseURL}query`, formDatta);
    },
    updateAction(ActionType, id, type, value) {
        let formDatta = new FormData();
        formDatta.append("ActionType", ActionType);
        formDatta.append("type", type);
        formDatta.append("value", value);
        formDatta.append("id", id);
        return axios.post(`${apiBaseURL}update`, formDatta);
    },
    deleteAction(ActionType, id) {
        let formDatta = new FormData();
        formDatta.append("ActionType", ActionType);
        formDatta.append("id", id);
        return axios.post(`${apiBaseURL}delete`, formDatta);
    },
    insertAction(ActionType, form) {
        let formDatta = new FormData();
        formDatta.append("StudentId", form.StudentId);
        formDatta.append("recTime", form.recTime);
        formDatta.append("description", form.description);
        if (ActionType == 'change') {
            formDatta.append("change", form.change);
            return axios.post(`${apiBaseURL}insert/change`, formDatta);

        }
        else if (ActionType == 'reward') {
            formDatta.append("levels", form.levels);
            return axios.post(`${apiBaseURL}insert/reward`, formDatta);
        }
        else if (ActionType == 'punishment') {
            formDatta.append("levels", form.levels);
            formDatta.append("enable", form.enable);
        }
    },


};