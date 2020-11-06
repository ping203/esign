<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="sealData">
                <h2 class="jh-entity-heading"><span v-text="$t('esignApp.sealData.detail.title')">SealData</span> {{sealData.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('esignApp.sealData.base64Str')">Base 64 Str</span>
                    </dt>
                    <dd>
                        <div v-if="sealData.base64Str">
                            <a v-on:click="openFile(sealData.base64StrContentType, sealData.base64Str)">
                                <img v-bind:src="'data:' + sealData.base64StrContentType + ';base64,' + sealData.base64Str" style="max-width: 100%;" alt="sealData image"/>
                            </a>
                            {{sealData.base64StrContentType}}, {{byteSize(sealData.base64Str)}}
                        </div>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.sealData.pdfSign')">Pdf Sign</span>
                    </dt>
                    <dd>
                        <div v-if="sealData.pdfSign">
                            <router-link :to="{name: 'PdfSignView', params: {pdfSignId: sealData.pdfSign.id}}">{{sealData.pdfSign.id}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="sealData.id" :to="{name: 'SealDataEdit', params: {sealDataId: sealData.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./seal-data-details.component.ts">
</script>
