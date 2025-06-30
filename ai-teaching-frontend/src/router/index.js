import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Home from '../views/Home.vue'
import PermissionOverview from '../views/system/PermissionOverview.vue'
import MenuManagement from '../views/system/MenuManagement.vue'
import RouteManagement from '../views/system/RouteManagement.vue'
import RoleManagement from '../views/system/RoleManagement.vue'
import UserManagement from '../views/system/UserManagement.vue'
import ProfileSettings from '../views/settings/ProfileSettings.vue'
import SecuritySettings from '../views/settings/SecuritySettings.vue'
import NotificationSettings from '../views/settings/NotificationSettings.vue'
import PreferenceSettings from '../views/settings/PreferenceSettings.vue'
import AccountSettings from '../views/settings/AccountSettings.vue'

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
    component: Home,
    meta: { requiresAuth: true },
    children: [
      {
        path: '',
        name: 'Dashboard',
        component: () => import('../components/Dashboard.vue')
      },
      // 权限管理子路由
      {
        path: 'system/permission',
        redirect: 'system/permission/overview'
      },
      {
        path: 'system/permission/overview',
        name: 'PermissionOverview',
        component: PermissionOverview
      },
      {
        path: 'system/permission/menu',
        name: 'MenuManagement',
        component: MenuManagement
      },
      {
        path: 'system/permission/route',
        name: 'RouteManagement',
        component: RouteManagement
      },
      {
        path: 'system/permission/role',
        name: 'RoleManagement',
        component: RoleManagement
      },
      {
        path: 'system/permission/user',
        name: 'UserManagement',
        component: UserManagement
      },
      // 个人设置子路由
      {
        path: 'settings/profile',
        name: 'ProfileSettings',
        component: ProfileSettings
      },
      {
        path: 'settings/security',
        name: 'SecuritySettings',
        component: SecuritySettings
      },
      {
        path: 'settings/notification',
        name: 'NotificationSettings',
        component: NotificationSettings
      },
      {
        path: 'settings/preference',
        name: 'PreferenceSettings',
        component: PreferenceSettings
      },
      {
        path: 'settings/account',
        name: 'AccountSettings',
        component: AccountSettings
      },
      
      // 系统设置子路由
      {
        path: 'system/settings/basic',
        name: 'BasicSettings',
        component: () => import('../views/system/settings/BasicSettings.vue')
      },
      {
        path: 'system/settings/email',
        name: 'EmailSettings',
        component: () => import('../views/system/settings/EmailSettings.vue')
      },
      {
        path: 'system/settings/security',
        name: 'SystemSecuritySettings',
        component: () => import('../views/system/settings/SystemSecuritySettings.vue')
      },
      {
        path: 'system/settings/backup',
        name: 'BackupSettings',
        component: () => import('../views/system/settings/BackupSettings.vue')
      },
      {
        path: 'system/settings/logs',
        name: 'LogsSettings',
        component: () => import('../views/system/settings/LogsSettings.vue')
      },
      {
        path: 'system/settings/info',
        name: 'SystemInfo',
        component: () => import('../views/system/settings/SystemInfo.vue')
      }
    ]
  },
  // 兼容旧链接
  {
    path: '/home',
    redirect: '/dashboard'
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