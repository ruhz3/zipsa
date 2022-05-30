<template>
  <div>
    <b-navbar toggleable="lg" type="light" variant="faded" class="px-5">
      <b-navbar-brand href="#">
        <router-link to="/">
          <img
            src="@/assets/new_logo3.png"
            class="d-inline-block mt-1 p-3"
            width="200px"
            alt="happyhouse"
          />
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" class="ml-5" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#" v-animate-css.hover="'fadeIn'" class="ml-3">
            <router-link :to="{ name: 'search' }" class="link"> 매매검색 </router-link>
          </b-nav-item>
          <!--
          <b-nav-item href="#" v-animate-css.hover="'fadeIn'" class="ml-3"
            ><router-link :to="{ name: 'community' }" class="link">
              커뮤니티</router-link
            ></b-nav-item
          >
          -->
          <b-nav-item href="#" v-animate-css.hover="'fadeIn'" class="ml-3"
            ><router-link :to="{ name: 'support' }" class="link">고객센터</router-link></b-nav-item
          >
          <b-nav-item href="#" v-animate-css.hover="'fadeIn'" class="ml-3"
            ><router-link :to="{ name: 'bookmark' }" class="link"
              >나의 관심단지</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto" v-if="userInfo">
          <b-nav-item-dropdown right>
            <template #button-content> {{ userInfo.userName }}님 환영합니다. </template>
            <b-dropdown-item href="#"
              ><img src="https://picsum.photos/1024/480/?image=932" style="height: 100px" />
              <hr />
            </b-dropdown-item>
            <b-dropdown-item href="#">
              <b-nav-item class="link align-self-center" @click.prevent="onClickLogout"
                >로그아웃</b-nav-item
              ></b-dropdown-item
            >
            <b-dropdown-item href="#"
              ><router-link :to="{ name: 'bookmark' }" class="link"
                >나의 관심단지</router-link
              ></b-dropdown-item
            >
            <b-dropdown-item href="#"
              ><router-link :to="{ name: 'signUp' }" class="link"
                >나의 문의내역</router-link
              ></b-dropdown-item
            ><b-dropdown-item href="#"
              ><router-link :to="{ name: 'modify' }" class="link"
                >개인정보 수정</router-link
              ></b-dropdown-item
            >
          </b-nav-item-dropdown>
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto" v-else>
          <b-nav-item-dropdown right>
            <template #button-content>
              <b-icon icon="people" font-scale="2"></b-icon>
            </template>
            <b-dropdown-item href="#"
              ><img src="https://picsum.photos/1024/480/?image=932" style="height: 100px" />
              <hr />
            </b-dropdown-item>
            <b-dropdown-item href="#"
              ><router-link :to="{ name: 'signIn' }" class="link"
                >로그인</router-link
              ></b-dropdown-item
            >
            <b-dropdown-item href="#"
              ><router-link :to="{ name: 'signUp' }" class="link">회원가입</router-link>
            </b-dropdown-item>
            <b-dropdown-item href="#"
              ><router-link :to="{ name: 'bookmark' }" class="link"
                >나의 관심단지</router-link
              ></b-dropdown-item
            >
            <b-dropdown-item href="#"
              ><router-link :to="{ name: 'home' }" class="link"
                >나의 문의내역</router-link
              ></b-dropdown-item
            ><b-dropdown-item href="#"
              ><router-link :to="{ name: 'modify' }" class="link"
                >개인정보 수정</router-link
              ></b-dropdown-item
            >
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
// import ms from "@/store/modules/memberStore";

const memberStore = "memberStore";

export default {
  name: "HeaderNaviBar",
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
  },
};
</script>

<style>
.underline-orange {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(231, 149, 27, 0.3) 30%);
}
</style>
