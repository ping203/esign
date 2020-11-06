<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="esignApp.msgSubTask.home.createOrEditLabel" v-text="$t('esignApp.msgSubTask.home.createOrEditLabel')">Create or edit a MsgSubTask</h2>
                <div>
                    <div class="form-group" v-if="msgSubTask.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="msgSubTask.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgSubTask.useridList')" for="msg-sub-task-useridList">Userid List</label>
                        <input type="text" class="form-control" name="useridList" id="msg-sub-task-useridList"
                            :class="{'valid': !$v.msgSubTask.useridList.$invalid, 'invalid': $v.msgSubTask.useridList.$invalid }" v-model="$v.msgSubTask.useridList.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgSubTask.taskId')" for="msg-sub-task-taskId">Task Id</label>
                        <input type="number" class="form-control" name="taskId" id="msg-sub-task-taskId"
                            :class="{'valid': !$v.msgSubTask.taskId.$invalid, 'invalid': $v.msgSubTask.taskId.$invalid }" v-model.number="$v.msgSubTask.taskId.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgSubTask.time')" for="msg-sub-task-time">Time</label>
                        <div class="d-flex">
                            <input id="msg-sub-task-time" type="datetime-local" class="form-control" name="time" :class="{'valid': !$v.msgSubTask.time.$invalid, 'invalid': $v.msgSubTask.time.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.msgSubTask.time.$model)"
                            @change="updateInstantField('time', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgSubTask.rspMsg')" for="msg-sub-task-rspMsg">Rsp Msg</label>
                        <input type="text" class="form-control" name="rspMsg" id="msg-sub-task-rspMsg"
                            :class="{'valid': !$v.msgSubTask.rspMsg.$invalid, 'invalid': $v.msgSubTask.rspMsg.$invalid }" v-model="$v.msgSubTask.rspMsg.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgSubTask.status')" for="msg-sub-task-status">Status</label>
                        <input type="number" class="form-control" name="status" id="msg-sub-task-status"
                            :class="{'valid': !$v.msgSubTask.status.$invalid, 'invalid': $v.msgSubTask.status.$invalid }" v-model.number="$v.msgSubTask.status.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgSubTask.progressInPercent')" for="msg-sub-task-progressInPercent">Progress In Percent</label>
                        <input type="number" class="form-control" name="progressInPercent" id="msg-sub-task-progressInPercent"
                            :class="{'valid': !$v.msgSubTask.progressInPercent.$invalid, 'invalid': $v.msgSubTask.progressInPercent.$invalid }" v-model.number="$v.msgSubTask.progressInPercent.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgSubTask.subTaskStatus')" for="msg-sub-task-subTaskStatus">Sub Task Status</label>
                        <select class="form-control" name="subTaskStatus" :class="{'valid': !$v.msgSubTask.subTaskStatus.$invalid, 'invalid': $v.msgSubTask.subTaskStatus.$invalid }" v-model="$v.msgSubTask.subTaskStatus.$model" id="msg-sub-task-subTaskStatus" >
                            <option value="SentSuccessfully" v-bind:label="$t('esignApp.MessageStatus.SentSuccessfully')">SentSuccessfully</option>
                            <option value="Sending" v-bind:label="$t('esignApp.MessageStatus.Sending')">Sending</option>
                            <option value="NotSentYet" v-bind:label="$t('esignApp.MessageStatus.NotSentYet')">NotSentYet</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgSubTask.invalidUserIdList')" for="msg-sub-task-invalidUserIdList">Invalid User Id List</label>
                        <input type="text" class="form-control" name="invalidUserIdList" id="msg-sub-task-invalidUserIdList"
                            :class="{'valid': !$v.msgSubTask.invalidUserIdList.$invalid, 'invalid': $v.msgSubTask.invalidUserIdList.$invalid }" v-model="$v.msgSubTask.invalidUserIdList.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgSubTask.forbiddenUserIdList')" for="msg-sub-task-forbiddenUserIdList">Forbidden User Id List</label>
                        <input type="text" class="form-control" name="forbiddenUserIdList" id="msg-sub-task-forbiddenUserIdList"
                            :class="{'valid': !$v.msgSubTask.forbiddenUserIdList.$invalid, 'invalid': $v.msgSubTask.forbiddenUserIdList.$invalid }" v-model="$v.msgSubTask.forbiddenUserIdList.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgSubTask.failedUserIdList')" for="msg-sub-task-failedUserIdList">Failed User Id List</label>
                        <input type="text" class="form-control" name="failedUserIdList" id="msg-sub-task-failedUserIdList"
                            :class="{'valid': !$v.msgSubTask.failedUserIdList.$invalid, 'invalid': $v.msgSubTask.failedUserIdList.$invalid }" v-model="$v.msgSubTask.failedUserIdList.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgSubTask.readUserIdList')" for="msg-sub-task-readUserIdList">Read User Id List</label>
                        <input type="text" class="form-control" name="readUserIdList" id="msg-sub-task-readUserIdList"
                            :class="{'valid': !$v.msgSubTask.readUserIdList.$invalid, 'invalid': $v.msgSubTask.readUserIdList.$invalid }" v-model="$v.msgSubTask.readUserIdList.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgSubTask.unreadUserIdList')" for="msg-sub-task-unreadUserIdList">Unread User Id List</label>
                        <input type="text" class="form-control" name="unreadUserIdList" id="msg-sub-task-unreadUserIdList"
                            :class="{'valid': !$v.msgSubTask.unreadUserIdList.$invalid, 'invalid': $v.msgSubTask.unreadUserIdList.$invalid }" v-model="$v.msgSubTask.unreadUserIdList.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgSubTask.invalidDeptIdList')" for="msg-sub-task-invalidDeptIdList">Invalid Dept Id List</label>
                        <input type="text" class="form-control" name="invalidDeptIdList" id="msg-sub-task-invalidDeptIdList"
                            :class="{'valid': !$v.msgSubTask.invalidDeptIdList.$invalid, 'invalid': $v.msgSubTask.invalidDeptIdList.$invalid }" v-model="$v.msgSubTask.invalidDeptIdList.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgSubTask.withdraw')" for="msg-sub-task-withdraw">Withdraw</label>
                        <input type="checkbox" class="form-check" name="withdraw" id="msg-sub-task-withdraw"
                            :class="{'valid': !$v.msgSubTask.withdraw.$invalid, 'invalid': $v.msgSubTask.withdraw.$invalid }" v-model="$v.msgSubTask.withdraw.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.msgSubTask.msgTask')" for="msg-sub-task-msgTask">Msg Task</label>
                        <select class="form-control" id="msg-sub-task-msgTask" name="msgTask" v-model="msgSubTask.msgTask">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="msgSubTask.msgTask && msgTaskOption.id === msgSubTask.msgTask.id ? msgSubTask.msgTask : msgTaskOption" v-for="msgTaskOption in msgTasks" :key="msgTaskOption.id">{{msgTaskOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.msgSubTask.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./msg-sub-task-update.component.ts">
</script>
