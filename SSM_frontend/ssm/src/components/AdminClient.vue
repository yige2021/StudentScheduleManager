<template>
  <div id="yige">
    <n-layout>
      <n-layout-header id="header">
        <n-layout has-sider style="height: 9vh">
          <n-layout-content
            style="
              background-color: rgba(255, 166, 0, 0.667);
              width: 40%;
              height: 8vh;
            "
          >
            <img src="../assets/logo.png" style="width: 35%; height: 8vh" />
          </n-layout-content>
          <n-layout-content
            style="
              background-color: rgba(255, 166, 0, 0.667);
              width: 60%;
              height: 8vh;
              text-align: right;
            "
          >
            <n-button
              strong
              secondary
              round
              @click="logout"
              type="info"
              style="top: 20%; right: 22%"
            >
              登出
            </n-button>
          </n-layout-content>
        </n-layout>
      </n-layout-header>
      <n-layout id="content">
        <n-layout-content>
          <n-card id="panel">
            <n-layout has-sider style="width: 100%; height: 100%">
              <n-layout-content style="width: 50%">
                <n-card id="lpanel">
                  <n-layout has-sider style="width: 100%">
                    <n-layout-content style="width: 80%">
                      <n-input
                        round
                        type="text"
                        placeholder="输入课程名称"
                        style="width: 100%; text-align: left"
                      ></n-input>
                    </n-layout-content>
                    <n-layout-content style="width: 20%">
                      <n-button type="primary" round style="width: 80%" >
                        搜索
                      </n-button>
                    </n-layout-content>
                  </n-layout>
                  <n-layout has-sider style="width: 100%">
                    <n-layout-content style="width: 33%; padding: 12px">
                      <n-button
                        v-model:value="add"
                        strong
                        secondary
                        type="primary"
                        style="width: 100%"
                        @click="addCourse"
                      >
                        增加课程
                      </n-button>
                    </n-layout-content>
                    <n-layout-content style="width: 33%; padding: 12px">
                      <n-button
                        strong
                        secondary
                        type="info"
                        style="width: 100%"
                        @click="checkCollision"
                      >
                        保存更改
                      </n-button>
                    </n-layout-content>
                    <n-layout-content style="width: 33%; padding: 12px">
                      <n-button
                        strong
                        secondary
                        type="error"
                        style="width: 100%"
                        @click="deleteCourse"
                      >
                        删除课程
                      </n-button>
                    </n-layout-content>
                  </n-layout>
                  <n-data-table
                    v-mode
                    :columns="columns"
                    :data="state.data"
                    :row-props="rowProps"
                    :pagination="pagination"
                  />
                </n-card>
              </n-layout-content>

              <n-layout-content style="width: 50%">
                <n-card id="rpanel" style="height: 100%">
                  <n-layout has-sider style="padding: 6px">
                    <n-layout-content style="width: 20%">
                      <p style="line-height: 0%">课程名称</p>
                    </n-layout-content>
                    <n-layout-content style="width: 70%">
                      <n-input
                        v-model:value="courseName"
                        placeholder=""
                      ></n-input>
                    </n-layout-content>
                  </n-layout>

                  <n-layout has-sider style="padding: 6px">
                    <n-layout-content style="width: 20%">
                      <p style="line-height: 0%">上课地点</p>
                    </n-layout-content>
                    <n-layout-content style="width: 70%">
                      <n-select 
                        v-model:value="locationList"
                        @update:value="handleUpdateValue"
                        placeholder=""
                        :options="items"
                        />
                    </n-layout-content>
                  </n-layout>

                  <n-layout has-sider style="padding: 6px">
                    <n-layout-content style="width: 20%">
                      <p style="line-height: 0%">考试地点</p>
                    </n-layout-content>
                    <n-layout-content style="width: 70%">
                      <n-select 
                        v-model:value="locationList2"
                        @update:value="handleUpdateValue2"
                        placeholder=""
                        :options="items"
                        />
                    </n-layout-content>
                  </n-layout>

                  <n-layout has-sider style="padding: 6px">
                    <n-layout-content style="width: 20%">
                      <p style="line-height: 0%">学分</p>
                    </n-layout-content>
                    <n-layout-content style="width: 25%">
                      <n-input-number
                        v-model:value="courseScore"
                        placeholder=""
                        :min="0"
                      />
                    </n-layout-content>
                    <n-layout-content style="width: 20%">
                      <p style="line-height: 0%">单节时长</p>
                    </n-layout-content>
                    <n-layout-content style="width: 25%">
                      <n-input-number
                        v-model:value="courseLength"
                        placeholder=""
                        :min="0"
                      />
                    </n-layout-content>
                  </n-layout>

                  <n-layout has-sider style="padding: 6px">

                    <n-layout-content style="width: 20%">
                      <p style="line-height: 0%">周(每周则输0)</p>
                    </n-layout-content>

                    <n-layout-content style="width: 20%">
                      <n-input-number
                        v-model:value="courseWeek"
                        placeholder=""
                        :min="0"
                      />
                    </n-layout-content>

                    <n-layout-content style="width: 10%">
                      <p style="line-height: 0%">星期</p>
                    </n-layout-content>

                    <n-layout-content style="width: 15%">
                      <n-input-number
                        v-model:value="courseDay"
                        placeholder=""
                        :min="1"
                        :max="7"
                      />
                    </n-layout-content>


                    <n-layout-content style="width: 10%">
                      <p style="line-height: 0%">节次</p>
                    </n-layout-content>

                    <n-layout-content style="width: 15%">
                      <n-input-number
                        v-model:value="courseTime"
                        placeholder=""
                        :min="1"
                        :max="12"
                      />
                    </n-layout-content>
                  </n-layout>



                  <n-layout has-sider style="padding: 6px">
                    <n-layout-content style="width: 20%">
                      <p style="line-height: 0%">考试周</p>
                    </n-layout-content>

                    <n-layout-content style="width: 20%">
                      <n-input-number
                        v-model:value="examWeek"
                        placeholder=""
                        :min="0"
                      />
                    </n-layout-content>


                    <n-layout-content style="width: 10%">
                      <p style="line-height: 0%">星期</p>
                    </n-layout-content>

                    <n-layout-content style="width: 15%">
                      <n-input-number
                        v-model:value="examDay"
                        placeholder=""
                        :min="1"
                        :max="7"
                      />
                    </n-layout-content>

                    <n-layout-content style="width: 10%">
                      <p style="line-height: 0%">节次</p>
                    </n-layout-content>

                    <n-layout-content style="width: 15%">
                      <n-input-number
                        v-model:value="examTime"
                        placeholder=""
                        :min="1"
                        :max="12"
                      />
                    </n-layout-content>

                    </n-layout>



                  <n-transfer ref="transfer" :options="useroptions" />


                </n-card>
              </n-layout-content>
            </n-layout>
          </n-card>
        </n-layout-content>
      </n-layout>
    </n-layout>
  </div>
</template>

<script lang="ts">
import {
  createDiscreteApi,
  NDatePicker,
  NSelect,
  NInputNumber,
  NTag,
  NDataTable,
  NCard,
  NTabs,
  NTabPane,
  NRadioGroup,
  NRadioButton,
  NForm,
  NInput,
  NButton,
  NLayout,
  NLayoutHeader,
  NLayoutSider,
  NLayoutFooter,
  NTransfer,
  NCheckbox
} from 'naive-ui'
import { useRouter } from 'vue-router'
import { defineComponent, ref, onMounted, reactive } from 'vue'
import axios from 'axios'
import type { DataTableColumns, SelectOption} from 'naive-ui'
import { computed } from '@vue/reactivity'

const { message } = createDiscreteApi(['message'], {})

type Course = {
  id: number
  name: string
  score: number
}

type Title = {
  title: string
  key: string
}

const state = reactive({
  data: []
});

const columns: Title[] = [
  { title: '课程编号', key: 'id' },
  { title: '课程名', key: 'name' },
  { title: '学分', key: 'score' }
]

const useroptions = [
  {
    label: '2021777003',
    value: 2021777003
  },
  {
    label: '2021666001',
    value: 2021666001
  },
  {
    label: '2021333002',
    value: 2021333002
  },
  {
    label: '2021211321',
    value: 2021211321
  },
  {
    label: '2021211319',
    value: 2021211319
  },
  {
    label: '2021211315',
    value: 2021211315
  },
  {
    label: '2021211314',
    value: 2021211314
  },
  {
    label: '2021211313',
    value: 2021211313
  },
  {
    label: '2021211312',
    value: 2021211312
  },
  {
    label: '2020123456',
    value: 2020123456
  },
  
]

export default defineComponent({
  components: {
    NDatePicker,
    NSelect,
    NInputNumber,
    NTag,
    NDataTable,
    NCard,
    NTabs,
    NTabPane,
    NRadioButton,
    NRadioGroup,
    NForm,
    NInput,
    NButton,
    NLayout,
    NLayoutFooter,
    NLayoutHeader,
    NLayoutSider,
    NTransfer,
    NCheckbox
  },

  setup() {
    const add = ref(null)
    const router = useRouter()
    
    var latestID //最新ID
    var courseID //课程ID
    var courseScore = ref() //课程学分
    var courseLength = ref() //课程长度
    var courseLocation = ref() //上课地点
    var examLocation = ref() //考试地点
    var courseName = ref() //课程名称
    var courseWeek = ref() //上课周次
    var courseDay = ref() //上课周次
    var courseTime = ref() //上课节次
    var examWeek = ref() //上课周次
    var examDay = ref() //上课周次
    var examTime = ref() //上课节次
    var isCollision = ref();

    const locationList = ref(null)
    const locationList2 = ref(null)
    var items = ref([])

    const fetchLocItems = async () => { // 异步方法,从后端获取json对象
      try {
        const response = await axios.get('http://localhost:8080/SSM/api/getlocation');
        items.value = response.data;
      } catch (error) {
        console.error(error);
      }
    };

    const fetchListItems = async () => { // 异步方法,从后端获取json对象
      try {
        const response = await axios.get('http://localhost:8080/SSM/api/getcourselist');
        state.data = response.data
        console.log(response.data)
      } catch (error) {
        console.error(error);
      }
    };

    fetchLocItems();
    fetchListItems();

    function addCourse() {
      const newCourse: Course = { 
        id: latestID + 1,
        name: '',
        score: 0
      };
      courseID = -1
      state.data.push(newCourse)
    }

    const checkCollision = async () => {
      isCollision = 0

      const params = new URLSearchParams()
      params.append('courseID', courseID)
      params.append('courseWeek', courseWeek.value)
      params.append('courseDay', courseDay.value)
      params.append('courseTime', courseTime.value)

      try {
        const response = await axios
        .post('http://localhost:8080/SSM/api/checkcollision', params, {
          //修改请求格式
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
        if (response.data == "1") {
          message.warning("该时段已经有课程！请更换时间")
        }
        else {
          editCourse();
        }
      }
      catch (error) {
        console.error(error);
      }
    }

    function editCourse() {
      const params = new URLSearchParams()
      //添加字段
      params.append('courseID', courseID)
      params.append('courseScore', courseScore.value)
      params.append('courseLength', courseLength.value)
      params.append('courseLocation', courseLocation)
      params.append('examLocation', examLocation)
      params.append('courseName', courseName.value)

      params.append('courseWeek', courseWeek.value)
      params.append('courseDay', courseDay.value)
      params.append('courseTime', courseTime.value)

      params.append('examWeek', examWeek.value)
      params.append('examDay', examDay.value)
      params.append('examTime', examTime.value)
      axios
        .post('http://localhost:8080/SSM/api/setcourse', params, {
          //修改请求格式
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
        .then((response) => {
          message.success('保存成功！')
          latestID = latestID + 1
          const item = state.data.find(Course => Course.id === latestID)
          const newCourse: Course = { 
            id: latestID,
            name: courseName.value,
            score: courseScore.value
          };
          Object.assign(item, newCourse);
        })
        .catch((error) => {
          console.error('网络错误')
        })
    }

    function deleteCourse() {
      const params = new URLSearchParams()
      params.append('courseID', courseID)
      axios
        .post('http://localhost:8080/SSM/api/deletecourse', params, {
          //修改请求格式
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
        .then((response) => {
          message.success('删除成功！')
          const index = state.data.findIndex(Course => Course.id === courseID);
          state.data.splice(index, 1)
        })
        .catch((error) => {
          console.error('网络错误')
        })
    }

    function getLatestID() {
      axios
        .get('http://localhost:8080/SSM/api/getlatestid')
        .then((response) => {
          latestID = response.data - 0
        })
        .catch((error) => {
          message.error('网络错误')
        })
    }

    function getCourse(id) {
      axios
        .get('http://localhost:8080/SSM/api/getcourse?courseID='+ encodeURIComponent(id))
        .then((response) => {
          const { stuid } = response.data;
          const { cl } = response.data;
          const { cs } = response.data;
          const { cn } = response.data;
          const { cw } = response.data;
          const { cd } = response.data;
          const { ct } = response.data;
          const { ew } = response.data;
          const { ed } = response.data;
          const { et } = response.data;

          courseID = stuid
          courseLength.value = cl
          courseScore.value = cs
          courseName.value = cn

          courseWeek.value = cw
          courseDay.value = cd
          courseTime.value = ct

          examWeek.value = ew
          examDay.value = ed
          examTime.value = et
        })
        .catch((error) => {
          message.error('网络错误')
        })
    }
    
    getLatestID()

    function logout() {
      router.push('/')
    }

    return {
      add,
      columns,
      state,
      rowProps: (row: Course) => {
        return {
          style: 'cursor: pointer;',
          onClick: () => {
            getCourse(row.id)
          }
        }
      },

      useroptions,

      pagination: {
        pageSize: 8
      },

      handleUpdateValue (value: string, option: SelectOption){
        courseLocation = JSON.stringify(value)
      },

      handleUpdateValue2 (value: string, option: SelectOption){
        examLocation = JSON.stringify(value)
      },

      addCourse,
      editCourse,
      deleteCourse,
      fetchLocItems,
      fetchListItems,
      getLatestID,
      getCourse,

      courseID,
      courseScore, //课程学分
      courseLength, //课程长度
      courseLocation, //上课地点
      examLocation, //考试地点
      courseName, //课程名称
      courseWeek, //上课周次
      courseDay,  //上课日次
      courseTime, //上课节次
      examWeek, //考试周次
      examDay,  //考试日次
      examTime, //考试节次

      items,
      locationList,
      locationList2,

      logout,
      
      isCollision,
      checkCollision,

    }
  }
})
</script>

<style>
#yige {
  position: absolute;
  top: 0%;
  left: 0%;
  height: 100%;
  width: 100%;
}

#header {
  background-color: rgba(255, 166, 0, 0.667);
  height: 8vh;
}

#content {
  background-color: aliceblue;
  height: 92vh;
}

#panel {
  left: 15%;
  top: 2%;
  height: 95%;
  width: 70%;
}

#lpanel {
  position: relative;
  left: 0%;
  height: 100%;
}
</style>
