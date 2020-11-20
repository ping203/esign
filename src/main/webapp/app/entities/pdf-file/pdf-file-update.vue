<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="esignApp.pdfFile.home.createOrEditLabel" v-text="$t('esignApp.pdfFile.home.createOrEditLabel')">Create or edit a PdfFile</h2>
                <div>
                    <div class="form-group" v-if="pdfFile.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="pdfFile.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfFile.name')" for="pdf-file-name">Name</label>
                        <input type="text" class="form-control" name="name" id="pdf-file-name"
                            :class="{'valid': !$v.pdfFile.name.$invalid, 'invalid': $v.pdfFile.name.$invalid }" v-model="$v.pdfFile.name.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfFile.mediaType')" for="pdf-file-mediaType">Media Type</label>
                        <input type="text" class="form-control" name="mediaType" id="pdf-file-mediaType"
                            :class="{'valid': !$v.pdfFile.mediaType.$invalid, 'invalid': $v.pdfFile.mediaType.$invalid }" v-model="$v.pdfFile.mediaType.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfFile.objName')" for="pdf-file-objName">Obj Name</label>
                        <input type="text" class="form-control" name="objName" id="pdf-file-objName"
                            :class="{'valid': !$v.pdfFile.objName.$invalid, 'invalid': $v.pdfFile.objName.$invalid }" v-model="$v.pdfFile.objName.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfFile.fileUrl')" for="pdf-file-fileUrl">File Url</label>
                        <input type="text" class="form-control" name="fileUrl" id="pdf-file-fileUrl"
                            :class="{'valid': !$v.pdfFile.fileUrl.$invalid, 'invalid': $v.pdfFile.fileUrl.$invalid }" v-model="$v.pdfFile.fileUrl.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('esignApp.pdfFile.creator')" for="pdf-file-creator">Creator</label>
                        <select class="form-control" id="pdf-file-creator" name="creator" v-model="pdfFile.creator">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="pdfFile.creator && ddUserOption.id === pdfFile.creator.id ? pdfFile.creator : ddUserOption" v-for="ddUserOption in ddUsers" :key="ddUserOption.id">{{ddUserOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.pdfFile.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./pdf-file-update.component.ts">
</script>
