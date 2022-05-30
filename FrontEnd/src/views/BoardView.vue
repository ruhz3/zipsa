<template>
  <div class="container-fluid-xl">
    <header-banner></header-banner>
    <router-view></router-view>
  </div>
</template>
<script>
import HeaderBanner from "@/components/layout/HeaderBanner.vue";

export default {
  name: "BoardView",
  components: {
    HeaderBanner,
  },
  data() {
    return {
      // Note 'isActive' is left out and will not appear in the rendered table
    };
  },
};
</script>
<style scoped></style>
