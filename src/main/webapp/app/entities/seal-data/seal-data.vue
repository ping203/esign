<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('esignApp.sealData.home.title')" id="seal-data-heading">Seal Data</span>
            <router-link :to="{name: 'SealDataCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-seal-data">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('esignApp.sealData.home.createLabel')">
                    Create a new Seal Data
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
        <div class="alert alert-warning" v-if="!isFetching && sealData && sealData.length === 0">
            <span v-text="$t('esignApp.sealData.home.notFound')">No sealData found</span>
        </div>
        <div class="table-responsive" v-if="sealData && sealData.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('base64Str')"><span v-text="$t('esignApp.sealData.base64Str')">Base 64 Str</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'base64Str'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('pdfSign.id')"><span v-text="$t('esignApp.sealData.pdfSign')">Pdf Sign</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'pdfSign.id'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="sealData in sealData"
                    :key="sealData.id">
                    <td>
                        <router-link :to="{name: 'SealDataView', params: {sealDataId: sealData.id}}">{{sealData.id}}</router-link>
                    </td>
                    <td>
                        <a v-if="sealData.base64Str" v-on:click="openFile(sealData.base64StrContentType, sealData.base64Str)">
                            <img v-bind:src="'data:' + sealData.base64StrContentType + ';base64,' + sealData.base64Str" style="max-height: 30px;" alt="sealData image"/>
                        </a>
                        <span v-if="sealData.base64Str">{{sealData.base64StrContentType}}, {{byteSize(sealData.base64Str)}}</span>
                    </td>
                    <td>
                        <div v-if="sealData.pdfSign">
                            <router-link :to="{name: 'PdfSignView', params: {pdfSignId: sealData.pdfSign.id}}">{{sealData.pdfSign.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'SealDataView', params: {sealDataId: sealData.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'SealDataEdit', params: {sealDataId: sealData.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(sealData)"
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
            <span slot="modal-title"><span id="esignApp.sealData.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-sealData-heading" v-text="$t('esignApp.sealData.delete.question', {'id': removeId})">Are you sure you want to delete this Seal Data?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-sealData" v-text="$t('entity.action.delete')" v-on:click="removeSealData()">Delete</button>
            </div>
        </b-modal>
        <div v-show="sealData && sealData.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./seal-data.component.ts">
</script>
