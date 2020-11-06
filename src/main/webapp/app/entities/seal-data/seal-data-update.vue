<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="esignApp.sealData.home.createOrEditLabel" v-text="$t('esignApp.sealData.home.createOrEditLabel')">Create or edit a SealData</h2>
                <div>
                    <div class="form-group" v-if="sealData.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="sealData.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.sealData.base64Str')" for="seal-data-base64Str">Base 64 Str</label>
                        <div>
                            <img v-bind:src="'data:' + sealData.base64StrContentType + ';base64,' + sealData.base64Str" style="max-height: 100px;" v-if="sealData.base64Str" alt="sealData image"/>
                            <div v-if="sealData.base64Str" class="form-text text-danger clearfix">
                                <span class="pull-left">{{sealData.base64StrContentType}}, {{byteSize(sealData.base64Str)}}</span>
                                <button type="button" v-on:click="clearInputImage('base64Str', 'base64StrContentType', 'file_base64Str')" class="btn btn-secondary btn-xs pull-right">
                                    <font-awesome-icon icon="times"></font-awesome-icon>
                                </button>
                            </div>
                            <input type="file" ref="file_base64Str" id="file_base64Str" v-on:change="setFileData($event, sealData, 'base64Str', true)" accept="image/*" v-text="$t('entity.action.addimage')"/>
                        </div>
                        <input type="hidden" class="form-control" name="base64Str" id="seal-data-base64Str"
                            :class="{'valid': !$v.sealData.base64Str.$invalid, 'invalid': $v.sealData.base64Str.$invalid }" v-model="$v.sealData.base64Str.$model" />
                        <input type="hidden" class="form-control" name="base64StrContentType" id="seal-data-base64StrContentType"
                            v-model="sealData.base64StrContentType" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.sealData.pdfSign')" for="seal-data-pdfSign">Pdf Sign</label>
                        <select class="form-control" id="seal-data-pdfSign" name="pdfSign" v-model="sealData.pdfSign">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="sealData.pdfSign && pdfSignOption.id === sealData.pdfSign.id ? sealData.pdfSign : pdfSignOption" v-for="pdfSignOption in pdfSigns" :key="pdfSignOption.id">{{pdfSignOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.sealData.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./seal-data-update.component.ts">
</script>
