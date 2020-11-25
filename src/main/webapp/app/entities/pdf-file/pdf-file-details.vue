<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="pdfFile">
                <h2 class="jh-entity-heading"><span v-text="$t('esignApp.pdfFile.detail.title')">PdfFile</span> {{pdfFile.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('esignApp.pdfFile.name')">Name</span>
                    </dt>
                    <dd>
                        <span>{{pdfFile.name}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.pdfFile.mediaType')">Media Type</span>
                    </dt>
                    <dd>
                        <span>{{pdfFile.mediaType}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.pdfFile.objName')">Obj Name</span>
                    </dt>
                    <dd>
                        <span>{{pdfFile.objName}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.pdfFile.fileUrl')">File Url</span>
                    </dt>
                    <dd>
                        <span>{{pdfFile.fileUrl}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.pdfFile.key')">Key</span>
                    </dt>
                    <dd>
                        <span>{{pdfFile.key}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.pdfFile.creator')">Creator</span>
                    </dt>
                    <dd>
                        <div v-if="pdfFile.creator">
                            <router-link :to="{name: 'DdUserView', params: {ddUserId: pdfFile.creator.id}}">{{pdfFile.creator.id}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="pdfFile.id" :to="{name: 'PdfFileEdit', params: {pdfFileId: pdfFile.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./pdf-file-details.component.ts">
</script>
