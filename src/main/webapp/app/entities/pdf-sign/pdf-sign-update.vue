<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="esignApp.pdfSign.home.createOrEditLabel" v-text="$t('esignApp.pdfSign.home.createOrEditLabel')">Create or edit a PdfSign</h2>
                <div>
                    <div class="form-group" v-if="pdfSign.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="pdfSign.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfSign.channelNo')" for="pdf-sign-channelNo">Channel No</label>
                        <input type="text" class="form-control" name="channelNo" id="pdf-sign-channelNo"
                            :class="{'valid': !$v.pdfSign.channelNo.$invalid, 'invalid': $v.pdfSign.channelNo.$invalid }" v-model="$v.pdfSign.channelNo.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfSign.accountId')" for="pdf-sign-accountId">Account Id</label>
                        <input type="text" class="form-control" name="accountId" id="pdf-sign-accountId"
                            :class="{'valid': !$v.pdfSign.accountId.$invalid, 'invalid': $v.pdfSign.accountId.$invalid }" v-model="$v.pdfSign.accountId.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfSign.pdfUrl')" for="pdf-sign-pdfUrl">Pdf Url</label>
                        <input type="text" class="form-control" name="pdfUrl" id="pdf-sign-pdfUrl"
                            :class="{'valid': !$v.pdfSign.pdfUrl.$invalid, 'invalid': $v.pdfSign.pdfUrl.$invalid }" v-model="$v.pdfSign.pdfUrl.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfSign.fileName')" for="pdf-sign-fileName">File Name</label>
                        <input type="text" class="form-control" name="fileName" id="pdf-sign-fileName"
                            :class="{'valid': !$v.pdfSign.fileName.$invalid, 'invalid': $v.pdfSign.fileName.$invalid }" v-model="$v.pdfSign.fileName.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfSign.key')" for="pdf-sign-key">Key</label>
                        <input type="text" class="form-control" name="key" id="pdf-sign-key"
                            :class="{'valid': !$v.pdfSign.key.$invalid, 'invalid': $v.pdfSign.key.$invalid }" v-model="$v.pdfSign.key.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfSign.posType')" for="pdf-sign-posType">Pos Type</label>
                        <input type="text" class="form-control" name="posType" id="pdf-sign-posType"
                            :class="{'valid': !$v.pdfSign.posType.$invalid, 'invalid': $v.pdfSign.posType.$invalid }" v-model="$v.pdfSign.posType.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfSign.posX')" for="pdf-sign-posX">Pos X</label>
                        <input type="number" class="form-control" name="posX" id="pdf-sign-posX"
                            :class="{'valid': !$v.pdfSign.posX.$invalid, 'invalid': $v.pdfSign.posX.$invalid }" v-model.number="$v.pdfSign.posX.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfSign.posY')" for="pdf-sign-posY">Pos Y</label>
                        <input type="number" class="form-control" name="posY" id="pdf-sign-posY"
                            :class="{'valid': !$v.pdfSign.posY.$invalid, 'invalid': $v.pdfSign.posY.$invalid }" v-model.number="$v.pdfSign.posY.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfSign.width')" for="pdf-sign-width">Width</label>
                        <input type="number" class="form-control" name="width" id="pdf-sign-width"
                            :class="{'valid': !$v.pdfSign.width.$invalid, 'invalid': $v.pdfSign.width.$invalid }" v-model.number="$v.pdfSign.width.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfSign.signType')" for="pdf-sign-signType">Sign Type</label>
                        <input type="text" class="form-control" name="signType" id="pdf-sign-signType"
                            :class="{'valid': !$v.pdfSign.signType.$invalid, 'invalid': $v.pdfSign.signType.$invalid }" v-model="$v.pdfSign.signType.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfSign.requestNo')" for="pdf-sign-requestNo">Request No</label>
                        <input type="text" class="form-control" name="requestNo" id="pdf-sign-requestNo"
                            :class="{'valid': !$v.pdfSign.requestNo.$invalid, 'invalid': $v.pdfSign.requestNo.$invalid }" v-model="$v.pdfSign.requestNo.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfSign.requestTime')" for="pdf-sign-requestTime">Request Time</label>
                        <div class="d-flex">
                            <input id="pdf-sign-requestTime" type="datetime-local" class="form-control" name="requestTime" :class="{'valid': !$v.pdfSign.requestTime.$invalid, 'invalid': $v.pdfSign.requestTime.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.pdfSign.requestTime.$model)"
                            @change="updateInstantField('requestTime', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfSign.status')" for="pdf-sign-status">Status</label>
                        <select class="form-control" name="status" :class="{'valid': !$v.pdfSign.status.$invalid, 'invalid': $v.pdfSign.status.$invalid }" v-model="$v.pdfSign.status.$model" id="pdf-sign-status" >
                            <option value="NotActive" v-bind:label="$t('esignApp.PdfSignStatus.NotActive')">NotActive</option>
                            <option value="Effective" v-bind:label="$t('esignApp.PdfSignStatus.Effective')">Effective</option>
                            <option value="Reminded" v-bind:label="$t('esignApp.PdfSignStatus.Reminded')">Reminded</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfSign.signedTime')" for="pdf-sign-signedTime">Signed Time</label>
                        <div class="d-flex">
                            <input id="pdf-sign-signedTime" type="datetime-local" class="form-control" name="signedTime" :class="{'valid': !$v.pdfSign.signedTime.$invalid, 'invalid': $v.pdfSign.signedTime.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.pdfSign.signedTime.$model)"
                            @change="updateInstantField('signedTime', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfSign.cycle')" for="pdf-sign-cycle">Cycle</label>
                        <input type="number" class="form-control" name="cycle" id="pdf-sign-cycle"
                            :class="{'valid': !$v.pdfSign.cycle.$invalid, 'invalid': $v.pdfSign.cycle.$invalid }" v-model.number="$v.pdfSign.cycle.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfSign.cycleUnit')" for="pdf-sign-cycleUnit">Cycle Unit</label>
                        <select class="form-control" name="cycleUnit" :class="{'valid': !$v.pdfSign.cycleUnit.$invalid, 'invalid': $v.pdfSign.cycleUnit.$invalid }" v-model="$v.pdfSign.cycleUnit.$model" id="pdf-sign-cycleUnit" >
                            <option value="Hour" v-bind:label="$t('esignApp.CycleUnit.Hour')">Hour</option>
                            <option value="Day" v-bind:label="$t('esignApp.CycleUnit.Day')">Day</option>
                            <option value="Week" v-bind:label="$t('esignApp.CycleUnit.Week')">Week</option>
                            <option value="Month" v-bind:label="$t('esignApp.CycleUnit.Month')">Month</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfSign.retry')" for="pdf-sign-retry">Retry</label>
                        <input type="number" class="form-control" name="retry" id="pdf-sign-retry"
                            :class="{'valid': !$v.pdfSign.retry.$invalid, 'invalid': $v.pdfSign.retry.$invalid }" v-model.number="$v.pdfSign.retry.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfSign.retrySwitch')" for="pdf-sign-retrySwitch">Retry Switch</label>
                        <input type="checkbox" class="form-check" name="retrySwitch" id="pdf-sign-retrySwitch"
                            :class="{'valid': !$v.pdfSign.retrySwitch.$invalid, 'invalid': $v.pdfSign.retrySwitch.$invalid }" v-model="$v.pdfSign.retrySwitch.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfSign.retryCount')" for="pdf-sign-retryCount">Retry Count</label>
                        <input type="number" class="form-control" name="retryCount" id="pdf-sign-retryCount"
                            :class="{'valid': !$v.pdfSign.retryCount.$invalid, 'invalid': $v.pdfSign.retryCount.$invalid }" v-model.number="$v.pdfSign.retryCount.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfSign.retryTime')" for="pdf-sign-retryTime">Retry Time</label>
                        <div class="d-flex">
                            <input id="pdf-sign-retryTime" type="datetime-local" class="form-control" name="retryTime" :class="{'valid': !$v.pdfSign.retryTime.$invalid, 'invalid': $v.pdfSign.retryTime.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.pdfSign.retryTime.$model)"
                            @change="updateInstantField('retryTime', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfSign.ddUser')" for="pdf-sign-ddUser">Dd User</label>
                        <select class="form-control" id="pdf-sign-ddUser" name="ddUser" v-model="pdfSign.ddUser">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="pdfSign.ddUser && ddUserOption.id === pdfSign.ddUser.id ? pdfSign.ddUser : ddUserOption" v-for="ddUserOption in ddUsers" :key="ddUserOption.id">{{ddUserOption.id}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfSign.pdfFile')" for="pdf-sign-pdfFile">Pdf File</label>
                        <select class="form-control" id="pdf-sign-pdfFile" name="pdfFile" v-model="pdfSign.pdfFile">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="pdfSign.pdfFile && pdfFileOption.id === pdfSign.pdfFile.id ? pdfSign.pdfFile : pdfFileOption" v-for="pdfFileOption in pdfFiles" :key="pdfFileOption.id">{{pdfFileOption.id}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfSign.msgTask')" for="pdf-sign-msgTask">Msg Task</label>
                        <select class="form-control" id="pdf-sign-msgTask" name="msgTask" v-model="pdfSign.msgTask">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="pdfSign.msgTask && msgTaskOption.id === pdfSign.msgTask.id ? pdfSign.msgTask : msgTaskOption" v-for="msgTaskOption in msgTasks" :key="msgTaskOption.id">{{msgTaskOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.pdfSign.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./pdf-sign-update.component.ts">
</script>
