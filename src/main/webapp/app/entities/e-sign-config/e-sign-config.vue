<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('esignApp.eSignConfig.home.title')" id="e-sign-config-heading">E Sign Configs</span>
            <router-link :to="{name: 'ESignConfigCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-e-sign-config">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('esignApp.eSignConfig.home.createLabel')">
                    Create a new E Sign Config
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
        <div class="alert alert-warning" v-if="!isFetching && eSignConfigs && eSignConfigs.length === 0">
            <span v-text="$t('esignApp.eSignConfig.home.notFound')">No eSignConfigs found</span>
        </div>
        <div class="table-responsive" v-if="eSignConfigs && eSignConfigs.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('name')"><span v-text="$t('esignApp.eSignConfig.name')">Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('time')"><span v-text="$t('esignApp.eSignConfig.time')">Time</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'time'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('channelNo')"><span v-text="$t('esignApp.eSignConfig.channelNo')">Channel No</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'channelNo'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('accessId')"><span v-text="$t('esignApp.eSignConfig.accessId')">Access Id</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'accessId'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('accessKeySecret')"><span v-text="$t('esignApp.eSignConfig.accessKeySecret')">Access Key Secret</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'accessKeySecret'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="eSignConfig in eSignConfigs"
                    :key="eSignConfig.id">
                    <td>
                        <router-link :to="{name: 'ESignConfigView', params: {eSignConfigId: eSignConfig.id}}">{{eSignConfig.id}}</router-link>
                    </td>
                    <td>{{eSignConfig.name}}</td>
                    <td>{{eSignConfig.time ? $d(Date.parse(eSignConfig.time), 'short') : ''}}</td>
                    <td>{{eSignConfig.channelNo}}</td>
                    <td>{{eSignConfig.accessId}}</td>
                    <td>{{eSignConfig.accessKeySecret}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ESignConfigView', params: {eSignConfigId: eSignConfig.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'ESignConfigEdit', params: {eSignConfigId: eSignConfig.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(eSignConfig)"
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
            <span slot="modal-title"><span id="esignApp.eSignConfig.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-eSignConfig-heading" v-text="$t('esignApp.eSignConfig.delete.question', {'id': removeId})">Are you sure you want to delete this E Sign Config?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-eSignConfig" v-text="$t('entity.action.delete')" v-on:click="removeESignConfig()">Delete</button>
            </div>
        </b-modal>
        <div v-show="eSignConfigs && eSignConfigs.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./e-sign-config.component.ts">
</script>
