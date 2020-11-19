<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('esignApp.msgTask.home.title')" id="msg-task-heading">Msg Tasks</span>
            <router-link :to="{name: 'MsgTaskCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-msg-task">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('esignApp.msgTask.home.createLabel')">
                    Create a new Msg Task
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
        <div class="alert alert-warning" v-if="!isFetching && msgTasks && msgTasks.length === 0">
            <span v-text="$t('esignApp.msgTask.home.notFound')">No msgTasks found</span>
        </div>
        <div class="table-responsive" v-if="msgTasks && msgTasks.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('title')"><span v-text="$t('esignApp.msgTask.title')">Title</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'title'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('deptIdList')"><span v-text="$t('esignApp.msgTask.deptIdList')">Dept Id List</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'deptIdList'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('useridList')"><span v-text="$t('esignApp.msgTask.useridList')">Userid List</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'useridList'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('toAllUser')"><span v-text="$t('esignApp.msgTask.toAllUser')">To All User</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'toAllUser'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('markdown')"><span v-text="$t('esignApp.msgTask.markdown')">Markdown</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'markdown'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('singleTitle')"><span v-text="$t('esignApp.msgTask.singleTitle')">Single Title</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'singleTitle'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('singleUrl')"><span v-text="$t('esignApp.msgTask.singleUrl')">Single Url</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'singleUrl'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('pdfUrl')"><span v-text="$t('esignApp.msgTask.pdfUrl')">Pdf Url</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'pdfUrl'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('coverUrl')"><span v-text="$t('esignApp.msgTask.coverUrl')">Cover Url</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'coverUrl'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('numberOfShards')"><span v-text="$t('esignApp.msgTask.numberOfShards')">Number Of Shards</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'numberOfShards'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('completeSharding')"><span v-text="$t('esignApp.msgTask.completeSharding')">Complete Sharding</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'completeSharding'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('msg')"><span v-text="$t('esignApp.msgTask.msg')">Msg</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'msg'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('executeTime')"><span v-text="$t('esignApp.msgTask.executeTime')">Execute Time</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'executeTime'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('agentId')"><span v-text="$t('esignApp.msgTask.agentId')">Agent Id</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'agentId'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('type')"><span v-text="$t('esignApp.msgTask.type')">Type</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'type'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('status')"><span v-text="$t('esignApp.msgTask.status')">Status</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'status'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('progressInPercent')"><span v-text="$t('esignApp.msgTask.progressInPercent')">Progress In Percent</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'progressInPercent'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('sender.id')"><span v-text="$t('esignApp.msgTask.sender')">Sender</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'sender.id'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="msgTask in msgTasks"
                    :key="msgTask.id">
                    <td>
                        <router-link :to="{name: 'MsgTaskView', params: {msgTaskId: msgTask.id}}">{{msgTask.id}}</router-link>
                    </td>
                    <td>{{msgTask.title}}</td>
                    <td>{{msgTask.deptIdList}}</td>
                    <td>{{msgTask.useridList}}</td>
                    <td>{{msgTask.toAllUser}}</td>
                    <td>{{msgTask.markdown}}</td>
                    <td>{{msgTask.singleTitle}}</td>
                    <td>{{msgTask.singleUrl}}</td>
                    <td>{{msgTask.pdfUrl}}</td>
                    <td>{{msgTask.coverUrl}}</td>
                    <td>{{msgTask.numberOfShards}}</td>
                    <td>{{msgTask.completeSharding}}</td>
                    <td>{{msgTask.msg}}</td>
                    <td>{{msgTask.executeTime ? $d(Date.parse(msgTask.executeTime), 'short') : ''}}</td>
                    <td>{{msgTask.agentId}}</td>
                    <td v-text="$t('esignApp.DdMessageType.' + msgTask.type)">{{msgTask.type}}</td>
                    <td v-text="$t('esignApp.MessageStatus.' + msgTask.status)">{{msgTask.status}}</td>
                    <td>{{msgTask.progressInPercent}}</td>
                    <td>
                        <div v-if="msgTask.sender">
                            <router-link :to="{name: 'DdUserView', params: {ddUserId: msgTask.sender.id}}">{{msgTask.sender.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'MsgTaskView', params: {msgTaskId: msgTask.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'MsgTaskEdit', params: {msgTaskId: msgTask.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(msgTask)"
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
            <span slot="modal-title"><span id="esignApp.msgTask.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-msgTask-heading" v-text="$t('esignApp.msgTask.delete.question', {'id': removeId})">Are you sure you want to delete this Msg Task?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-msgTask" v-text="$t('entity.action.delete')" v-on:click="removeMsgTask()">Delete</button>
            </div>
        </b-modal>
        <div v-show="msgTasks && msgTasks.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./msg-task.component.ts">
</script>
