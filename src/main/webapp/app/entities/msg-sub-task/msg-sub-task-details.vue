<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="msgSubTask">
                <h2 class="jh-entity-heading"><span v-text="$t('esignApp.msgSubTask.detail.title')">MsgSubTask</span> {{msgSubTask.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('esignApp.msgSubTask.useridList')">Userid List</span>
                    </dt>
                    <dd>
                        <span>{{msgSubTask.useridList}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.msgSubTask.taskId')">Task Id</span>
                    </dt>
                    <dd>
                        <span>{{msgSubTask.taskId}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.msgSubTask.time')">Time</span>
                    </dt>
                    <dd>
                        <span v-if="msgSubTask.time">{{$d(Date.parse(msgSubTask.time), 'long') }}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.msgSubTask.rsp')">Rsp</span>
                    </dt>
                    <dd>
                        <span>{{msgSubTask.rsp}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.msgSubTask.msgTask')">Msg Task</span>
                    </dt>
                    <dd>
                        <div v-if="msgSubTask.msgTask">
                            <router-link :to="{name: 'MsgTaskView', params: {msgTaskId: msgSubTask.msgTask.id}}">{{msgSubTask.msgTask.id}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="msgSubTask.id" :to="{name: 'MsgSubTaskEdit', params: {msgSubTaskId: msgSubTask.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./msg-sub-task-details.component.ts">
</script>
