<template>
  <div>
    <KakaoMap></KakaoMap>
  </div>
</template>
<script>
import KakaoMap from "@/components/search/KakaoMap.vue";

export default {
  name: "SearchView",
  components: {
    KakaoMap,
  },
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(231, 149, 27, 0.3) 30%);
}
</style>
