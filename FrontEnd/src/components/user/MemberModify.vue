<template>
  <b-row class="mx-auto" style="width: 800px; background-color: #f6e45e; border-radius: 20px">
    <b-col cols="3"></b-col>
    <b-col cols="9" style="background-color: white; padding: 80px">
      <router-link to="/">
        <img src="@/assets/hh_logo.png" class="mx-auto d-block" width="150px" alt="happyhouse" />
      </router-link>
      <div class="container w-75 mt-5 mx-auto" style="min-width: 400px; max-width: 500px">
        <b-form>
          <div class="d-grid gap-2 mb-4">
            <div class="row mb-2">
              <div class="h5 col-3 text-end">
                <label for="id"> 아이디 </label>
              </div>
              <div class="col-9">
                <b-form-input
                  id="id"
                  v-model="user.userId"
                  type="text"
                  data-toggle="tooltip"
                  data-placement="left"
                  title="Tooltip on left"
                  disabled
                  required
                  autofocus
                />
              </div>
            </div>
            <div class="row mb-2">
              <div class="h5 col-3 text-end">
                <label for="pw">비밀번호</label>
              </div>
              <div class="col-9">
                <b-form-input id="pw" v-model="user.userPassword" type="password" />
              </div>
            </div>
          </div>
          <div class="d-grid gap-2 mb-4">
            <div class="row mb-2">
              <div class="h5 col-3 text-end">
                <label for="name">이름</label>
              </div>
              <div class="col-9">
                <b-form-input id="name" v-model="user.userName" type="text" />
              </div>
            </div>
            <div class="row mb-2">
              <div class="h5 col-3 text-end">
                <label for="phone">전화번호</label>
              </div>
              <div class="col-9">
                <b-form-input id="phone" v-model="user.userPhone" type="text" />
              </div>
            </div>
            <div class="row mb-2">
              <div class="h5 col-3 text-end">
                <label for="phone">이메일</label>
              </div>
              <div class="col-9">
                <b-form-input id="email" v-model="user.userEmail" type="text" />
              </div>
            </div>
            <div class="row">
              <div class="h5 col-3 text-end">
                <label for="addr1">주소</label>
              </div>
              <div class="col-6">
                <b-form-input
                  id="addr1"
                  v-model="user.userAddress"
                  type="text"
                  class="form-control"
                  readonly
                />
              </div>
              <div class="col-3">
                <button
                  id="addr_search_btn"
                  class="btn btn-secondary y w-100"
                  type="button"
                  @click="addressSearch"
                >
                  검색
                </button>
              </div>
            </div>
          </div>
          <div class="d-grid gap-2 mb-4">
            <div class="row mb-2">
              <div class="col-12">
                <button
                  type="button"
                  id="join_btn"
                  class="btn btn-dark w-100"
                  style="background-color: #f6e45e; color: black; border: 0px"
                  @click="confirm"
                >
                  수정하기
                </button>
              </div>
            </div>
            <div class="row">
              <div class="col-12">
                <button type="button" id="join_btn" class="btn btn-dark w-100" @click="movePage">
                  뒤로가기
                </button>
              </div>
            </div>
          </div>
        </b-form>
      </div>
    </b-col>
  </b-row>
</template>

<script>
import { modifyMember } from "../../api/member";
import { mapState } from "vuex";
// import ms from "@/store/modules/memberStore";

const memberStore = "memberStore";

export default {
  name: "MemberModify",
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  data() {
    return {
      user: {
        userId: null,
        userPassword: null,
        userName: null,
        userAddress: null,
        userPhone: null,
        userEmail: null,
        isAdmin: 0,
      },
    };
  },
  created() {
    this.user = this.userInfo;
  },
  mounted() {
    const script = document.createElement("script");
    /* global daum */
    script.src = "//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js";
    document.head.appendChild(script);
  },
  methods: {
    addressSearch() {
      new daum.Postcode({
        oncomplete: function (data) {
          document.getElementById("addr1").value = data.address;
        },
      }).open();
    },
    async confirm() {
      modifyMember(
        this.user,
        (response) => {
          console.log(response.data);
          this.movePage();
        },
        (error) => {
          console.log(error);
        },
      );
    },
    movePage() {
      this.$router.push({ name: "home" });
    },
  },
};
</script>

<style></style>
