<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="eSignConfig">
                <h2 class="jh-entity-heading"><span v-text="$t('esignApp.eSignConfig.detail.title')">ESignConfig</span> {{eSignConfig.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('esignApp.eSignConfig.name')">Name</span>
                    </dt>
                    <dd>
                        <span>{{eSignConfig.name}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.eSignConfig.time')">Time</span>
                    </dt>
                    <dd>
                        <span v-if="eSignConfig.time">{{$d(Date.parse(eSignConfig.time), 'long') }}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.eSignConfig.channelNo')">Channel No</span>
                    </dt>
                    <dd>
                        <span>{{eSignConfig.channelNo}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.eSignConfig.accessId')">Access Id</span>
                    </dt>
                    <dd>
                        <span>{{eSignConfig.accessId}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.eSignConfig.accessKeySecret')">Access Key Secret</span>
                    </dt>
                    <dd>
                        <span>{{eSignConfig.accessKeySecret}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="eSignConfig.id" :to="{name: 'ESignConfigEdit', params: {eSignConfigId: eSignConfig.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./e-sign-config-details.component.ts">
</script>
