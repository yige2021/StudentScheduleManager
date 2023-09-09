<template>
  <div id="yige">
    <n-layout>
      <n-layout-header id="header">
          <n-layout has-sider style="height: 9vh">
            <n-layout-content
              style="
                background-color: rgba(147, 200, 128, 0.667);
                width: 40%;
                height: 8vh;
              "
            >
            <img src="../assets/logo.png" style="width: 35%; height: 8vh" />
          </n-layout-content>
          <n-layout-content
            style="
              background-color: rgba(147, 200, 128, 0.667);
              width: 60%;
              height: 8vh;
              text-align: right;
            "
          >
            <n-button
              strong
              secondary
              round
              type="info"
              @click="logout"
              style="top: 20%; right: 22%"
            >
              登出
            </n-button>
            </n-layout-content>
          </n-layout>
      </n-layout-header>

      <n-tabs type="line" animated style="position: relative; width: 96%; left: 2%;">

        <n-tab-pane name="table" tab="日程管理">
          <n-layout id="content">
            <n-layout-content>
              <n-card id="panel">

                <n-layout has-sider style="width: 100%; height: 100%">
                  <n-layout-content style="width: 50%">
                    <n-layout-content style="position: relative; top: 25px;">
                      现在是第<n-input v-model:value="week" type="text" placeholder="" disabled= "!active" style="position: relative; width: 50px;"/>周，
                      星期<n-input v-model:value="day" type="text" placeholder="" disabled= "!active" style="position: relative; width: 50px;"/>，
                      时间：<n-input v-model:value="hour" type="text" placeholder="" disabled= "!active" style="position: relative; width: 100px;"/>
                      <n-button type="success" @click="startTimer">启动</n-button>
                      <n-button type="error" @click="stopTimer">停止</n-button>
                      设置速度<n-input v-model:value="speed" type="text" placeholder="" style="position: relative; width: 70px;"/>毫秒
                    </n-layout-content>
                  </n-layout-content>

                  <n-layout-content style="width: 50%">
                    <n-data-table
                        :columns="columns"
                        :data="state.data"
                        style="height: 100px;"
                    />
                  </n-layout-content>
                </n-layout>

                <n-layout has-sider style="width: 100%; height: 100%">
                  <n-layout-content style="width: 33%;">
                    <n-card id="panel1">
                      课程类日程
                      <n-data-table
                        :columns="columns1"
                        :data="state1.data"
                        :pagination="pagination"
                        :row-props="rowProps"
                      />
                      学分：<n-input v-model:value="courseScore" type="text" placeholder="" disabled= "!active" style="position:relative; width: 50px;"/>
                      上课地点：<n-input v-model:value="courseLocation" type="text" placeholder="" disabled= "!active" style="position:relative; width: 80px;"/>
                      考试地点：<n-input v-model:value="examLocation" type="text" placeholder="" disabled= "!active" style="position:relative; width: 80px;"/>
                      考试时间：第<n-input v-model:value="examWeek" type="text" placeholder="" disabled= "!active" style="position:relative; width: 80px;"/>周，星期<n-input v-model:value="examDay" type="text" placeholder="" disabled= "!active" style="position:relative; width: 50px;"/>，<n-input v-model:value="examTime" type="text" placeholder="" disabled= "!active" style="position:relative; width: 120px;"/>
                    </n-card>
                  </n-layout-content>

                  <n-layout-content style="width: 33%">
                    <n-card id="panel2" style="height: 100%">
                      课外活动日程
                      <n-data-table
                        :columns="columns2"
                        :data="state2.data"
                        :pagination="pagination"
                      />

                      <n-layout has-sider style="padding: 6px">
                        <n-layout-content style="width: 20%">
                          <p style="line-height: 0%">活动地点</p>
                        </n-layout-content>
                        <n-layout-content style="width: 30%">
                          <n-select 
                            v-model:value="taskLocation"
                            @update:value="locationUpdateValue1"
                            placeholder=""
                            :options="locaItems1"
                            />
                          </n-layout-content>

                          <n-layout-content style="width: 20%">
                            <p style="line-height: 0%">活动类型</p>
                          </n-layout-content>
                          <n-layout-content style="width: 30%">
                            <n-select 
                              v-model:value="taskList"
                              @update:value="taskUpdateValue"
                              placeholder=""
                              :options="taskItems"
                              />
                          </n-layout-content>
                      </n-layout>

                      <n-layout has-sider style="padding: 6px">
                        <n-layout-content style="width: 28%">
                          <p style="line-height: 0%">周(每周则输0)</p>
                        </n-layout-content>

                        <n-layout-content style="width: 20%">
                          <n-input-number
                            v-model:value="taskWeek"
                            placeholder=""
                            :min="0"
                          />
                        </n-layout-content>


                        <n-layout-content style="width: 10%">
                          <p style="line-height: 0%">星期</p>
                        </n-layout-content>

                        <n-layout-content style="width: 20%">
                          <n-input-number
                            v-model:value="taskDay"
                            placeholder=""
                            :min="1"
                            :max="7"
                          />
                        </n-layout-content>

                        <n-layout-content style="width: 10%">
                          <p style="line-height: 0%">时间</p>
                        </n-layout-content>

                        <n-layout-content style="width: 20%">
                          <n-input-number
                            v-model:value="taskTime"
                            placeholder=""
                            :min="6"
                            :max="22"
                          />
                        </n-layout-content>
                      </n-layout>
                      <n-button type="primary" @click="checkCollision">添加课外活动</n-button>  
                    </n-card>
                  </n-layout-content>

                  <n-layout-content style="width: 33%">
                    <n-card id="panel3" style="height: 100%">
                      临时事务日程
                      <n-data-table
                        :columns="columns3"
                        :data="state3.data"
                        :pagination="pagination"
                      />

                      <n-layout has-sider style="padding: 6px">
                        <n-layout-content style="width: 25%">
                          <p style="line-height: 0%">临时事务地点</p>
                        </n-layout-content>
                        <n-layout-content style="width: 25%">
                          <n-select 
                            v-model:value="tempLocation"
                            @update:value="locationUpdateValue2"
                            placeholder=""
                            :options="locaItems2"
                            />
                          </n-layout-content>

                          <n-layout-content style="width: 25%">
                            <p style="line-height: 0%">临时事务类型</p>
                          </n-layout-content>
                          <n-layout-content style="width: 25%">
                            <n-select 
                              v-model:value="tempList"
                              @update:value="tempUpdateValue"
                              placeholder=""
                              :options="tempItems"
                              />
                          </n-layout-content>
                      </n-layout>

                      <n-layout has-sider style="padding: 6px">
                        <n-layout-content style="width: 25%">
                          <p style="line-height: 0%">时间</p>
                        </n-layout-content>

                        <n-layout-content style="width: 20%">
                          <n-input-number
                            v-model:value="tempTime"
                            placeholder=""
                            :min="6"
                            :max="22"
                          />
                        </n-layout-content>
                      </n-layout>
                      <n-button type="primary" @click="checkCollision2">添加临时事务</n-button>  
                    </n-card>
                  </n-layout-content>
                </n-layout>
              </n-card>
            </n-layout-content>
      </n-layout>
        </n-tab-pane>

        test<br/>
          

        <n-tab-pane name="navigation" tab="导航">
          <n-layout>
              <n-card>
                <n-layout has-sider style="height: 100%">
                  <n-layout-content style="width: 50%;">
                    <img src="../assets/map.jpg" style=" width: 95%; height: 95%" />
                  </n-layout-content>
                  <n-layout-content style="width: 50%;">
                    <n-layout has-sider>
                      <n-layout-content style="width: 20%;  padding: 6px;">
                        起点<n-select 
                        v-model:value="startPoint"
                        @update:value="startUpdateValue"
                        placeholder=""
                        :options="locaItems2"
                        />
                      </n-layout-content>

                      <n-layout-content style="width: 40%;  padding: 6px;">
                        必经点（不超过10个）<n-select 
                        v-model:value="midPoint"
                        @update:value="midUpdateValue"
                        placeholder=""
                        multiple
                        :options="locaItems2"
                        />
                      </n-layout-content>

                      <n-layout-content style="width: 20%; padding: 6px;">
                        终点<n-select 
                        v-model:value="endPoint"
                        @update:value="endUpdateValue"
                        placeholder=""
                        :options="locaItems2"
                        />
                      </n-layout-content>

                      <n-layout-content style="width: 20%; padding: 6px;">
                        <br/>
                          <n-button type="success" @click="navigation">查询路径</n-button>
                      </n-layout-content>

                      
                    </n-layout>
                    <n-input
                        v-model:value="routeInfo"
                        type="textarea"
                        placeholder=""
                        size="small"
                        :autosize="{
                          minRows: 3,
                          maxRows: 10
                        }"
                      />
                  </n-layout-content>
                </n-layout>
              </n-card>
          </n-layout>
        </n-tab-pane>

        <n-tab-pane name="log" tab="日志">
          <n-input
                        v-model:value="logInfo"
                        type="textarea"
                        placeholder=""
                        size="small"
                        :autosize="{
                          minRows: 3,
                          maxRows: 100
                        }"
                      />
        </n-tab-pane>

    </n-tabs>
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
  SelectOption
} from 'naive-ui'
import { useRouter } from 'vue-router'
import { defineComponent, ref, onMounted, reactive } from 'vue'
import axios from 'axios'
import { hoursToMilliseconds } from 'date-fns'

const { message } = createDiscreteApi(['message'], {})

const columns: Title[] = [
  { title: '学号', key: 'lstuID' },
  { title: '姓名', key: 'lstuName' },
  { title: '学院', key: 'lschool' },
  { title: '专业', key: 'lsubject' },
  { title: '班级', key: 'lclassID' }
]

const columns1 = [
  { title: '编号', key: 'id'},
  { title: '名称', key: 'name'},
  { title: '上课时间', key: 'time', defaultSortOrder: 'ascend', sorter: 'default'}
]

const c2items = [
      {
          "value": 0,
          "label": "自习"
      },
      {
          "value": 1,
          "label": "锻炼"
      },
      {
          "value": 2,
          "label": "外出"
      },
      {
          "value": 3,
          "label": "散步"
      },
      {
          "value": 4,
          "label": "校园跑"
      },
      {
          "value": 5,
          "label": "读书"
      },
      {
          "value": 6,
          "label": "参加比赛"
      },
      {
          "value": 7,
          "label": "参加讲座"
      },
      {
          "value": 8,
          "label": "参加社团活动"
      },
      {
          "value": 9,
          "label": "经营个人社交账号"
      },
      {
          "value": 10,
          "label": "参加志愿者工作"
      },
      {
          "value": 11,
          "label": "参加文化艺术活动"
      },
      {
          "value": 12,
          "label": "游戏"
      },
      {
          "value": 13,
          "label": "旅游"
      },
      {
          "value": 14,
          "label": "学习音乐"
      },
      {
          "value": 15,
          "label": "班会"
      },
      {
          "value": 16,
          "label": "小组作业"
      },
      {
          "value": 17,
          "label": "创新创业"
      },
      {
          "value": 18,
          "label": "聚餐"
      },
      {
          "value": 19,
          "label": "组织比赛"
      },
      {
          "value": 20,
          "label": "组织讲座"
      },
      {
          "value": 21,
          "label": "组织社团活动"
      },
      {
          "value": 22,
          "label": "组织文化艺术活动"
      },
      {
          "value": 23,
          "label": "企业实习"
      },
      {
          "value": 24,
          "label": "运动会"
      },
      {
          "value": 25,
          "label": "组织交流会"
      },
      {
          "value": 26,
          "label": "参加合作项目"
      },
      {
          "value": 27,
          "label": "组织晚会等大型活动"
      },
      {
          "value": 28,
          "label": "管理社团"
      },
      {
          "value": 29,
          "label": "组织学习研讨会"
      },
      {
          "value": 30,
          "label": "asdas"
      }
]

const c3items = [
  
]

const columns2 = [
  { title: '类型', key: 'type',
    defaultFilterOptionValues: [],
    filterOptions: c2items,
    filter (label, row) {
      return ~row.address.indexOf(label)
    }
  },
  { title: '地点', key: 'location' },
  { title: '时间', key: 'time', defaultSortOrder: 'ascend', sorter: 'default'}
]

const columns3 = [
  { title: '类型', key: 'type', 
    
  },
  { title: '地点', key: 'location' },
  { title: '时间', key: 'time', defaultSortOrder: 'ascend', sorter: 'default'}
]

type Task = {
  type: string
  location: string
  time: string
}

type Title = {
  title: string
  key: string
}

const state = reactive({
  data: []
});

const state1 = reactive({
  data: []
});

const state2 = reactive({
  data: []
});

const state3 = reactive({
  data: []
});

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
    NLayoutSider
  },

  setup() {
    const router = useRouter()
    var timer
    var speed = ref()
    var stuID = ref()
    var week = ref()
    var day = ref()
    var hour = ref()
    var weekVal
    var dayVal
    var hourVal

    var taskID
    var tempID
    var latestTaskID
    var latestTempID
    var taskWeek = ref()
    var taskDay = ref()
    var taskTime = ref()
    var tempTime = ref()

    var logInfo = ref()

    const taskLocation = ref(null)
    const tempLocation = ref(null)
    const startPoint = ref(null)
    const midPoint = ref(null)
    const endPoint = ref(null)
    const taskList = ref(null)
    const tempList = ref(null)

    var locaItems1 = ref([])
    var locaItems2 = ref([])
    var taskItems = ref([])
    var tempItems = ref([])

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

    var isCollision = ref()

    var routeInfo = ref()

    function logout() {
      router.push('/')
    }

    const fetchUserInfo = async () => { // 获取用户信息
      try {
        const response = await axios.get('http://localhost:8080/SSM/api/getstuinfo');
        state.data = response.data
        
      } catch (error) {
        console.error(error)
      }
    };

    const fetchCourseList = async () => { // 获取课程列表
      try {
        const response = await axios.get('http://localhost:8080/SSM/api/stu_getcourselist ');
        state1.data = response.data
      } catch (error) {
        console.error(error);
      }
    };

    const fetchTaskList = async () => { // 获取课外活动列表
      try {
        const response = await axios.get('http://localhost:8080/SSM/api/gettasklist ');
        state2.data = response.data
      } catch (error) {
        console.error(error);
      }
    };

    const fetchTempList = async () => { // 获取临时事务列表
      try {
        const response = await axios.get('http://localhost:8080/SSM/api/gettemplist ');
        state3.data = response.data
      } catch (error) {
        console.error(error);
      }
    };

    const fetchLocItems1 = async () => { // 获取地点信息
      try {
        const response = await axios.get('http://localhost:8080/SSM/api/getlocation2');
        locaItems1.value = response.data;
        locaItems2.value = response.data;
      } catch (error) {
        console.error(error);
      }
    };

    const fetchTaskItems = async () => { // 获取课外活动类型
      try {
        const response = await axios.get('http://localhost:8080/SSM/api/gettaskinfo');
        taskItems.value = response.data
      } catch (error) {
        message.error(error);
      }
    };

    const fetchTempItems = async () => { // 获取课外活动类型
      try {
        const response = await axios.get('http://localhost:8080/SSM/api/gettempinfo');
        tempItems.value = response.data
      } catch (error) {
        console.error(error);
      }
    };

    const checkCollision = async () => {
      const params = new URLSearchParams()
      params.append('taskWeek', taskWeek.value)
      params.append('taskDay', taskDay.value)
      params.append('taskTime', taskTime.value)

      try {
        const response = await axios
        .post('http://localhost:8080/SSM/api/checkcollision2', params, {
          //修改请求格式
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
        if (response.data == "1") {
          message.warning("该时段已经有课程！")
          getAdvice();
        }
        else if (response.data == "2") {
          message.warning("该时段已经有课外活动！")
          getAdvice();
        }
        else {
          addTask();
        }
      }
      catch (error) {
        console.error(error);
      }
    }

    const checkCollision2 = async () => {
      const params = new URLSearchParams()
      params.append('taskWeek', weekVal)
      params.append('taskDay', dayVal)
      params.append('taskTime', tempTime.value)

      try {
        const response = await axios
        .post('http://localhost:8080/SSM/api/checkcollision2', params, {
          //修改请求格式
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
        if (response.data == "1") {
          message.warning("该时段已经有课程！")
        }
        else if (response.data == "2") {
          message.warning("该时段已经有课外活动！")
        }
        else {
          addTemp();
        }
      }
      catch (error) {
        console.error(error);
      }
    }

    function showTime(w, d, h) {
      weekVal = w
      dayVal = d
      hourVal = h
      week.value = w
      hour.value = h + ":00"

      if (d == 1) {
            day.value = "一";
        }
        if (d == 2) {
          day.value = "二";
        }
        if (d == 3) {
          day.value = "三";
        }
        if (d == 4) {
          day.value = "四";
        }
        if (d == 5) {
          day.value = "五";
        }
        if (d == 6) {
          day.value = "六";
        }
        if (d == 7) {
          day.value = "日";
        }


    }

    function getTime() {
      axios
        .get('http://localhost:8080/SSM/api/gettime')
        .then((response) => {
          const { w } = response.data
          const { d } = response.data
          const { h } = response.data
          showTime(w, d, h)
        })
        .catch((error) => {
          message.error('网络错误')
        })
    }

    function changeTime() {

      if (hourVal == 6) {
        state3.data = [];
      }

      hourVal = hourVal + 1

      if (hourVal == 22) {
        dayVal = dayVal + 1
        message.success("明天的课表与课外活动已更新！请注意安排")
      }

      if (dayVal == 8) {
        weekVal = weekVal + 1
        dayVal = 1
      }

      if (hourVal == 23) {
        hourVal = 6
      }

      const params = new URLSearchParams()
      //添加字段
      params.append('week', weekVal)
      params.append('day', dayVal)
      params.append('hour', hourVal)
      axios
        .post('http://localhost:8080/SSM/api/timerun', params, {
          //修改请求格式
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
        .then((response) => {
          if (response.data != "") {
            message.info(response.data)
          }
          if (hourVal == 22) {
            fetchCourseList()
            fetchTaskList()
          }
        })
        .catch((error) => {
          message.error('网络错误')
        })
        if (hourVal == 22) {
          dayVal = dayVal - 1
          showTime(weekVal, dayVal, hourVal)
          dayVal = dayVal + 1
        }
        else {
          showTime(weekVal, dayVal, hourVal)
        }
    }

    function startTimer() {
      timer = setInterval(changeTime, speed.value)
    }

    function stopTimer() {
      clearInterval(timer)
    }

    function getLatestID() {
      axios
        .get('http://localhost:8080/SSM/api/stu_getlatestid')
        .then((response) => {
          latestTaskID = response.data
        })
        .catch((error) => {
          message.error('网络错误')
        })
    }

    const getLog = async () => {
      try {
        const response = await axios
        .get('http://localhost:8080/SSM/api/getlog', {
          //修改请求格式
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
        logInfo.value = response.data
      }
      catch (error) {
        console.error(error);
      }
    }

    function getCourse(id) {
      axios
        .get('http://localhost:8080/SSM/api/getcourse?courseID='+ encodeURIComponent(id))
        .then((response) => {
          const { cl } = response.data;
          const { cs } = response.data;
          const { cn } = response.data;
          const { cw } = response.data;
          const { cd } = response.data;
          const { ct } = response.data;
          const { ew } = response.data;
          const { ed } = response.data;
          const { et } = response.data;
          const { clo } = response.data;
          const { el } = response.data;

          courseLength.value = cl
          courseScore.value = cs
          courseName.value = cn

          courseWeek.value = cw
          courseDay.value = cd
          courseTime.value = ct

          examWeek.value = ew
          examDay.value = ed
          examTime.value = (et + 7) + ":00"

          courseLocation.value = clo
          examLocation.value = el
        })
        .catch((error) => {
          message.error('网络错误')
        })
    }

    function addTask() {
      const params = new URLSearchParams()
      //添加字段
      params.append('taskID', latestTaskID)
      params.append('taskWeek', taskWeek.value)
      params.append('taskDay', taskDay.value)
      params.append('taskTime', taskTime.value)
      params.append('taskLocation', taskLocation.value)
      params.append('taskType', taskList.value)
      
      axios
        .post('http://localhost:8080/SSM/api/addtask', params, {
          //修改请求格式
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
        .then((response) => {
          const { type } = response.data;
          const { location } = response.data;
          const { time } = response.data;

          const newTask: Task = { 
            type: type,
            location: location,
            time: time
          };
          taskID = -1
          state2.data.push(newTask)

          message.success('保存成功！')
          latestTaskID = latestTaskID + 1
          message.info(latestTaskID)
        })
        .catch((error) => {
          console.error('网络错误')
        })
    }

    function addTemp() {
      const params = new URLSearchParams()
      //添加字段
      params.append('tempTime', tempTime.value)
      params.append('tempLocation', tempLocation.value)
      params.append('tempType', tempList.value)
      
      axios
        .post('http://localhost:8080/SSM/api/addtemp', params, {
          //修改请求格式
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
        .then((response) => {
          const { type } = response.data;
          const { location } = response.data;
          const { time } = response.data;

          const newTemp: Task = { 
            type: type,
            location: location,
            time: time
          };
          state3.data.push(newTemp)
          message.success('保存成功！')
        })
        .catch((error) => {
          console.error('网络错误')
        })
    }

    function getAdvice() {
      axios
        .get('http://localhost:8080/SSM/api/getadvice')
        .then((response) => {
          message.info(response.data)
        })
        .catch((error) => {
          message.error('网络错误')
        })
    }

    function navigation() {
      const params = new URLSearchParams()
      //添加字段
      params.append('startPoint', startPoint.value)
      if (midPoint.value == null) {
        params.append('midPoint', "-1")
      }
      else {
        params.append('midPoint', midPoint.value)
      }
      params.append('endPoint', endPoint.value)
      
      axios
        .post('http://localhost:8080/SSM/api/navigation', params, {
          //修改请求格式
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
        .then((response) => {
          message.success("路线规划成功！")
          routeInfo.value = response.data
        })
        .catch((error) => {
          console.error('网络错误')
        })
    }

    fetchUserInfo();

    fetchCourseList();
    fetchTaskList();
    fetchTempList();
    
    fetchLocItems1();
    getLatestID();
    getLog();
    fetchTaskItems();
    fetchTempItems();
    
    getTime();

    return {
      logout,

      columns,
      columns1,
      columns2,
      columns3,

      state,
      state1,
      state2,
      state3,

      pagination: {
        pageSize: 6
      },

      rowProps: (row: Course) => {
        return {
          style: 'cursor: pointer;',
          onClick: () => {
            getCourse(row.id)
          }
        }
      },

      week,
      day,
      hour,
      weekVal,
      dayVal,
      hourVal,

      stuID,

      fetchUserInfo,
      fetchCourseList,
      fetchTaskList,
      fetchTempList,
      getLatestID,
      getLog,
      getTime,
      checkCollision,
      checkCollision2,
      isCollision,
      navigation,
      
      timer,
      speed,
      changeTime,
      startTimer,
      stopTimer,

      taskLocation,
      tempLocation,
      startPoint,
      midPoint,
      endPoint,
      taskList,
      tempList,

      locaItems1,
      locaItems2,
      taskItems,
      tempItems,

      fetchLocItems1,
      fetchTaskItems,
      fetchTempItems,

      getCourse,

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

      taskID,
      tempID,
      latestTaskID,
      latestTempID,
      taskWeek,
      taskDay,
      taskTime,
      tempTime,

      addTask,
      addTemp,

      routeInfo,
      logInfo
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
  background-color: rgba(147, 200, 128, 0.667);
  height: 9vh;
}

#content {
  background-color: aliceblue;
  height: 91vh;
}

.inline {
  display: inline;
}

</style>