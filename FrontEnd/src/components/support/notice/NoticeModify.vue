<template>
  <div class="container-fluid" style="padding: 30px 400px">
    <div class="display-4 underline-illuminating mb-3" style="position: relative; bottom: 60px">
      커뮤니티
    </div>
    <h1 class="mb-2">글수정</h1>
    <hr />
    <notice-input-item type="modify" />
  </div>
</template>

<script>
import NoticeInputItem from "@/components/support/notice/item/NoticeInputItem.vue";

export default {
  name: "NoticeModify",
  components: {
    NoticeInputItem,
  },
};
</script>

<style></style>
