import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const DdUser = () => import('@/entities/dd-user/dd-user.vue');
// prettier-ignore
const DdUserUpdate = () => import('@/entities/dd-user/dd-user-update.vue');
// prettier-ignore
const DdUserDetails = () => import('@/entities/dd-user/dd-user-details.vue');
// prettier-ignore
const MsgTask = () => import('@/entities/msg-task/msg-task.vue');
// prettier-ignore
const MsgTaskUpdate = () => import('@/entities/msg-task/msg-task-update.vue');
// prettier-ignore
const MsgTaskDetails = () => import('@/entities/msg-task/msg-task-details.vue');
// prettier-ignore
const MsgSubTask = () => import('@/entities/msg-sub-task/msg-sub-task.vue');
// prettier-ignore
const MsgSubTaskUpdate = () => import('@/entities/msg-sub-task/msg-sub-task-update.vue');
// prettier-ignore
const MsgSubTaskDetails = () => import('@/entities/msg-sub-task/msg-sub-task-details.vue');
// prettier-ignore
const PdfFile = () => import('@/entities/pdf-file/pdf-file.vue');
// prettier-ignore
const PdfFileUpdate = () => import('@/entities/pdf-file/pdf-file-update.vue');
// prettier-ignore
const PdfFileDetails = () => import('@/entities/pdf-file/pdf-file-details.vue');
// prettier-ignore
const PdfSign = () => import('@/entities/pdf-sign/pdf-sign.vue');
// prettier-ignore
const PdfSignUpdate = () => import('@/entities/pdf-sign/pdf-sign-update.vue');
// prettier-ignore
const PdfSignDetails = () => import('@/entities/pdf-sign/pdf-sign-details.vue');
// prettier-ignore
const SealData = () => import('@/entities/seal-data/seal-data.vue');
// prettier-ignore
const SealDataUpdate = () => import('@/entities/seal-data/seal-data-update.vue');
// prettier-ignore
const SealDataDetails = () => import('@/entities/seal-data/seal-data-details.vue');
// prettier-ignore
const ESignConfig = () => import('@/entities/e-sign-config/e-sign-config.vue');
// prettier-ignore
const ESignConfigUpdate = () => import('@/entities/e-sign-config/e-sign-config-update.vue');
// prettier-ignore
const ESignConfigDetails = () => import('@/entities/e-sign-config/e-sign-config-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/dd-user',
    name: 'DdUser',
    component: DdUser,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/dd-user/new',
    name: 'DdUserCreate',
    component: DdUserUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/dd-user/:ddUserId/edit',
    name: 'DdUserEdit',
    component: DdUserUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/dd-user/:ddUserId/view',
    name: 'DdUserView',
    component: DdUserDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/msg-task',
    name: 'MsgTask',
    component: MsgTask,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/msg-task/new',
    name: 'MsgTaskCreate',
    component: MsgTaskUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/msg-task/:msgTaskId/edit',
    name: 'MsgTaskEdit',
    component: MsgTaskUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/msg-task/:msgTaskId/view',
    name: 'MsgTaskView',
    component: MsgTaskDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/msg-sub-task',
    name: 'MsgSubTask',
    component: MsgSubTask,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/msg-sub-task/new',
    name: 'MsgSubTaskCreate',
    component: MsgSubTaskUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/msg-sub-task/:msgSubTaskId/edit',
    name: 'MsgSubTaskEdit',
    component: MsgSubTaskUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/msg-sub-task/:msgSubTaskId/view',
    name: 'MsgSubTaskView',
    component: MsgSubTaskDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/pdf-file',
    name: 'PdfFile',
    component: PdfFile,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/pdf-file/new',
    name: 'PdfFileCreate',
    component: PdfFileUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/pdf-file/:pdfFileId/edit',
    name: 'PdfFileEdit',
    component: PdfFileUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/pdf-file/:pdfFileId/view',
    name: 'PdfFileView',
    component: PdfFileDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/pdf-sign',
    name: 'PdfSign',
    component: PdfSign,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/pdf-sign/new',
    name: 'PdfSignCreate',
    component: PdfSignUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/pdf-sign/:pdfSignId/edit',
    name: 'PdfSignEdit',
    component: PdfSignUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/pdf-sign/:pdfSignId/view',
    name: 'PdfSignView',
    component: PdfSignDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/seal-data',
    name: 'SealData',
    component: SealData,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/seal-data/new',
    name: 'SealDataCreate',
    component: SealDataUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/seal-data/:sealDataId/edit',
    name: 'SealDataEdit',
    component: SealDataUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/seal-data/:sealDataId/view',
    name: 'SealDataView',
    component: SealDataDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/e-sign-config',
    name: 'ESignConfig',
    component: ESignConfig,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/e-sign-config/new',
    name: 'ESignConfigCreate',
    component: ESignConfigUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/e-sign-config/:eSignConfigId/edit',
    name: 'ESignConfigEdit',
    component: ESignConfigUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/e-sign-config/:eSignConfigId/view',
    name: 'ESignConfigView',
    component: ESignConfigDetails,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];