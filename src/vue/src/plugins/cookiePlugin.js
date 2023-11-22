// 在 src/plugins 目录下创建一个 cookiePlugin.js 文件

export default {
    install(app) {
        app.config.globalProperties.$cookie = {
            set(key, value, days) {
                const expires = new Date();
                expires.setTime(expires.getTime() + days * 24 * 60 * 60 * 1000);
                document.cookie = `${key}=${value};expires=${expires.toUTCString()};path=/`;
            },
            get(key) {
                const name = `${key}=`;
                const decodedCookie = decodeURIComponent(document.cookie);
                const cookieArray = decodedCookie.split(';');
                for (let i = 0; i < cookieArray.length; i++) {
                    let cookie = cookieArray[i];
                    while (cookie.charAt(0) === ' ') {
                        cookie = cookie.substring(1);
                    }
                    if (cookie.indexOf(name) === 0) {
                        return cookie.substring(name.length, cookie.length);
                    }
                }
                return null;
            },
        };
    },
};
