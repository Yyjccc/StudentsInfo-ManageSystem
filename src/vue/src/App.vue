
<template>
  <div>
    <header>
      <Header />
    </header>
    <el-container class="layout-container-demo">
      <el-aside width="200px" style="">
        <el-scrollbar>
          <el-menu :default-openeds="['1', '3']">
            <el-menu-item index="1">
              <router-link to="/students">
                <el-icon><icon-menu /></el-icon>
                <span @click="controlhead(index)">学生基本信息</span>
              </router-link>
            </el-menu-item>

            <el-menu-item index="2">
              <router-link to="/change">
                <el-icon><icon-menu /></el-icon>
                <span>学籍变更</span>
              </router-link>
            </el-menu-item>
            <el-menu-item index="3">
              <router-link to="/reward">
                <el-icon>
                  <icon-menu />
                  <document />
                </el-icon>
                <span>获奖记录</span>
              </router-link>
            </el-menu-item>
            <el-menu-item index="4">
              <router-link to="/punishment">
                <el-icon>
                  <icon-menu />
                  <document />
                </el-icon>
                <span>处分记录</span>
              </router-link>
            </el-menu-item>

            <el-menu-item index="5">
              <el-icon>
                <setting />
              </el-icon>
              <span>账号设置</span>
            </el-menu-item>
          </el-menu>
        </el-scrollbar>
      </el-aside>

      <el-container>
        <el-header style="text-align: right; font-size: 12px">

          <div id="tit"> </div>
        </el-header>

        <el-main>
          <el-scrollbar>
            <router-view></router-view>
          </el-scrollbar>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script >
import { ref } from 'vue'
import { Menu as IconMenu, Message, Setting } from '@element-plus/icons-vue'
import Header from './components/Header.vue';
import apiService from './api';



const item = {
  date: '2016-05-02',
  name: 'Tom',
  address: 'No. 189, Grove St, Los Angeles',
}
const tableData = ref(Array.from({ length: 20 }).fill(item))

export default {
  setup() {
    const headertitle = ["学生基本信息表", "学生学籍变更表", "学生获奖记录表", "学生处分记录表"]
    const nowtitle = ref('')


    return {
      nowtitle, headertitle,

    }
  }
}

</script>



<style scoped>
.el-container,
.el-header,
.el-aside,
.el-main {
  margin: 0;

  padding: 0;
}

.el-container {
  height: 100vh;
  /* 视口高度 */
  width: 100vw;

}

.layout-container-demo .el-header {
  position: relative;
  background-color: var(--el-color-primary-light-7);
  color: var(--el-text-color-primary);
}

.layout-container-demo .el-aside {
  color: var(--el-text-color-primary);
  background: var(--el-color-primary-light-8);
}

.layout-container-demo .el-menu {
  border-right: none;
}

.layout-container-demo .el-main {
  padding: 0;
}

.layout-container-demo .toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 20px;
}

#tit {
  position: relative;
  right: 750px;
  top: 13px;
  font-size: 24px;

}
</style>


