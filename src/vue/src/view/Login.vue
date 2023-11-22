<template>
    <form @submit.prevent="login">
        <input name="username" v-model="credentials.username" />
        <input name="password" type="password" v-model="credentials.password" />
        <button type="submit">登录</button>
    </form>
</template>
  
<script>
import apiService from '../api';

export default {
    data() {
        return {
            credentials: {
                username: '',
                password: ''
            },
        }
    },
    methods: {
        login() {
            apiService.login(this.credentials.username, this.credentials.password).
                then((response) => {
                    let ok = response.data.ok;
                    if (ok) {
                        let token = response.data.info.token;
                        if (token) {
                            this.$cookie.set('jwt', token, 1)
                            this.$router.push({ name: 'home' });
                        }
                    } else {
                        alert("登录失败")
                    }
                });
        }
    }
}
</script>
