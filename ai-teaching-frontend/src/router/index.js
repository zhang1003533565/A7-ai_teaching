import { createRouter, createWebHistory } from 'vue-router'
import { getUserPermissions } from '@/api/permission'
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
import BasicSettings from '../views/system-settings/BasicSettings.vue'
import EmailSettings from '../views/system-settings/EmailSettings.vue'
import SystemSecuritySettings from '../views/system-settings/SystemSecuritySettings.vue'
import BackupSettings from '../views/system-settings/BackupSettings.vue'
import LogsSettings from '../views/system-settings/LogsSettings.vue'
import SystemInfo from '../views/system-settings/SystemInfo.vue'
import CoursesOverview from '../views/courses/CoursesOverview.vue'
import CoursesManage from '../views/courses/CoursesManage.vue'
import CoursesContent from '../views/courses/CoursesContent.vue'
import CoursesSchedule from '../views/courses/CoursesSchedule.vue'
import CoursesStudents from '../views/courses/CoursesStudents.vue'
import CoursesResources from '../views/courses/CoursesResources.vue'
import HomeworkOverview from '../views/homework/HomeworkOverview.vue'
import HomeworkPublish from '../views/homework/HomeworkPublish.vue'
import HomeworkGrading from '../views/homework/HomeworkGrading.vue'
import HomeworkGrades from '../views/homework/HomeworkGrades.vue'
import HomeworkSubmissions from '../views/homework/HomeworkSubmissions.vue'
import HomeworkTemplates from '../views/homework/HomeworkTemplates.vue'
import AnalysisOverview from '../views/analysis/AnalysisOverview.vue'
import LearningProgress from '../views/analysis/LearningProgress.vue'
import PerformanceAnalysis from '../views/analysis/PerformanceAnalysis.vue'
import EngagementAnalysis from '../views/analysis/EngagementAnalysis.vue'
import KnowledgeMap from '../views/analysis/KnowledgeMap.vue'
import LearningReport from '../views/analysis/LearningReport.vue'
import ResourcesOverview from '../views/resources/ResourcesOverview.vue'
import ResourcesMaterials from '../views/resources/ResourcesMaterials.vue'
import ResourcesVideos from '../views/resources/ResourcesVideos.vue'
import ResourcesDocuments from '../views/resources/ResourcesDocuments.vue'
import ResourcesTemplates from '../views/resources/ResourcesTemplates.vue'
import ResourcesShared from '../views/resources/ResourcesShared.vue'
import QuestionsOverview from '../views/questions/QuestionsOverview.vue'
import QuestionsBank from '../views/questions/QuestionsBank.vue'
import QuestionsCategory from '../views/questions/QuestionsCategory.vue'
import QuestionsTags from '../views/questions/QuestionsTags.vue'

// AI对话相关组件
import AIChatAssistant from '../views/ai/AIChatAssistant.vue'
import AIChatQA from '../views/ai/AIChatQA.vue'
import AIChatWriting from '../views/ai/AIChatWriting.vue'
import AIChatAnalysis from '../views/ai/AIChatAnalysis.vue'
import AIChatHistory from '../views/ai/AIChatHistory.vue'

// AI批改相关组件
import GradingOverview from '../views/ai/grading/GradingOverview.vue'
import GradingHomework from '../views/ai/grading/GradingHomework.vue'
import GradingExam from '../views/ai/grading/GradingExam.vue'
import GradingCode from '../views/ai/grading/GradingCode.vue'
import GradingTemplate from '../views/ai/grading/GradingTemplate.vue'

// 学习推荐
import RecommendOverview from '../views/ai/recommend/RecommendOverview.vue'
import RecommendCourse from '../views/ai/recommend/RecommendCourse.vue'
import RecommendResource from '../views/ai/recommend/RecommendResource.vue'
import RecommendExercise from '../views/ai/recommend/RecommendExercise.vue'
import RecommendPlan from '../views/ai/recommend/RecommendPlan.vue'

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
    path: '/403',
    name: 'Forbidden',
    component: () => import('../views/403.vue')
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
        path: 'permission',
        redirect: 'permission/overview'
      },
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
      // 课程管理子路由
      {
        path: 'courses',
        redirect: 'courses/overview'
      },
      {
        path: 'courses/overview',
        name: 'CoursesOverview',
        component: CoursesOverview
      },
      {
        path: 'courses/manage',
        name: 'CoursesManage',
        component: CoursesManage
      },
      {
        path: 'courses/content',
        name: 'CoursesContent',
        component: CoursesContent
      },
      {
        path: 'courses/schedule',
        name: 'CoursesSchedule',
        component: CoursesSchedule
      },
      {
        path: 'courses/students',
        name: 'CoursesStudents',
        component: CoursesStudents
      },
      {
        path: 'courses/resources',
        name: 'CoursesResources',
        component: CoursesResources
      },
      
      // 作业管理子路由
      {
        path: 'homework',
        redirect: 'homework/overview'
      },
      {
        path: 'homework/overview',
        name: 'HomeworkOverview',
        component: HomeworkOverview
      },
      {
        path: 'homework/publish',
        name: 'HomeworkPublish',
        component: HomeworkPublish
      },
      {
        path: 'homework/grading',
        name: 'HomeworkGrading',
        component: HomeworkGrading
      },
      {
        path: 'homework/grades',
        name: 'HomeworkGrades',
        component: HomeworkGrades
      },
      {
        path: 'homework/submissions',
        name: 'HomeworkSubmissions',
        component: HomeworkSubmissions
      },
      {
        path: 'homework/templates',
        name: 'HomeworkTemplates',
        component: HomeworkTemplates
      },
      
      // 个人设置子路由
      {
        path: 'personal',
        redirect: 'personal/profile'
      },
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
        path: 'system-settings',
        redirect: 'system-settings/basic'
      },
      {
        path: 'system-settings/basic',
        name: 'BasicSettings',
        component: BasicSettings
      },
      {
        path: 'system-settings/email',
        name: 'EmailSettings',
        component: EmailSettings
      },
      {
        path: 'system-settings/security',
        name: 'SystemSecuritySettings',
        component: SystemSecuritySettings
      },
      {
        path: 'system-settings/backup',
        name: 'BackupSettings',
        component: BackupSettings
      },
      {
        path: 'system-settings/logs',
        name: 'LogsSettings',
        component: LogsSettings
      },
      {
        path: 'system-settings/info',
        name: 'SystemInfo',
        component: SystemInfo
      },

      // 学情分析子路由
      {
        path: 'analysis',
        redirect: 'analysis/overview'
      },
      {
        path: 'analysis/overview',
        name: 'AnalysisOverview',
        component: AnalysisOverview
      },
      {
        path: 'analysis/progress',
        name: 'LearningProgress',
        component: LearningProgress
      },
      {
        path: 'analysis/performance',
        name: 'PerformanceAnalysis',
        component: PerformanceAnalysis
      },
      {
        path: 'analysis/engagement',
        name: 'EngagementAnalysis',
        component: EngagementAnalysis
      },
      {
        path: 'analysis/knowledge',
        name: 'KnowledgeMap',
        component: KnowledgeMap
      },
      {
        path: 'analysis/report',
        name: 'LearningReport',
        component: LearningReport
      },

      // 教学资源子路由
      {
        path: 'resources',
        redirect: 'resources/overview'
      },
      {
        path: 'resources/overview',
        name: 'ResourcesOverview',
        component: ResourcesOverview
      },
      {
        path: 'resources/materials',
        name: 'ResourcesMaterials',
        component: ResourcesMaterials
      },
      {
        path: 'resources/videos',
        name: 'ResourcesVideos',
        component: ResourcesVideos
      },
      {
        path: 'resources/documents',
        name: 'ResourcesDocuments',
        component: ResourcesDocuments
      },
      {
        path: 'resources/templates',
        name: 'ResourcesTemplates',
        component: ResourcesTemplates
      },
      {
        path: 'resources/shared',
        name: 'ResourcesShared',
        component: ResourcesShared
      },

      // 题库管理子路由
      {
        path: 'questions',
        redirect: 'questions/overview'
      },
      {
        path: 'questions/overview',
        name: 'QuestionsOverview',
        component: QuestionsOverview
      },
      {
        path: 'questions/bank',
        name: 'QuestionsBank',
        component: QuestionsBank
      },
      {
        path: 'questions/category',
        name: 'QuestionsCategory',
        component: QuestionsCategory
      },
      {
        path: 'questions/tags',
        name: 'QuestionsTags',
        component: QuestionsTags
      },
      {
        path: 'questions/import',
        name: 'QuestionsImport',
        component: () => import('../views/questions/QuestionsImport.vue')
      },
      {
        path: 'questions/export',
        name: 'QuestionsExport',
        component: () => import('../views/questions/QuestionsExport.vue')
      },
      // AI对话相关路由
      {
        path: 'ai/assistant',
        name: 'ai-chat-assistant',
        component: AIChatAssistant
      },
      {
        path: 'ai/qa',
        name: 'ai-chat-qa',
        component: AIChatQA
      },
      {
        path: 'ai/writing',
        name: 'ai-chat-writing',
        component: AIChatWriting
      },
      {
        path: 'ai/analysis',
        name: 'ai-chat-analysis',
        component: AIChatAnalysis
      },
      {
        path: 'ai/history',
        name: 'ai-chat-history',
        component: AIChatHistory
      },
      // AI批改相关路由
      {
        path: 'ai/grading/overview',
        name: 'ai-grading-overview',
        component: GradingOverview
      },
      {
        path: 'ai/grading/homework',
        name: 'ai-grading-homework',
        component: GradingHomework
      },
      {
        path: 'ai/grading/exam',
        name: 'ai-grading-exam',
        component: GradingExam
      },
      {
        path: 'ai/grading/code',
        name: 'ai-grading-code',
        component: GradingCode
      },
      {
        path: 'ai/grading/template',
        name: 'ai-grading-template',
        component: GradingTemplate
      },
      // 学习推荐
      {
        path: 'ai/recommend/overview',
        name: 'RecommendOverview',
        component: RecommendOverview
      },
      {
        path: 'ai/recommend/course',
        name: 'RecommendCourse',
        component: RecommendCourse
      },
      {
        path: 'ai/recommend/resource',
        name: 'RecommendResource',
        component: RecommendResource
      },
      {
        path: 'ai/recommend/exercise',
        name: 'RecommendExercise',
        component: RecommendExercise
      },
      {
        path: 'ai/recommend/plan',
        name: 'RecommendPlan',
        component: RecommendPlan
      },
      {
        path: 'homework/statistics',
        name: 'HomeworkSubmissions',
        component: HomeworkSubmissions
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

// 路由守卫：检查是否需要登录和权限
router.beforeEach(async (to, from, next) => {
  // 如果是登录页面或403页面，直接通过
  if (to.path === '/login' || to.path === '/403') {
    next();
    return;
  }

  const token = localStorage.getItem('token');
  const userRole = localStorage.getItem('userRole');
  const permissionPaths = JSON.parse(localStorage.getItem('permissionPaths') || '[]');
  
  if (to.meta.requiresAuth && !token) {
    // 需要登录但没有token，跳转到登录页
    next('/login');
  } else if (to.meta.requiresAuth) {
    // 权限验证
    if (userRole === 'admin') {
      // 管理员拥有所有权限
      next();
    } else {
      // 检查是否有访问该路由的权限
      const hasPermission = permissionPaths.some(path => {
        // 完全匹配
        if (path === to.path) return true;
        
        // 检查父级路径
        const pathParts = to.path.split('/').filter(Boolean);
        let currentPath = '';
        for (const part of pathParts) {
          currentPath += '/' + part;
          if (path === currentPath) return true;
        }
        return false;
      });

      if (hasPermission) {
        next();
      } else {
        next('/403');
      }
    }
  } else {
    // 不需要权限验证的路由
    next();
  }
});

// 检查是否有权限访问路由
function hasPermission(permissions, path) {
  // 检查完整路径匹配
  const hasDirectPermission = permissions.some(p => p.routePath === path);
  if (hasDirectPermission) {
    return true;
  }

  // 检查父级路径权限
  const pathParts = path.split('/').filter(Boolean);
  let currentPath = '';
  for (const part of pathParts) {
    currentPath += '/' + part;
    const hasParentPermission = permissions.some(p => p.routePath === currentPath);
    if (hasParentPermission) {
      return true;
    }
  }

  return false;
}

export default router 