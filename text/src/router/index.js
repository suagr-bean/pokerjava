import { createRouter, createWebHistory} from "vue-router";

import homePage from "@/components/homePage.vue";
import calApp from "@/components/calApp.vue";
import blogRelase from "@/components/blogRelase.vue";

const routes=[   // 必须 映射
 {path:'/',component:homePage},
 {path:'/cal',component:calApp},
 {path:'/rel',component:blogRelase}
]
const router= createRouter({
   history: createWebHistory(),
   routes,
})
export default  router;
//写路由的地方 