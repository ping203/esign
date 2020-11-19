<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="esignApp.msgTask.home.createOrEditLabel" v-text="$t('esignApp.msgTask.home.createOrEditLabel')">Create or edit a MsgTask</h2>
                <div>
                    <div class="form-group" v-if="msgTask.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="msgTask.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgTask.title')" for="msg-task-title">Title</label>
                        <input type="text" class="form-control" name="title" id="msg-task-title"
                            :class="{'valid': !$v.msgTask.title.$invalid, 'invalid': $v.msgTask.title.$invalid }" v-model="$v.msgTask.title.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgTask.deptIdList')" for="msg-task-deptIdList">Dept Id List</label>
                        <input type="text" class="form-control" name="deptIdList" id="msg-task-deptIdList"
                            :class="{'valid': !$v.msgTask.deptIdList.$invalid, 'invalid': $v.msgTask.deptIdList.$invalid }" v-model="$v.msgTask.deptIdList.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgTask.useridList')" for="msg-task-useridList">Userid List</label>
                        <input type="text" class="form-control" name="useridList" id="msg-task-useridList"
                            :class="{'valid': !$v.msgTask.useridList.$invalid, 'invalid': $v.msgTask.useridList.$invalid }" v-model="$v.msgTask.useridList.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgTask.toAllUser')" for="msg-task-toAllUser">To All User</label>
                        <input type="checkbox" class="form-check" name="toAllUser" id="msg-task-toAllUser"
                            :class="{'valid': !$v.msgTask.toAllUser.$invalid, 'invalid': $v.msgTask.toAllUser.$invalid }" v-model="$v.msgTask.toAllUser.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgTask.markdown')" for="msg-task-markdown">Markdown</label>
                        <input type="text" class="form-control" name="markdown" id="msg-task-markdown"
                            :class="{'valid': !$v.msgTask.markdown.$invalid, 'invalid': $v.msgTask.markdown.$invalid }" v-model="$v.msgTask.markdown.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgTask.singleTitle')" for="msg-task-singleTitle">Single Title</label>
                        <input type="text" class="form-control" name="singleTitle" id="msg-task-singleTitle"
                            :class="{'valid': !$v.msgTask.singleTitle.$invalid, 'invalid': $v.msgTask.singleTitle.$invalid }" v-model="$v.msgTask.singleTitle.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgTask.singleUrl')" for="msg-task-singleUrl">Single Url</label>
                        <input type="text" class="form-control" name="singleUrl" id="msg-task-singleUrl"
                            :class="{'valid': !$v.msgTask.singleUrl.$invalid, 'invalid': $v.msgTask.singleUrl.$invalid }" v-model="$v.msgTask.singleUrl.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgTask.pdfUrl')" for="msg-task-pdfUrl">Pdf Url</label>
                        <input type="text" class="form-control" name="pdfUrl" id="msg-task-pdfUrl"
                            :class="{'valid': !$v.msgTask.pdfUrl.$invalid, 'invalid': $v.msgTask.pdfUrl.$invalid }" v-model="$v.msgTask.pdfUrl.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgTask.coverUrl')" for="msg-task-coverUrl">Cover Url</label>
                        <input type="text" class="form-control" name="coverUrl" id="msg-task-coverUrl"
                            :class="{'valid': !$v.msgTask.coverUrl.$invalid, 'invalid': $v.msgTask.coverUrl.$invalid }" v-model="$v.msgTask.coverUrl.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgTask.numberOfShards')" for="msg-task-numberOfShards">Number Of Shards</label>
                        <input type="number" class="form-control" name="numberOfShards" id="msg-task-numberOfShards"
                            :class="{'valid': !$v.msgTask.numberOfShards.$invalid, 'invalid': $v.msgTask.numberOfShards.$invalid }" v-model.number="$v.msgTask.numberOfShards.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgTask.completeSharding')" for="msg-task-completeSharding">Complete Sharding</label>
                        <input type="checkbox" class="form-check" name="completeSharding" id="msg-task-completeSharding"
                            :class="{'valid': !$v.msgTask.completeSharding.$invalid, 'invalid': $v.msgTask.completeSharding.$invalid }" v-model="$v.msgTask.completeSharding.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgTask.msg')" for="msg-task-msg">Msg</label>
                        <input type="text" class="form-control" name="msg" id="msg-task-msg"
                            :class="{'valid': !$v.msgTask.msg.$invalid, 'invalid': $v.msgTask.msg.$invalid }" v-model="$v.msgTask.msg.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgTask.executeTime')" for="msg-task-executeTime">Execute Time</label>
                        <div class="d-flex">
                            <input id="msg-task-executeTime" type="datetime-local" class="form-control" name="executeTime" :class="{'valid': !$v.msgTask.executeTime.$invalid, 'invalid': $v.msgTask.executeTime.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.msgTask.executeTime.$model)"
                            @change="updateInstantField('executeTime', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgTask.agentId')" for="msg-task-agentId">Agent Id</label>
                        <input type="number" class="form-control" name="agentId" id="msg-task-agentId"
                            :class="{'valid': !$v.msgTask.agentId.$invalid, 'invalid': $v.msgTask.agentId.$invalid }" v-model.number="$v.msgTask.agentId.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgTask.type')" for="msg-task-type">Type</label>
                        <select class="form-control" name="type" :class="{'valid': !$v.msgTask.type.$invalid, 'invalid': $v.msgTask.type.$invalid }" v-model="$v.msgTask.type.$model" id="msg-task-type" >
                            <option value="Voice" v-bind:label="$t('esignApp.DdMessageType.Voice')">Voice</option>
                            <option value="ActionCard" v-bind:label="$t('esignApp.DdMessageType.ActionCard')">ActionCard</option>
                            <option value="Markdown" v-bind:label="$t('esignApp.DdMessageType.Markdown')">Markdown</option>
                            <option value="Oa" v-bind:label="$t('esignApp.DdMessageType.Oa')">Oa</option>
                            <option value="Link" v-bind:label="$t('esignApp.DdMessageType.Link')">Link</option>
                            <option value="File" v-bind:label="$t('esignApp.DdMessageType.File')">File</option>
                            <option value="Text" v-bind:label="$t('esignApp.DdMessageType.Text')">Text</option>
                            <option value="Image" v-bind:label="$t('esignApp.DdMessageType.Image')">Image</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgTask.status')" for="msg-task-status">Status</label>
                        <select class="form-control" name="status" :class="{'valid': !$v.msgTask.status.$invalid, 'invalid': $v.msgTask.status.$invalid }" v-model="$v.msgTask.status.$model" id="msg-task-status" >
                            <option value="SentSuccessfully" v-bind:label="$t('esignApp.MessageStatus.SentSuccessfully')">SentSuccessfully</option>
                            <option value="Sending" v-bind:label="$t('esignApp.MessageStatus.Sending')">Sending</option>
                            <option value="NotSentYet" v-bind:label="$t('esignApp.MessageStatus.NotSentYet')">NotSentYet</option>
                            <option value="Withdraw" v-bind:label="$t('esignApp.MessageStatus.Withdraw')">Withdraw</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgTask.progressInPercent')" for="msg-task-progressInPercent">Progress In Percent</label>
                        <input type="number" class="form-control" name="progressInPercent" id="msg-task-progressInPercent"
                            :class="{'valid': !$v.msgTask.progressInPercent.$invalid, 'invalid': $v.msgTask.progressInPercent.$invalid }" v-model.number="$v.msgTask.progressInPercent.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgTask.sender')" for="msg-task-sender">Sender</label>
                        <select class="form-control" id="msg-task-sender" name="sender" v-model="msgTask.sender">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="msgTask.sender && ddUserOption.id === msgTask.sender.id ? msgTask.sender : ddUserOption" v-for="ddUserOption in ddUsers" :key="ddUserOption.id">{{ddUserOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.msgTask.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./msg-task-update.component.ts">
</script>
