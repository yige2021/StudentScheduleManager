<template>
  <n-card title="学生日程管理系统">
    <n-input v-model:value="userid" id="stuid" type="text" placeholder="学号" />
    <n-input
      v-model:value="password"
      id="password"
      type="password"
      show-password-on="mousedown"
      placeholder="密码"
    />
    <n-button type="primary" @click="login">登录</n-button>
  </n-card>
</template>

<script lang="ts">
import { NCard, NInput, NButton, createDiscreteApi } from 'naive-ui'
import axios from 'axios'
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const { message } = createDiscreteApi(['message'], {})

export default {
  components: {
    NButton,
    NCard,
    NInput
  },

  setup() {
    const router = useRouter()
    const userid = ref(null)
    const password = ref(null)

    function login() {
      const params = new URLSearchParams()
      //添加字段
      params.append('userid', userid.value)
      params.append('password', password.value)

      axios
        .post('http://localhost:8080/SSM/api/login', params, {
          //修改请求格式
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
        .then((response) => {
          if (response.data == 1) {
            message.error('用户名或密码为空')
          }
          if (response.data == 2) {
            message.error('用户不存在')
          }
          if (response.data == 3) {
            message.error('密码错误')
          }
          if (response.data == 0) {
            message.success('登录成功')
            if (userid.value == 'admin') {
              router.push('/AdminClient')
            }
            else {
              router.push('/StudentClient')
            }
            
          }
        })
        .catch((error) => {
          message.error('网络错误')
        })
    }
    return {
      userid,
      password,
      login
    }
  }
}
</script>

<style>
.n-card {
  max-width: 400px;
  text-align: center;
}
#stuid {
  text-align: left;
  margin-bottom: 1rem;
}
#password {
  text-align: left;
  margin-bottom: 1rem;
}

body {
  background-color: rgba(173, 194, 231, 0.082);
}
</style>
