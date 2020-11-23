<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('esignApp.pdfSign.home.title')" id="pdf-sign-heading">Pdf Signs</span>
            <router-link :to="{name: 'PdfSignCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-pdf-sign">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('esignApp.pdfSign.home.createLabel')">
                    Create a new Pdf Sign
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
        <div class="alert alert-warning" v-if="!isFetching && pdfSigns && pdfSigns.length === 0">
            <span v-text="$t('esignApp.pdfSign.home.notFound')">No pdfSigns found</span>
        </div>
        <div class="table-responsive" v-if="pdfSigns && pdfSigns.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('channelNo')"><span v-text="$t('esignApp.pdfSign.channelNo')">Channel No</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'channelNo'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('accountId')"><span v-text="$t('esignApp.pdfSign.accountId')">Account Id</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'accountId'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('pdfUrl')"><span v-text="$t('esignApp.pdfSign.pdfUrl')">Pdf Url</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'pdfUrl'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('fileName')"><span v-text="$t('esignApp.pdfSign.fileName')">File Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'fileName'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('key')"><span v-text="$t('esignApp.pdfSign.key')">Key</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'key'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('posType')"><span v-text="$t('esignApp.pdfSign.posType')">Pos Type</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'posType'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('posX')"><span v-text="$t('esignApp.pdfSign.posX')">Pos X</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'posX'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('posY')"><span v-text="$t('esignApp.pdfSign.posY')">Pos Y</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'posY'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('width')"><span v-text="$t('esignApp.pdfSign.width')">Width</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'width'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('signType')"><span v-text="$t('esignApp.pdfSign.signType')">Sign Type</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'signType'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('requestNo')"><span v-text="$t('esignApp.pdfSign.requestNo')">Request No</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'requestNo'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('requestTime')"><span v-text="$t('esignApp.pdfSign.requestTime')">Request Time</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'requestTime'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('status')"><span v-text="$t('esignApp.pdfSign.status')">Status</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'status'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('cycle')"><span v-text="$t('esignApp.pdfSign.cycle')">Cycle</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'cycle'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('cycleUnit')"><span v-text="$t('esignApp.pdfSign.cycleUnit')">Cycle Unit</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'cycleUnit'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('retry')"><span v-text="$t('esignApp.pdfSign.retry')">Retry</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'retry'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('retrySwitch')"><span v-text="$t('esignApp.pdfSign.retrySwitch')">Retry Switch</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'retrySwitch'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('retryCount')"><span v-text="$t('esignApp.pdfSign.retryCount')">Retry Count</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'retryCount'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('retryTime')"><span v-text="$t('esignApp.pdfSign.retryTime')">Retry Time</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'retryTime'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('ddUser.id')"><span v-text="$t('esignApp.pdfSign.ddUser')">Dd User</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'ddUser.id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('pdfFile.id')"><span v-text="$t('esignApp.pdfSign.pdfFile')">Pdf File</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'pdfFile.id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('msgTask.id')"><span v-text="$t('esignApp.pdfSign.msgTask')">Msg Task</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'msgTask.id'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="pdfSign in pdfSigns"
                    :key="pdfSign.id">
                    <td>
                        <router-link :to="{name: 'PdfSignView', params: {pdfSignId: pdfSign.id}}">{{pdfSign.id}}</router-link>
                    </td>
                    <td>{{pdfSign.channelNo}}</td>
                    <td>{{pdfSign.accountId}}</td>
                    <td>{{pdfSign.pdfUrl}}</td>
                    <td>{{pdfSign.fileName}}</td>
                    <td>{{pdfSign.key}}</td>
                    <td>{{pdfSign.posType}}</td>
                    <td>{{pdfSign.posX}}</td>
                    <td>{{pdfSign.posY}}</td>
                    <td>{{pdfSign.width}}</td>
                    <td>{{pdfSign.signType}}</td>
                    <td>{{pdfSign.requestNo}}</td>
                    <td>{{pdfSign.requestTime ? $d(Date.parse(pdfSign.requestTime), 'short') : ''}}</td>
                    <td v-text="$t('esignApp.PdfSignStatus.' + pdfSign.status)">{{pdfSign.status}}</td>
                    <td>{{pdfSign.cycle}}</td>
                    <td v-text="$t('esignApp.CycleUnit.' + pdfSign.cycleUnit)">{{pdfSign.cycleUnit}}</td>
                    <td>{{pdfSign.retry}}</td>
                    <td>{{pdfSign.retrySwitch}}</td>
                    <td>{{pdfSign.retryCount}}</td>
                    <td>{{pdfSign.retryTime ? $d(Date.parse(pdfSign.retryTime), 'short') : ''}}</td>
                    <td>
                        <div v-if="pdfSign.ddUser">
                            <router-link :to="{name: 'DdUserView', params: {ddUserId: pdfSign.ddUser.id}}">{{pdfSign.ddUser.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="pdfSign.pdfFile">
                            <router-link :to="{name: 'PdfFileView', params: {pdfFileId: pdfSign.pdfFile.id}}">{{pdfSign.pdfFile.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="pdfSign.msgTask">
                            <router-link :to="{name: 'MsgTaskView', params: {msgTaskId: pdfSign.msgTask.id}}">{{pdfSign.msgTask.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'PdfSignView', params: {pdfSignId: pdfSign.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'PdfSignEdit', params: {pdfSignId: pdfSign.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(pdfSign)"
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
            <span slot="modal-title"><span id="esignApp.pdfSign.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-pdfSign-heading" v-text="$t('esignApp.pdfSign.delete.question', {'id': removeId})">Are you sure you want to delete this Pdf Sign?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-pdfSign" v-text="$t('entity.action.delete')" v-on:click="removePdfSign()">Delete</button>
            </div>
        </b-modal>
        <div v-show="pdfSigns && pdfSigns.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./pdf-sign.component.ts">
</script>
