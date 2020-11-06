<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="esignApp.eSignConfig.home.createOrEditLabel" v-text="$t('esignApp.eSignConfig.home.createOrEditLabel')">Create or edit a ESignConfig</h2>
                <div>
                    <div class="form-group" v-if="eSignConfig.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="eSignConfig.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.eSignConfig.name')" for="e-sign-config-name">Name</label>
                        <input type="text" class="form-control" name="name" id="e-sign-config-name"
                            :class="{'valid': !$v.eSignConfig.name.$invalid, 'invalid': $v.eSignConfig.name.$invalid }" v-model="$v.eSignConfig.name.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.eSignConfig.time')" for="e-sign-config-time">Time</label>
                        <div class="d-flex">
                            <input id="e-sign-config-time" type="datetime-local" class="form-control" name="time" :class="{'valid': !$v.eSignConfig.time.$invalid, 'invalid': $v.eSignConfig.time.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.eSignConfig.time.$model)"
                            @change="updateInstantField('time', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.eSignConfig.channelNo')" for="e-sign-config-channelNo">Channel No</label>
                        <input type="text" class="form-control" name="channelNo" id="e-sign-config-channelNo"
                            :class="{'valid': !$v.eSignConfig.channelNo.$invalid, 'invalid': $v.eSignConfig.channelNo.$invalid }" v-model="$v.eSignConfig.channelNo.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.eSignConfig.accessId')" for="e-sign-config-accessId">Access Id</label>
                        <input type="text" class="form-control" name="accessId" id="e-sign-config-accessId"
                            :class="{'valid': !$v.eSignConfig.accessId.$invalid, 'invalid': $v.eSignConfig.accessId.$invalid }" v-model="$v.eSignConfig.accessId.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.eSignConfig.accessKeySecret')" for="e-sign-config-accessKeySecret">Access Key Secret</label>
                        <input type="text" class="form-control" name="accessKeySecret" id="e-sign-config-accessKeySecret"
                            :class="{'valid': !$v.eSignConfig.accessKeySecret.$invalid, 'invalid': $v.eSignConfig.accessKeySecret.$invalid }" v-model="$v.eSignConfig.accessKeySecret.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.eSignConfig.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./e-sign-config-update.component.ts">
</script>
