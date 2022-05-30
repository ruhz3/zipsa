<template>
  <div class="container-fluid-xl">
    <header-banner></header-banner>
    <router-view></router-view>
  </div>
</template>

<script>
import HeaderBanner from "@/components/layout/HeaderBanner.vue";
export default {
  name: "SupportView",
  components: {
    HeaderBanner,
  },
};
</script>

<style scoped>
.underline-green {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(56, 233, 40, 0.3) 30%);
}
</style>
