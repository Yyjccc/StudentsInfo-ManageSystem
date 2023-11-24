export default {

    DateFormat(timestamp) {
        if (timestamp != '') {
            const date = new Date(timestamp);
            // 使用正则表达式检查是否符合 "yyyy-mm-dd" 格式
            const dateRegex = /^\d{4}-\d{2}-\d{2}$/;
            if (dateRegex.test(date)) {
                return date;
            } else {
                var year = date.getFullYear();
                var month = ('0' + (date.getMonth() + 1)).slice(-2); // 月份从0开始，需要加1
                var day = ('0' + date.getDate()).slice(-2);

                // 拼接成 yyyy-mm-dd 格式
                var formattedDate = year + '-' + month + '-' + day;

                return formattedDate;
            }
        }
    },

}