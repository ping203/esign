<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('esignApp.apiInvokeLog.home.title')" id="api-invoke-log-heading">Api Invoke Logs</span>
            <router-link :to="{name: 'ApiInvokeLogCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-api-invoke-log">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('esignApp.apiInvokeLog.home.createLabel')">
                    Create a new Api Invoke Log
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
        <div class="alert alert-warning" v-if="!isFetching && apiInvokeLogs && apiInvokeLogs.length === 0">
            <span v-text="$t('esignApp.apiInvokeLog.home.notFound')">No apiInvokeLogs found</span>
        </div>
        <div class="table-responsive" v-if="apiInvokeLogs && apiInvokeLogs.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('login')"><span v-text="$t('esignApp.apiInvokeLog.login')">Login</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'login'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('apiName')"><span v-text="$t('esignApp.apiInvokeLog.apiName')">Api Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'apiName'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('httpStatusCode')"><span v-text="$t('esignApp.apiInvokeLog.httpStatusCode')">Http Status Code</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'httpStatusCode'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('time')"><span v-text="$t('esignApp.apiInvokeLog.time')">Time</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'time'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('reqeustData')"><span v-text="$t('esignApp.apiInvokeLog.reqeustData')">Reqeust Data</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'reqeustData'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('responseData')"><span v-text="$t('esignApp.apiInvokeLog.responseData')">Response Data</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'responseData'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="apiInvokeLog in apiInvokeLogs"
                    :key="apiInvokeLog.id">
                    <td>
                        <router-link :to="{name: 'ApiInvokeLogView', params: {apiInvokeLogId: apiInvokeLog.id}}">{{apiInvokeLog.id}}</router-link>
                    </td>
                    <td>{{apiInvokeLog.login}}</td>
                    <td>{{apiInvokeLog.apiName}}</td>
                    <td>{{apiInvokeLog.httpStatusCode}}</td>
                    <td>{{apiInvokeLog.time ? $d(Date.parse(apiInvokeLog.time), 'short') : ''}}</td>
                    <td>{{apiInvokeLog.reqeustData}}</td>
                    <td>{{apiInvokeLog.responseData}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ApiInvokeLogView', params: {apiInvokeLogId: apiInvokeLog.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'ApiInvokeLogEdit', params: {apiInvokeLogId: apiInvokeLog.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(apiInvokeLog)"
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
            <span slot="modal-title"><span id="esignApp.apiInvokeLog.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-apiInvokeLog-heading" v-text="$t('esignApp.apiInvokeLog.delete.question', {'id': removeId})">Are you sure you want to delete this Api Invoke Log?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-apiInvokeLog" v-text="$t('entity.action.delete')" v-on:click="removeApiInvokeLog()">Delete</button>
            </div>
        </b-modal>
        <div v-show="apiInvokeLogs && apiInvokeLogs.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./api-invoke-log.component.ts">
</script>
