<template>
  <div class="container-fluid" style="padding: 0px 180px">
    <div class="display-4 underline-illuminating mb-3" style="position: relative; bottom: 140px">
      고객센터
    </div>
    <h1 class="mb-2">글쓰기</h1>
    <hr />
    <notice-input-item type="register" />
  </div>
</template>

<script>
import NoticeInputItem from "@/components/support/item/NoticeInputItem.vue";

export default {
  name: "NoticeWrite",
  components: {
    NoticeInputItem,
  },
};
</script>

<style></style>
