<template>
  <div id="app">
    <navi-bar />
    <router-view />
    <footer-bar />
  </div>
</template>

<script>
import NaviBar from "@/components/layout/HeaderNaviBar.vue";
import FooterBar from "@/components/layout/FooterBar.vue";
import VAnimateCss from "v-animate-css";
import Vue from "vue";
Vue.use(VAnimateCss);

export default {
  name: "App",
  components: {
    NaviBar,
    FooterBar,
  },
};
</script>

<style>
@import url("https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css");

a {
  text-decoration: none;
}

a:hover {
  text-decoration: none;
  font-weight: bold;
  color: chartreuse;
}
.item-link {
  color: #191919;
  font-size: 18px;
}
.link {
  color: black;
  font-size: 20px;
}
.link:hover {
  text-decoration: none;
  color: #f6e45e;
}

* {
  font-family: Pretendard, -apple-system, BlinkMacSystemFont, system-ui, Roboto, "Helvetica Neue",
    "Segoe UI", "Apple SD Gothic Neo", "Noto Sans KR", "Malgun Gothic", sans-serif;
}

.underline-ultimategray {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 85%, rgba(153, 154, 156, 1) 15%);
}
.underline-illuminating {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(246, 228, 94, 1) 30%);
}
</style>
