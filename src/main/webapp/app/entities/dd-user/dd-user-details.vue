<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="ddUser">
                <h2 class="jh-entity-heading"><span v-text="$t('esignApp.ddUser.detail.title')">DdUser</span> {{ddUser.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('esignApp.ddUser.unionid')">Unionid</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.unionid}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.ddUser.remark')">Remark</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.remark}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.ddUser.userid')">Userid</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.userid}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.ddUser.isLeaderInDepts')">Is Leader In Depts</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.isLeaderInDepts}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.ddUser.isBoss')">Is Boss</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.isBoss}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.ddUser.hiredDate')">Hired Date</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.hiredDate}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.ddUser.isSenior')">Is Senior</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.isSenior}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.ddUser.tel')">Tel</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.tel}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.ddUser.department')">Department</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.department}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.ddUser.workPlace')">Work Place</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.workPlace}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.ddUser.orderInDepts')">Order In Depts</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.orderInDepts}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.ddUser.mobile')">Mobile</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.mobile}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.ddUser.errmsg')">Errmsg</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.errmsg}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.ddUser.active')">Active</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.active}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.ddUser.avatar')">Avatar</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.avatar}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.ddUser.isAdmin')">Is Admin</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.isAdmin}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.ddUser.isHide')">Is Hide</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.isHide}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.ddUser.jobnumber')">Jobnumber</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.jobnumber}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.ddUser.name')">Name</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.name}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.ddUser.extattr')">Extattr</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.extattr}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.ddUser.stateCode')">State Code</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.stateCode}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.ddUser.position')">Position</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.position}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.ddUser.roles')">Roles</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.roles}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.ddUser.accountId')">Account Id</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.accountId}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('esignApp.ddUser.idNumber')">Id Number</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.idNumber}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="ddUser.id" :to="{name: 'DdUserEdit', params: {ddUserId: ddUser.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./dd-user-details.component.ts">
</script>
