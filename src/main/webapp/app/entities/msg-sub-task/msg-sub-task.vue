<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('esignApp.msgSubTask.home.title')" id="msg-sub-task-heading">Msg Sub Tasks</span>
            <router-link :to="{name: 'MsgSubTaskCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-msg-sub-task">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('esignApp.msgSubTask.home.createLabel')">
                    Create a new Msg Sub Task
                </span>
            </router-link>
        </h2>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && msgSubTasks && msgSubTasks.length === 0">
            <span v-text="$t('esignApp.msgSubTask.home.notFound')">No msgSubTasks found</span>
        </div>
        <div class="table-responsive" v-if="msgSubTasks && msgSubTasks.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('useridList')"><span v-text="$t('esignApp.msgSubTask.useridList')">Userid List</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'useridList'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('taskId')"><span v-text="$t('esignApp.msgSubTask.taskId')">Task Id</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'taskId'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('time')"><span v-text="$t('esignApp.msgSubTask.time')">Time</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'time'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('rspMsg')"><span v-text="$t('esignApp.msgSubTask.rspMsg')">Rsp Msg</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'rspMsg'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('status')"><span v-text="$t('esignApp.msgSubTask.status')">Status</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'status'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('progressInPercent')"><span v-text="$t('esignApp.msgSubTask.progressInPercent')">Progress In Percent</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'progressInPercent'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('subTaskStatus')"><span v-text="$t('esignApp.msgSubTask.subTaskStatus')">Sub Task Status</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'subTaskStatus'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('invalidUserIdList')"><span v-text="$t('esignApp.msgSubTask.invalidUserIdList')">Invalid User Id List</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'invalidUserIdList'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('forbiddenUserIdList')"><span v-text="$t('esignApp.msgSubTask.forbiddenUserIdList')">Forbidden User Id List</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'forbiddenUserIdList'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('failedUserIdList')"><span v-text="$t('esignApp.msgSubTask.failedUserIdList')">Failed User Id List</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'failedUserIdList'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('readUserIdList')"><span v-text="$t('esignApp.msgSubTask.readUserIdList')">Read User Id List</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'readUserIdList'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('unreadUserIdList')"><span v-text="$t('esignApp.msgSubTask.unreadUserIdList')">Unread User Id List</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'unreadUserIdList'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('invalidDeptIdList')"><span v-text="$t('esignApp.msgSubTask.invalidDeptIdList')">Invalid Dept Id List</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'invalidDeptIdList'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('withdraw')"><span v-text="$t('esignApp.msgSubTask.withdraw')">Withdraw</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'withdraw'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('msgTask.id')"><span v-text="$t('esignApp.msgSubTask.msgTask')">Msg Task</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'msgTask.id'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="msgSubTask in msgSubTasks"
                    :key="msgSubTask.id">
                    <td>
                        <router-link :to="{name: 'MsgSubTaskView', params: {msgSubTaskId: msgSubTask.id}}">{{msgSubTask.id}}</router-link>
                    </td>
                    <td>{{msgSubTask.useridList}}</td>
                    <td>{{msgSubTask.taskId}}</td>
                    <td>{{msgSubTask.time ? $d(Date.parse(msgSubTask.time), 'short') : ''}}</td>
                    <td>{{msgSubTask.rspMsg}}</td>
                    <td>{{msgSubTask.status}}</td>
                    <td>{{msgSubTask.progressInPercent}}</td>
                    <td v-text="$t('esignApp.MessageStatus.' + msgSubTask.subTaskStatus)">{{msgSubTask.subTaskStatus}}</td>
                    <td>{{msgSubTask.invalidUserIdList}}</td>
                    <td>{{msgSubTask.forbiddenUserIdList}}</td>
                    <td>{{msgSubTask.failedUserIdList}}</td>
                    <td>{{msgSubTask.readUserIdList}}</td>
                    <td>{{msgSubTask.unreadUserIdList}}</td>
                    <td>{{msgSubTask.invalidDeptIdList}}</td>
                    <td>{{msgSubTask.withdraw}}</td>
                    <td>
                        <div v-if="msgSubTask.msgTask">
                            <router-link :to="{name: 'MsgTaskView', params: {msgTaskId: msgSubTask.msgTask.id}}">{{msgSubTask.msgTask.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'MsgSubTaskView', params: {msgSubTaskId: msgSubTask.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'MsgSubTaskEdit', params: {msgSubTaskId: msgSubTask.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(msgSubTask)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="esignApp.msgSubTask.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-msgSubTask-heading" v-text="$t('esignApp.msgSubTask.delete.question', {'id': removeId})">Are you sure you want to delete this Msg Sub Task?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-msgSubTask" v-text="$t('entity.action.delete')" v-on:click="removeMsgSubTask()">Delete</button>
            </div>
        </b-modal>
        <div v-show="msgSubTasks && msgSubTasks.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./msg-sub-task.component.ts">
</script>
