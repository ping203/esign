<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="pdfSign">
                <h2 class="jh-entity-heading"><span v-text="$t('esignApp.pdfSign.detail.title')">PdfSign</span> {{pdfSign.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('esignApp.pdfSign.channelNo')">Channel No</span>
                    </dt>
                    <dd>
                        <span>{{pdfSign.channelNo}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.pdfSign.accountId')">Account Id</span>
                    </dt>
                    <dd>
                        <span>{{pdfSign.accountId}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.pdfSign.pdfUrl')">Pdf Url</span>
                    </dt>
                    <dd>
                        <span>{{pdfSign.pdfUrl}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.pdfSign.fileName')">File Name</span>
                    </dt>
                    <dd>
                        <span>{{pdfSign.fileName}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.pdfSign.key')">Key</span>
                    </dt>
                    <dd>
                        <span>{{pdfSign.key}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.pdfSign.posType')">Pos Type</span>
                    </dt>
                    <dd>
                        <span>{{pdfSign.posType}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.pdfSign.posX')">Pos X</span>
                    </dt>
                    <dd>
                        <span>{{pdfSign.posX}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.pdfSign.posY')">Pos Y</span>
                    </dt>
                    <dd>
                        <span>{{pdfSign.posY}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.pdfSign.width')">Width</span>
                    </dt>
                    <dd>
                        <span>{{pdfSign.width}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.pdfSign.signType')">Sign Type</span>
                    </dt>
                    <dd>
                        <span>{{pdfSign.signType}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.pdfSign.requestNo')">Request No</span>
                    </dt>
                    <dd>
                        <span>{{pdfSign.requestNo}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.pdfSign.requestTime')">Request Time</span>
                    </dt>
                    <dd>
                        <span v-if="pdfSign.requestTime">{{$d(Date.parse(pdfSign.requestTime), 'long') }}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.pdfSign.status')">Status</span>
                    </dt>
                    <dd>
                        <span v-text="$t('esignApp.PdfSignStatus.' + pdfSign.status)">{{pdfSign.status}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.pdfSign.cycle')">Cycle</span>
                    </dt>
                    <dd>
                        <span>{{pdfSign.cycle}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.pdfSign.cycleUnit')">Cycle Unit</span>
                    </dt>
                    <dd>
                        <span v-text="$t('esignApp.CycleUnit.' + pdfSign.cycleUnit)">{{pdfSign.cycleUnit}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.pdfSign.retry')">Retry</span>
                    </dt>
                    <dd>
                        <span>{{pdfSign.retry}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.pdfSign.retrySwitch')">Retry Switch</span>
                    </dt>
                    <dd>
                        <span>{{pdfSign.retrySwitch}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.pdfSign.ddUser')">Dd User</span>
                    </dt>
                    <dd>
                        <div v-if="pdfSign.ddUser">
                            <router-link :to="{name: 'DdUserView', params: {ddUserId: pdfSign.ddUser.id}}">{{pdfSign.ddUser.id}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.pdfSign.pdfFile')">Pdf File</span>
                    </dt>
                    <dd>
                        <div v-if="pdfSign.pdfFile">
                            <router-link :to="{name: 'PdfFileView', params: {pdfFileId: pdfSign.pdfFile.id}}">{{pdfSign.pdfFile.id}}</router-link>
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.pdfSign.msgTask')">Msg Task</span>
                    </dt>
                    <dd>
                        <div v-if="pdfSign.msgTask">
                            <router-link :to="{name: 'MsgTaskView', params: {msgTaskId: pdfSign.msgTask.id}}">{{pdfSign.msgTask.id}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="pdfSign.id" :to="{name: 'PdfSignEdit', params: {pdfSignId: pdfSign.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./pdf-sign-details.component.ts">
</script>
