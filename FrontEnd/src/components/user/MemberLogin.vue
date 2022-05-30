<template>
  <b-row class="mx-auto" style="width: 800px; background-color: #f6e45e; border-radius: 20px">
    <b-col cols="3"></b-col>
    <b-col cols="9" style="background-color: white; padding: 80px">
      <div class="px-5">
        <router-link to="/">
          <img src="@/assets/hh_logo.png" class="mx-auto d-block" width="150px" alt="happyhouse" />
        </router-link>
        <b-form class="text-left mt-5">
          <b-alert show variant="danger" v-if="isLoginError"
            >아이디 또는 비밀번호를 확인하세요.</b-alert
          >
          <h5>아이디</h5>
          <b-form-group>
            <b-form-input
              id="userid"
              v-model="user.userId"
              required
              placeholder="아이디를 입력하세요."
              size="lg"
              @keyup.enter="confirm"
            ></b-form-input>
          </b-form-group>
          <h5>비밀번호</h5>
          <b-form-group>
            <b-form-input
              type="password"
              id="userpwd"
              v-model="user.userPassword"
              required
              placeholder="비밀번호를 입력하세요."
              size="lg"
              @keyup.enter="confirm"
            ></b-form-input>
          </b-form-group>
          <!--로그인 버튼-->
          <b-button
            type="button"
            class="mt-2"
            style="width: 100%; background-color: #f6e45e; color: black; border: 0px"
            @click="confirm"
            >로그인</b-button
          >
          <b-button type="button" class="mt-2" style="width: 100%" @click="movePage()"
            >회원가입</b-button
          >
        </b-form>
        <div class="mt-2 text-center"><a>아이디 찾기</a> | <a>비밀번호 찾기</a></div>
      </div>
    </b-col>
  </b-row>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberLogin",
  data() {
    return {
      user: {
        userId: null,
        userPassword: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "home" });
      }
      console.log("4");
    },
    movePage() {
      this.$router.push({ name: "signUp" });
    },
  },
};
</script>

<style></style>
