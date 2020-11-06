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
                        <label class="form-control-label" v-text="$t('esignApp.msgSubTask.rsp')" for="msg-sub-task-rsp">Rsp</label>
                        <input type="text" class="form-control" name="rsp" id="msg-sub-task-rsp"
                            :class="{'valid': !$v.msgSubTask.rsp.$invalid, 'invalid': $v.msgSubTask.rsp.$invalid }" v-model="$v.msgSubTask.rsp.$model" />
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
