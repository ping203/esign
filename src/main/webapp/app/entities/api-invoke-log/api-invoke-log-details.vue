<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="apiInvokeLog">
                <h2 class="jh-entity-heading"><span v-text="$t('esignApp.apiInvokeLog.detail.title')">ApiInvokeLog</span> {{apiInvokeLog.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('esignApp.apiInvokeLog.login')">Login</span>
                    </dt>
                    <dd>
                        <span>{{apiInvokeLog.login}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.apiInvokeLog.apiName')">Api Name</span>
                    </dt>
                    <dd>
                        <span>{{apiInvokeLog.apiName}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.apiInvokeLog.httpStatusCode')">Http Status Code</span>
                    </dt>
                    <dd>
                        <span>{{apiInvokeLog.httpStatusCode}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.apiInvokeLog.time')">Time</span>
                    </dt>
                    <dd>
                        <span v-if="apiInvokeLog.time">{{$d(Date.parse(apiInvokeLog.time), 'long') }}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.apiInvokeLog.reqeustData')">Reqeust Data</span>
                    </dt>
                    <dd>
                        <span>{{apiInvokeLog.reqeustData}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.apiInvokeLog.responseData')">Response Data</span>
                    </dt>
                    <dd>
                        <span>{{apiInvokeLog.responseData}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="apiInvokeLog.id" :to="{name: 'ApiInvokeLogEdit', params: {apiInvokeLogId: apiInvokeLog.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./api-invoke-log-details.component.ts">
</script>
