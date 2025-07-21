<template> 
<!--bolg -->
    
<div class="contents"> 
        <div class="show">
        <h1>{{ contents }}</h1> <!--目录-->
       
        <ol v-for="(post,index) in posts" :key = "index">
            <h2 @click = dealwith(index)>{{ post.title }}</h2>
        </ol>
        </div>
  
    <div class="detail">
    <div class="title"><h3> {{ title }}</h3></div>
     <div class ="time"><h3>{{ Time}}</h3></div>
    <div class="article" v-if ="selected" v-html="dealcontent(selected)"></div>
    </div>
 </div>

</template>

<script setup>
import { ref} from 'vue'
import { marked } from 'marked'
marked.setOptions({
  breaks: true // 启用换行即换行，单个 \n 会转成 <br>
});

import { onMounted } from 'vue'
 import axios from 'axios'
const contents =ref('目录')
let selected =ref('')
let Time =ref('')
let title =ref('') 
const posts =ref([ ])  //数据
 function dealwith(index){ //点击拿到索引  显示正文内容
    selected.value= posts.value[index].content;
    Time .value =posts.value[index].publishTime;
    title.value =posts.value[index].title;
 }
 function dealcontent (text){
    return marked(text);
 }

 //从后端拿到数据
const fetchPosts = async()=>{
    const response =await axios.get('http://localhost:8080/api/posts');
 posts.value=response.data
   console.log(posts.value)
}
onMounted(() => { //异步
    fetchPosts()
})
    
</script>
<style>

.contents{
   background-color: mintcream;
    padding: 1vw;
    display: flex;
    flex-direction: row;
    width: auto;
  
}
.show{
    margin-left: 3vw;
    width: 25vw;
    height: 80vh;
   
}
.show ol{
  
    font-size: 0.6vw;
    overflow-y: auto;
    margin-bottom: 0.6em;
}
.show h2{
     color:#4B4B4B;
}
.detail{
    display: flex;
    flex-direction: column;
    margin-left: 5vw;
}
.article{
  width: 70vw;
}
</style>