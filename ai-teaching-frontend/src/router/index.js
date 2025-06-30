import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Home from '../views/Home.vue'
import PermissionManagement from '../views/system/PermissionManagement.vue'
import PermissionOverview from '../views/system/PermissionOverview.vue'
import MenuManagement from '../views/system/MenuManagement.vue'
import RouteManagement from '../views/system/RouteManagement.vue'
import RoleManagement from '../views/system/RoleManagement.vue'
import UserManagement from '../views/system/UserManagement.vue'

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: Home,
    meta: { requiresAuth: true }
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    meta: { requiresAuth: true }
  },
  {
    path: '/system/permission',
    component: PermissionManagement,
    meta: { requiresAuth: true },
    children: [
          {
            path: '',
            name: 'PermissionOverview',
            component: PermissionOverview
          },
          {
            path: 'menu',
            name: 'MenuManagement',
            component: MenuManagement
          },
          {
            path: 'route',
            name: 'RouteManagement',
            component: RouteManagement
          },
          {
            path: 'role',
            name: 'RoleManagement',
            component: RoleManagement
          },
          {
            path: 'user',
            name: 'UserManagement',
            component: UserManagement
          }
        ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 路由守卫：检查是否需要登录
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  
  if (to.meta.requiresAuth && !token) {
    // 需要登录但没有token，跳转到登录页
    next('/login')
  } else if (to.path === '/login' && token) {
    // 已经登录但访问登录页，跳转到首页
    next('/dashboard')
  } else {
    // 正常访问
    next()
  }
})

export default router 