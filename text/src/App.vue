

<template>    
  <div id="app">
    <!--路由导航 -->
    <nav class="nav">
      <router-link to="/"> blog</router-link>
      <router-link to="/cal">计算器</router-link>
      <router-link to="/rel"> 发布</router-link>
      <input type="text" >
      <button> {{ text}}</button>
  
    </nav>
     <router-view> </router-view>
  </div>
</template>

<style>
.nav{
  width: 100vw;
  height: 3vh;
  background-color: mintcream;
  box-shadow:0 0 0.8vw rgb(0,0,0,0.8); /*阴影*/ 
  padding: 1.5% ;
  display: flex;
  flex-direction: row;
  font-size: larger;
  gap: 3%;
}
</style>

<script setup>
import {ref} from "vue";
const text = ref('搜素');
</script>