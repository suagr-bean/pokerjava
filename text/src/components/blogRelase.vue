<template> 
<!--发布内容 给博客的 自己可以发布  不展示 -->
    <div class="all">
    <textarea   id="title" v-model="title"></textarea> 
    <textarea id="content" v-model="content"> </textarea>
    <button @click="dealwith()">提交</button>
    </div>
    
</template>

<style>
  .all{
    display: flex;
    flex-direction: column;
    width: 100vw;
    height:100vh;
    gap: 5%;
  }
  #title{
    margin-top: 1%;
    margin-left: 3%;
    width: 80%;
    height: 5vh;
   
  }
  #content{
    width: 80%;
    height: 40vh;
    margin-left: 3%;
  }
  .all button{
    width: 30%;
    height: 5vh;
    margin-left: 25%;
  }
</style>


<script setup>
import axios from 'axios';
import {ref} from 'vue';
import dayjs from 'dayjs';
let title =ref('');
const content =ref('');
function  getNowTime(){ // 用 第三方打库 dayjs  获取当前时间
  return dayjs().format('YYYY年MM月DD日 HH:mm')
}

function dealwith(){ //通过axios 把数据给到后端
 const sendData={
  title :title.value,
  content :content.value,
  publishTime :getNowTime() //当前时间
 }
 console.log(sendData)
 axios.post('http://localhost:8080/api/posts',sendData).then((response)=>{
  title.value=''
  content.value=''
  console.log('发送成功')
  console.log(response.data)
  console.log(getNowTime())
 })
}

</script>