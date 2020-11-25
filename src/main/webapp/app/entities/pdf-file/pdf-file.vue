<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('esignApp.pdfFile.home.title')" id="pdf-file-heading">Pdf Files</span>
            <router-link :to="{name: 'PdfFileCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-pdf-file">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('esignApp.pdfFile.home.createLabel')">
                    Create a new Pdf File
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
        <div class="alert alert-warning" v-if="!isFetching && pdfFiles && pdfFiles.length === 0">
            <span v-text="$t('esignApp.pdfFile.home.notFound')">No pdfFiles found</span>
        </div>
        <div class="table-responsive" v-if="pdfFiles && pdfFiles.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('name')"><span v-text="$t('esignApp.pdfFile.name')">Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('mediaType')"><span v-text="$t('esignApp.pdfFile.mediaType')">Media Type</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'mediaType'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('objName')"><span v-text="$t('esignApp.pdfFile.objName')">Obj Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'objName'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('fileUrl')"><span v-text="$t('esignApp.pdfFile.fileUrl')">File Url</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'fileUrl'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('key')"><span v-text="$t('esignApp.pdfFile.key')">Key</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'key'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('creator.id')"><span v-text="$t('esignApp.pdfFile.creator')">Creator</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'creator.id'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="pdfFile in pdfFiles"
                    :key="pdfFile.id">
                    <td>
                        <router-link :to="{name: 'PdfFileView', params: {pdfFileId: pdfFile.id}}">{{pdfFile.id}}</router-link>
                    </td>
                    <td>{{pdfFile.name}}</td>
                    <td>{{pdfFile.mediaType}}</td>
                    <td>{{pdfFile.objName}}</td>
                    <td>{{pdfFile.fileUrl}}</td>
                    <td>{{pdfFile.key}}</td>
                    <td>
                        <div v-if="pdfFile.creator">
                            <router-link :to="{name: 'DdUserView', params: {ddUserId: pdfFile.creator.id}}">{{pdfFile.creator.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'PdfFileView', params: {pdfFileId: pdfFile.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'PdfFileEdit', params: {pdfFileId: pdfFile.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(pdfFile)"
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
            <span slot="modal-title"><span id="esignApp.pdfFile.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-pdfFile-heading" v-text="$t('esignApp.pdfFile.delete.question', {'id': removeId})">Are you sure you want to delete this Pdf File?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-pdfFile" v-text="$t('entity.action.delete')" v-on:click="removePdfFile()">Delete</button>
            </div>
        </b-modal>
        <div v-show="pdfFiles && pdfFiles.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./pdf-file.component.ts">
</script>
