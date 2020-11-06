<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="msgTask">
                <h2 class="jh-entity-heading"><span v-text="$t('esignApp.msgTask.detail.title')">MsgTask</span> {{msgTask.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('esignApp.msgTask.title')">Title</span>
                    </dt>
                    <dd>
                        <span>{{msgTask.title}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.msgTask.deptIdList')">Dept Id List</span>
                    </dt>
                    <dd>
                        <span>{{msgTask.deptIdList}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.msgTask.useridList')">Userid List</span>
                    </dt>
                    <dd>
                        <span>{{msgTask.useridList}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.msgTask.toAllUser')">To All User</span>
                    </dt>
                    <dd>
                        <span>{{msgTask.toAllUser}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.msgTask.msg')">Msg</span>
                    </dt>
                    <dd>
                        <span>{{msgTask.msg}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.msgTask.executeTime')">Execute Time</span>
                    </dt>
                    <dd>
                        <span v-if="msgTask.executeTime">{{$d(Date.parse(msgTask.executeTime), 'long') }}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.msgTask.agentId')">Agent Id</span>
                    </dt>
                    <dd>
                        <span>{{msgTask.agentId}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.msgTask.type')">Type</span>
                    </dt>
                    <dd>
                        <span v-text="$t('esignApp.DdMessageType.' + msgTask.type)">{{msgTask.type}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.msgTask.status')">Status</span>
                    </dt>
                    <dd>
                        <span v-text="$t('esignApp.MessageStatus.' + msgTask.status)">{{msgTask.status}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="msgTask.id" :to="{name: 'MsgTaskEdit', params: {msgTaskId: msgTask.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./msg-task-details.component.ts">
</script>
