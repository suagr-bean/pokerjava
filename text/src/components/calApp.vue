<template>
    <!-- 这是德州计算器的 前端代码-->
    <div class="attention">
        <h1> {{ explain }}</h1>
        <p> {{ message }}</p>
    </div>
    <div  class="card1">
        <p> {{ publics}}</p>
        <input  type="text" v-model="card1">
        <input type="text" v-model="card2">
        <input type="text" v-model="card3">
        <input type="text" v-model="card4">
        <input type="text" v-model="card5">
    </div>
    <div class="card2">
        <p> {{ plays }}</p>
        <input type="text" v-model ="play1" >
        <input type="text" v-model ="play2">  
    </div>
    <div class="result">
        <input type="text" v-model=" result">
    </div>
    <div class="allbut">
    <div class="choosebut">
        <button @click="playcard()"> 手牌</button>
        <button @click="pub()"> 公牌</button>
        <button @click="clearall()"> 清除</button>
         <button @click="cal()"> 计算</button>
        </div>
         <div class="but">
            <button v-for ="(label,index) in  colors" :key="index" @click="presscolor(label)">
            {{ label }}
        </button>
        <button v-for="(label,index) in number" :key="index" @click="press(label)">
            {{ label }}
        </button>
         </div>
        </div>
        
   
</template>


<script setup>
import {ref} from "vue";
import axios from "axios";
const card1 = ref('')
const card2= ref('')
const card3 =ref('')
const card4 =ref('')
const card5 =ref('')
const play1 =ref('')
const play2 =ref('')
const result =ref('')

 const  message= ref('黑桃 S 红心H 方块D 梅花 C ');
 const  number =ref(['2','3','4','5','6','7','8','9','10','J','Q','K','A']);
 const colors= ref(['S','H','C','D']) ;
 const explain = ref('花色说明');
 const publics =ref('公牌')
const plays =ref('手牌')
let play =ref(1)
   let choose =ref(1)
  let  csecard =ref(1)

function playcard(){  
    play.value=2
}
function pub(){
    play.value=3
}
function presscolor(label){
    if (play.value == 3){
        if(card1.value ==''){
       card1.value += label
    } else  if(card2.value == ''){
        card2.value += label
    } else if(card3.value == ''){
        card3.value += label
    } else if(card4.value == ''){
        card4.value += label
    } else if(card5.value ==''){
        card5.value+=label
    }
    } else if (play.value ==2){
        if(play1.value ==''){
            play1.value+=label
        } else if(play2.value ==''){
            play2.value+=label
        }
    } 
}
function press(label){
    if(play.value ==3){
        if (choose.value ==1){
            card1.value+=label
            choose.value++
        } else if(choose.value == 2){
        card2.value += label
      choose.value++
    }  else if(choose.value ==3){
        choose.value++;
        card3.value += label
    } else if(choose.value == 4){
        choose.value++;
        card4.value +=label
    } else if(choose.value == 5){
        card5.value += label
    } 
    } else if(play.value ==2){
        if(csecard.value ==1){
            csecard.value++
             play1.value +=label
        } else if(csecard.value == 2){
            play2.value += label
        }
    }
}
     
  

function clearall(){ //清除
   card1.value =''
   card2.value=''
   card3.value=''
   card4.value=''
   card5.value=''
   play1.value=''
   play2.value=''
   choose.value = 1
   play.value =1
   csecard.value =1
   result.value =''
} 
function cal(){ //获取全部input数据然后发送给后端
   const cardlist = ref([]) // 数组
    cardlist.value.push(card1.value,card2.value,card3.value,card4.value,card5.value,
        play1.value,play2.value
    ) //把数据传进去
    console.log( cardlist.value)
    axios.post('http://localhost:8080/api/cards',cardlist.value).then(()=>{
        console.log("发送成功")
    })
     // 这里接收后端返回的胜率

}

</script>


<style>
.card1{
    display: grid;
    grid-template-columns: repeat(6,8vw);
    grid-template-rows: 8vh;
    gap: 2%;
    margin-left:30%;
    margin-top: 1%;
}
.card2{
    display: grid;
    grid-template-columns: repeat(3,8vw);
    grid-template-rows: 8vh;
    gap: 2%;
    margin-left: 30%;
    margin-top: 2%;
}
.choosebut{
    display: grid;
    grid-template-columns: repeat(4,80px);
    grid-template-rows: repeat(1,80px);
    gap: 20px;
    margin-left: 800px;
    margin-top: 30px;
}
.but{
    display: grid;
    gap: 20px;
    grid-template-columns: repeat(4,80px);
    grid-template-rows: repeat(6,80px);
    margin-left: 800px;
    margin-top: 10px;
}
.allbut button{
    border-radius: 20px;
    font-size: 15px;
    background-color: bisque;
}
.attention{
    margin-left: 800px;
}
.result{
   
    margin-left: 900px;
    margin-top: 5px;
}
.result input{
    background-color: white;
    width: 150px;
    height: 60px;
}


</style>