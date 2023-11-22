


export default {
    methods: {
        checkStudentId(studentid) {
            try {
                const intValue = parseInt(studentid, 10);
                // 检查是否在 BIGINT范围内
                //  BIGINT范围为-9223372036854775808 到 9223372036854775807
                const id = intValue >= -9223372036854775808 && intValue <= 9223372036854775807;
                if (id) {
                    return true;
                }
                return false;
            } catch (e) {
                return false;
            }
        },
        //数据处理器
        DataParse(data) {
            //将true改为是，是改为true
            //将时间戳转为日期格式

        }

    },
}