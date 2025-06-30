import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Home from '../views/Home.vue'
import PermissionOverview from '../views/permission/PermissionOverview.vue'
import PermissionMenuManagement from '../views/permission/PermissionMenuManagement.vue'
import PermissionRouteManagement from '../views/permission/PermissionRouteManagement.vue'
import PermissionRoleManagement from '../views/permission/PermissionRoleManagement.vue'
import PermissionUserManagement from '../views/permission/PermissionUserManagement.vue'
import PersonalProfileSettings from '../views/personal/PersonalProfileSettings.vue'
import PersonalSecuritySettings from '../views/personal/PersonalSecuritySettings.vue'
import PersonalNotificationSettings from '../views/personal/PersonalNotificationSettings.vue'
import PersonalPreferenceSettings from '../views/personal/PersonalPreferenceSettings.vue'
import PersonalAccountSettings from '../views/personal/PersonalAccountSettings.vue'

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
        path: 'permission/overview',
        name: 'PermissionOverview',
        component: PermissionOverview
      },
      {
        path: 'permission/menu',
        name: 'PermissionMenuManagement',
        component: PermissionMenuManagement
      },
      {
        path: 'permission/route',
        name: 'PermissionRouteManagement',
        component: PermissionRouteManagement
      },
      {
        path: 'permission/role',
        name: 'PermissionRoleManagement',
        component: PermissionRoleManagement
      },
      {
        path: 'permission/user',
        name: 'PermissionUserManagement',
        component: PermissionUserManagement
      },
      // 个人设置子路由
      {
        path: 'personal/profile',
        name: 'PersonalProfileSettings',
        component: PersonalProfileSettings
      },
      {
        path: 'personal/security',
        name: 'PersonalSecuritySettings',
        component: PersonalSecuritySettings
      },
      {
        path: 'personal/notification',
        name: 'PersonalNotificationSettings',
        component: PersonalNotificationSettings
      },
      {
        path: 'personal/preference',
        name: 'PersonalPreferenceSettings',
        component: PersonalPreferenceSettings
      },
      {
        path: 'personal/account',
        name: 'PersonalAccountSettings',
        component: PersonalAccountSettings
      },
      
      // 系统设置子路由
      {
        path: 'system-settings/basic',
        name: 'BasicSettings',
        component: () => import('../views/system-settings/BasicSettings.vue')
      },
      {
        path: 'system-settings/email',
        name: 'EmailSettings',
        component: () => import('../views/system-settings/EmailSettings.vue')
      },
      {
        path: 'system-settings/security',
        name: 'SystemSecuritySettings',
        component: () => import('../views/system-settings/SystemSecuritySettings.vue')
      },
      {
        path: 'system-settings/backup',
        name: 'BackupSettings',
        component: () => import('../views/system-settings/BackupSettings.vue')
      },
      {
        path: 'system-settings/logs',
        name: 'LogsSettings',
        component: () => import('../views/system-settings/LogsSettings.vue')
      },
      {
        path: 'system-settings/info',
        name: 'SystemInfo',
        component: () => import('../views/system-settings/SystemInfo.vue')
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